package net.eofitg.hyfake.command;

import net.eofitg.hyfake.HyFake;
import net.eofitg.hyfake.util.PlayerUtil;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;

public class HyFakeCommand extends CommandBase {

    @Override
    public String getCommandName() {
        return "hyfake";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/hyfake toggle|rank <value>|guildtag <value>";
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
                HyFake.config.setEnabled(!HyFake.config.isEnabled());
                boolean isEnabled = HyFake.config.isEnabled();
                String status = isEnabled ? EnumChatFormatting.GREEN + "enabled" : EnumChatFormatting.RED + "disabled";
                PlayerUtil.addMessage(EnumChatFormatting.GOLD + "Mod " + status + EnumChatFormatting.GOLD + ".");
                HyFake.saveConfig();
                break;
            }
            case "rank": {
                if (args.length >= 2) {
                    HyFake.config.setRank(args[1]);
                    PlayerUtil.addMessage(EnumChatFormatting.GOLD + "Player Rank Set to " + args[1] + ".");
                    HyFake.saveConfig();
                }
                break;
            }
            case "guildtag": {
                if (args.length >= 2) {
                    HyFake.config.setGuildTag(args[1]);
                    PlayerUtil.addMessage(EnumChatFormatting.GOLD + "Player Guild Tag Set to " + args[1] + ".");
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
