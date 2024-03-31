package model.AccountUser;
import java.util.Date;

public abstract class AccountUser {
        private String userName;
        private String password;
        private String fullName;
        private String email;
        private String phoneNumber;
        private Date birthDate;

    //*********************************************

    public AccountUser(String userName, String password, String fullName, String email, String phoneNumber, Date birthDate) {
            this.userName = userName;
            this.password = password;
            this.fullName = fullName;
            this.email = email;
            this.phoneNumber = phoneNumber;
            this.birthDate = birthDate;
        }

    //*********************************************

    public String getUserName() {
            return userName;
        }

        public String getPassword() {
            return password;
        }

        public String getFullName() {
            return fullName;
        }

        public String getEmail() {
            return email;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public Date getBirthDate() {
            return birthDate;
        }

    //*********************************************

    public void setUserName(String userName){
        this.userName = userName;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public void setFullName(String fullName){
        this.fullName = fullName;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setUserName(Date birthDate ){
        this.birthDate = birthDate;
    }

    //*********************************************
    @Override
        public String toString() {
            return "UserAccount{" +
                    "username='" + userName + '\'' +
                    ", password='" + password + '\'' +
                    ", fullName='" + fullName + '\'' +
                    ", email='" + email + '\'' +
                    ", phoneNumber='" + phoneNumber + '\'' +
                    ", birthDate=" + birthDate +
                    '}';
        }

    }

