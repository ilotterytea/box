package kz.ilotterytea.box.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kz.ilotterytea.box.BoxProperties;
import kz.ilotterytea.box.models.FileDataModel;
import kz.ilotterytea.box.models.ResponseModel;
import kz.ilotterytea.box.utils.HashUtils;
import kz.ilotterytea.box.utils.KeyUtils;
import org.apache.tika.mime.MimeTypeException;
import org.apache.tika.mime.MimeTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;

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

    @GetMapping("/{id}")
    public void serveFile(HttpServletResponse response, @PathVariable("id") String id) throws IOException {
        File folder = new File(properties.getUploadedPath());
        File dataFolder = new File(properties.getDataPath());

        if (!folder.exists() || !dataFolder.exists()) {
            response.setStatus(500);
            response.setContentType("application/json");
            response.getWriter().print(new Gson().toJson(new ResponseModel<>(
                    500,
                    "No folders for uploaded files and data files."
            )));
            return;
        }

        File data = new File(String.format("%s/%s.json", dataFolder.getAbsolutePath(), id));

        if (!data.exists()) {
            response.setStatus(404);
            response.setContentType("application/json");
            response.getWriter().print(new Gson().toJson(new ResponseModel<>(
                    404,
                    String.format("File with ID %s not found!", id)
            )));
            return;
        }

        FileDataModel model = null;

        try {
            FileInputStream fis = new FileInputStream(data);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Gson gson = new GsonBuilder().create();

            model = gson.fromJson(ois.readUTF(), FileDataModel.class);

            ois.close();
            fis.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        File file = new File(String.format("%s/%s%s", folder.getAbsolutePath(), model.getId(), model.getExt()));

        if (!file.exists()) {
            response.setStatus(404);
            response.setContentType("application/json");
            response.getWriter().print(new Gson().toJson(new ResponseModel<>(
                    404,
                    String.format("The file data with the ID %s is found, but the file itself - not found!!", id)
            )));
            return;
        }

        response.reset();
        response.setBufferSize(4096);
        response.setContentType(model.getMime());
        response.getOutputStream().write(Files.readAllBytes(file.toPath()));
    }

    private FileDataModel processFile(MultipartFile file, String url) {
        FileDataModel model = null;

        try {
            model = new FileDataModel(
                    KeyUtils.generateCharKey(properties.getChars(), properties.getDefaultIdLength()),
                    file.getContentType(),
                    MimeTypes.getDefaultMimeTypes().forName(file.getContentType()).getExtension(),
                    file.getSize()
            );
        } catch (MimeTypeException e) {
            e.printStackTrace();
        }

        File folder = new File(properties.getUploadedPath());
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File file1 = new File(String.format(
                "%s/%s%s",
                folder.getAbsolutePath(),
                (model != null) ? model.getId() : file.getOriginalFilename(),
                (model != null) ? model.getExt() : ""
        ));

        try {
            file.transferTo(file1);

            if (properties.getGenerateHashSum()) {
                model.setSum(HashUtils.generateMD5Hash(file1));
            } else {
                model.setSum(null);
            }

            model.setGet(url + model.getId() + model.getExt());

            if (properties.getGenerateDataFiles()) {
                folder = new File(properties.getDataPath());
                if (!folder.exists()) folder.mkdirs();

                FileOutputStream fos = new FileOutputStream(String.format("%s/%s%s.json", properties.getDataPath(), model.getId(), model.getExt()));
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                Gson gson = new GsonBuilder().setPrettyPrinting().create();

                oos.writeUTF(gson.toJson(model));

                oos.close();
                fos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return model;
    }

    @PostMapping("/upload")
    public ResponseEntity<FileDataModel[]> handleFileUpload(@RequestParam("file") MultipartFile[] files, HttpServletRequest request) {
        FileDataModel[] models = new FileDataModel[files.length];
        for (int i = 0; i < files.length; i++) {
            models[i] = processFile(files[i], request.getRequestURL().toString().substring(0, request.getRequestURL().toString().length() - 6));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(models);
   }
}
