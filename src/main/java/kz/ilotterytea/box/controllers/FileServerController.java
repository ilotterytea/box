package kz.ilotterytea.box.controllers;

import kz.ilotterytea.box.BoxProperties;
import kz.ilotterytea.box.models.FileDataModel;
import kz.ilotterytea.box.utils.KeyUtils;
import org.apache.tika.mime.MimeTypeException;
import org.apache.tika.mime.MimeTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * A controller for file serving: uploading, sharing, deleting.
 * @author ilotterytea
 * @since 1.0
 */
@Controller
public class FileServerController {
    private BoxProperties properties;

    @Autowired
    public FileServerController(BoxProperties properties) {
        this.properties = properties;
    }

    private FileDataModel processFile(MultipartFile file) {
        FileDataModel model = null;

        try {
            model = new FileDataModel(
                    KeyUtils.generateCharKey(properties.getChars(), properties.getDefaultIdLength()),
                    file.getContentType(),
                    MimeTypes.getDefaultMimeTypes().forName(file.getContentType()).getExtension()
            );
        } catch (MimeTypeException e) {
            e.printStackTrace();
        }

        File folder = new File(properties.getUploadedPath());
        if (!folder.exists()) {
            folder.mkdirs();
        }

        try {
            file.transferTo(new File(String.format(
                    "%s/%s%s",
                    folder.getAbsolutePath(),
                    (model != null) ? model.getId() : file.getOriginalFilename(),
                    (model != null) ? model.getExt() : ""
            )));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return model;
    }

    @PostMapping("/upload")
    public ResponseEntity<FileDataModel[]> handleFileUpload(@RequestParam("files") MultipartFile[] files) {
        FileDataModel[] models = new FileDataModel[files.length];
        for (int i = 0; i < files.length; i++) {
            models[i] = processFile(files[i]);
        }

        return ResponseEntity.ok(models);
   }
}
