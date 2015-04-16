package com.tracebucket.tron.context;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Arrays;
import java.util.HashSet;
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
			log.info("Configuring Assembler Resolution ...");
			log.info("Scanning for assemblers in " + StringUtils.join(assemblerPackages, ","));
			BasePackageStore basePackageStore = new BasePackageStore();
			basePackageStore.addAll(assemblerPackages);
			return basePackageStore;
		}
	}
}
