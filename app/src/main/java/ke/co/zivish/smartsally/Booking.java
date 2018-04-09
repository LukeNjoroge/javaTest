package ke.co.zivish.smartsally;

/**
 * Created by Luke on 8/19/2017.
 */

public class Booking {

    private String booking_no,customer_name,service_name,time;

    public Booking(String booking_no, String customer_name, String service_name, String time){
        this.setBooking_no(booking_no);
        this.setCustomer_name(customer_name);
        this.setService_name(service_name);
        this.setTime(time);
    }

    public String getBooking_no() {
        return booking_no;
    }

    public void setBooking_no(String booking_no) {
        this.booking_no = booking_no;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
