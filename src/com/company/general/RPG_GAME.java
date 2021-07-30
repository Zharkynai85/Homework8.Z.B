package com.company.general;

import com.company.players.*;

public class RPG_GAME {
    public static void start() {

        Boss boss = new Boss(450,50);
        Magic magic = new Magic(250,10,5);
        Medic medic = new Medic(250,10,10);
        Tank tank = new Tank(250,10,50);
        Warrior warrior = new Warrior(250,10,5);
        Medic youngMedic = new Medic(350, 10, 5);

        Hero[] heroes = {warrior, tank, magic, medic, youngMedic};


        printStatistic(boss,heroes);
        while (!isFinished(boss,heroes)){
            round(boss,heroes);
        }
    }

    private static void round(Boss boss, Hero[] heroes){
        BossHit(boss,heroes);
        herosHit(boss,heroes);
        heroesApplySuperAlbilities(boss,heroes);
        printStatistic(boss,heroes);
    }

    private static void printStatistic(Boss boss, Hero[]heroes){
        System.out.println("_______________ статистика:__________________");
        System.out.println("Boss Health: "+ boss.getHealth()+ " [" + boss.getDamage() + "]\n" );
        for (int i = 0; i < heroes.length; i++) {
            //    System.out.println("Hero Health: "+ heroes[i].getHealth());
            System.out.println(heroes[i].getClass().getSimpleName()+" health: "
                    +heroes[i].getHealth()+ " [" + heroes[i].getDamage() + "]");
        }
        System.out.println("_________________Итоги:_____________________");

    }

    private static  boolean isFinished(Boss boss,Hero[] heroes){
        if (boss.getHealth() <= 0){
            System.out.println("Heroes won!");
            return true;
        }
        boolean allHeroesDied = true;
        for (int i = 0; i < heroes.length; i++) {
            if (heroes[i].getHealth() > 0) {
                allHeroesDied = false;
                break;
            }
        }
        if (allHeroesDied){
            System.out.println("boss won");
        }
        return allHeroesDied;

    }
    private static void BossHit(Boss boss, Hero[] heroes){
        for (int i = 0; i < heroes.length; i++) {
            if (heroes[i].getHealth() > 0 && boss.getHealth() > 0){
                if (heroes[i].getHealth() - boss.getDamage() < 0){
                    heroes[i].setHealth(0);
                }else {
                    heroes[i].setHealth(heroes[i].getHealth() - boss.getDamage());
                }
            }
        }
    }
    private static void herosHit(Boss boss, Hero [] heroes){
        for (int i = 0; i < heroes.length; i++) {
            if (heroes[i].getHealth() > 0 && boss.getHealth() > 0){
                if (boss.getHealth() - heroes[i].getDamage() < 0){
                    boss.setHealth(0);
                }else {
                    boss.setHealth(boss.getHealth() - heroes[i].getDamage());
                }
            }

        }
    }
    private static void heroesApplySuperAlbilities(Boss boss,Hero[] heroes){
        for (int i = 0; i < heroes.length; i++) {
            heroes[i].applySuperAbility(boss,heroes);
        }
    }
}
