package com.tracebucket.tron.service.impl;

import com.tracebucket.tron.cqrs.support.Command;
import com.tracebucket.tron.cqrs.support.CommandHelper;
import com.tracebucket.tron.ddd.domain.AggregateId;
import com.tracebucket.tron.domain.Organization;
import com.tracebucket.tron.domain.OrganizationUnit;
import com.tracebucket.tron.rest.assembler.entity.EntityAssembler;
import com.tracebucket.tron.rest.assembler.entity.OrganizationUnitEntityAssembler;
import com.tracebucket.tron.rest.assembler.resource.OrganizationResourceAssembler;
import com.tracebucket.tron.rest.assembler.resource.ResourceAssembler;
import com.tracebucket.tron.rest.command.AddOrganizationUnitCommand;
import com.tracebucket.tron.rest.resource.OrganizationResource;
import com.tracebucket.tron.rest.resource.OrganizationUnitResource;
import com.tracebucket.tron.service.OrganizationHandler;
import com.tracebucket.tron.service.OrganizationService;
import com.tracebucket.tron.util.AssemblerResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.async.DeferredResult;
import reactor.spring.context.annotation.Consumer;
import reactor.spring.context.annotation.Selector;

/**
 * @author FFL
 * @since 12-02-2015
 * An application service is a command handler in CQRS.
 */
@Consumer
public class OrganizationHandlerImpl implements OrganizationHandler {
    private static Logger log = LoggerFactory.getLogger(OrganizationHandlerImpl.class);

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private OrganizationUnitEntityAssembler organizationUnitEntityAssembler;

    @Autowired
    private OrganizationResourceAssembler organizationResourceAssembler;

    @Autowired
    private CommandHelper commandHelper;

    @Autowired
    private AssemblerResolver assemblerResolver;

    @Selector(value = "Command|addOrganizationUnit", reactor="@commandBus")
    public void onAddOrganizationUnit(Command<AddOrganizationUnitCommand> command) {
        log.info("Received command " + command.toString());
        AddOrganizationUnitCommand organizationUnitCommand = command.getData();
        EntityAssembler entityAssembler = assemblerResolver.resolveEntityAssembler(OrganizationUnit.class, OrganizationUnitResource.class);
        OrganizationUnit organizationUnit = (OrganizationUnit)entityAssembler.toEntity(organizationUnitCommand.getOrganizationUnit(), OrganizationUnit.class);
        Organization organization = organizationService.addOrganizationUnit(organizationUnit,
                new AggregateId(organizationUnitCommand.getOrganizationId()));
        DeferredResult deferredResult = commandHelper.fetch(command.getCid());
        log.info("Setting result for command " + command.getCid());
        ResourceAssembler resourceAssembler = assemblerResolver.resolveResourceAssembler(OrganizationResource.class, Organization.class);
        OrganizationResource organizationResource = (OrganizationResource)resourceAssembler.toResource(organization);
        deferredResult.setResult(organizationResource);
    }
}
