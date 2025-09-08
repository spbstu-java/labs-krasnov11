package ru.spbstu.edu.krasnov2.lab1;

public class Hero {

    private final String _name;
    private HeroMove _moveStrategy;
    private int _xPos;
    private int _yPos;

    public Hero(String name, int xPos, int yPos, HeroMove moveStrategy){
        setMoveStrategy(moveStrategy);
        _name = name;
        _xPos = xPos;
        _yPos = yPos;
    }

    public void setMoveStrategy(HeroMove moveStrategy){
        if (moveStrategy == null)
            throw new IllegalArgumentException("moveStrategy is null");
        _moveStrategy = moveStrategy;
    }

    public void move(int x, int y){
        _moveStrategy.move(this, x, y);
    }

    public String getName() {
        return _name;
    }

    public int getXPos() {
        return _xPos;
    }

    public void setXPos(int xPos) {
        this._xPos = xPos;
    }

    public int getYPos() {
        return _yPos;
    }

    public void setYPos(int yPos) {
        this._yPos = yPos;
    }
}
