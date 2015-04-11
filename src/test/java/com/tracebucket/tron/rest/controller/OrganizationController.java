package com.tracebucket.tron.rest.controller;

import com.tracebucket.tron.cqrs.support.Command;
import com.tracebucket.tron.cqrs.support.CommandHelper;
import com.tracebucket.tron.ddd.domain.AggregateId;
import com.tracebucket.tron.domain.Organization;
import com.tracebucket.tron.rest.assembler.entity.OrganizationEntityAssembler;
import com.tracebucket.tron.rest.assembler.entity.OrganizationUnitEntityAssembler;
import com.tracebucket.tron.rest.assembler.resource.OrganizationResourceAssembler;
import com.tracebucket.tron.rest.assembler.resource.OrganizationUnitResourceAssembler;
import com.tracebucket.tron.rest.command.AddOrganizationUnitCommand;
import com.tracebucket.tron.rest.resource.OrganizationResource;
import com.tracebucket.tron.service.OrganizationService;
import com.tracebucket.tron.util.AssemblerResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by sadath on 10-Feb-15.
 */
@RestController
public class OrganizationController {

    private static Logger log = LoggerFactory.getLogger(OrganizationController.class);

    @Autowired
    private CommandHelper commandHelper;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private OrganizationEntityAssembler organizationEntityAssembler;

    @Autowired
    private OrganizationResourceAssembler organizationResourceAssembler;

    @Autowired
    private OrganizationUnitResourceAssembler organizationUnitResourceAssembler;

    @Autowired
    private OrganizationUnitEntityAssembler organizationUnitEntityAssembler;

    @Autowired
    private AssemblerResolver assemblerResolver;

    @RequestMapping(value = "/organization/organizationunit", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public DeferredResult<OrganizationResource> addOrganizationUnit(@RequestBody AddOrganizationUnitCommand addOrganizationUnitCommand) {
        Command<AddOrganizationUnitCommand> baseCurrencyCommand = Command.wrap(addOrganizationUnitCommand);
        DeferredResult<OrganizationResource> deferredResult = new DeferredResult<OrganizationResource>();
        commandHelper.intent(Command.name("addOrganizationUnit"), baseCurrencyCommand, deferredResult);
        return deferredResult;
    }

    @RequestMapping(value = "/organization", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrganizationResource> createOrganizaton(@RequestBody OrganizationResource organizationResource) {
        Organization organization = organizationEntityAssembler.toEntity(organizationResource, Organization.class);
        organization = organizationService.save(organization);
        organizationResource = organizationResourceAssembler.toResource(organization);
        return new ResponseEntity<OrganizationResource>(organizationResource, HttpStatus.OK);
    }

/*    @RequestMapping(value = "/organization/{organizationUid}/organizationunit", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrganizationResource> addOrganizationUnit(@PathVariable("organizationUid") String organizationUid, @RequestBody OrganizationUnitResource organizationUnitResource) {
        OrganizationUnit organizationUnit = organizationUnitEntityAssembler.toEntity(organizationUnitResource, OrganizationUnit.class);
        Organization organization = organizationService.addOrganizationUnit(organizationUnit, new AggregateId(organizationUid));
        OrganizationResource organizationResource = organizationResourceAssembler.toResource(organization);
        return new ResponseEntity<OrganizationResource>(organizationResource, HttpStatus.OK);
    }*/

    @RequestMapping(value = "/organization/{organizationUid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrganizationResource> getOrganization(@PathVariable("organizationUid") String organizationUid) {
        Organization organization = organizationService.findOne(new AggregateId(organizationUid));
        OrganizationResource organizationResource = organizationResourceAssembler.toResource(organization);
        return new ResponseEntity<OrganizationResource>(organizationResource, HttpStatus.OK);
    }

    @RequestMapping(value = "/organizations", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<OrganizationResource>> getOrganizations(@PathVariable("organizationUid") String organizationUid) {
        List<OrganizationResource> organizationResources = new ArrayList<OrganizationResource>();
        return new ResponseEntity<Set<OrganizationResource>>(organizationResourceAssembler.toResources(organizationService.findAll()), HttpStatus.OK);
    }

    @RequestMapping(value = "/organization/{organizationUid}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> deleteOrganization(@PathVariable("organizationUid") String organizationUid) {
        return new ResponseEntity<Boolean>(organizationService.delete(new AggregateId(organizationUid)), HttpStatus.OK);
    }
}
