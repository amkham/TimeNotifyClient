package responce;

public class Response {
    private Time time;
    private final Notification[] notifications;

    public Response(Notification[] notifications, Time time) {
        this.notifications = notifications;
        this.time = time;
    }

    public Notification[] getNotifications() {
        return notifications;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}