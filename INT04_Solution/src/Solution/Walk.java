package Solution;

public class Walk {
    private String startPoint;
    private String endPoint;
    private int duration;

    public Walk(String startPoint, String endPoint, int duration)
    {
        this.startPoint=startPoint;
        this.endPoint=endPoint;
        this.duration=duration;
    }

    public String getStartPoint() {
        return this.startPoint;
    }

    public String getEndPoint() {
        return this.endPoint;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

}
