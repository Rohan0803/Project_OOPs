package rohan.myappcompany.e_laundrysystemapp.Models;

public class Users {
    private String Name,Password,Phone;
    private String state,City,pincode,Houseno;

    private Users(){

    }

    public Users(String Name,String Phone,String Password) {
        this.Name = Name;
        this.Phone =Phone;
        this.Password = Password;
    }

    public Users(String state, String City, String pincode, String Houseno){
        this.state = state;
        this.City = City;
        this.pincode = pincode;
        this.Houseno = Houseno;
    }

    public String getHouseno() {
        return Houseno;
    }

    public void setHouseno(String houseno) {
        Houseno = houseno;
    }

    public String getState() {
        return state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public void setState(String state) {
        this.state = state;
    }



    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
