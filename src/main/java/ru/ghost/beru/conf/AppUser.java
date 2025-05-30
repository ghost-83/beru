package ru.ghost.beru.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import ru.ghost.beru.dto.User;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppUser {
    private List<User> users;
}
