package controller.command;

import controller.command.process.Process;
import model.enums.ProcessName;

import java.util.ArrayList;

public class CommandController {

    public String runCommand(final String command) throws Exception {
        final String[] split = split(command);
        final String processName = split[0];
        final Process process = ProcessName.getProcess(processName);
        if (process == null) {
            throw new Exception(processName + ": command not found");
        }
        return process.exec(split);
    }

    public static String[] split(final String command) {
        ArrayList<String> parts = new ArrayList<>();
        StringBuilder currentPart = new StringBuilder();
        boolean insideQuotes = false;
        for (int i = 0; i < command.length(); i++) {
            final char currentChar = command.charAt(i);
            if (currentChar == '\"') {
                insideQuotes = !insideQuotes;
                continue;
            }
            if (currentChar == ' ' && !insideQuotes) {
                parts.add(currentPart.toString());
                currentPart = new StringBuilder();
            } else {
                currentPart.append(currentChar);
            }
        }
        parts.add(currentPart.toString());
        return parts.toArray(new String[]{});
    }

}
