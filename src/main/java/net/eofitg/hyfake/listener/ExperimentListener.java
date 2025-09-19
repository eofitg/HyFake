package net.eofitg.hyfake.listener;

import net.eofitg.hyfake.HyFake;
import net.eofitg.hyfake.util.BedwarsUtil;
import net.eofitg.hyfake.util.PlayerUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ExperimentListener extends Gui {

    private final Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void onRenderOverlayPre(RenderGameOverlayEvent.Pre event) {
        if (!HyFake.config.isEnabled() || !HyFake.config.isBedwarsEnabled()) return;
        if (!BedwarsUtil.inBedwarsGame()) return;
        if (event.type == RenderGameOverlayEvent.ElementType.EXPERIENCE) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onRenderOverlayPost(RenderGameOverlayEvent.Post event) {
        if (!HyFake.config.isEnabled() || !HyFake.config.isBedwarsEnabled()) return;
        if (!BedwarsUtil.inBedwarsGame()) return;
        if (event.type != RenderGameOverlayEvent.ElementType.ALL) return;
        if (PlayerUtil.getThePlayer() == null || mc.gameSettings.showDebugInfo) return;

        ScaledResolution res = new ScaledResolution(mc);
        int width = res.getScaledWidth();
        int height = res.getScaledHeight();

        int barWidth = 182;
        int barX = (width / 2) - (barWidth / 2);
        int barY = height - 32 + 3;

        mc.getTextureManager().bindTexture(Gui.icons);
        drawTexturedModalRect(barX, barY, 0, 64, barWidth, 5);

        float progress = PlayerUtil.getThePlayer().experience;
        int filled = (int)(progress * (float)(barWidth + 1));
        if (filled > 0) {
            drawTexturedModalRect(barX, barY, 0, 69, filled, 5);
        }

        String levelStr = Integer.toString(HyFake.config.getBedwarsTargetLevel());
        int x = (width - mc.fontRendererObj.getStringWidth(levelStr)) / 2;
        int y = barY - 6;
        mc.fontRendererObj.drawString(levelStr, x + 1, y, 0, false);
        mc.fontRendererObj.drawString(levelStr, x - 1, y, 0, false);
        mc.fontRendererObj.drawString(levelStr, x, y + 1, 0, false);
        mc.fontRendererObj.drawString(levelStr, x, y - 1, 0, false);
        mc.fontRendererObj.drawString(levelStr, x, y, 0x80FF20, false);
    }

}
