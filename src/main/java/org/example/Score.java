package org.example;

public class Score {
    private int placements;
    private int inclusions;

    public Score() {
        this.placements = 0;
        this.inclusions = 0;
    }

    public Score(int placements, int inclusions) {
        this.placements = placements;
        this.inclusions = inclusions;
    }

    public int getPlacements() {
        return placements;
    }

    public void setPlacements(int placements) {
        this.placements = placements;
    }

    public void increasePlacements() {
        this.placements++;
    }

    public int getInclusions() {
        return inclusions;
    }

    public void setInclusions(int inclusions) {
        this.inclusions = inclusions;
    }

    public void increaseInclusions() {
        this.inclusions++;
    }
}
