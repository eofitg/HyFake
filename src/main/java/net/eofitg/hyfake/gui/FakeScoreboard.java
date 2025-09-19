package net.eofitg.hyfake.gui;

import net.eofitg.hyfake.HyFake;
import net.eofitg.hyfake.util.BedwarsUtil;
import net.eofitg.hyfake.util.PlayerUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.EnumChatFormatting;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")  // class was called via asm
public class FakeScoreboard extends Gui {

    public static FakeScoreboard INSTANCE;
    private final Minecraft mc = Minecraft.getMinecraft();

    public FakeScoreboard() {
        INSTANCE = this;
    }

    public void render(ScoreObjective objective, ScaledResolution res) {
        final int orgStar = HyFake.config.getBedwarsOriginalLevel();
        final int tgtStar = HyFake.config.getBedwarsTargetLevel();
        boolean flag = HyFake.config.isEnabled() && HyFake.config.isBedwarsEnabled() && BedwarsUtil.inBedwars();

        if (orgStar == -1 || tgtStar == -1) {
            PlayerUtil.addMessage(EnumChatFormatting.RED + "Some parameters are missing.");
            flag = false;
        }

        final String orgColoredStarText = BedwarsUtil.getColoredBedwarsStar(orgStar);
        final String tgtColoredStarText = BedwarsUtil.getColoredBedwarsStar(tgtStar);

        Scoreboard scoreboard = objective.getScoreboard();
        Collection<Score> scores = scoreboard.getSortedScores(objective);

        List<Score> list = scores.stream()
                .filter(s -> s.getPlayerName() != null && !s.getPlayerName().startsWith("#"))
                .limit(15)
                .collect(Collectors.toList());

        FontRenderer fr = mc.fontRendererObj;
        int maxWidth = fr.getStringWidth(objective.getDisplayName());

        List<String> names = new ArrayList<>();
        List<String> scoreStrs = new ArrayList<>();

        for (Score score : list) {
            ScorePlayerTeam team = scoreboard.getPlayersTeam(score.getPlayerName());
            String name = ScorePlayerTeam.formatPlayerName(team, score.getPlayerName());

            if (flag && name.contains(orgColoredStarText)) {
                name = name.replace(orgColoredStarText, tgtColoredStarText);
            }

            String scoreStr = Integer.toString(score.getScorePoints());
            maxWidth = Math.max(maxWidth, fr.getStringWidth(name) + fr.getStringWidth(" " + scoreStr));

            names.add(name);
            scoreStrs.add(scoreStr);
        }

        int totalHeight = list.size() * fr.FONT_HEIGHT;
        int startY = res.getScaledHeight() / 2 + totalHeight / 3;
        int xRight = res.getScaledWidth() - 3;
        int left = xRight - maxWidth;
        for (int i = 0; i < list.size(); i++) {
            int y = startY - (i + 1) * fr.FONT_HEIGHT;
            Gui.drawRect(left - 2, y, xRight, y + fr.FONT_HEIGHT, 0x50000000);
            String name = names.get(i);
            String scoreStr = EnumChatFormatting.RED + scoreStrs.get(i);

            fr.drawString(name, left, y, 0x20FFFFFF);
            fr.drawString(scoreStr, xRight - fr.getStringWidth(scoreStr), y, 0x20FFFFFF);
        }

        int titleY = startY - totalHeight - fr.FONT_HEIGHT;
        Gui.drawRect(left - 2, titleY, xRight, titleY + fr.FONT_HEIGHT, 0x60000000);
        String title = objective.getDisplayName();
        int titleX = left + (maxWidth - fr.getStringWidth(title)) / 2;
        fr.drawString(title, titleX, titleY, 0x20FFFFFF);
    }

}
