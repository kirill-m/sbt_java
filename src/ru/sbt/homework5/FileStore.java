package ru.sbt.homework5;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kirill on 04.08.16
 */
public class FileStore implements Store {
    private final String fileName;

    public FileStore(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void save(String t) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(fileName, true));
            bw.write(t);
        } catch (IOException e) {
            throw new StoreException("Error occurred during working with the file " + fileName, e);
        } finally {
            try {
                bw.close();
            } catch (IOException e) {
                throw new StoreException("Error occurred during closing resource.", e);
            }
        }
    }

    @Override
    public List<String> getAll() {
        List<String> result = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(fileName));
            String tmp = null;

            while ((tmp = br.readLine()) != null)
                result.add(tmp);

        } catch (FileNotFoundException e) {
            throw new StoreException("File was not found.", e);
        } catch (IOException e) {
            throw new StoreException("Error occurred during reading the file", e);
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                throw new StoreException("Error occurred during closing resource.", e);
            }
        }

        return result;
    }
}
