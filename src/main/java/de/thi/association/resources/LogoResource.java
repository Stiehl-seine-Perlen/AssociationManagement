package de.thi.association.resources;

import de.benevolo.minio.S3Service;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Path("/logo/")
public class LogoResource {

    @Inject
    S3Service s3Service;

    @GET
    @Path("{logoName}/img")
    @Produces({"image/jpeg"})
    public InputStream getLogo(@PathParam("logoName") String logoName) throws Exception {
        return s3Service.getObject(logoName);
    }

    @POST
    @Path("/")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String addAssociationLogo(MultipartFormDataInput input) throws Exception {
        Map<String, List<InputPart>> formParts = input.getFormDataMap();

        // upload file to s3
        InputPart logoPart = formParts.get("files").get(0);
        int fileInputSize = Integer.parseInt(formParts.get("logoSize").get(0).getBodyAsString());
        return handleUpload(s3Service, logoPart, fileInputSize);
    }

    private String handleUpload(S3Service s3Service, InputPart fileInputPart, int fileInputSize) throws Exception {
        // Example Content-Type: image/jpeg
        MultivaluedMap<String, String> headers = fileInputPart.getHeaders();
        String encType = headers.getFirst("Content-Type").split("/")[1];
         try (InputStream inputStream = fileInputPart.getBody(InputStream.class, null)) {
            String s3ObjectName = s3Service.putObject(inputStream, encType, fileInputSize);
            return s3ObjectName;
        }
    }
}
