package com.pocket.main;

/**
 * @author Pocket
 */
public class FightLog {
    private boolean[] log;

    public FightLog(int... records) {
        int n = records.length;
        log = new boolean[n];
        int idx = 0;
        for(int record: records){
            log[idx++] = record == 1;
        }
    }

    public boolean[] getLog() {
        return log;
    }
}
