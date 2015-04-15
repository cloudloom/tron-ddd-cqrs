package com.tracebucket.tron.assembler;

import com.tracebucket.tron.context.BasePackageStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.beans.Introspector;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by sadath on 09-Apr-15.
 */
@Component
public class AssemblerResolver {

    @Autowired
    private BasePackageStore basePackages;

    private static final Logger log = LoggerFactory.getLogger(AssemblerResolver.class);

    @Autowired
    private ApplicationContext applicationContext;

    public <T, V extends BaseResource> EntityAssembler<T, V> resolveEntityAssembler(Class<T> entity, Class<V> resource) {
        EntityAssembler<T, V> entityAssembler = null;
        List<String> classes = resolveClasses();
        if(classes != null && classes.size() > 0) {
            for (String className : classes) {
                try {
                    Class clazz = Class.forName(className, true, Thread.currentThread().getContextClassLoader());
                    if (clazz.isAnnotationPresent(Component.class)) {
                        final ParameterizedType type = (ParameterizedType) clazz.getGenericSuperclass();
                        if (type.getActualTypeArguments()[0].getTypeName().equals(entity.getCanonicalName())
                                && type.getActualTypeArguments()[1].getTypeName().equals(resource.getCanonicalName())) {
                            log.info("Entity Assembler : " + clazz.getName());
                            try {
                                entityAssembler = (EntityAssembler) applicationContext.getBean(Introspector.decapitalize(clazz.getSimpleName()));
                            } catch (NoSuchBeanDefinitionException nsbde) {
                                if(entityAssembler == null) {
                                    try {
                                        Component component = (Component) clazz.getAnnotation(Component.class);
                                        if (component.value() != null && component.value().length() > 0) {
                                            entityAssembler = (EntityAssembler) applicationContext.getBean(component.value());
                                        }
                                    } catch (NoSuchBeanDefinitionException nsbdex) {
                                    }
                                }
                            }
                        }
                    }
                } catch (ClassNotFoundException cnfe) {
                    cnfe.printStackTrace();
                } catch (ClassCastException cce) {

                }
            }
        }
        if(entityAssembler == null) {
            try {
                entityAssembler = (EntityAssembler)applicationContext.getBean("entityAssembler");
            }catch (NoSuchBeanDefinitionException nsbdex) {
            }
        }
        return entityAssembler;
    }

    public <T extends BaseResource, V> ResourceAssembler<T, V> resolveResourceAssembler(Class<T> resource, Class<V> entity) {
        ResourceAssembler<T, V> resourceAssembler = null;
        List<String> classes = resolveClasses();
        if(classes != null && classes.size() > 0) {
            for (String className : classes) {
                try {
                    Class clazz = Class.forName(className, true, Thread.currentThread().getContextClassLoader());
                    if (clazz.isAnnotationPresent(Component.class)) {
                        final ParameterizedType type = (ParameterizedType) clazz.getGenericSuperclass();
                        if (type.getActualTypeArguments()[0].getTypeName().equals(resource.getCanonicalName())
                                && type.getActualTypeArguments()[1].getTypeName().equals(entity.getCanonicalName())) {
                            log.info("Resource Assembler : " + clazz.getName());
                            try {
                                resourceAssembler = (ResourceAssembler) applicationContext.getBean(Introspector.decapitalize(clazz.getSimpleName()));
                            } catch (NoSuchBeanDefinitionException nsbde) {
                                if(resourceAssembler == null) {
                                    try {
                                        Component component = (Component) clazz.getAnnotation(Component.class);
                                        if (component.value() != null && component.value().length() > 0) {
                                            resourceAssembler = (ResourceAssembler) applicationContext.getBean(component.value());
                                        }
                                    } catch (NoSuchBeanDefinitionException nsbdex) {
                                    }
                                }
                            }
                        }
                    }
                } catch (ClassNotFoundException cnfe) {
                    cnfe.printStackTrace();
                } catch (ClassCastException cce) {

                }
            }
        }
        if(resourceAssembler == null) {
            try {
                resourceAssembler = (ResourceAssembler) applicationContext.getBean("resourceAssembler");
            }catch (NoSuchBeanDefinitionException nsbdex) {
            }
        }
        return resourceAssembler;
    }

    private List<String> resolveClasses() {
        List<String> classes = new ArrayList<String>();
            if(basePackages != null && basePackages.getAll().size() > 0) {
                PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
                MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(resolver);
                for(String pkg : basePackages.getAll()) {
                    String basePath = ClassUtils.convertClassNameToResourcePath(pkg);
                    Resource[] resources = null;
                    try {
                        resources = resolver.getResources("classpath*:" + basePath + "/**/*.class");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if(resources != null) {
                        for (Resource resource : resources) {
                            MetadataReader reader = null;
                            try {
                                reader = readerFactory.getMetadataReader(resource);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            if(reader != null) {
                                classes.add(reader.getClassMetadata().getClassName());
                            }
                        }
                    }
                }
        }
        return classes;
    }
}