package com.epam.mentoring.homework2.web.classloader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * <p/>
 * Date: 02/28/2017
 *
 * @author Raman Kuchynski
 */
public class PluginClassLoader extends ClassLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(PluginClassLoader.class);

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return super.loadClass(name);
    }
}
