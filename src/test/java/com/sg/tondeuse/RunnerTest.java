package com.sg.tondeuse;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import java.net.URISyntaxException;

@SpringBootTest
@ExtendWith(OutputCaptureExtension.class)
class RunnerTest {
    @Autowired
    private Runner runner;

    @Test
    public void should_run_main_and_print_on_console(CapturedOutput capturedOutput) throws URISyntaxException {

        String[] args = {};
        runner.run(args);

        Assertions.assertTrue(
                capturedOutput.getOut().contains("com.sg.tondeuse.Runner                   : 1 3 N 5 1 E ")
        );
    }
}
