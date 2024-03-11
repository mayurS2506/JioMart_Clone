package DTO;

public class CustomerDto {
private int cust_id;

    public int getCust_id() {
        return cust_id;
    }

    public void setCust_id(int cust_id) {
        this.cust_id = cust_id;
    }

    public String getCust_name() {
        return cust_name;
    }

    public void setCust_name(String cust_name) {
        this.cust_name = cust_name;
    }

    public String getCust_contact_no() {
        return cust_contact_no;
    }

    public void setCust_contact_no(String cust_contact_no) {
        this.cust_contact_no = cust_contact_no;
    }

    public String getCust_address() {
        return cust_address;
    }

    public void setCust_address(String cust_address) {
        this.cust_address = cust_address;
    }

    public String getCust_email() {
        return cust_email;
    }

    public void setCust_email(String cust_email) {
        this.cust_email = cust_email;
    }

    public String getCust_pass() {
        return cust_pass;
    }

    public void setCust_pass(String cust_pass) {
        this.cust_pass = cust_pass;
    }

    private  String cust_name;
private  String cust_contact_no;
private  String cust_address;
private String cust_email;
private  String cust_pass;
}
