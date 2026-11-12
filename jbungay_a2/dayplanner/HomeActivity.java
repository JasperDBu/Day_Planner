package dayplanner;

/**
 * An activity that takes place at home.
 * A home activity has a title, a start time, an end time, and an optional comment.
 *
 * @author Jasper Bungay
 * @version 2.0
 */
public class HomeActivity extends Activity {

    /**
     * Creates a home activity.
     *
     * @param title activity title; required, and not blank
     * @param startTime time the activity starts; required
     * @param endTime time the activity ends; required, and after the start
     * @param comment optional note; a null comment is stored as an empty string
     * @throws IllegalArgumentException if the title is blank, a time is
     *         missing, or the start time is not before the end time
     */
    public HomeActivity(String title, Time startTime, Time endTime, String comment) {
        super(title, startTime, endTime, comment);
    }

    /**
     * Returns the file type for a home activity.
     *
     * @return "home"
     */
    @Override
    public String getType() {
        return "home";
    }
}
