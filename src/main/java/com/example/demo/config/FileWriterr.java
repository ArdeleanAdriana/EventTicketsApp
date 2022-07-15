package com.example.demo.config;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterr {

    public FileWriterr(String s, int order) {
        writeFile(s, order);
    }

    private void writeFile(String s, int order) {
        File f = new File("Chitanta" + order + ".txt");
        try {
            f.createNewFile();
            FileWriter w;
            w = new FileWriter(f);
            w.write(s);
            w.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
