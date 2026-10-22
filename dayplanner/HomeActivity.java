package dayplanner;

/**
 * Represents a home activity stored within the
 * DayPlanner application.
 *
 * A HomeActivity contains a title, start time,
 * end time, and an optional comment.
 *
 * @author Jasper Bungay
 */
public class HomeActivity {

    private String title;
    private Time startTime;
    private Time endTime;
    private String comment;

    /**
     * Creates a HomeActivity object.
     *
     * @param titleString Activity title.
     * @param startTime Activity starting time.
     * @param endTime Activity ending time.
     * @param commentString Optional activity comment.
     */
    public HomeActivity( String titleString, Time startTime, Time endTime, String commentString){
        title = titleString;
        this.startTime = startTime;
        this.endTime = endTime;
        comment = commentString;
    }

    /**
     * Retrieves the title of the activity.
     *
     * @return Activity title.
     */
    public String getTitle(){
        return title;
    }

    /**
     * Retrieves the start time of the activity.
     *
     * @return Activity start time.
     */
    public Time getStartTime(){
        return startTime;
    }
    
    /**
     * Retrieves the end time of the activity.
     *
     * @return Activity end time.
     */
    public Time getEndTime(){
        return endTime;
    }

    /**
     * Retrieves the comment of the activity.
     *
     * @return Activity comment.
     */
    public String getComment(){
        return comment;
    }
}