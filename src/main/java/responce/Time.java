package responce;

public class Time {
    int hour, min, sec;

    public Time(){}
    public Time(int hour, int min, int sec) {
        this.hour = hour;
        this.min = min;
        this.sec = sec;
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

    private String formatTime(int time){
        return time < 10 ?  "0" + time : String.valueOf(time);
    }

    @Override
    public String toString() {
        return formatTime(hour) + ":" + formatTime(min) + ":" + formatTime(sec);
    }
}
