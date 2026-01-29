package github.aqumpusaxy.toomanyhotkeys;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = Tags.MOD_ID, name = Tags.MOD_NAME, version = Tags.VERSION)
public class TooManyHotkeys {
    @Mod.EventHandler
    public void printKeyBindings(FMLPostInitializationEvent event) {
        KeyBindingClearHandler.initBlackList();
    }

    @Mod.EventHandler
    public void registerCommand(FMLServerStartingEvent event) {
        ClientCommandHandler.instance.registerCommand(new CommandKeyBindingClear());
    }
    //TODO:添加匹配modid的config
}
