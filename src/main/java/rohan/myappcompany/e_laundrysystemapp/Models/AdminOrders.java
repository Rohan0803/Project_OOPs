package rohan.myappcompany.e_laundrysystemapp.Models;

public class AdminOrders {

    String City,Houseno,Name,Orders,Phone,Time,Total_Amount,date,pincode,state,status;

    public AdminOrders() {

    }

    public AdminOrders(String city, String houseno, String name, String orders, String phone, String time, String total_Amount, String date, String pincode, String state, String status) {
        City = city;
        Houseno = houseno;
        Name = name;
        Orders = orders;
        Phone = phone;
        Time = time;
        Total_Amount = total_Amount;
        this.date = date;
        this.pincode = pincode;
        this.state = state;
        this.status = status;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getHouseno() {
        return Houseno;
    }

    public void setHouseno(String houseno) {
        Houseno = houseno;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getOrders() {
        return Orders;
    }

    public void setOrders(String orders) {
        Orders = orders;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getTotal_Amount() {
        return Total_Amount;
    }

    public void setTotal_Amount(String total_Amount) {
        Total_Amount = total_Amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
