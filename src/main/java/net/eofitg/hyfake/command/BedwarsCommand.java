package net.eofitg.hyfake.command;

import net.eofitg.hyfake.HyFake;
import net.eofitg.hyfake.util.BedwarsUtil;
import net.eofitg.hyfake.util.PlayerUtil;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;

public class BedwarsCommand extends CommandBase {

    @Override
    public String getCommandName() {
        return "hfbw";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/hfbw toggle|lvl <original> <target>";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (!(sender instanceof EntityPlayer)) return;
        if (args.length == 0) {
            PlayerUtil.addMessage(EnumChatFormatting.YELLOW + "Usage: " + getCommandUsage(sender));
            return;
        }

        String sub = args[0].toLowerCase();

        switch (sub) {
            case "toggle": {
                HyFake.config.setBedwarsEnabled(!HyFake.config.isBedwarsEnabled());
                boolean isEnabled = HyFake.config.isBedwarsEnabled();
                String status = isEnabled ? EnumChatFormatting.GREEN + "enabled" : EnumChatFormatting.RED + "disabled";
                PlayerUtil.addMessage(EnumChatFormatting.GOLD + "HyFake Bedwars module " + status + EnumChatFormatting.GOLD + ".");
                HyFake.saveConfig();
                break;
            }
            case "lvl": {
                if (args.length >= 3) {
                    HyFake.config.setBedwarsOriginalLevel(Integer.parseInt(args[1]));
                    HyFake.config.setBedwarsTargetLevel(Integer.parseInt(args[2]));
                    PlayerUtil.addMessage(EnumChatFormatting.GREEN + "Faked Bedwars Level From " + args[1] + " to " + args[2]);
                    PlayerUtil.addMessage(EnumChatFormatting.RESET + "Now " + BedwarsUtil.getBedwarsStar(Integer.parseInt(args[1])));
                    HyFake.saveConfig();
                }
                break;
            }
            default: {
                PlayerUtil.addMessage(EnumChatFormatting.RED + "Unknown argument: " + sub);
                PlayerUtil.addMessage(EnumChatFormatting.YELLOW + "Usage: " + getCommandUsage(sender));
            }
        }
    }

}
