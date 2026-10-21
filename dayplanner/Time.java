package dayplanner;

public class Time {

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    public Time(int year, int month, int day, int hour, int minute){
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }

    public int getTime(){
        int time = minute + (hour * 100) + (day * 10000) + (month * 1000000) + (year * 100000000);
        return time;
    }

    public int compareTo( Time other){
        int thisTime = this.getTime();
        int otherTime = other.getTime();

        if (thisTime == otherTime){
            return 0;
        } else if (thisTime > otherTime){
            return 1;
        }
        return 0;
    }

    public String toString(){ 
        if(minute <= 9){
            return  year + "/" + month + "/" + day + ", " + hour + ":0" + minute;
        }
        
        return  year + "/" + month + "/" + day + ", " + hour + ":" + minute;
    }

    public static boolean timeIsValid( int year, int month, int day, int hour, int minute){
        
        if(year < 0 || month < 1 || month > 12 || hour > 23 || hour < 0 || minute > 59 || minute < 0){
            return false;
        }
        if(month == 4){
            if((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)){
                if ( day > 29 || day < 1){
                    return false;
                }
            } else {
                if(day > 28 || day < 1){
                    return false;
                }
            }
        }
        return true;
    }

}