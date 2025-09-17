package ru.spbstu.edu.krasnov2.lab1;

public class WalkHeroMove implements HeroMove {
    @Override
    public void move(Hero hero, int x, int y) {
        System.out.printf("Hero '%s' walking from (%d, %d) to (%d, %d)%n",
                hero.getName(),
                hero.getXPos(),
                hero.getYPos(),
                x, y);

        hero.setXPos(x);
        hero.setYPos(y);
    }
}
