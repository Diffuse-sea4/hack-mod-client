package net.example.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class MyClientMod implements ClientModInitializer {
    private static KeyBinding flyKey;

    @Override
    public void onInitializeClient() {
        flyKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.client.toggle_fly",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_G, 
                "category.client.cheats"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (flyKey.wasPressed()) {
                ModuleManager.toggle("Flight");
                if (client.player != null) {
                    client.player.sendMessage(net.minecraft.text.Text.literal("§aFlight toggled!"), false);
                }
            }
        });
    }
}
