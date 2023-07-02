package de.benevolo;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.junit.Assert;

import javax.ws.rs.BadRequestException;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static io.restassured.RestAssured.given;


@QuarkusTest
public class TestLogoResource {

    @Test
    public void testAddAssociationLogo() {
        String filePath = String.format("%s%s", System.getProperty("user.dir"),
                "/test-files/defaultLogo.jpeg");
        File file = new File(filePath);
        RestAssured.config().getHttpClientConfig().setParam("http.connection.timeout", 10000);

        try (InputStream uploadedFile = new FileInputStream(file);
             InputStream receivedFile = RestAssured.get(file.getName() + "/img").asInputStream()) {

            if (!compareInputStreams(uploadedFile, receivedFile)) {
                Assert.fail("test failed");
            }
        } catch (Exception e) {
            throw new BadRequestException();
        }
    }

    @Test
    public void testGetLogo() {
        // we expect here an defaultImage to be uploaded already
        given()
                .when()
                .get("/logo/defaultLogo.jpeg/img")
                .then()
                .statusCode(200);
    }



    private boolean compareInputStreams(InputStream inputStream1, InputStream inputStream2) throws Exception {
        int data1 = inputStream1.read();
        int data2 = inputStream2.read();

        while (data1 != -1 && data2 != -1) {
            if (data1 != data2) {
                return false;
            }
            data1 = inputStream1.read();
            data2 = inputStream2.read();
        }
        return true;
    }
}
