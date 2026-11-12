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
public class OtherActivity extends Activity {
    
    private String location;

    public OtherActivity( String titleString, Time startTime, Time endTime, String commentString, String locationString){
        super(titleString, startTime, endTime, commentString);
        location = locationString;
    }

    /**
     * Retrieves the location of the activity.
     *
     * @return Activity location.
     */
    public String getLocation(){
        return location;
    }

    /**
     * Sets the location of the activity.
     *
     * @param location Activity location.
     */
    public void setLocation(String location){
        this.location = location;
    }

    /**
     * Returns the file type for an other activity.
     *
     * @return "other".
     */
    @Override
    public String getType(){
        return "other";
    }

    /**
     * Returns this activity in the data-file format,
     * including its location.
     *
     * @return File lines for this activity.
     */
    @Override
    public String toString(){
        return super.toFileString() + "location = \"" + location + "\"\n";
    }
}
