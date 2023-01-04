package kz.ilotterytea.box.components;

import kz.ilotterytea.box.BoxProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Expired files collector.
 * @author ilotterytea
 * @since 1.0
 */
@Component
public class CollectorComponent {
    private final BoxProperties properties;
    private static final Logger LOGGER = LoggerFactory.getLogger(CollectorComponent.class);

    @Autowired
    public CollectorComponent(BoxProperties properties) {
        this.properties = properties;

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                collectExpiredFiles();
            }
        }, 0, properties.getCollectExpiredFilesIntervalMs());
    }

    private void collectExpiredFiles() {
        File uploadedFilesFolder = new File(properties.getUploadedPath());
        File dataFilesFolder = new File(properties.getDataPath());
        long maxFileAge = properties.getMaxFileAgeMs();
        int datFilesCount = 0;
        int uplFilesCount = 0;

        if (uploadedFilesFolder.isFile() || dataFilesFolder.isFile()) {
            LOGGER.error("The path to the folder with the uploaded files/data files directs to the file. Abort!");
            return;
        }

        for (File file : Objects.requireNonNull(uploadedFilesFolder.listFiles())) {
            if (file.isDirectory()) continue;

            long time = System.currentTimeMillis() - file.lastModified();

            if (time > maxFileAge) {
                if (file.delete()) {
                    uplFilesCount++;
                }
            }
        }

        for (File file : Objects.requireNonNull(dataFilesFolder.listFiles())) {
            if (file.isDirectory()) continue;

            long time = System.currentTimeMillis() - file.lastModified();

            if (time > maxFileAge) {
                if (file.delete()) {
                    datFilesCount++;
                }
            }
        }

        LOGGER.info("Finished collecting expired files! A total of "+uplFilesCount+" uploaded files and "+datFilesCount+" data files were erased.");
    }
}
