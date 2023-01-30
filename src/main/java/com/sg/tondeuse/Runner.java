package com.sg.tondeuse;

import com.sg.tondeuse.service.mowermover.printer.MowerMovementPrinterService;
import com.sg.tondeuse.util.FileUtils;
import com.sg.tondeuse.validator.MowerMovementInstructionFileValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class Runner {
    private static final Logger LOGGER = Logger.getLogger(Runner.class.getName());
    private final MowerMovementInstructionFileValidator mowerMovementInstructionValidator;
    private final MowerMovementPrinterService mowerMovementPrinterService;

    public void run(String[] args) throws URISyntaxException {
        List<String> fileLines;
        if (args == null || args.length == 0) {
            fileLines = FileUtils.readFileLinesFromResource("mower-input.txt");
        } else {
            fileLines = FileUtils.readFileLinesFromDisk(args[0]);
        }
        if (mowerMovementInstructionValidator.test(fileLines)) {
            LOGGER.info(mowerMovementPrinterService.print(fileLines));
        }
    }
}
