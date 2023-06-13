package de.thi.association.services;

import de.benevolo.entities.association.Association;
import de.benevolo.entities.finance.AccountType;
import de.benevolo.entities.finance.FinancialAccount;
import de.thi.association.connector.FinanceRestClient;
import de.thi.association.repositories.AssociationRepository;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotAcceptableException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class AssociationService {

    @Inject
    AssociationRepository associationRepository;

    @Inject
    @RestClient
    FinanceRestClient financeRestClient;

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

    public void initialize(Association association){
        BigDecimal openingBalance = new BigDecimal(0);
        Long associationId = association.getId();

        FinancialAccount[] financialAccounts = new FinancialAccount[] {
                new FinancialAccount("Spenden",
                        "Vorderfiniertes Spendenkonto",
                        associationId,
                        openingBalance,
                        AccountType.IDEELLER_BEREICH),  //TODO Überprüfen @Merlin
                new FinancialAccount("Mitgliedsbeiträge",
                        "Vordefiniertes Mitgliedsbeiträge Konto",
                        associationId,
                        openingBalance,
                        AccountType.ZWECKBETRIEB), //TODO Überprüfen @Merlin
                new FinancialAccount("Sammelposten Einnahmen",
                        "Vorderfiniertes Einnahmekonto",
                        associationId,
                        openingBalance,
                        AccountType.WIRTSCHAFTLICH), //TODO Überprüfen @Merlin
                new FinancialAccount("Sammelposten Aufwendungen",
                        "Vordefiniertes Aufwendungskonto",
                        associationId,
                        openingBalance,
                        AccountType.ZWECKBETRIEB), //TODO Überprüfen @Merlin
                new FinancialAccount("Rechtskosten",
                        "Vordefiniertes Rechtskostenkonto",
                        associationId,
                        openingBalance,
                        AccountType.ZWECKBETRIEB), //TODO Überprüfen @Merlin
                new FinancialAccount("Mitarbeiterkosten",
                        "Vordefiniertes Mitarbeiterkostenkonto",
                        associationId,
                        openingBalance,
                        AccountType.ZWECKBETRIEB) //TODO Überprüfen @Merlin
        };

        for (FinancialAccount financialAccount : financialAccounts) {
            financeRestClient.createFinancialAccount(financialAccount);
        }

    }

}
