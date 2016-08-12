package ru.sbt.homework7.part1;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by kirill on 12.08.16
 */
public class PluginManager {
    private final String pluginRootDirectory;

    public PluginManager(String pluginRootDirectory) {
        this.pluginRootDirectory = pluginRootDirectory;
    }

    public Plugin load(String pluginName, String pluginClassName) throws MalformedURLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        URL url = new URL("file://" + pluginRootDirectory + pluginName);
        PluginClassLoader loader = new PluginClassLoader(new URL[]{url});

        return (Plugin) loader.loadClass(pluginClassName).newInstance();
    }
}
