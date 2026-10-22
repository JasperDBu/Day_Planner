package dayplanner;

/**
 * Represents a school activity stored within the
 * DayPlanner application.
 *
 * A SchoolActivity contains a title, start time,
 * end time, and an optional comment.
 *
 * @author Jasper Bungay
 * @version 1.0
 */
public class SchoolActivity {

    private String title;
    private Time startTime;
    private Time endTime;
    private String comment;

    /**
     * Creates a SchoolActivity.
     *
     * @param titleString The activity title.
     * @param startTime The activity start time.
     * @param endTime The activity end time.
     * @param commentString Optional comment.
     */
    public SchoolActivity( String titleString, Time startTime, Time endTime, String commentString){
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