package ru.ghost.beru.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.ghost.beru.service.BeryService;
import ru.ghost.beru.type.Step;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BeruController {

    private final BeryService beryService;

    @GetMapping("/get-matrix")
    public String getAntGrid(@AuthenticationPrincipal UserDetails user) {
        return beryService.getMap(user.getUsername());
    }

    @PostMapping("/step")
    public String getAntGrid(@RequestBody Step step, @AuthenticationPrincipal UserDetails user) {
        return beryService.stepToMap(step, user.getUsername());
    }
}
