package de.thi.association.services;

import java.math.BigDecimal;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotAcceptableException;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import de.benevolo.entities.association.Association;
import de.benevolo.entities.finance.AccountType;
import de.benevolo.entities.finance.FinancialAccount;
import de.thi.association.connector.FinanceRestClient;
import de.thi.association.repositories.AssociationRepository;

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
    public boolean deleteAssociation(Long id) {
        try {
            associationRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new NotAcceptableException("Could Not Delete Association With ID: " + id);
        }
    }
}
