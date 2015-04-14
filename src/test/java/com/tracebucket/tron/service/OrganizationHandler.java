package com.tracebucket.tron.service;

import com.tracebucket.tron.cqrs.support.Command;
import com.tracebucket.tron.rest.command.AddOrganizationUnitCommand;

/**
 * Created by ffl on 12-02-2015.
 */
public interface OrganizationHandler {
    public void onAddOrganizationUnit(Command<AddOrganizationUnitCommand> command);
}
