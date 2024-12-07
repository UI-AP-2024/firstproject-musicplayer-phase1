package controller.command.process;

public class ExitProcess implements Process {
    @Override
    public String exec(final String[] commands) {
        return "exit";
    }
}
