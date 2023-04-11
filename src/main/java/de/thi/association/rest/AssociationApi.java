package de.thi.association.rest;

import de.thi.association.beans.Association;
import de.thi.association.entities.AssociationEntry;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("/association")
public class AssociationApi {

    @Inject
    Association association;

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addAssociation(AssociationEntry entry) {
        association.persist(entry);
    }
}
