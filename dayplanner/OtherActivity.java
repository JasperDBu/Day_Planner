package dayplanner;

public class OtherActivity {
    
    private String title;
    private Time startTime;
    private Time endTime;
    private String comment;
    private String location;

    public OtherActivity( String titleString, Time startTime, Time endTime, String commentString, String locationString){
        title = titleString;
        this.startTime = startTime;
        this.endTime = endTime;
        comment = commentString;
        location = locationString;
    }

    public String getTitle(){
        return title;
    }

    public Time getStartTime(){
        return startTime;
    }
    
    public Time getEndTime(){
        return endTime;
    }

    public String getComment(){
        return comment;
    }

    public String getLocation(){
        return location;
    }
}
