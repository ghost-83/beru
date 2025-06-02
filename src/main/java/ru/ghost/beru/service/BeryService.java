package ru.ghost.beru.service;

import org.springframework.lang.NonNull;
import ru.ghost.beru.type.Step;

public interface BeryService {

    @NonNull
    String getMap(@NonNull String username);

    @NonNull
    String stepToMap(@NonNull Step step, @NonNull String username);

    @NonNull
    Integer getMaxStep(@NonNull String username);
}
