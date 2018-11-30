package ru.ftob.grostore.service.file;

import java.io.IOException;
import java.net.MalformedURLException;

public interface FileStorageService {

    String store(String link, String filename) throws IOException;

    String load(String filename);

    void delete (String filename);
}
