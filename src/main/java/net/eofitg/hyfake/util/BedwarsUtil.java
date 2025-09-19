package net.eofitg.hyfake.util;

import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.StringUtils;

import java.util.Collection;
import java.util.Objects;

public class BedwarsUtil {

    public static boolean inBedwars() {
        Scoreboard scoreboard = PlayerUtil.getThePlayer().getWorldScoreboard();
        if (scoreboard == null) return false;
        ScoreObjective scoreboardTitle = scoreboard.getObjectiveInDisplaySlot(1);
        if (scoreboardTitle == null) return false;
        String gameFromScoreboard = StringUtil.stripColor(scoreboardTitle.getDisplayName()).replace(" ", "");
        return Objects.equals(gameFromScoreboard, "BEDWARS");
    }

    public static boolean inBedwarsGame() {
        Scoreboard scoreboard = PlayerUtil.getThePlayer().getWorldScoreboard();
        if (scoreboard == null) return false;
        ScoreObjective scoreboardTitle = scoreboard.getObjectiveInDisplaySlot(1);
        if (scoreboardTitle == null) return false;
        String gameFromScoreboard = StringUtil.stripColor(scoreboardTitle.getDisplayName()).replace(" ", "");
        if (!Objects.equals(gameFromScoreboard, "BEDWARS")) return false;

        Collection<Score> scores = scoreboard.getSortedScores(scoreboardTitle);
        for (Score score : scores) {
            String displayText = getScoreboardDisplayText(scoreboard, score);
            String cleanText = StringUtils.stripControlCodes(displayText);
            if (cleanText.contains("YOU")) return true;
        }
        return false;
    }

    private static String getScoreboardDisplayText(Scoreboard scoreboard, Score score) {
        String playerName = score.getPlayerName();
        ScorePlayerTeam team = scoreboard.getPlayersTeam(playerName);
        if (team != null) {
            return team.getColorPrefix() + playerName + team.getColorSuffix();
        } else {
            return playerName;
        }
    }


    /**
     * Get bounded star text. (0 ~ 3099)
     *  "[100✫]" "[1100✪]" "[3000❀]"
     * Credit: <a href="https://hypixel.net/threads/tool-bedwars-prestige-colors-in-minecraft-color-code-and-hex-code-high-effort-post.3841719/">...</a>
     */
    public static String getBedwarsStarUnformatted(int star) {
        /* 0 ~ 1099 */
        if (star < 1100) return "[" + star + "✫]";
        /* 1100 ~ 2099 */
        if (star < 2100) return "[" + star + "✪]";
        /* 2100 ~ 3099 */
        if (star < 3100) return "[" + star + "❀]";

        return "[" + star + "✫]";
    }

    /**
     * Get colored star text. (0 ~ 3099)
     * Credit: <a href="https://hypixel.net/threads/tool-bedwars-prestige-colors-in-minecraft-color-code-and-hex-code-high-effort-post.3841719/">...</a>
     */
    public static String getColoredBedwarsStar(int star) {
        /* 0 ~ 999 */
        if (star < 100) return "§7" + star + "✫";
        if (star < 200) return "§f" + star + "✫";
        if (star < 300) return "§6" + star + "✫";
        if (star < 400) return "§b" + star + "✫";
        if (star < 500) return "§2" + star + "✫";
        if (star < 600) return "§3" + star + "✫";
        if (star < 700) return "§4" + star + "✫";
        if (star < 800) return "§d" + star + "✫";
        if (star < 900) return "§9" + star + "✫";
        if (star < 1000) return "§5" + star + "✫";

        /* 1000 ~ 1999 */
        String s = Integer.toString(star);
        if (star < 1100) return "§61§e0§a" + s.charAt(2) + "§b" + s.charAt(3) + "§d✫";
        if (star < 1200) return "§f" + s + "§7✪";
        if (star < 1300) return "§e" + s + "§6✪";
        if (star < 1400) return "§b" + s + "§3✪";
        if (star < 1500) return "§a" + s + "§2✪";
        if (star < 1600) return "§3" + s + "§9✪";
        if (star < 1700) return "§c" + s + "§4✪";
        if (star < 1800) return "§d" + s + "§5✪";
        if (star < 1900) return "§9" + s + "§1✪";
        if (star < 2000) return "§5" + s + "§8✪";

        /* 2000 ~ 2999 */
        if (star < 2100) return "§72§f0" + s.charAt(2) + "§7" + s.charAt(3) + "✪";
        if (star < 2200) return "§f2§e1" + s.charAt(2) + "§6" + s.charAt(3) + "❀";
        if (star < 2300) return "§62§f2" + s.charAt(2) + "§b" + s.charAt(3) + "§3❀";
        if (star < 2400) return "§52§d3" + s.charAt(2) + "§6" + s.charAt(3) + "§e❀";
        if (star < 2500) return "§b2§f4" + s.charAt(2) + "§7" + s.charAt(3) + "❀";
        if (star < 2600) return "§f2§a5" + s.charAt(2) + "§2" + s.charAt(3) + "❀";
        if (star < 2700) return "§42§c6" + s.charAt(2) + "§d" + s.charAt(3) + "❀";
        if (star < 2800) return "§e2§f7" + s.charAt(2) + "§8" + s.charAt(3) + "❀";
        if (star < 2900) return "§a2§28" + s.charAt(2) + "§6" + s.charAt(3) + "❀";
        if (star < 3000) return "§b2§39" + s.charAt(2) + "§9" + s.charAt(3) + "❀";

        /* 3000 ~ 3099 */
        if (star < 3100) return "§e3§60" + s.charAt(2) + "§c" + s.charAt(3) + "❀";

        return "§7" + star + "✫";
    }

