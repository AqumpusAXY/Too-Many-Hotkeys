package github.aqumpusaxy.toomanyhotkeys;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

import java.util.HashSet;

public class KeyBindingClearHandler {

    private static final HashSet<KeyBinding> keyBlackList = new HashSet<>();

    public static void clearKeyBindings() {
        GameSettings settings = Minecraft.getMinecraft().gameSettings;

        KeyBinding[] keys = settings.keyBindings;
        for (KeyBinding key : keys) {
            if (keyBlackList.contains(key)) continue;
            settings.setOptionKeyBinding(key, Keyboard.KEY_NONE);
        }

        KeyBinding.resetKeyBindingArrayAndHash();
    }

    private static void addKeyToBlackList(KeyBinding key) {
        keyBlackList.add(key);
    }

    public static void initBlackList() {
        initVanillaBlackList();
        //TODO: 添加mod黑名单
    }

    private static void initVanillaBlackList() {
        GameSettings settings = Minecraft.getMinecraft().gameSettings;

        // Movement
        addKeyToBlackList(settings.keyBindForward);    // W
        addKeyToBlackList(settings.keyBindLeft);       // A
        addKeyToBlackList(settings.keyBindBack);       // S
        addKeyToBlackList(settings.keyBindRight);      // D
        addKeyToBlackList(settings.keyBindJump);       // Space
        addKeyToBlackList(settings.keyBindSneak);      // Shift
        addKeyToBlackList(settings.keyBindSprint);     // Ctrl

        // Other
        addKeyToBlackList(settings.keyBindInventory);  // E
        addKeyToBlackList(settings.keyBindUseItem);    // Right Click
        addKeyToBlackList(settings.keyBindAttack);     // Left Click
        addKeyToBlackList(settings.keyBindChat);       // T
        addKeyToBlackList(settings.keyBindPlayerList); // Tab
        addKeyToBlackList(settings.keyBindDrop);       // Q
        addKeyToBlackList(settings.keyBindPickBlock);  // Middle Click
        addKeyToBlackList(settings.keyBindCommand);    // /
        addKeyToBlackList(settings.keyBindScreenshot); // F2
        addKeyToBlackList(settings.keyBindTogglePerspective); // F5
        addKeyToBlackList(settings.keyBindFullscreen); // F11
        addKeyToBlackList(settings.keyBindSmoothCamera); // F3 + C
        addKeyToBlackList(settings.keyBindAdvancements); // L

        // Hotbar
        for (KeyBinding key : settings.keyBindsHotbar) {
            addKeyToBlackList(key);
        }

        // Toolbar
        addKeyToBlackList(settings.keyBindSaveToolbar);
        addKeyToBlackList(settings.keyBindLoadToolbar);
    }
}
