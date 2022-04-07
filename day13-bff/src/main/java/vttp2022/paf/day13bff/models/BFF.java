package vttp2022.paf.day13bff.models;

import java.util.Date;

public class BFF {

    private String email;
    private String name;
    private String phone;
    private String status;
    private String passphrase;
    private Date dob;

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getPassphrase() { return passphrase; }
    public void setPassphrase(String passphrase) { this.passphrase = passphrase; }

    public Date getDob() { return dob; }
    public void setDob(Date dob) { this.dob = dob; }

    @Override
    public String toString() {
        return "BFF [dob=" + dob + ", email=" + email + ", name=" + name + ", passphrase=" + passphrase + ", phone="
                + phone + ", status=" + status + "]";
    }
}
