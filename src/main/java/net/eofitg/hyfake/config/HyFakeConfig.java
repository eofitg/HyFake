package net.eofitg.hyfake.config;

public class HyFakeConfig {

    private boolean enabled = true;
    private String rank = "";
    private String guildTag = "";
    private boolean bedwarsEnabled = true;
    private int bedwarsOriginalLevel = -1;
    private int bedwarsTargetLevel = -1;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getGuildTag() {
        return guildTag;
    }

    public void setGuildTag(String guildTag) {
        this.guildTag = guildTag;
    }

    public boolean isBedwarsEnabled() {
        return bedwarsEnabled;
    }

    public void setBedwarsEnabled(boolean bedwarsEnabled) {
        this.bedwarsEnabled = bedwarsEnabled;
    }

    public int getBedwarsOriginalLevel() {
        return bedwarsOriginalLevel;
    }

    public void setBedwarsOriginalLevel(int bedwarsOriginalLevel) {
        this.bedwarsOriginalLevel = bedwarsOriginalLevel;
    }

    public int getBedwarsTargetLevel() {
        return bedwarsTargetLevel;
    }

    public void setBedwarsTargetLevel(int bedwarsTargetLevel) {
        this.bedwarsTargetLevel = bedwarsTargetLevel;
    }

}