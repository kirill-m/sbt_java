package ru.sbt.homework7.part2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by kirill on 12.08.16
 */
public class EncryptedClassLoader extends ClassLoader {

    private final String key;
    private final File dir;


    public EncryptedClassLoader(String key, File dir, ClassLoader parent) {
        super(parent);
        this.key = key;
        this.dir = dir;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            File file = new File(dir + "/" + name.replace('.', '/').concat(".class"));
            FileInputStream fis = new FileInputStream(file);
            ArrayList<Byte> bytesList = new ArrayList<>();

            while (fis.available() > 0) {
                byte tmp = (byte)fis.read();
                bytesList.add(tmp);
            }
            byte[] bytes = new byte[bytesList.size()];

            for (int i = 0; i < bytesList.size(); i++) {
                bytes[i] =  (byte)(bytesList.get(i) + key.getBytes()[1]);

                System.out.println(bytes[i] + " : " + bytesList.get(i));
            }

            return defineClass(name, bytes, 0, bytes.length);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File was not found.", e);
        } catch (IOException e) {
            throw new RuntimeException("IOException", e);
        }
    }
}
