public class Flight {

    private String flightname;
    private String source;
    private String depart;
    private String date;
    private String deptime;
    private String arrtime;
    private String flightcharge;

    public Flight(String flightname, String source, String depart,
        String date, String deptime, String arrtime, String flightcharge) {
        this.flightname = flightname;
        this.source = source;
        this.depart = depart;
        this.date = date;
        this.deptime = deptime;
        this.arrtime = arrtime;
        this.flightcharge = flightcharge;
    }

    public String getFlightname() {
        return flightname;
    }

    public void setFlightname(String flightname) {
        this.flightname = flightname;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDeptime() {
        return deptime;
    }

    public void setDeptime(String deptime) {
        this.deptime = deptime;
    }

    public String getArrtime() {
        return arrtime;
    }

    public void setArrtime(String arrtime) {
        this.arrtime = arrtime;
    }

    public String getFlightcharge() {
        return flightcharge;
    }

    public void setFlightcharge(String flightcharge) {
        this.flightcharge = flightcharge;
    }
}
