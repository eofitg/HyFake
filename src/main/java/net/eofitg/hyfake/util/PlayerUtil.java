package net.eofitg.hyfake.util;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class PlayerUtil {

    private static final Minecraft mc = Minecraft.getMinecraft();

    public static void addMessage(String msg) {
        mc.thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "[HyFake] " + msg));
    }

    public static EntityPlayer getThePlayer() {
        return mc.thePlayer;
    }

}
