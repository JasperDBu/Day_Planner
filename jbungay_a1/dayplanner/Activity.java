package dayplanner;

public abstract class Activity {
    protected String title;
    protected Time startTime;
    protected Time endTime;
    protected String comment;

    public Activity(String title, Time startTime, Time endTime, String comment){
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.comment = comment;
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

    public void setTitle(String title){
        this.title = title;
    }

    public void setStartTime(Time startTime){
        this.startTime = startTime;
    }

    public void setEndTime(Time endTime){
        this.endTime = endTime;
    }

    public void setComment(String comment){
        this.comment = comment;
    }

    /**
     * Returns the activity type written in the data file.
     *
     * @return "home", "school", or "other".
     */
    public abstract String getType();

    /**
     * Returns this activity in the data-file format.
     *
     * @return Lines for type, title, start, end, and comment.
     */
    public String toFileString(){
        return "type = \"" + getType() + "\"\n"
            + "title = \"" + title + "\"\n"
            + "start = \"" + startTime.toString() + "\"\n"
            + "end = \"" + endTime.toString() + "\"\n"
            + "comment = \"" + comment + "\"\n";
    }
}