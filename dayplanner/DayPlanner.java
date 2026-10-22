package dayplanner;
import java.util.Scanner;

public class DayPlanner {
    public final static int MAX_ARRAY_LEN = 10; 

    public static HomeActivity[] HomeActivities = new HomeActivity[MAX_ARRAY_LEN];
    public static SchoolActivity[] SchoolActivities = new SchoolActivity[MAX_ARRAY_LEN];
    public static OtherActivity[] OtherActivities = new OtherActivity[MAX_ARRAY_LEN];

    public static int homeCount = 0;
    public static int schoolCount = 0;
    public static int otherCount = 0;

    public static Scanner inScanner = new Scanner(System.in);

    public static void addActivity(int activityType){
        System.out.println("Please input a title: ");
        String title = inScanner.nextLine().trim();
        if (title.isEmpty()){
            System.err.println("Error: Title cannot be empty.");
            return;
        }
        
        System.out.println("Please input a start time (YYYY/MM/DD, HH:MM):");
        String startTimeString = inScanner.nextLine().trim();
        Time startTime = parseTime(startTimeString);
        if (startTime == null){
            System.err.println("Error: inputed time is invalid");
        }
        
        System.out.println("Please input an end time (YYYY/MM/DD, HH:MM):");
        String endTimeString = inScanner.nextLine().trim();
        Time endTime = parseTime(endTimeString);
        if (endTime == null){
            System.err.println("Error: inputed time is invalid");
        }
        if( startTime.compareTo(endTime) != 0){
            System.err.println("Error: Invalid start and end times.");
        }

        System.out.println("(Optional) Please enter a comment.");
        String comment = inScanner.nextLine().trim();

        switch (activityType) {
            case 1:
                HomeActivities[homeCount] = new HomeActivity( title, startTime, endTime, comment);   
                break;
            case 2:
                SchoolActivities[schoolCount] = new SchoolActivity( title, startTime, endTime, comment);
                break;
            case 3:
                System.out.println("Please enter a location: ");
                String location = inScanner.nextLine().trim();
                if (location.isEmpty()){
                    System.err.println("Error: location is invalid");
                    return;
                }
                OtherActivities[otherCount] = new OtherActivity( title, startTime, endTime, comment, location);
                break;
        }
    }

    public static Time parseTime(String input){ 
        int year;
        int month;
        int day;
        int hour;
        int minute;

        String[] dateTimeParts = input.split(",");
        if (dateTimeParts.length != 2){
            return null;
        }

        String[] datePieces = dateTimeParts[0].trim().split("/");
        if (datePieces.length != 3){
            return null;
        }

        String[] timePieces = dateTimeParts[1].trim().split(":");
        if (timePieces.length != 2){
            return null;
        }

        try{
            year = Integer.parseInt(datePieces[0].trim());
            month = Integer.parseInt(datePieces[1].trim());
            day = Integer.parseInt(datePieces[2].trim());

            hour = Integer.parseInt(timePieces[0].trim());
            minute = Integer.parseInt(timePieces[1].trim());
        } catch( Exception err) {
            return null;
        }

        if (!Time.timeIsValid(year, month, day, hour, minute)){
            return null;
        }

        return new Time( year, month, day, hour, minute);
    }

    public static void searchActivity(){
        
    }


    public static void main(String[] args) {
        boolean active = true;
        while(active == true){
            System.out.println("============================================================================\n" +
                "('add') Create new activity\n" + 
                "('search) search activity\n" +
                "('q' or 'quit') End Program\n" +
                "============================================================================\n" +
                "Input: "
            );
            String choice = inScanner.nextLine().toLowerCase().trim();

            switch (choice) {
                case "add": {
                    System.out.println("Please enter the type of activity (home/school/other)");
                    String activityType = inScanner.nextLine().toLowerCase().trim();
                    switch (activityType){
                        case "home": 
                            if(homeCount == MAX_ARRAY_LEN){
                                System.err.println("Error: HomeActivities is full.");
                                break;
                            }
                            addActivity(1);
                            break;

                        case "school":
                            if(schoolCount == MAX_ARRAY_LEN){
                                System.err.println("Error: SchoolActivities is full.");
                                break;
                            }
                            addActivity(2);
                            break;

                        case "other":
                            if(otherCount == MAX_ARRAY_LEN){
                                System.err.println("Error: OtherActivities is full.");
                                break;
                            }
                            addActivity(3);
                            break;

                        default:
                            System.err.println("Error: inputed an invalid activity type");
                            break;
                    }
                    break;
                }
                case "search": {
                    System.out.println("Please enter the type of activity (home/school/other) or leave blank to search all activities.");
                    String activityType = inScanner.nextLine().toLowerCase().trim();
                    switch (activityType){
                        case "home":
                            
                            break;

                        case "school":
                        
                            break;

                        case "other":
                            
                            break;

                        default:
                            if (activityType.isEmpty()){

                            } else {
                                System.err.println("Error: inputed an invalid activity type");
                            }
                            break;
                    }
                    break;
                }
                case "q":
                    break;
                case "quit":
                    active = false;
                    break;
                default:
                    System.err.println("Not a valid input, please try again.");
                    break;
            }
        }
    }
}
