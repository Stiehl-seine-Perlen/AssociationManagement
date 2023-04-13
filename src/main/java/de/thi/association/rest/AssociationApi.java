package de.thi.association.rest;

import de.thi.association.entities.Association;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/association")
public class AssociationApi {

    @Inject
    de.thi.association.beans.Association association;

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public void addAssociation(Association entry) {
        association.persist(entry);
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Association> getAllEntries() {
        return association.listAll();
    }

}
