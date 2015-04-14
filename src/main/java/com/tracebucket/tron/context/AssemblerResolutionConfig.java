package com.tracebucket.tron.context;

import com.tracebucket.tron.ddd.annotation.PersistChanges;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.*;

import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.type.AnnotationMetadata;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by ffl on 14-04-2015.
 */

public class AssemblerResolutionConfig implements ImportSelector{

	private static Logger log = LoggerFactory.getLogger(AssemblerResolutionConfig.class);

	private static Set<String> assemblerPackages = new HashSet<>(0);


	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		AnnotationAttributes attributes =
				AnnotationAttributes.fromMap(
						importingClassMetadata.getAnnotationAttributes
								(EnableAutoAssemblerResolution.class.getName(), false));
		String[] basePackages = attributes.getStringArray("basePackages");
		assemblerPackages.addAll(Arrays.asList(basePackages));
		return new String[]{"com.tracebucket.tron.context.AssemblerResolutionConfig$BasePackage"};
	}

	@Configuration
	@ComponentScan(basePackages = {"com.tracebucket.tron.assembler"})
	public static class BasePackage{

		@Bean
		public BasePackageStore basePackages(){

			BasePackageStore basePackageStore = new BasePackageStore();
			basePackageStore.addAll(assemblerPackages);
			return basePackageStore;
		}
	}
}
