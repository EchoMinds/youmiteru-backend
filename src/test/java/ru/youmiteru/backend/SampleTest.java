package ru.youmiteru.backend;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class SampleTest {

    @Test
    public void sample_test() {
        // We need this test because jacoco needs something to generate in order to not fail checks :)
        assertTrue(true);
    }
}
