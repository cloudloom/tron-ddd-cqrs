package com.tracebucket.tron.fixture;

import com.tracebucket.tron.builder.DepartmentResourceBuilder;
import com.tracebucket.tron.rest.resource.DepartmentResource;

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