package dayplanner;

/**
 * Represents a specific date and time using
 * a 24-hour clock.
 *
 * The Time class provides time validation,
 * comparison, and string formatting methods.
 *
 * @author Jasper Bungay
 * @version 1.0
 */
public class Time {

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    /**
     * Creates a new Time object.
     * Validates the date and time values before
     * assigning them.
     *
     * @param year Year value.
     * @param month Month value (1-12).
     * @param day Day value.
     * @param hour Hour value (0-23).
     * @param minute Minute value (0-59).
     * @throws IllegalArgumentException if the
     *         date and time values are invalid.
     */
    public Time(int year, int month, int day, int hour, int minute){
        if (!timeIsValid(year, month, day, hour, minute)){
            throw new IllegalArgumentException("Invalid date/time values.");
        }
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }

    /**
     * Retrieves the year.
     *
     * @return Year value.
     */
    public int getYear(){
        return year;
    }

    /**
     * Retrieves the month.
     *
     * @return Month value.
     */
    public int getMonth(){
        return month;
    }

    /**
     * Retrieves the day.
     *
     * @return Day value.
     */
    public int getDay(){
        return day;
    }

    /**
     * Retrieves the hour.
     *
     * @return Hour value.
     */
    public int getHour(){
        return hour;
    }

    /**
     * Retrieves the minute.
     *
     * @return Minute value.
     */
    public int getMinute(){
        return minute;
    }

    /**
     * Sets the year after validating the resulting
     * date and time.
     *
     * @param year Year value.
     * @throws IllegalArgumentException if the
     *         resulting date/time would be invalid.
     */
    public void setYear(int year){
        if (!timeIsValid(year, this.month, this.day, this.hour, this.minute)){
            throw new IllegalArgumentException("Invalid year value.");
        }
        this.year = year;
    }

    /**
     * Sets the month after validating the resulting
     * date and time.
     *
     * @param month Month value.
     * @throws IllegalArgumentException if the
     *         resulting date/time would be invalid.
     */
    public void setMonth(int month){
        if (!timeIsValid(this.year, month, this.day, this.hour, this.minute)){
            throw new IllegalArgumentException("Invalid month value.");
        }
        this.month = month;
    }

    /**
     * Sets the day after validating the resulting
     * date and time.
     *
     * @param day Day value.
     * @throws IllegalArgumentException if the
     *         resulting date/time would be invalid.
     */
    public void setDay(int day){
        if (!timeIsValid(this.year, this.month, day, this.hour, this.minute)){
            throw new IllegalArgumentException("Invalid day value.");
        }
        this.day = day;
    }

    /**
     * Sets the hour after validating the resulting
     * date and time.
     *
     * @param hour Hour value.
     * @throws IllegalArgumentException if the
     *         resulting date/time would be invalid.
     */
    public void setHour(int hour){
        if (!timeIsValid(this.year, this.month, this.day, hour, this.minute)){
            throw new IllegalArgumentException("Invalid hour value.");
        }
        this.hour = hour;
    }

    /**
     * Sets the minute after validating the resulting
     * date and time.
     *
     * @param minute Minute value.
     * @throws IllegalArgumentException if the
     *         resulting date/time would be invalid.
     */
    public void setMinute(int minute){
        if (!timeIsValid(this.year, this.month, this.day, this.hour, minute)){
            throw new IllegalArgumentException("Invalid minute value.");
        }
        this.minute = minute;
    }

    /**
     * Converts this Time object into a numeric
     * representation used for easy comparisons.
     *
     * @return Numeric representation of the
     *         stored date and time.
     */
    public long getTime(){
        //formula to turn it into workable value for checks and comparisons
        long time = minute + (hour * 100) + (day * 10000) + (month * 1000000) + (year * 100000000);
        return time;
    }

    /**
     * Compares this Time object with another
     * Time object.
     *
     * @param other Time object to compare with.
     *
     * @return 0 if equal,
     *         1 if this Time occurs after other,
     *        -1 if this Time occurs before other.
     */
    public int compareTo( Time other){
        long thisTime = this.getTime();
        long otherTime = other.getTime();

        if (thisTime == otherTime){
            return 0;
        } else if (thisTime > otherTime){
            return 1;
        }
        return -1;
    }

    /**
     * Returns a formatted String representation
     * of this Time object.
     *
     * @return Time in the format
     *         YYYY/MM/DD, HH:MM.
     */
    public String toString(){ 
        if(minute <= 9){
            return  year + "/" + month + "/" + day + ", " + hour + ":0" + minute;
        }
        
        return  year + "/" + month + "/" + day + ", " + hour + ":" + minute;
    }

    /**
     * Determines whether the supplied date and
     * time values represent a valid point in time.
     *
     * Leap years are handled according to the
     * calendar rules.
     *
     * @param year Year value.
     * @param month Month value.
     * @param day Day value.
     * @param hour Hour value.
     * @param minute Minute value.
     *
     * @return true if the date and time are valid;
     *         false otherwise.
     */
    public static boolean timeIsValid( int year, int month, int day, int hour, int minute){
        
        // year check, month check, and time checks
        if(year < 1 || month < 1 || month > 12 || hour > 23 || hour < 0 || minute > 59 || minute < 0){
            return false;
        }

        //Month checks, will do seperate things for day check depedning on the month or year.
        if(month == 2){
            if((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)){
                if ( day > 29 || day < 1){
                    return false;
                }
            } else {
                if(day > 28 || day < 1){
                    return false;
                }
            }
        } else if(month == 4 || month == 6 || month == 9 || month == 11){
            if(day > 30 || day < 1){
                 return false;
            }
        } else if (day > 31 || day < 1){
            return false;
        }
        return true;
    }
}
