package com.tracebucket.tron.test.assembler.fixture;

import com.tracebucket.tron.test.assembler.builder.OrganizationUnitResourceBuilder;
import com.tracebucket.tron.test.assembler.sample.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created by sadath on 31-Mar-15.
 */
public class OrganizationUnitResourceFixture {
    public static OrganizationUnitResource standardOrganizationUnitResource(){
        OrganizationResource organization = null;
        OrganizationUnitResource parent = null;

        Set<DepartmentResource> departments = new HashSet<DepartmentResource>();
        departments.add(DepartmentResourceFixture.standardDepartment());

        Set<BusinessLineResource> businessLines = new HashSet<BusinessLineResource>();
        businessLines.add(BusinessLineResourceFixture.standardBusinessLine());

        Set<OrganizationFunction> organizationFunctions = new HashSet<OrganizationFunction>();
        organizationFunctions.add(OrganizationFunction.SALES);
        organizationFunctions.add(OrganizationFunction.PURCHASE);

/*        Set<OrganizationUnitResource> children = new HashSet<OrganizationUnitResource>();
        children.add(OrganizationUnitFixture.standardOrganizationUnit());*/

        OrganizationUnitResource organizationUnit = OrganizationUnitResourceBuilder.anOrganizationUnitResourceBuilder()
                .withName("OrganizationResource " + new Date().getTime())
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