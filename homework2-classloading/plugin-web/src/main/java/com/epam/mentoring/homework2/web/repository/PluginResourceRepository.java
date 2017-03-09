package com.epam.mentoring.homework2.web.repository;

import com.epam.mentoring.homework2.api.IPlugin;
import com.epam.mentoring.homework2.web.exception.PluginManagementException;
import com.epam.mentoring.homework2.web.util.MessageFormatUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.clapper.util.classutil.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Plugin resource repository, scans plugin dir to find .jar files that store plugins.
 * <p/>
 * Date: 02/28/2017
 *
 * @author Raman Kuchynski
 */
@Component
public class PluginResourceRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(PluginResourceRepository.class);
    private static final String PLUGIN_LOCATION_ERROR_MSG = "Plugin location is invalid: {0}";
    private static final String CLASS_NAME_COLLISION_MSG =
            "Class name collision: class {0} from the file {1} was ignored.";
    private static final String CLASS_FOUND_MSG =
            "Class {} found within file {}.";

    private Map<String, File> classFromFileRegistry = Maps.newConcurrentMap();

    public boolean scanPath(String pluginsLocation) {
        validatePluginsLocation(pluginsLocation);
        Collection<File> classFiles = FileUtils.listFiles(new File(pluginsLocation),
                new ClassOrJarFileFilter(), TrueFileFilter.INSTANCE);
        ClassFinder finder = new ClassFinder();
        finder.add(classFiles);
        finder.addClassPath();
        ClassFilter pluginClassFilter = new AndClassFilter(
                new NotClassFilter(new InterfaceOnlyClassFilter()),
                new NotClassFilter(new AbstractClassFilter()),
                new SubclassClassFilter(IPlugin.class)
        );
        Collection<ClassInfo> foundClasses = Lists.newArrayList();
        finder.findClasses(foundClasses, pluginClassFilter);
        for(ClassInfo classInfo : foundClasses) {
            if (classFromFileRegistry.containsKey(classInfo.getClassName())) {
                LOGGER.warn(CLASS_NAME_COLLISION_MSG,
                        classInfo.getClassName(), classInfo.getClassLocation().getAbsolutePath());
            } else {
                LOGGER.info(CLASS_FOUND_MSG,
                        classInfo.getClassName(), classInfo.getClassLocation().getAbsolutePath());
                classFromFileRegistry.put(classInfo.getClassName(), classInfo.getClassLocation());
            }
        }
        return MapUtils.isNotEmpty(classFromFileRegistry);
    }

    public boolean hasClass(String className) {
        return classFromFileRegistry.containsKey(className);
    }

    public String getClassLocationExt(String className) {
        String path = classFromFileRegistry.get(className).getAbsolutePath();
        return path.substring(path.lastIndexOf('.')).toUpperCase();
    }

    public File getClassLocation(String className) {
        return classFromFileRegistry.get(className);
    }

    public Set<String> getPluginClassNames() {
        return classFromFileRegistry.keySet();
    }

    private void validatePluginsLocation(String pluginsLocation) {
        File pluginsDir = new File(pluginsLocation);
        if (!(pluginsDir.exists() && pluginsDir.isDirectory())) {

            LOGGER.warn(PLUGIN_LOCATION_ERROR_MSG, pluginsLocation);
            throw new PluginManagementException(
                    MessageFormatUtil.formatMessage(PLUGIN_LOCATION_ERROR_MSG, pluginsLocation)
            );
        }
    }

    private static class ClassOrJarFileFilter implements IOFileFilter {

        private final List<String> supportedExtensions = Lists.newArrayList(".JAR");

        @Override
        public boolean accept(File file) {
            if (file.isDirectory()) {
                return false;
            }
            for(String ext : supportedExtensions) {
                if (file.getAbsolutePath().toUpperCase().endsWith(ext)) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public boolean accept(File dir, String name) {
            return true;
        }
    }

}
