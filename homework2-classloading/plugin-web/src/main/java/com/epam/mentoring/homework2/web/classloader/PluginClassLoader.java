package com.epam.mentoring.homework2.web.classloader;

import com.epam.mentoring.homework2.web.exception.PluginManagementException;
import com.epam.mentoring.homework2.web.repository.PluginResourceRepository;
import com.epam.mentoring.homework2.web.util.MessageFormatUtil;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Plugin class loader.
 * <p/>
 * Date: 02/28/2017
 *
 * @author Raman Kuchynski
 */
public class PluginClassLoader extends ClassLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(PluginClassLoader.class);
    private static final String CLASS_FILE_EXT = ".CLASS";

    private static final String LOADING_CLASS_BEGIN_MSG = "Trying to load class {}...";
    private static final String CLASS_SUPPORTED_MSG = "Class {} is supported.";
    private static final String CLASS_LOADING_EXCEPTION = "Failed to load class {0}.";
    private static final String PLUGIN_MALFORMED_ERROR_MSG = "Found class file {0} is malformed.";

    private PluginResourceRepository pluginResourceRepository = new PluginResourceRepository();

    public PluginClassLoader(PluginResourceRepository pluginResourceRepository) {
        this.pluginResourceRepository = pluginResourceRepository;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        LOGGER.info(LOADING_CLASS_BEGIN_MSG, name);
        if (pluginResourceRepository.hasClass(name)) {
            LOGGER.info(CLASS_SUPPORTED_MSG, name);
                byte[] classBuffer = loadClassBytes(name);
                Class<?> clazz = defineClass(name, classBuffer, 0, classBuffer.length);
                resolveClass(clazz);
                return clazz;
        }
        return super.loadClass(name);
    }

    private byte[] loadClassBytes(String name) {
        byte[] classBytes = null;
        try {
            JarFile jarFile = new JarFile(pluginResourceRepository.getClassLocation(name));
            Enumeration<JarEntry> jarEntryEnumeration = jarFile.entries();
            while (jarEntryEnumeration.hasMoreElements()) {
                JarEntry jarEntry = jarEntryEnumeration.nextElement();
                if (jarEntryStoresClass(jarEntry, name)) {
                    classBytes = IOUtils.toByteArray(jarFile.getInputStream(jarEntry));
                    break;
                }
            }
        } catch (IOException ioexc) {
            LOGGER.error(MessageFormatUtil.formatMessage(CLASS_LOADING_EXCEPTION, name), ioexc);
            throw new PluginManagementException(MessageFormatUtil.formatMessage(CLASS_LOADING_EXCEPTION, name), ioexc);
        }
        if (null == classBytes || classBytes.length == 0) {
            LOGGER.error(MessageFormatUtil.formatMessage(PLUGIN_MALFORMED_ERROR_MSG, name));
            throw new PluginManagementException(MessageFormatUtil.formatMessage(PLUGIN_MALFORMED_ERROR_MSG, name));
        }
        return classBytes;
    }

    private boolean jarEntryStoresClass(JarEntry jarEntry, String name) {
        String entryNameUpper = jarEntry.getName().toUpperCase();
        if (jarEntry.isDirectory() || !entryNameUpper.endsWith(CLASS_FILE_EXT)) {
            return false;
        }
        String transformedEntryName = jarEntry.getName()
                .substring(0, entryNameUpper.lastIndexOf(CLASS_FILE_EXT))
                .replaceAll("/",".");
        return transformedEntryName.equals(name);
    }
}
