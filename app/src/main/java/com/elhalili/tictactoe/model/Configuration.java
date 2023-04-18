package com.elhalili.tictactoe.model;

public class Configuration {
    private String plan;
    private String opponent;
    private boolean soundMuted;

    public Configuration(String plan, String opponent, boolean soundMuted) {
        this.plan = plan;
        this.opponent = opponent;
        this.soundMuted = soundMuted;
    }

    public Configuration() {}

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getOpponent() {
        return opponent;
    }

    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }

    public boolean isSoundMuted() {
        return soundMuted;
    }

    public void setSoundMuted(boolean soundMuted) {
        this.soundMuted = soundMuted;
    }
}
