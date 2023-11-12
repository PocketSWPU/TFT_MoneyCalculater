package com.pocket.main;

/**
 * @author Pocket
 */
public class Player {
    final int STREAK_TWO = 2;
    final int STREAK_THREE = 3;
    final int STREAK_FOUR = 4;
    final int STREAK_FIVE = 5;
    private int money;
    private int streak;

    Player(int initMoney){
        this.money = initMoney;
        this.streak = 0;
    }

    /**
     * 通过战斗记录计算金币
     * @param fightLog 战斗记录
     */
    public void calculateByLog(FightLog fightLog){
        boolean[] fightLogArr = fightLog.getLog();
        boolean preLog = fightLogArr[0];
        for(boolean fight: fightLogArr){
            streak = fight == preLog ? ++streak : 1;
            preLog = fight;
            nextRound(fight);
        }
    }

    public void calculateByLog(FightLog... fightLog){
        for(FightLog log: fightLog){
            calculateByLog(log);
        }
    }


    /**
     * 计算下回合拥有的金币
     * @param result 战局结果
     */
    private void nextRound(boolean result){
        // 胜利+1
        money += result ? 1 : 0;

        // 利息
        money += Math.min(money / 10, 5);

        // 连胜 + 基础
        money += calculateGoldBasedOnStreak(streak) + 5;

        System.out.print(money + "->");
    }

    /**
     * 根据连胜/连败数获取额外金币
     * @param currentStreak 当前连败数
     * @return 额外金币
     */
    private int calculateGoldBasedOnStreak(int currentStreak){
        /*
        规则:
        2-3连胜 - 1金币
        4连胜 - 2金币
        5连胜 - 3金币
         */
        return switch (Math.min(currentStreak, STREAK_FIVE)){
            case STREAK_TWO,STREAK_THREE -> 1;
            case STREAK_FOUR -> 2;
            case STREAK_FIVE -> 3;
            default -> 0;
        };
//        if (currentStreak == STREAK_TWO || currentStreak == STREAK_THREE) {
//            return 1;
//        }
//        if (currentStreak == STREAK_FOUR){
//            return 2;
//        }
//        if (currentStreak > STREAK_FOUR){
//            return 3;
//        }
//        return 0;
    }

    private int getStage(int rounds){
        // 规则： P P P P P E
        return rounds / 6;
    }

    public void showLog(){

    }

    public static void main(String[] args) {
        FightLog fightLogAllWin = new FightLog(0, 0, 0, 0, 1, 0, 0, 0);
        FightLog fightLogAllLose = new FightLog(0, 0, 0, 0, 0, 0, 0, 0);
        Player p1 = new Player(10);
        Player p2 = new Player(10);
        p1.calculateByLog(fightLogAllWin);

        System.out.println(p1.money);
        System.out.println();
        p2.calculateByLog(fightLogAllLose);
        System.out.println(p2.money);
    }
}
