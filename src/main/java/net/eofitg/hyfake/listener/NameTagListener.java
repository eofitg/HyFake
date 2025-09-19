package net.eofitg.hyfake.listener;

import net.eofitg.hyfake.HyFake;
import net.eofitg.hyfake.util.BedwarsUtil;
import net.eofitg.hyfake.util.PlayerUtil;
import net.eofitg.hyfake.util.StringUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class NameTagListener {

    private static final Set<String> processedArmorStands = new HashSet<>();

    @SubscribeEvent
    public void onRenderNameTag(RenderLivingEvent.Specials.Pre<EntityLivingBase> event) {
        if (!HyFake.config.isEnabled()) return;

        Entity entity = event.entity;
        if (!(entity instanceof EntityArmorStand)) return;

        String armorStandUuid = entity.getUniqueID().toString() + "|" +  entity.posX + "|" + entity.posY + "|" + entity.posZ;
        if (processedArmorStands.contains(armorStandUuid)) return;

        String formattedText = entity.getDisplayName().getFormattedText();
        String pureText = StringUtil.removeFormattingCodes(entity.getDisplayName().getUnformattedText());
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
            final String orgStarTextUnformatted_2 = BedwarsUtil.getBedwarsStarUnformatted(orgStar - 1);
            final String orgStarText = BedwarsUtil.getBedwarsStar(orgStar);
            final String orgStarText_2 = BedwarsUtil.getBedwarsStar(orgStar - 1);
            final String tgtStarText = BedwarsUtil.getBedwarsStar(tgtStar);
            final String tgtStarText_2 = BedwarsUtil.getBedwarsStar(tgtStar - 1);

            final String check_0 = orgStarTextUnformatted + " " + PlayerUtil.getThePlayer().getName();
            final String check_1 = orgStarTextUnformatted + " [" + HyFake.config.getRank() + "] " + PlayerUtil.getThePlayer().getName();
            final String check_2 = "Your Level: " + orgStarTextUnformatted;
            final String check_3 = PlayerUtil.getThePlayer().getName() + " [" + HyFake.config.getGuildTag() + "] - " + orgStarTextUnformatted;
            final String check_4 = PlayerUtil.getThePlayer().getName() + " [" + HyFake.config.getGuildTag() + "] - " + orgStarTextUnformatted_2;

            if (pureText.contains(check_0) || pureText.contains(check_1) || pureText.contains(check_2) || pureText.contains(check_3)) {
                newText = newText.replace(orgStarText, tgtStarText);
                processedArmorStands.add(armorStandUuid);
            } else if (pureText.contains(check_4)) {
                newText = newText.replace(orgStarText_2, tgtStarText_2);
                processedArmorStands.add(armorStandUuid);
            }
        }

        if (!Objects.equals(newText, formattedText)) {
            entity.setCustomNameTag(newText);
        }
    }

    @SubscribeEvent
    public void onWorldUnload(WorldEvent.Unload event) {
        processedArmorStands.clear();
    }

}