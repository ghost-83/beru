package ru.ghost.beru.service;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import ru.ghost.beru.model.BeruGridState;
import ru.ghost.beru.type.Step;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class BeruServiceImpl implements BeryService {

    private final ConcurrentHashMap<String, BeruGridState> stateMap = new ConcurrentHashMap<>();

    @NonNull
    @Override
    public String getMap(@NonNull String username) {
        var state = stateMap.computeIfAbsent(username, (s) -> new BeruGridState());
        return toHtml(state);
    }

    @NonNull
    @Override
    public String stepToMap(@NonNull Step step, @NonNull String username) {

        var state = stateMap.computeIfAbsent(username, (s) -> new BeruGridState());

        switch (step) {
            case UP -> state.moveUp();
            case DOWN -> state.moveDown();
            case LEFT -> state.moveLeft();
            case RIGHT -> state.moveRight();
        }

        return toHtml(state);
    }

    @NonNull
    private String toHtml(@NonNull BeruGridState state) {

        StringBuilder sb = new StringBuilder();
        sb.append("<table border='1' style='border-collapse:collapse;'>");

        for (int i = 0; i < state.getGridSizeX(); i++) {
            sb.append("<tr>");

            for (int j = 0; j < state.getGridSizeY(); j++) {

                int x = state.getCellX(i);
                int y = state.getCellY(j);
                String color;

                if (x == state.getAntX() && y == state.getAntY()) color = "lime";
                else if (state.isAccessible(x, y)) color = "white";
                else color = "gray";

                sb.append("<td style='width:0.5rem;height:0.5rem;background:")
                        .append(color)
                        .append(";' title='[")
                        .append(x).append(",")
                        .append(y)
                        .append("]'></td>");

                sb.append("</td>");
            }
            sb.append("</tr>");
        }
        sb.append("</table>");
        return sb.toString();
    }
}
