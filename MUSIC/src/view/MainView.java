package view;

import controller.command.CommandController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainView {

    private CommandController commandController;

    public void runView() {

        commandController = new CommandController();

        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        final ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute(() -> {
            while (true) {
                try {
                    System.out.print("$ ");
                    final String line = reader.readLine();
                    if (line != null && !handler(line)) {
                        break;
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            executorService.shutdown();
        });

    }

    private boolean handler(final String line) {
        try {
            final String result = commandController.runCommand(line);
            if (result.equalsIgnoreCase("exit")) {
                return false;
            }
            print(result);
        } catch (Exception e) {
            print(e.getMessage());
        }

        return true;
    }

    private void print(final String val) {
        System.out.printf("\n-------\n%s\n-------\n", val);
    }

}
