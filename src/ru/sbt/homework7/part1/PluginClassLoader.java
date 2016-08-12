package ru.sbt.homework7.part1;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by kirill on 12.08.16
 */
public class PluginClassLoader extends URLClassLoader {

    public PluginClassLoader(URL[] urls) {
        super(urls);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (name.startsWith("java") || name.equals("ru.sbt.homework7.part1.Plugin")) {
            return super.loadClass(name);
        }
        return findClass(name);
    }
}
