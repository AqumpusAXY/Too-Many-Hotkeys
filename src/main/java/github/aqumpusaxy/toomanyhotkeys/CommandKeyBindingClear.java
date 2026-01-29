package github.aqumpusaxy.toomanyhotkeys;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collections;
import java.util.List;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class CommandKeyBindingClear extends CommandBase {
    @Override
    public String getName() {
        return "toomanyhotkeys";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/toomanyhotkeys clear";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length != 1 || !args[0].equals("clear")) {
            throw new WrongUsageException("/toomanyhotkeys clear");
        } else {
            KeyBindingClearHandler.clearKeyBindings();
            //TODO:i18n
            sender.sendMessage(new TextComponentString("Cleared"));
        }
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        if (args.length == 1) {
            return getListOfStringsMatchingLastWord(args, "clear");
        } else {
            return Collections.emptyList();
        }
    }
}
