package kz.ilotterytea.bin.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.multipart.CompletedFileUpload;
import kz.ilotterytea.bin.ApplicationProperties;
import kz.ilotterytea.bin.entities.File;
import kz.ilotterytea.bin.schemas.Payload;
import kz.ilotterytea.bin.utils.HibernateUtil;
import org.hibernate.Session;

import java.io.*;
import java.util.List;
import java.util.Random;

/**
 * @author ilotterytea
 * @since 1.0
 */
@Controller()
public class APIController {
    private final ApplicationProperties properties;

    public APIController(ApplicationProperties properties) {
        this.properties = properties;
    }

    @Post(
            value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA,
            produces = MediaType.APPLICATION_JSON
    )
    public HttpResponse<Payload<File>> uploadFile(CompletedFileUpload fileUpload) throws IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();

        // Generate a token and generate a new one if one already exists:
        StringBuilder token = new StringBuilder();
        while (true) {
            for (int i = 0; i < this.properties.getTokenLength(); i++) {
                token.append(this.properties.getTokenChars().charAt(new Random().nextInt(this.properties.getTokenChars().length())));
            }

            List<File> files = session.createQuery("from File where id = :id", File.class)
                    .setParameter("id", token.toString())
                    .getResultList();

            if (files.isEmpty()) {
                break;
            } else {
                token = new StringBuilder();
            }
        }

        // Get the MIME type and the extension:
        String mime = null;
        String extension = null;

        if (fileUpload.getContentType().isPresent()) {
            MediaType type = fileUpload.getContentType().get();

            extension = type.getExtension();
            mime = type.getName();
        }

        // Create a new File instance:
        File file = new File(
                token.toString(),
                mime,
                extension,
                fileUpload.getBytes()
        );

        // Save the file:
        session.getTransaction().begin();
        session.persist(file);
        session.getTransaction().commit();
        session.close();

        return HttpResponse.ok(new Payload<>(file));
    }

    @Get(
            value = "/{id}"
    )
    public HttpResponse getFile(String id, @QueryValue("key") String sKey) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<File> files = session.createQuery("from File where id = :id", File.class)
                .setParameter("id", id)
                .getResultList();
        session.close();

        if (files.isEmpty()) {
            return HttpResponse.notFound();
        }

        File file = files.get(0);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(file.getData());

        return HttpResponse.ok(inputStream)
                .contentType(MediaType.of(file.getMime()))
                .header("Content-Disposition", "inline; filename=\"" + file.getId() + "\"")
                .header("Content-Length", String.valueOf(file.getData().length));
    }
}