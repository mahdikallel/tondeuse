package com.sg.tondeuse.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.util.List;

class FileUtilsTest {

    @Test
    public void should_return_file_lines_as_string_list_when_read_from_class_path() throws URISyntaxException {
        // given


        // when
        List<String> fileLines = FileUtils.readFileLinesFromResource("mower-input-test.txt");

        // then
        Assertions.assertFalse(fileLines.isEmpty());
    }
}
