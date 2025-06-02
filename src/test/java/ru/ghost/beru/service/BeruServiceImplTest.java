package ru.ghost.beru.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import ru.ghost.beru.type.Step;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BeruServiceImplTest {

    @Autowired
    private BeryService beruService;

    @Test
    void getMap() throws IOException {

        String expectedGetMap;
        ClassPathResource resource = new ClassPathResource("test-data/expectedGetMap.html");
        try (InputStream inputStream = resource.getInputStream()) {
            expectedGetMap = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        }

        var username = "Beru";
        beruService.getMap(username);

        IntStream.range(0, 100)
                .parallel()
                .forEach(integer -> {
                    beruService.getMap(username);
                    if (integer == 0)
                        beruService.stepToMap(Step.RIGHT, username);
                });

        var actual = beruService.getMap(username);
        assertEquals(expectedGetMap, actual);
    }

    @Test
    void stepToMap() {

        var username1 = "user1";
        var username2 = "user2";
        beruService.getMap(username1);
        beruService.getMap(username2);

        IntStream.range(0, 100)
                .forEach(integer -> beruService.stepToMap(Step.RIGHT, username1));

        IntStream.range(0, 100)
                .parallel()
                .forEach(integer -> beruService.stepToMap(Step.RIGHT, username2));

        var expected = beruService.getMap(username1);
        var actual = beruService.getMap(username2);

        assertEquals(expected, actual);
    }

    @Test
    void getMaxStep() {

        var username = "userMax";

        var testData = beruService.getMaxStep(username);

        assertEquals(148848, testData);
    }
}