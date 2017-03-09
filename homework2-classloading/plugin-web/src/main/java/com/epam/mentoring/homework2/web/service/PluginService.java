package com.epam.mentoring.homework2.web.service;

import com.epam.mentoring.homework2.api.IPlugin;
import com.epam.mentoring.homework2.web.classloader.PluginClassLoader;
import com.epam.mentoring.homework2.web.exception.PluginManagementException;
import com.epam.mentoring.homework2.web.repository.PluginResourceRepository;
import com.epam.mentoring.homework2.web.util.MessageFormatUtil;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Set;

/**
 * Plugin service.
 * <p/>
 * Date: 02/27/2017
 *
 * @author Raman Kuchynski
 */
@Service
public class PluginService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PluginService.class);

    private static final String NO_PLUGINS_FOUND_MSG = "No plugin found for the location {0}.";
    private static final String PLUGIN_CLASS_NOT_FOUND_MSG = "Plugin class {0} not found.";
    private static final String CANNOT_INSTANTIATE_PLUGIN_MSG = "Cannot instantiate plugin: {0}.";
    private static final String PLUGIN_ID_COLLISION_MSG =
            "Plugin ID collision: plugin {0} from the file {1} was ignored.";

    @Value("${plugin.class.location}")
    private String pluginClassLocation;

    @Autowired
    private PluginResourceRepository pluginResourceRepository;

    private PluginClassLoader pluginClassLoader;

    private Map<String, IPlugin> plugins = Maps.newConcurrentMap();

    @PostConstruct
    public void initService() {
        if (pluginResourceRepository.scanPath(pluginClassLocation)) {
            pluginClassLoader = new PluginClassLoader(pluginResourceRepository);
            for(String className : pluginResourceRepository.getPluginClassNames()) {
                loadPlugin(pluginClassLoader, className);
            }
        } else {
            LOGGER.error(NO_PLUGINS_FOUND_MSG, pluginClassLocation);
            throw new PluginManagementException(
                    MessageFormatUtil.formatMessage(NO_PLUGINS_FOUND_MSG, pluginClassLocation)
            );
        }
    }

    private void loadPlugin(PluginClassLoader classLoader, String className) {
        try {
            Class<?> clazz = classLoader.loadClass(className);
            IPlugin pluginInstance = (IPlugin) clazz.newInstance();
            if (!plugins.containsKey(pluginInstance.getId())) {
                plugins.put(pluginInstance.getId(), pluginInstance);
            } else {
                LOGGER.warn(PLUGIN_ID_COLLISION_MSG,
                        pluginInstance.getId(),
                        pluginResourceRepository.getClassLocation(className).getAbsolutePath());
            }
        } catch (ClassNotFoundException e) {
            LOGGER.warn(MessageFormatUtil.formatMessage(PLUGIN_CLASS_NOT_FOUND_MSG, className), e);
        } catch (IllegalAccessException | InstantiationException e) {
            LOGGER.error(MessageFormatUtil.formatMessage(CANNOT_INSTANTIATE_PLUGIN_MSG, className), e);
        }
    }

    public IPlugin getPluginById(String id) {
        return plugins.get(id);
    }

    public Set<String> getPluginIds() {
        return Sets.newHashSet(plugins.keySet());
    }

    public IPlugin getPluginByClassName(String className) {
        try {
            Class<?> clazz = Class.forName(className, false, pluginClassLoader);
            return (IPlugin) clazz.newInstance();
        } catch (ClassNotFoundException e) {
            LOGGER.warn(MessageFormatUtil.formatMessage(PLUGIN_CLASS_NOT_FOUND_MSG, className), e);
        } catch (IllegalAccessException | InstantiationException e) {
            LOGGER.error(MessageFormatUtil.formatMessage(CANNOT_INSTANTIATE_PLUGIN_MSG, className), e);
        }
        return null;
    }

}
