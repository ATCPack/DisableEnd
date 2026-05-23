package com.disableend;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.EntityTravelToDimensionEvent;

@Mod("disableend")
public class DisableEnd {

    public static final GameRules.Key<GameRules.BooleanValue> DISABLE_END =
            GameRules.register("disableEnd", GameRules.Category.PLAYER,
                    GameRules.BooleanValue.create(false));

    public DisableEnd(IEventBus modEventBus) {
        NeoForge.EVENT_BUS.addListener(this::onEntityTravel);
    }

    private void onEntityTravel(EntityTravelToDimensionEvent event) {
        if (!event.getDimension().equals(Level.END)) return;
        MinecraftServer server = event.getEntity().getServer();
        if (server != null && server.getGameRules().getBoolean(DISABLE_END)) {
            event.setCanceled(true);
        }
    }
}
