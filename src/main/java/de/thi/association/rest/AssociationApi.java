package de.thi.association.rest;

import de.thi.association.entities.Association;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
        // has password, because we like data security
        entry.setPassword(hashPassword(entry.getPassword()));
        association.persist(entry);
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Association> getAllEntries() {
        return association.listAll();
    }

    //region Methods

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return new String(hash);
        } catch (NoSuchAlgorithmException e) {
            //TODO: create a more useful exception
            throw new RuntimeException(e);
        }
    }

    //endregion
}
