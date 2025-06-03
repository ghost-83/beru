package ru.ghost.beru.model;

import lombok.Getter;
import org.springframework.lang.NonNull;
import ru.ghost.beru.type.Step;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

@Getter
public class BeruGridState {

    private final int gridSizeX = 40;
    private final int gridSizeY = 100;
    private final int limit;
    private int antX;
    private int antY;
    private int maxSteps = 0;

    public BeruGridState(int antX, int antY, int limit) {
        this.antX = antX;
        this.antY = antY;
        this.limit = limit;
    }

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

    public synchronized int getCellX(int row) {
        return antX - gridSizeX / 2 + row;
    }

    public synchronized int getCellY(int col) {
        return antY - gridSizeY / 2 + col;
    }

    public synchronized void move(@NonNull Step step) {
        int newX = antX + step.dx;
        int newY = antY + step.dy;
        if (isAccessible(newX, newY)) {
            antX = newX;
            antY = newY;
        }
    }

    public void countReachableCells() {

        Queue<Point> queue = new LinkedList<>();
        Set<Point> visited = new HashSet<>();

        Point start = new Point(antX, antY);
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {

            Point point = queue.poll();

            for (Step step : Step.values()) {

                int newX = point.x + step.dx;
                int newY = point.y + step.dy;
                Point neighbor = new Point(newX, newY);

                if (!visited.contains(neighbor) && isAccessible(newX, newY)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        maxSteps = visited.size();
    }

    record Point(int x, int y) {
    }
}

