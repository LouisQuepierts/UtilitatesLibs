package net.quepierts.libs.configuration;

import net.quepierts.libs.dao.Registry;
import org.apache.commons.lang.Validate;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.Map;

public class UlConfigurationSerialization {
    private static final Registry<Class<? extends ConfigurationSerializable>> registry = new Registry<>();

    public static void register(String name, Class<? extends ConfigurationSerializable> clazz) {
        Validate.notEmpty(name);

        registry.register(name, clazz);
    }

    public static void deserialize(Map<String, Object> map) {

    }
}
