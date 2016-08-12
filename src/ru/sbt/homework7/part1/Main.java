package ru.sbt.homework7.part1;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by kirill on 12.08.16
 */
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, MalformedURLException, InstantiationException, IllegalAccessException {
        URL url = new URL("file:///Users/kirill/IdeaProjects/sbt_homework/target/classes/");
        URLClassLoader initiating = new URLClassLoader(new URL[]{url});
        Class<?> clazz = initiating.loadClass("ru.sbt.homework7.part1.Plugin");
        System.out.println(clazz.getClassLoader());
        System.out.println("_______________");

        Plugin p = new PluginImpl();
        p.doSomething();

        PluginManager pm = new PluginManager("/Users/kirill/IdeaProjects/sbt_homework/src/ru/sbt/");

        Plugin p2 = pm.load("pluginDir/plugin2/", "ru.sbt.homework7.part1.PluginImpl");
        p2.doSomething();

        Plugin p3 = pm.load("pluginDir/plugin1/", "ru.sbt.homework7.part1.PluginImpl");
        p3.doSomething();
    }
}
