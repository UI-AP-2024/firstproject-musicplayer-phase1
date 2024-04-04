package controller.command.process.signup;

import controller.SingerController;
import controller.command.process.Process;
import model.exception.ProcessException;
import model.model.user.Singer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SignupSingerProcess implements Process {

    private final SingerController singerController;

    public SignupSingerProcess() {
        singerController = new SingerController();
    }

    @Override
    public String exec(final String[] commands) throws ProcessException {
        final Singer singer = new Singer();
        try {
            singer.setUsername(getValue(commands[2]));
            singer.setPassword(getValue(commands[3]));
            singer.setFirstname(getValue(commands[4]));
            singer.setLastname(getValue(commands[5]));
            singer.setEmail(getValue(commands[6]));
            singer.setPhoneNumber(getValue(commands[7]));
            singer.setDateOfBirth(LocalDate.parse(getValue(commands[8]), DateTimeFormatter.ofPattern("yyyyMMdd")));
            singer.setBio(getValue(commands[9]));
        } catch (Exception e) {
            if (e instanceof ProcessException) throw e;
            else {
                throw new ProcessException(this, "Invalid param: " + e.getMessage());
            }
        }

        try {
            singerController.saveSigner(singer);
            return "Successfully added new signer";
        } catch (Exception e) {
            throw new ProcessException(this, e.getMessage());
        }
    }
}
