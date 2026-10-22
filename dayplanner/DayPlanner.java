package dayplanner;
import java.util.Scanner;

/**
 * DayPlanner is a simple activity management
 * application that allows users to add and
 * search for Home, School, and Other activities.
 *
 * Activities are stored in fixed-size arrays and
 * can be searched using keywords and time ranges.
 *
 * The application uses a text-based menu system
 * and stores all dates and times using a
 * 24-hour clock.
 *
 * @author Jasper Bungay
 * @version 1.0
 */
public class DayPlanner {
    public final static int MAX_ARRAY_LEN = 10; 

    public static HomeActivity[] HomeActivities = new HomeActivity[MAX_ARRAY_LEN];
    public static SchoolActivity[] SchoolActivities = new SchoolActivity[MAX_ARRAY_LEN];
    public static OtherActivity[] OtherActivities = new OtherActivity[MAX_ARRAY_LEN];
    
    //Constants for the different activity type options in my code
    private static final int HOME = 1;
    private static final int SCHOOL = 2;
    private static final int OTHER = 3;
    private static final int ALL = 4;

    public static int homeCount = 0;
    public static int schoolCount = 0;
    public static int otherCount = 0;

    public static Scanner inScanner = new Scanner(System.in);

    /**
     * Prompts the user for activity information and
     * adds a new activity to the corresponding array.
     *
     * @param activityType
     *        1 = HomeActivity
     *        2 = SchoolActivity
     *        3 = OtherActivity
     */
    public static void addActivity(int activityType){

        //Getting Title
        System.out.println("Please input a title: ");
        String title = inScanner.nextLine().trim();
        if (title.isEmpty()){
            System.err.println("Error: Title cannot be empty.");
            return;
        }
        
        //Getting start time
        System.out.println("Please input a start time (YYYY/MM/DD, HH:MM) Reminder 24 hour clock:");
        String startTimeString = inScanner.nextLine().trim();
        Time startTime = parseTime(startTimeString);
        if (startTime == null){
            System.err.println("Error: inputed time is invalid");
            return;
        }
        
        //Getting end time
        System.out.println("Please input an end time (YYYY/MM/DD, HH:MM) Reminder 24 hour clock:");
        String endTimeString = inScanner.nextLine().trim();
        Time endTime = parseTime(endTimeString);
        if (endTime == null){
            System.err.println("Error: inputed time is invalid");
            return;
        }

        //checks if time range is impossible
        if( startTime.compareTo(endTime) > 0){
            System.err.println("Error: Invalid start and end times.");
            return;
        }

        System.out.println("(Optional) Please enter a comment.");
        String comment = inScanner.nextLine().trim();

        switch (activityType) {
            case HOME:
                HomeActivities[homeCount] = new HomeActivity( title, startTime, endTime, comment);   
                homeCount++;
                break;
            case SCHOOL:
                SchoolActivities[schoolCount] = new SchoolActivity( title, startTime, endTime, comment);
                schoolCount++;
                break;
            case OTHER:
                System.out.println("Please enter a location: ");
                String location = inScanner.nextLine().trim();
                if (location.isEmpty()){
                    System.err.println("Error: location is invalid");
                    return;
                }
                OtherActivities[otherCount] = new OtherActivity( title, startTime, endTime, comment, location);
                otherCount++;
                break;
        }
    }

    /**
     * Converts a string in the format
     * YYYY/MM/DD, HH:MM into a Time object.
     *
     * @param input Time string entered by the user.
     * @return A valid Time object or null if
     *         the format or values are invalid.
     */
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

    /**
     * Searches activities matching the specified
     * activity type, keywords, and time range.
     *
     * @param activityType
     *        1 = HomeActivity
     *        2 = SchoolActivity
     *        3 = OtherActivity
     *        4 = Search all activities
     */
    public static void searchActivity( int activityType){
        System.out.println("Please enter keywords to search:");
        String keyWords = inScanner.nextLine().trim().toLowerCase();

        System.out.println("Please input a target start time (YYYY/MM/DD, HH:MM) Reminder 24 hour clock:");
        String startTimeString = inScanner.nextLine().trim();
        Time startTime = null;
        if (startTimeString.isBlank()){
            startTime = null;
        } else {
            startTime = parseTime(startTimeString);
            if (startTime == null){
                System.err.println("Error: inputed time is invalid");
                return;
            }
        }

        System.out.println("Please input a target end time (YYYY/MM/DD, HH:MM) Reminder 24 hour clock:");
        String endTimeString = inScanner.nextLine().trim();
        Time endTime = null;
        if (endTimeString.isBlank()){
            endTime = null;
        } else {
            endTime = parseTime(endTimeString);
            if (endTime == null){
                System.err.println("Error: inputed time is invalid");
                return;
            }
        }

        boolean found = false;
        switch (activityType){
            case HOME:
                for(int i = 0; i < homeCount; i++){
                    HomeActivity activity = HomeActivities[i];
                    if (keyWordsMatch(activity.getTitle(), keyWords) && timeRangeMatch(activity.getStartTime(), activity.getEndTime(), startTime, endTime)){
                        printActivity(activity.getTitle(), activity.getStartTime(), activity.getEndTime(), activity.getComment(), "");
                        found = true;
                    }
                }
                break;
            case SCHOOL:
                for(int i = 0; i < schoolCount; i++){
                    SchoolActivity activity = SchoolActivities[i];
                    if (keyWordsMatch(activity.getTitle(), keyWords) && timeRangeMatch(activity.getStartTime(), activity.getEndTime(), startTime, endTime)){
                        printActivity(activity.getTitle(), activity.getStartTime(), activity.getEndTime(), activity.getComment(), "");
                        found = true;
                    }
                }
                break;
            case OTHER:
                for(int i = 0; i < otherCount; i++){
                    OtherActivity activity = OtherActivities[i];
                    if (keyWordsMatch(activity.getTitle(), keyWords) && timeRangeMatch(activity.getStartTime(), activity.getEndTime(), startTime, endTime)){
                        printActivity(activity.getTitle(), activity.getStartTime(), activity.getEndTime(), activity.getComment(), activity.getLocation());
                        found = true;
                    }
                }
                break;
            case ALL:
                for(int i = 0; i < homeCount; i++){
                    HomeActivity activity = HomeActivities[i];
                    if (keyWordsMatch(activity.getTitle(), keyWords) && timeRangeMatch(activity.getStartTime(), activity.getEndTime(), startTime, endTime)){
                        System.out.println("chec");
                        printActivity(activity.getTitle(), activity.getStartTime(), activity.getEndTime(), activity.getComment(), "");
                        found = true;
                    }
                }
                for(int i = 0; i < schoolCount; i++){
                    SchoolActivity activity = SchoolActivities[i];
                    if (keyWordsMatch(activity.getTitle(), keyWords) && timeRangeMatch(activity.getStartTime(), activity.getEndTime(), startTime, endTime)){
                        printActivity(activity.getTitle(), activity.getStartTime(), activity.getEndTime(), activity.getComment(), "");
                        found = true;
                    }
                }
                for(int i = 0; i < otherCount; i++){
                    OtherActivity activity = OtherActivities[i];
                    if (keyWordsMatch(activity.getTitle(), keyWords) && timeRangeMatch(activity.getStartTime(), activity.getEndTime(), startTime, endTime)){
                        printActivity(activity.getTitle(), activity.getStartTime(), activity.getEndTime(), activity.getComment(), activity.getLocation());
                        found = true;
                    }
                }
                break;
        }
        if (!found){
            System.out.println("No matching activities found.");
        }
    }

