package mainpack;

public class Person {
    private String fullName;
    private String phone;
    private String pesel;

    public Person(String fullName, String phone, String pesel) {
        this.fullName = fullName;
        this.phone = phone;
        this.pesel = pesel;
    }

    public Person(String name, String surname, String phone, String pesel) {
        this.fullName = name + " " + surname;
        this.phone = phone;
        this.pesel = pesel;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getSurname() {
        return fullName.split(" ")[1];
    }

    public String getName() {
        return fullName.split(" ")[0];
    }
}