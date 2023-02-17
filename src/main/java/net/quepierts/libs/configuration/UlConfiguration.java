package net.quepierts.libs.configuration;

import net.quepierts.libs.configuration.field.FieldNumber;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

public class UlConfiguration extends YamlConfiguration {
    public UlConfiguration() {
        super();
    }

    public Object getObject(String path, Object def) {
        if (def instanceof ConfigurationSerializable) {

        }

        return def;
    }


}
