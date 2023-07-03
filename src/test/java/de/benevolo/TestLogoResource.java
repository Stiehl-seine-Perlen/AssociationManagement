package de.benevolo;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.eclipse.microprofile.config.ConfigProvider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.Assert;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.HttpWaitStrategy;

import javax.ws.rs.BadRequestException;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.time.Duration;
import java.util.Arrays;

import static io.restassured.RestAssured.given;


@QuarkusTest
public class TestLogoResource {

    @BeforeAll
    public static void setup() throws Exception {

        // Arrange
        String accessKey = ConfigProvider.getConfig().getValue("minio.accessKey",
                String.class);
        String secretKey = ConfigProvider.getConfig().getValue("minio.secretKey",
                String.class);

        GenericContainer minioServer = new GenericContainer("minio/minio")
                .withEnv("MINIO_ACCESS_KEY", accessKey)
                .withEnv("MINIO_SECRET_KEY", secretKey)
                .withCommand("server /data")
                .withExposedPorts(9000, 9001)
                .waitingFor(new HttpWaitStrategy()
                        .forPath("/minio/health/ready")
                        .forPort(9000)
                        .withStartupTimeout(Duration.ofSeconds(10)));

        minioServer.setPortBindings(Arrays.asList("9000:9000", "9001:9001"));
        minioServer.start();
    }

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
