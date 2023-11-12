package com.pocket.main;

/**
 * @author Pocket
 */

public enum FightLogEnum {

    // 五连胜
    FIVE_WINNING_STREAK(1,1,1,1,1,1),
    // 五连败
    FIVE_LOSING_STREAK(0,0,0,0,0,0);

    private final FightLog log;

    FightLogEnum(int... records) {
        log = new FightLog(records);
    }

    FightLog getFightLog(){
        return log;
    }
}
