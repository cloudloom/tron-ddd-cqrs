package com.tracebucket.tron.test.assembler.fixture;

import com.tracebucket.tron.test.assembler.builder.DepartmentBuilder;
import com.tracebucket.tron.test.assembler.sample.Department;

/**
 * Created by sadath on 25-Nov-14.
 */
public class DepartmentFixture {
    public static Department standardDepartment(){
        Department department = DepartmentBuilder.aDepartment()
                .withName("Account")
                .withDescription("Account desc")
                .build();
        return department;
    }
}
