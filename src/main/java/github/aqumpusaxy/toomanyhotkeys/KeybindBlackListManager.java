package github.aqumpusaxy.toomanyhotkeys;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Set;

public class KeybindBlackListManager {
    private static final Set<String> MODID_LIST = new HashSet<>();
    private static final Set<String> MODID_BLACK_LIST = new HashSet<>();
    private static final Set<KeyBinding> KEY_BLACK_LIST = new HashSet<>();

    public static Set<String> getModidList() {
        return MODID_LIST;
    }

    public static Set<String> getModidBlackList() {
        return MODID_BLACK_LIST;
    }

    public static Set<KeyBinding> getKeyBlackList() {
        return KEY_BLACK_LIST;
    }

    public static boolean addModidToBlackList(String modid) {
        return MODID_BLACK_LIST.add(modid);
    }

    public static boolean removeModidFromBlackList(String modid) {
        return MODID_BLACK_LIST.remove(modid);
    }

    public static void addKeyToBlackList(KeyBinding key) {
        KEY_BLACK_LIST.add(key);
    }

    public static void initModidList() {
        KeyBinding[] keys = Minecraft.getMinecraft().gameSettings.keyBindings;

        for (KeyBinding key : keys) {
            String namespace = getKeyBindNamespace(key);

            if (namespace != null && !namespace.equals("hotbar")) {
                MODID_LIST.add(namespace);
            }
        }
    }

    @Nullable
    private static String getKeyBindNamespace(KeyBinding key) {
        String keyDesc = key.getKeyDescription();

        int firstDotIndex = keyDesc.indexOf(".");
        if (firstDotIndex == -1 || firstDotIndex + 1 >= keyDesc.length()) {
            return null;
        }

        int secondDotIndex = keyDesc.indexOf(".", firstDotIndex + 1);
        if (secondDotIndex == -1) {
            return null;
        }

        return keyDesc.substring(firstDotIndex + 1, secondDotIndex);
    }

    public static void initVanillaBlackList() {
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
