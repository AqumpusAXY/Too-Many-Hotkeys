package github.aqumpusaxy.toomanyhotkeys;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.*;
import java.util.stream.Collectors;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@Mod.EventBusSubscriber
public class CommandTooManyHotkeys extends CommandBase {
    @Override
    public String getName() {
        return "toomanyhotkeys";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/toomanyhotkeys (show_mods|clear) OR /toomanyhotkeys (add|remove) <modid>";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length < 1) {
            throw new WrongUsageException(getUsage(sender));
        } else if (args.length < 2) {
            switch (args[0]) {
                case "show_mods":
                    sender.sendMessage(new TextComponentString(KeybindBlackListManager.getModidList().toString()));
                    break;
                case "show_black_list":
                    sender.sendMessage(new TextComponentString(KeybindBlackListManager.getModidBlackList().toString()));
                    break;
                case "clear":
                    KeyBindClearingHandler.initModKeyBlackList();
                    KeyBindClearingHandler.clearKeyBinds();
                    sender.sendMessage(new TextComponentString("Keybinds cleared"));
                    break;
                default:
                    throw new WrongUsageException(getUsage(sender));
            }
        } else if (args.length < 3) {
            switch (args[0]) {
                case "add":
                    if (!KeybindBlackListManager.getModidList().contains(args[1])) {
                        sender.sendMessage(new TextComponentString("This mod does not register any keybind"));
                    } else if (KeybindBlackListManager.addModidToBlackList(args[1])) {
                        sender.sendMessage(new TextComponentString("Add mod " + args[1] + " to the black list"));
                    } else {
                        sender.sendMessage(new TextComponentString("This mod is already in the black list"));
                    }
                    break;
                case "remove":
                    if (!KeybindBlackListManager.removeModidFromBlackList(args[1])) {
                        sender.sendMessage(new TextComponentString("This mod is not in the black list"));
                    } else {
                        sender.sendMessage(new TextComponentString("Remove mod " + args[1] + " from the black list"));
                    }
                    break;
                default:
                    throw new WrongUsageException(getUsage(sender));
            }
        } else {
            throw new WrongUsageException(getUsage(sender));
        }
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        if (args.length == 1) {
            return getListOfStringsMatchingLastWord(args, "show_mods", "show_black_list", "add", "remove", "clear");
        } else if (args.length == 2) {
            switch (args[0]) {
                case "add":
                    Set<String> modidList = KeybindBlackListManager.getModidList();
                    if (modidList.isEmpty()) return new ArrayList<>();

                    Set<String> modRemainingList = modidList.stream()
                            .filter(modid -> !KeybindBlackListManager.getModidBlackList().contains(modid))
                            .collect(Collectors.toSet());

                    return getListOfStringsMatchingLastWord(args, modRemainingList);
                case "remove":
                    Set<String> modidBlackList = KeybindBlackListManager.getModidBlackList();
                    if (modidBlackList.isEmpty()) return new ArrayList<>();
                    return getListOfStringsMatchingLastWord(args, KeybindBlackListManager.getModidBlackList());
            }
        }
        return Collections.emptyList();
    }

    @SubscribeEvent
    public static void registerCommand(FMLNetworkEvent.ClientConnectedToServerEvent event) {
        ClientCommandHandler.instance.registerCommand(new CommandTooManyHotkeys());
    }
}