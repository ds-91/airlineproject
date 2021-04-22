package airline;

public class ticketC {

    private String id;
    private String flightid;
    private String custid;
    private String classes;
    private String price;
    private String seats;
    private String date;

    public ticketC (String id, String flightid, String custid, String classes, String price, String seats, String date ) {
        this.id = id;
        this.flightid = flightid;
        this.custid = custid;
        this.classes = classes;
        this.price = price;
        this.seats = seats;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFlightid() {
        return flightid;
    }

    public void setFlightid(String flightid) {
        this.flightid = flightid;
    }

    public String getCustid() {
        return custid;
    }

    public void setCustid(String custid) {
        this.custid = custid;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
