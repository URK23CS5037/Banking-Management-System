package model;

public class Customer {

    private int customerId;
    private String name;
    private String phone;
    private String email;
    private String aadhaar;

    // Default Constructor
    public Customer() {
    }

    // Parameterized Constructor
    public Customer(String name, String phone,
                    String email, String aadhaar) {

        this.name = name;
        this.phone = phone;
        this.email = email;
        this.aadhaar = aadhaar;
    }

    // Getters and Setters

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAadhaar() {
        return aadhaar;
    }

    public void setAadhaar(String aadhaar) {
        this.aadhaar = aadhaar;
    }
}
