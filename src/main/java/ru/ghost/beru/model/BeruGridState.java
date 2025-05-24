package ru.ghost.beru.model;

import lombok.Getter;

@Getter
public class BeruGridState {

    private final int gridSizeX = 40;
    private final int gridSizeY = 100;
    private final int limit = 25;
    private int antX = 1000;
    private int antY = 1000;

    public boolean isAccessible(int x, int y) {
        return sumDigits(Math.abs(x)) + sumDigits(Math.abs(y)) <= limit;
    }

    private int sumDigits(int n) {
        int s = 0;
        while (n > 0) {
            s += n % 10;
            n /= 10;
        }
        return s;
    }

    public synchronized int getCellX(int row) { return antX - gridSizeX / 2 + row; }
    public synchronized int getCellY(int col) { return antY - gridSizeY / 2 + col; }

    public synchronized void moveUp() { if (isAccessible(antX - 1, antY)) antX--; }
    public synchronized void moveDown() { if (isAccessible(antX + 1, antY)) antX++; }
    public synchronized void moveLeft() { if (isAccessible(antX, antY - 1)) antY--; }
    public synchronized void moveRight() { if (isAccessible(antX, antY + 1)) antY++; }
}
