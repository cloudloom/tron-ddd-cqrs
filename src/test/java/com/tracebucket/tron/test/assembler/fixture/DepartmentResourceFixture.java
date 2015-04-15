package com.tracebucket.tron.test.assembler.fixture;

import com.tracebucket.tron.test.assembler.builder.DepartmentResourceBuilder;
import com.tracebucket.tron.test.assembler.sample.DepartmentResource;

/**
 * Created by sadath on 31-Mar-15.
 */
public class DepartmentResourceFixture {
    public static DepartmentResource standardDepartment(){
        DepartmentResource department = DepartmentResourceBuilder.aDepartmentResource()
                .withName("Account")
                .withDescription("Account desc")
                .build();
        return department;
    }
}