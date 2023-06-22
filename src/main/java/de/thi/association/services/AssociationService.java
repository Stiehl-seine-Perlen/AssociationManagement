package de.thi.association.services;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotAcceptableException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.benevolo.entities.association.Association;
import de.benevolo.entities.association.AssociationRole;
import de.benevolo.entities.association.Membership;
import de.benevolo.entities.user.PlatformUser;
import de.thi.association.repositories.AssociationRepository;
import de.thi.association.repositories.MembershipRepository;

@ApplicationScoped
public class AssociationService {

    private static final Logger logger = LoggerFactory.getLogger(AssociationService.class);

    @Inject
    AssociationRepository associationRepository;

    @Inject
    MembershipRepository membershipRepository;

    public Association getAssociationById(Long id) {
        return associationRepository.findById(id);
    }

    public List<Association> getAllAssociations() {
        return associationRepository.listAll();
    }

    @Transactional
    public Association persistAssociation(Association association) {
        try {
            associationRepository.persist(association);

            return association;
        } catch (Exception e) {
            logger.error("Could Not Persist Association", e);
            return null;
        }
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
            logger.error("Update Failed", e);
            return false;
        }
    }

    @Transactional
    public boolean deleteAssociation(Long id) {
        try {
            associationRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            logger.error("Could Not Delete Association With ID: ", id);
            return false;
        }
    }

    @Transactional
    public Membership persistMembership(Membership membership){
        //Workaround to addMemberships
        Membership freshMembership = new Membership();
        logger.info("Membership: " + membership);
        membership.setAssociationRole(freshMembership.getAssociationRole());
        membership.setMembershipId(null);
        
        try {
            membershipRepository.persist(membership);
            logger.info("Membership persisted.");
           return membership;
        } catch (Exception e) {
           logger.error("Could Not Persist Membership", e);
            return membership;
        }
    }
}
