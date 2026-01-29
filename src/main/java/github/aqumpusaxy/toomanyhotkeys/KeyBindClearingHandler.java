package github.aqumpusaxy.toomanyhotkeys;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.settings.KeyModifier;
import org.lwjgl.input.Keyboard;

public class KeyBindClearingHandler {
    public static void clearKeyBinds() {
        GameSettings settings = Minecraft.getMinecraft().gameSettings;

        KeyBinding[] keys = settings.keyBindings;

        for (KeyBinding key : keys) {
            if (KeybindBlackListManager.getKeyBlackList().contains(key)) continue;

            key.setKeyModifierAndCode(KeyModifier.NONE, Keyboard.KEY_NONE);
        }

        settings.saveOptions();
        KeyBinding.resetKeyBindingArrayAndHash();
    }

    public static void initModKeyBlackList() {
        KeyBinding[] keys = Minecraft.getMinecraft().gameSettings.keyBindings;

        for (KeyBinding key : keys) {
            for (String modid : KeybindBlackListManager.getModidBlackList()) {
                if (key.getKeyDescription().startsWith(modid, 4)) {
                    KeybindBlackListManager.addKeyToBlackList(key);
                    break;
                }
            }
        }
    }
}
