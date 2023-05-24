package de.thi.association.services;

import de.benevolo.entities.association.Association;
import de.thi.association.repositories.AssociationRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotAcceptableException;
import java.util.List;

@ApplicationScoped
public class AssociationService {

    @Inject
    AssociationRepository associationRepository;

    public Association getAssociationById(Long id) {
        return associationRepository.findById(id);
    }

    public List<Association> getAllAssociations() {
        return associationRepository.listAll();
    }

    public Association createAssociation(Association association) {
        return this.persistAssociation(association);
    }

    public boolean removeAssociation(Long id) {
        return this.deleteAssociation(id);
    }

    @Transactional
    public boolean updateAssociation(Long id, Association association) {
        Association existingAssociation = associationRepository.findById(id);

        if (existingAssociation != null) {
            existingAssociation.setAssociationName(association.getAssociationName());
            existingAssociation.setBusinessMail(association.getBusinessMail());
            existingAssociation.setAddress(association.getAddress());
            existingAssociation.setDescription(association.getDescription());
        }

        try {
            associationRepository.persist(existingAssociation);
            return true;

        } catch (Exception e) {
            throw new NotAcceptableException("update failed");
        }
    }

    @Transactional
    public Association persistAssociation(Association association) {
        try {
            associationRepository.persist(association);
            return association;
        } catch (Exception e) {
            throw new NotAcceptableException("Could Not Persist Association: " + e);
        }
    }

    @Transactional
    public boolean deleteAssociation(Long id) {
        try {
            associationRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new NotAcceptableException("Could Not Delete Association With ID: " + id);
        }
    }
}
