package dayplanner;

/**
 * Shared information for every activity in the day planner.
 * Home, school, and other activities inherit the title, start time,
 * end time, and comment stored here.
 *
 * <p>An activity is valid only when the title is not empty, both times
 * are present, and the start time is strictly before the end time.
 * Subclasses add their own type name and any extra fields.
 *
 * @author Jasper Bungay
 * @version 2.0
 */
public abstract class Activity {

    private String title;
    private Time startTime;
    private Time endTime;
    private String comment;

    /**
     * Creates an activity with the fields shared by every activity type.
     *
     * @param title activity title; required, and not blank
     * @param startTime time the activity starts; required
     * @param endTime time the activity ends; required, and after the start
     * @param comment optional note; a null comment is stored as an empty string
     * @throws IllegalArgumentException if the title is blank, a time is
     *         missing, or the start time is not before the end time
     */
    public Activity(String title, Time startTime, Time endTime, String comment) {
        if (title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty.");
        }
        if (startTime == null) {
            throw new IllegalArgumentException("Start time is required.");
        }
        if (endTime == null) {
            throw new IllegalArgumentException("End time is required.");
        }
        if (startTime.compareTo(endTime) >= 0) {
            throw new IllegalArgumentException("Start time must be before end time.");
        }

        this.title = title.trim();
        this.startTime = startTime;
        this.endTime = endTime;
        this.comment = comment == null ? "" : comment.trim();
    }

    /**
     * Returns the activity title.
     *
     * @return title with surrounding whitespace removed
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the time the activity starts.
     *
     * @return start time
     */
    public Time getStartTime() {
        return startTime;
    }

    /**
     * Returns the time the activity ends.
     *
     * @return end time
     */
    public Time getEndTime() {
        return endTime;
    }

    /**
     * Returns the optional comment.
     *
     * @return comment, or an empty string when no comment was entered
     */
    public String getComment() {
        return comment;
    }

    /**
     * Returns the activity type written in the data file.
     *
     * @return code for "home", "school", or "other"
     */
    public abstract String getType();

    /**
     * Returns this activity as text for the console.
     *
     * @return type, title, time range, comment, and any subclass fields
     */
    public String toString() {
        String details = "Type: " + getType() + "\n"
                + "Title : " + title + "\n"
                + "Time Range: " + startTime.toString() + " - " + endTime.toString() + "\n"
                + "Comment: " + comment;
        return details;
    }

    /**
     * Returns this activity in the data-file format.
     * The text can be read back the next time the program starts.
     *
     * @return lines for type, title, start, end, comment, and subclass fields
     */
    public String toFileString() {
        String block = fileField("type", getType()) + "\n"
                + fileField("title", title) + "\n"
                + fileField("start", startTime.toString()) + "\n"
                + fileField("end", endTime.toString()) + "\n"
                + fileField("comment", comment);
        return block + "\n";
    }

    /**
     * Formats one data-file field.
     *
     * @param key field name
     * @param value field value
     * @return one key = "value" line, without a trailing newline
     */
    protected String fileField(String key, String value) {
        return key + " = \"" + value + "\"";
    }
}
