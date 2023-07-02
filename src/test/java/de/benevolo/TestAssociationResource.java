package de.benevolo;

import de.benevolo.entities.association.Association;
import de.benevolo.entities.global.Address;
import de.thi.association.services.AssociationService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;

@QuarkusTest
public class TestAssociationResource {
    @Inject
    AssociationService associationService;

    @BeforeEach
    @Transactional
    public void setCleanData() {
        Association association = new Association(
                "associationName",
                "123456",
                "1234567",
                "123@mail.de",
                "this is a description",
                new Address(),
                "logoTestName",
                new BigDecimal(12.00),
                5
        );

        associationService.persistAssociation(association);
    }

    @Test
    public void testGetAllAssociations() {
        given()
                .when()
                .get("/all")
                .then()
                .statusCode(200)
                .body("", hasSize(3));
    }

    @Test
    public void testGetAssociationById() {
        given()
                .when()
                .get("/" + "1")
                .then()
                .statusCode(200);

    }

    @Test
    public void testAssociationCreation() {
        Association association = new Association(
                "associationName",
                "123456",
                "1234567",
                "123@mail.de",
                "this is a description",
                new Address(),
                "logoTestName",
                new BigDecimal(12.00),
                5
        );

        Association newAssociation = given()
                .when()
                .body(association)
                .contentType(ContentType.JSON)
                .post("/")
                .then()
                .statusCode(200)
                .and()
                .extract().as(Association.class);

        given().when()
                .delete("/" + newAssociation.getId())
                .then().statusCode(200);
    }

    @Test
    public void testGetMembersForAssociation() {
        // you might think, that this test is useless
        // take this cat if you really believe so: ᓚᘏᗢ

        given()
                .when()
                .get("/1/members")
                .then()
                .statusCode(200)
                .body("", hasSize(5));
    }
}
