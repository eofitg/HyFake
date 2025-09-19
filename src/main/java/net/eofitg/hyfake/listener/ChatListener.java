package net.eofitg.hyfake.listener;

import net.eofitg.hyfake.HyFake;
import net.eofitg.hyfake.util.BedwarsUtil;
import net.eofitg.hyfake.util.PlayerUtil;
import net.eofitg.hyfake.util.StringUtil;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Objects;

public class ChatListener {

    @SubscribeEvent
    public void onChatMessage(ClientChatReceivedEvent event) {
        if (!HyFake.config.isEnabled()) return;

        final String formattedText = event.message.getFormattedText();
        final String pureText = StringUtil.removeFormattingCodes(event.message.getUnformattedText());
        String newText = formattedText;

        if (HyFake.config.isBedwarsEnabled()) {
            if (!BedwarsUtil.inBedwars()) return;

            final int orgStar = HyFake.config.getBedwarsOriginalLevel();
            final int tgtStar = HyFake.config.getBedwarsTargetLevel();

            if (orgStar == -1 || tgtStar == -1) {
                PlayerUtil.addMessage(EnumChatFormatting.RED + "Some parameters are missing.");
                return;
            }

            final String orgStarTextUnformatted = BedwarsUtil.getBedwarsStarUnformatted(orgStar);
            final String orgStarText = BedwarsUtil.getBedwarsStar(orgStar);
            final String tgtStarText = BedwarsUtil.getBedwarsStar(tgtStar);

            final String check_0 = orgStarTextUnformatted + " " + PlayerUtil.getThePlayer().getName();
            final String check_1 = orgStarTextUnformatted + " [" + HyFake.config.getRank() + "] " + PlayerUtil.getThePlayer().getName();

            if (pureText.contains(check_0) || pureText.contains(check_1)) {
                newText = newText.replace(orgStarText, tgtStarText);
            }
        }

        if (!Objects.equals(newText, formattedText)) {
            event.message = new ChatComponentText(newText);
        }
    }

}
