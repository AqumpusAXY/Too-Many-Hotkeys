package github.aqumpusaxy.toomanyhotkeys;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

import java.util.HashSet;
import java.util.Set;

public class KeyBindingClearHandler {
    private static final Set<String> MODID_BLACK_LIST = new HashSet<>();

    private static final HashSet<KeyBinding> KEY_BLACK_LIST = new HashSet<>();

    //TODO: 可配置的MODID名单
    static {
        MODID_BLACK_LIST.add("jei");
        MODID_BLACK_LIST.add("hei");
    }

    public static void clearKeyBindings() {
        GameSettings settings = Minecraft.getMinecraft().gameSettings;

        KeyBinding[] keys = settings.keyBindings;
        for (KeyBinding key : keys) {
            if (KEY_BLACK_LIST.contains(key)) continue;
            settings.setOptionKeyBinding(key, Keyboard.KEY_NONE);
        }

        KeyBinding.resetKeyBindingArrayAndHash();
    }

    private static void addKeyToBlackList(KeyBinding key) {
        KEY_BLACK_LIST.add(key);
    }

    public static void initBlackList() {
        initVanillaBlackList();
        initModKeyToBlackList();
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

    private static void initModKeyToBlackList() {
        KeyBinding[] keys = Minecraft.getMinecraft().gameSettings.keyBindings;

        for (KeyBinding key : keys) {
            for (String modid : MODID_BLACK_LIST) {
                if (key.getKeyDescription().contains(modid)) {
                    addKeyToBlackList(key);
                    break;
                }
            }
        }
    }
}
