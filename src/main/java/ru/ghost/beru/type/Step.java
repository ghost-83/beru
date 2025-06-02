package ru.ghost.beru.type;

public enum Step {
    UP(-1, 0),
    DOWN(1, 0),
    LEFT(0, -1),
    RIGHT(0, 1);

    public final int dx, dy;

    Step(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }
}
