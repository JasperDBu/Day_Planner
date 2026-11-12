package dayplanner;

/**
 * Represents a home activity stored within the
 * DayPlanner application.
 *
 * A HomeActivity contains a title, start time,
 * end time, and an optional comment.
 *
 * @author Jasper Bungay
 * @version 1.0
 */
public class HomeActivity extends Activity {

    /**
     * Creates a HomeActivity object.
     *
     * @param titleString Activity title.
     * @param startTime Activity starting time.
     * @param endTime Activity ending time.
     * @param commentString Optional activity comment.
     */
    public HomeActivity( String titleString, Time startTime, Time endTime, String commentString){
        super(titleString, startTime, endTime, commentString);
    }

    /**
     * Returns the file type for a home activity.
     *
     * @return "home".
     */
    @Override
    public String getType(){
        return "home";
    }
}