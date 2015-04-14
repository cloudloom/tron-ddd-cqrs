package com.tracebucket.tron.fixture;

import com.tracebucket.tron.builder.OrganizationUnitBuilder;
import com.tracebucket.tron.dictionary.OrganizationFunction;
import com.tracebucket.tron.domain.BusinessLine;
import com.tracebucket.tron.domain.Department;
import com.tracebucket.tron.domain.Organization;
import com.tracebucket.tron.domain.OrganizationUnit;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created by sadath on 25-Nov-14.
 */
public class OrganizationUnitFixture {
    public static OrganizationUnit standardOrganizationUnit(){
        Organization organization = null;
        OrganizationUnit parent = null;

        Set<Department> departments = new HashSet<Department>();
        departments.add(DepartmentFixture.standardDepartment());

        Set<BusinessLine> businessLines = new HashSet<BusinessLine>();
        businessLines.add(BusinessLineFixture.standardBusinessLine());

        Set<OrganizationFunction> organizationFunctions = new HashSet<OrganizationFunction>();
        organizationFunctions.add(OrganizationFunction.SALES);
        organizationFunctions.add(OrganizationFunction.PURCHASE);

/*        Set<OrganizationUnit> children = new HashSet<OrganizationUnit>();
        children.add(OrganizationUnitFixture.standardOrganizationUnit());*/

        OrganizationUnit organizationUnit = OrganizationUnitBuilder.anOrganizationUnitBuilder()
                .withName("Organization " + new Date().getTime())
                .withDescription(UUID.randomUUID().toString())
                .withBusinessLines(businessLines)
                //.withChildren(children)
                .withDepartments(departments)
                .withOrganizationFunctions(organizationFunctions)
                .withOrganization(organization)
                .withParent(parent)
                .build();
        return organizationUnit;
    }
}