    /**
     * Get colored and bounded star text. (0 ~ 3099)
     *  "§7[100✫]" "§c[§61§e0§a0§b0§d✫§5]"
     * Credit: <a href="https://hypixel.net/threads/tool-bedwars-prestige-colors-in-minecraft-color-code-and-hex-code-high-effort-post.3841719/">...</a>
     */
    public static String getBedwarsStar(int star) {
        /* 0 ~ 999 */
        if (star < 100) return "§7[" + star + "✫]";
        if (star < 200) return "§f[" + star + "✫]";
        if (star < 300) return "§6[" + star + "✫]";
        if (star < 400) return "§b[" + star + "✫]";
        if (star < 500) return "§2[" + star + "✫]";
        if (star < 600) return "§3[" + star + "✫]";
        if (star < 700) return "§4[" + star + "✫]";
        if (star < 800) return "§d[" + star + "✫]";
        if (star < 900) return "§9[" + star + "✫]";
        if (star < 1000) return "§5[" + star + "✫]";

        /* 1000 ~ 1999 */
        String s = Integer.toString(star);
        if (star < 1100) return "§c[§61§e0§a" + s.charAt(2) + "§b" + s.charAt(3) + "§d✫§5]";
        if (star < 1200) return "§7[§f" + s + "§7✪]";
        if (star < 1300) return "§7[§e" + s + "§6✪§7]";
        if (star < 1400) return "§7[§b" + s + "§3✪§7]";
        if (star < 1500) return "§7[§a" + s + "§2✪§7]";
        if (star < 1600) return "§7[§3" + s + "§9✪§7]";
        if (star < 1700) return "§7[§c" + s + "§4✪§7]";
        if (star < 1800) return "§7[§d" + s + "§5✪§7]";
        if (star < 1900) return "§7[§9" + s + "§1✪§7]";
        if (star < 2000) return "§7[§5" + s + "§8✪§7]";

        /* 2000 ~ 2999 */
        if (star < 2100) return "§8[§72§f0" + s.charAt(2) + "§7" + s.charAt(3) + "✪§8]";
        if (star < 2200) return "§f[2§e1" + s.charAt(2) + "§6" + s.charAt(3) + "❀]";
        if (star < 2300) return "§6[2§f2" + s.charAt(2) + "§b" + s.charAt(3) + "§3❀]";
        if (star < 2400) return "§5[2§d3" + s.charAt(2) + "§6" + s.charAt(3) + "§e❀]";
        if (star < 2500) return "§b[2§f4" + s.charAt(2) + "§7" + s.charAt(3) + "❀§8]";
        if (star < 2600) return "§f[2§a5" + s.charAt(2) + "§2" + s.charAt(3) + "❀]";
        if (star < 2700) return "§4[2§c6" + s.charAt(2) + "§d" + s.charAt(3) + "❀§5]";
        if (star < 2800) return "§e[2§f7" + s.charAt(2) + "§8" + s.charAt(3) + "❀]";
        if (star < 2900) return "§a[2§28" + s.charAt(2) + "§6" + s.charAt(3) + "❀§e]";
        if (star < 3000) return "§b[2§39" + s.charAt(2) + "§9" + s.charAt(3) + "❀§1]";

        /* 3000 ~ 3099 */
        if (star < 3100) return "§e[3§60" + s.charAt(2) + "§c" + s.charAt(3) + "❀§4]";

        return "[" + star + "✫]";
    }

}
