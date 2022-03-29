package Task3;

public class Patient {
    private String FirstName;
    private String SurName;
    private int Age;
    private String City;
    private String NIC_PassPortNumber;


    public Patient(){

    }
    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getSurName() {
        return SurName;
    }

    public void setSurName(String surName) {
        SurName = surName;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getNIC_PassPortNumber() {
        return NIC_PassPortNumber;
    }

    public void setNIC_PassPortNumber(String NIC_PassPortNumber) {
        this.NIC_PassPortNumber = NIC_PassPortNumber;
    }


}
