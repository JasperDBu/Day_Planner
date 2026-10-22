package dayplanner;

/**
 * Represents an activity that occurs at
 * a specific location.
 *
 * An OtherActivity contains a title,
 * start time, end time, optional comment,
 * and location.
 *
 * @author Jasper Bungay
 * @version 1.0
 */
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

    /**
     * Retrieves the location of the activity.
     *
     * @return Activity location.
     */
    public String getLocation(){
        return location;
    }
}
