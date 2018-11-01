package ru.ftob.grostore.service.file;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final String STORAGE_ROOT = "/var/lib/grostore";
    private final int BUFFER_SIZE = 1024;

    @Override
    public String store(String link, String fileName) throws IOException {

        Assert.notNull(link, "URL must not be null");
        link = link.replace("https://", "http://");
        String extension = "";
        int i = link.lastIndexOf('.');
        if (i > 0) {
            extension = link.substring(i+1);
        }

        String filePath = STORAGE_ROOT + fileName + "." + extension;
        //TODO compare files if existing
        URL url = new URL(link);
        try (
                InputStream in = url.openStream();
                FileOutputStream out = new FileOutputStream(filePath)
        ) {
            byte[] buf = new byte[BUFFER_SIZE];
            int n = in.read(buf);
            while (n >= 0) {
                out.write(buf, 0, n);
                n = in.read(buf);
            }
            out.flush();
        }

        return filePath;
    }

    @Override
    public String load(String filename) {
        return null;
    }

    @Override
    public void delete(String filename) {

    }
}
