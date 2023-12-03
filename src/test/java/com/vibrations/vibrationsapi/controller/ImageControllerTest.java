package com.vibrations.vibrationsapi.controller;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ImageControllerTest {

    @Autowired
    private ImageController controller;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }
}
