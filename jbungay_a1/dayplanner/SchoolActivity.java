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
public class SchoolActivity extends Activity{

    public SchoolActivity(String title, Time startTime, Time endTime, String comment){
        super(title, startTime, endTime, comment);
    }

    /**
     * Returns the file type for a school activity.
     *
     * @return "school".
     */
    @Override
    public String getType(){
        return "school";
    }
}