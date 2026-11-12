package dayplanner;

/**
 * An activity that takes place at a specific location.
 * An other activity has a title, a start time, an end time,
 * an optional comment, and a location.
 *
 * @author Jasper Bungay
 * @version 2.0
 */
public class OtherActivity extends Activity {

    private String location;

    /**
     * Creates an other activity.
     *
     * @param title activity title; required, and not blank
     * @param startTime time the activity starts; required
     * @param endTime time the activity ends; required, and after the start
     * @param comment optional note; a null comment is stored as an empty string
     * @param location place where the activity happens; required, and not blank
     * @throws IllegalArgumentException if the title or location is blank,
     *         a time is missing, or the start time is not before the end time
     */
    public OtherActivity(String title, Time startTime, Time endTime, String comment, String location) {
        super(title, startTime, endTime, comment);
        if (location.trim().isEmpty()) {
            throw new IllegalArgumentException("Location cannot be empty.");
        }
        this.location = location.trim();
    }

    /**
     * Returns the location of this activity.
     *
     * @return location with surrounding whitespace removed
     */
    public String getLocation() {
        return location;
    }

    /**
     * Changes the location of this activity.
     *
     * @param location place where the activity happens; required, and not blank
     * @throws IllegalArgumentException if the location is blank
     */
    public void setLocation(String location) {
        if (location.trim().isEmpty()) {
            throw new IllegalArgumentException("Location cannot be empty.");
        }
        this.location = location.trim();
    }

    /**
     * Returns the file type for an other activity.
     *
     * @return "other"
     */
    @Override
    public String getType() {
        return "other";
    }

    /**
     * Returns this activity in the data-file format,
     * including its location.
     *
     * @return File lines for this activity.
     */
    @Override
    public String toFileString() {
        return super.toFileString() + "location = \"" + location + "\"\n";
    }

    /**
     * Returns this activity in the data-file format,
     * including its location.
     *
     * @return File lines for this activity.
     */
    @Override
    public String toString() {
        return toFileString();
    }
}
