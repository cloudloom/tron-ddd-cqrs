package com.tracebucket.tron.test.assembler.resolution;

import com.tracebucket.tron.assembler.AssemblerResolver;
import com.tracebucket.tron.context.EnableAutoAssemblerResolution;
import com.tracebucket.tron.test.assembler.config.AssemblerTestConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by ffl on 14-04-2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AssemblerTestConfig.class})
public class ResourceAssemblerTest {

	@Autowired
	private AssemblerResolver assemblerResolver;

	@Test
	public void testInit(){
		Assert.assertNotNull(assemblerResolver);
	}


	/*@Configuration
	@EnableAutoAssemblerResolution(basePackages = "com.tracebucket.tron.test.assembler.sample")
	@ComponentScan(basePackages = {"com.tracebucket.tron.assembler"})
	public static class SpringConfig {}*/
}
