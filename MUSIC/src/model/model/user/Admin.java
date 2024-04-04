package model.model.user;

import java.time.LocalDate;

public class Admin extends User {
    private static Admin admin;

    public static Admin getAdmin() {
        if (admin == null) {
            admin = new Admin();
            admin.setUsername("admin");
            admin.setPassword("admin");
            admin.setFirstname("admin");
            admin.setLastname("admin");
            admin.setEmail("admin@email.com");
            admin.setPhoneNumber("09123456789");
            admin.setDateOfBirth(LocalDate.of(1996, 9, 3));
        }
        return admin;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
