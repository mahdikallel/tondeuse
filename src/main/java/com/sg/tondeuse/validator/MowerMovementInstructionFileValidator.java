package com.sg.tondeuse.validator;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Pattern;

@Component
public class MowerMovementInstructionFileValidator {


    public static final String DIGIT_REGEX = "\\d+";
    public static final String ESCAPE = " ";

    public static final String ORIENTATION_REGEX = "[^NEWS]";
    private final Pattern orientation_pattern = Pattern.compile(ORIENTATION_REGEX);

    public static final String MOVEMENT_INSTRUCTIONS_REGEX = "[^GAD]";
    private final Pattern movement_instruction_pattern = Pattern.compile(MOVEMENT_INSTRUCTIONS_REGEX);


    public boolean test(List<String> lines) {
        if(lines.isEmpty()){
            throw new IllegalArgumentException("File empty !");
        }
        boolean lineValidated = validateLawnLine(lines.get(0));
        for (int index = 1; index < lines.size(); index++) {
            if (index % 2 != 0) {
                lineValidated = lineValidated && validateMowerLine(lines.get(index), index);
            } else {
                lineValidated = lineValidated && validateInstructionMovementLine(lines.get(index), index);
            }
        }
        return lineValidated;
    }

    private boolean validateInstructionMovementLine(String instructionMovementLine, int index) {
        boolean instructionMovement = !movement_instruction_pattern.matcher(instructionMovementLine).find();
        if (!instructionMovement) {
            throw new IllegalArgumentException(String.format("invalid instruction movements ! Line %s", index));
        }
        return true;
    }

    private boolean validateMowerLine(String mowerLine, int index) {
        String[] mowerInputs = mowerLine.split(ESCAPE);
        boolean mowerLineValidated = mowerInputs.length == 3 &&
                mowerInputs[0].matches(DIGIT_REGEX) &&
                mowerInputs[1].matches(DIGIT_REGEX) &&
                !orientation_pattern.matcher(mowerInputs[2]).find();
        if (!mowerLineValidated) {
            throw new IllegalArgumentException(String.format("invalid mower inputs ! Line %s", index));
        }
        return true;
    }

    private boolean validateLawnLine(String firstLine) {
        String[] word = firstLine.split(ESCAPE);
        boolean firstLineValidated = word.length == 2 &&
                word[0].matches(DIGIT_REGEX) &&
                word[1].matches(DIGIT_REGEX);
        if (!firstLineValidated) {
            throw new IllegalArgumentException("invalid Lawn position coordinates Line 0!");
        }
        return true;
    }
}
