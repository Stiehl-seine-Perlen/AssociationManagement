package de.thi.association.services;

import java.util.Collections;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.BadRequestException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.benevolo.entities.association.Association;
import de.benevolo.entities.association.Membership;

import de.thi.association.repositories.AssociationRepository;
import de.thi.association.repositories.MembershipRepository;

@ApplicationScoped
public class AssociationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AssociationService.class);

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
            LOGGER.error("Could Not Persist Association", e);
            return null;
        }
    }

    @Transactional
    public boolean updateAssociation(Long id, Association association) {
        Association existingAssociation = associationRepository.findById(id);

        if (existingAssociation != null) {
            existingAssociation.setAssociationName(association.getAssociationName());
            existingAssociation.setBusinessMail(association.getBusinessMail());
            existingAssociation.setDescription(association.getDescription());
            existingAssociation.setMembershipFee(association.getMembershipFee());
            existingAssociation.setFeeCollectionsPerYear(association.getFeeCollectionsPerYear());
            existingAssociation.setLogoName(association.getLogoName());
            existingAssociation.setAddress(association.getAddress());
        }

        try {
            associationRepository.persist(existingAssociation);
            return true;

        } catch (Exception e) {
            LOGGER.error("Update Failed", e);
            return false;
        }
    }

    @Transactional
    public boolean deleteAssociation(Long id) {
        try {
            associationRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            LOGGER.error("Could Not Delete Association With ID: ", id);
            return false;
        }
    }

    @Transactional
    public Membership persistMembership(Membership membership) {
        try {
            membershipRepository.persist(membership);
            LOGGER.info("Membership persisted.");
            return membership;
        } catch (Exception e) {
            LOGGER.error("Could Not Persist - Membership has to be null.");
            throw new BadRequestException("Could not persist membership: ", e);
        }
    }

    
    public List<Membership> getMembershipsByAssociationId(Long associationId) {
        try {
            List<Membership> resultList = membershipRepository.find("association_id", associationId).list();
            LOGGER.info("LISTE MEMBERS: {}", resultList);
            return resultList;
        } catch (Exception e) {
            LOGGER.error("Error getting Memberships: ", e);
            return Collections.emptyList();
        }
    }


    @Transactional
    public boolean deleteMembership(Long id) {
        try {
            LOGGER.info("Membership deleted.");
            membershipRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            LOGGER.error("Could Not Delete Membership With ID: {}", id);
            return false;
        }
    }

}
