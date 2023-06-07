package de.thi.association;

import de.thi.association.controller.AssociationResource;
import de.thi.association.repositories.AssociationRepository;
import de.thi.association.services.AssociationService;
import io.quarkus.test.junit.QuarkusTest;
import de.benevolo.entities.association.Association;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@QuarkusTest
public class InitializationTest {

    @Inject
    AssociationResource associationRessource;


    @Test
    public void shouldInitializeAsociation(){
        Association association = new Association("Test Assoc", "test@test.de", "test", null);

        associationRessource.addAssociation(association);

        //TODO ausstehend
    }

}
