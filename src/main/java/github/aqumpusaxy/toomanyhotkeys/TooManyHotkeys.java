package github.aqumpusaxy.toomanyhotkeys;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

@Mod(modid = Tags.MOD_ID, name = Tags.MOD_NAME, version = Tags.VERSION, clientSideOnly = true)
@Mod.EventBusSubscriber
public class TooManyHotkeys {
    @Mod.EventHandler
    public void initKeybindsClearingBlackList(FMLPostInitializationEvent event) {
        KeyBindingClearHandler.initBlackList();
    }
    //TODO:添加匹配modid的config
}