    /**
     * Determines whether a title contains
     * the specified search keywords.
     *
     * @param title Activity title.
     * @param keyWords User search keywords.
     * @return true if all search keywords are
     *         present in the title, false otherwise.
     */
    public static boolean keyWordsMatch(String title, String keyWords){
        if (keyWords.isEmpty()){
            return true;
        }

        String targetWords[] = keyWords.split("[ ]+");
        String titleWords[] = title.split("[ ]+");

        for (String titleWord : titleWords){
            boolean found = false;
            for (String targetWord : targetWords){
                if( targetWord.equalsIgnoreCase(titleWord)){
                    found = true;
                    break;
                }
            }

            if (!found){
                return false;
            }
        }

        return true;
    }

    /**
     * Determines whether an activity falls
     * within a searched time range.
     *
     * @param activityStartTime Activity start time.
     * @param activityEndTime Activity end time.
     * @param targetStartTime Search range start.
     * @param targetEndTime Search range end.
     *
     * @return true if the activity satisfies
     *         the search range constraints,
     *         false otherwise.
     */
    public static boolean timeRangeMatch( Time activityStartTime, Time activityEndTime, Time targetStartTime, Time targetEndTime){
        if (targetStartTime == null && targetEndTime == null){
            return true;
        } else if (targetStartTime == null){
            if ( activityEndTime.compareTo(targetEndTime) <= 0){
                return true;
            }
        } else if ( targetEndTime == null){
            if (activityStartTime.compareTo(targetStartTime) >= 0){
                return true;
            }
        } else if ( activityEndTime.compareTo(targetEndTime) <= 0 && activityStartTime.compareTo(targetStartTime) >= 0){
            return true;
        }
        return false;
    }

    /**
     * Prints the details of an activity.
     *
     * @param title Activity title.
     * @param startTime Activity start time.
     * @param endTime Activity end time.
     * @param comment Activity comment.
     * @param location Activity location,
     *                 if applicable.
     */
    public static void printActivity( String title, Time startTime, Time endTime, String comment, String location){
        System.out.println("\nTitle : " + title);
        System.out.println("Time Range: " + startTime.toString() + " - " + endTime.toString());
        System.out.println("Comment: " + comment);
        if(!location.isBlank()){
            System.out.println("Location: " + location);
        }
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
                case "a":
                    // intentional fall through
                case "add": {
                    System.out.println("Please enter the type of activity (home/school/other)");
                    String activityType = inScanner.nextLine().toLowerCase().trim();
                    switch (activityType){
                        case "home": 
                            if(homeCount == MAX_ARRAY_LEN){
                                System.err.println("Error: HomeActivities is full.");
                                break;
                            }
                            addActivity(HOME);
                            break;

                        case "school":
                            if(schoolCount == MAX_ARRAY_LEN){
                                System.err.println("Error: SchoolActivities is full.");
                                break;
                            }
                            addActivity(HOME);
                            break;

                        case "other":
                            if(otherCount == MAX_ARRAY_LEN){
                                System.err.println("Error: OtherActivities is full.");
                                break;
                            }
                            addActivity(OTHER);
                            break;

                        default:
                            System.err.println("Error: inputed an invalid activity type");
                            break;
                    }
                    break;
                }
                case "s":
                    // intentional fall through
                case "search": {
                    System.out.println("Please enter the type of activity (home/school/other) or leave blank to search all activities.");
                    String activityType = inScanner.nextLine().toLowerCase().trim();
                    switch (activityType){
                        case "home":
                            searchActivity(HOME);
                            break;

                        case "school":
                            searchActivity(SCHOOL);
                            break;

                        case "other":
                            searchActivity(OTHER);
                            break;

                        default:
                            if (activityType.isEmpty()){
                                searchActivity(ALL);
                            } else {
                                System.err.println("Error: inputed an invalid activity type");
                            }
                            break;
                    }
                    break;
                }
                case "q":
                    // intentional fall through
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
