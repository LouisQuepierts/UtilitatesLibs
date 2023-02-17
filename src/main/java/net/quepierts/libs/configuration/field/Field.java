package net.quepierts.libs.configuration.field;

import org.bukkit.entity.Player;

public interface Field<T> {
    void update(Player player);

    T result();
}
