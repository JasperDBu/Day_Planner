package dayplanner;

public class HomeActivity {

    private String title;
    private Time startTime;
    private Time endTime;
    private String comment;

    public HomeActivity( String titleString, Time startTime, Time endTime, String commentString){
        title = titleString;
        this.startTime = startTime;
        this.endTime = endTime;
        comment = commentString;
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
}