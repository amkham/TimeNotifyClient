package responce;

public class Notification {
    private String name, message;
    private int hour, min, sec;


    public Notification() {
    }

    public Notification(String name, String message, int hour, int min, int sec) {
        this.name = name;
        this.message = message;
        this.hour = hour;
        this.min = min;
        this.sec = sec;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getSec() {
        return sec;
    }

    public void setSec(int sec) {
        this.sec = sec;
    }
}
