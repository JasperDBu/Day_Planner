package dayplanner;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * DayPlanner is a simple activity management
 * application that allows users to add and
 * search for Home, School, and Other activities.
 *
 * Activities are stored in one ArrayList and
 * can be searched using keywords and time ranges.
 * Title keywords are kept in a HashMap from a
 * lowercase word to the activity positions that
 * contain it. The map is built while the file
 * loads and updated when an activity is added.
 *
 * The application uses a text-based menu system
 * and stores all dates and times using a
 * 24-hour clock.
 *
 * @author Jasper Bungay
 * @version 2.0
 */
public class DayPlanner {

    private static ArrayList<Activity> activities = new ArrayList<Activity>();
    private static HashMap<String, ArrayList<Integer>> titleIndex = new HashMap<String, ArrayList<Integer>>();

    //Constants for the different activity type options in my code
    private static final int HOME = 1;
    private static final int SCHOOL = 2;
    private static final int OTHER = 3;
    private static final int ALL = 4;

    private static Scanner inScanner = new Scanner(System.in);
    private static String dataFile = "activities.txt";

    /**
     * Prompts the user for activity information and
     * adds a new activity to the activity list.
     * The title index is updated in this method.
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
        if( startTime.getTime() >= endTime.getTime()){
            System.err.println("Error: Invalid start and end times.");
            return;
        }

        System.out.println("(Optional) Please enter a comment.");
        String comment = inScanner.nextLine().trim();

        Activity activity = null;
        switch (activityType) {
            case HOME:
                activity = new HomeActivity( title, startTime, endTime, comment);
                break;
            case SCHOOL:
                activity = new SchoolActivity( title, startTime, endTime, comment);
                break;
            case OTHER:
                System.out.println("Please enter a location: ");
                String location = inScanner.nextLine().trim();
                if (location.isEmpty()){
                    System.err.println("Error: location is invalid");
                    return;
                }
                activity = new OtherActivity( title, startTime, endTime, comment, location);
                break;
        }

        if (activity == null){
            return;
        }

        //Adding activity to the list and updating the title index for the hashmap search function
        activities.add(activity);
        int position = activities.size() - 1;
        String[] words = activity.getTitle().toLowerCase().split("[ ]+");
        for (int i = 0; i < words.length; i++){
            boolean seen = false;
            for (int earlier = 0; earlier < i; earlier++){
                if (words[i].equals(words[earlier])){
                    seen = true;
                    break;
                }
            }
            if (seen || words[i].isEmpty()){
                continue;
            }

            ArrayList<Integer> positions = titleIndex.get(words[i]);
            if (positions == null){
                positions = new ArrayList<Integer>();
                titleIndex.put(words[i], positions);
            }
            positions.add(position);
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

            return new Time( year, month, day, hour, minute);
        } catch( Exception err) {
            return null;
        }
    }

    /**
     * Searches activities matching the specified
     * activity type, keywords, and time range.
     *
     * When keywords are entered, each word is looked
     * up in the title index and those position lists
     * are intersected. Type and time are checked only
     * on that smaller set. With no keywords, the whole
     * activity list is checked in order.
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

        // null means there were no keywords, so search the whole list.
        // An empty list means a keyword was missing from the index.
        ArrayList<Integer> matches = null;
        if (!keyWords.isEmpty()){
            String targetWords[] = keyWords.split("[ ]+");
            for (int w = 0; w < targetWords.length; w++){
                if (targetWords[w].isEmpty()){
                    continue;
                }
                ArrayList<Integer> hits = titleIndex.get(targetWords[w]);
                if (hits == null){
                    matches = new ArrayList<Integer>();
                    break;
                }
                if (matches == null){
                    matches = new ArrayList<Integer>(hits);
                } else {
                    ArrayList<Integer> overlap = new ArrayList<Integer>();
                    for (int i = 0; i < matches.size(); i++){
                        if (hits.contains(matches.get(i))){
                            overlap.add(matches.get(i));
                        }
                    }
                    matches = overlap;
                }
            }
            if (matches == null){
                matches = new ArrayList<Integer>();
            }
        }

        boolean found = false;
        int count = matches == null ? activities.size() : matches.size();
        for (int n = 0; n < count; n++){
            int index = matches == null ? n : matches.get(n);
            Activity activity = activities.get(index);

            boolean typeOk = false;
            if (activityType == ALL){
                typeOk = true;
            } else if (activityType == HOME && activity.getType().equals("home")){
                typeOk = true;
            } else if (activityType == SCHOOL && activity.getType().equals("school")){
                typeOk = true;
            } else if (activityType == OTHER && activity.getType().equals("other")){
                typeOk = true;
            }

            if (typeOk && timeRangeMatch(activity.getStartTime(), activity.getEndTime(), startTime, endTime)){
                String location = "";
                if (activity instanceof OtherActivity){
                    location = ((OtherActivity) activity).getLocation();
                }
                printActivity(activity.getTitle(), activity.getStartTime(), activity.getEndTime(), activity.getComment(), location);
                found = true;
            }
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

        for (String targetWord : targetWords){
            boolean found = false;
            for (String titleWord : titleWords){
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

    /**
     * Loads activities from a text file.
     * Each activity is a block of key = "value" lines,
     * separated from the next activity by a blank line.
     * An invalid record is skipped so the rest of the
     * file can still be read.
     *
     * @param filename File to read.
     */
    public static void loadActivities(String filename){
        try{
            FileInputStream fileInputStream = new FileInputStream(filename);
            Scanner fileScanner = new Scanner(fileInputStream);

            String type = null;
            String title = null;
            Time startTime = null;
            Time endTime = null;
            String comment = "";
            String location = null;
            boolean readingActivity = false;

            while(fileScanner.hasNextLine()){
                String line = fileScanner.nextLine();
                if (line.isBlank()){
                    if (readingActivity){
                        addActivityFromFile(type, title, startTime, endTime, comment, location);
                        type = null;
                        title = null;
                        startTime = null;
                        endTime = null;
                        comment = "";
                        location = null;
                        readingActivity = false;
                    }
                    continue;
                }

                readingActivity = true;

                String[] keyValue = line.split("=");
                if (keyValue.length != 2){
                    continue;
                }
                String key = keyValue[0].trim();
                String value = stripQuotes(keyValue[1].trim());
                switch (key){
                    case "type":
                        type = value;
                        break;
                    case "title":
                        title = value;
                        break;
                    case "start":
                        startTime = parseTime(value);
                        break;
                    case "end":
                        endTime = parseTime(value);
                        break;
                    case "comment":
                        comment = value;
                        break;
                    case "location":
                        location = value;
                        break;
                    default:
                        continue;
                }
            }

            if (readingActivity){
                addActivityFromFile(type, title, startTime, endTime, comment, location);
            }
            fileScanner.close();
        } catch(FileNotFoundException e){
            System.err.println("Error: file does not exist");
        }
    }

    /**
     * Saves every activity to a text file.
     * Home and school activities use type, title, start,
     * end, and comment. Other activities also include location.
     *
     * @param filename File to write.
     */
    public static void saveActivities(String filename){
        try{
            FileOutputStream fileOutputStream = new FileOutputStream(filename);
            PrintWriter fileWriter = new PrintWriter(fileOutputStream);
            for(int i = 0; i < activities.size(); i++){
                fileWriter.println(activities.get(i).toFileString());
                if (i < activities.size() - 1){
                    fileWriter.println();
                }
            }
            fileWriter.close();
        }
        catch(FileNotFoundException e){
            System.err.println("Error: file does not exist");
        }
    }

    /**
     * Adds one activity that was read from a file.
     * Valid activities are indexed here as well.
     *
     * @param type Activity type.
     * @param title Activity title.
     * @param startTime Activity start time.
     * @param endTime Activity end time.
     * @param comment Activity comment.
     * @param location Activity location.
     */
    private static void addActivityFromFile(String type, String title, Time startTime, Time endTime, String comment, String location){
        if (type == null || title == null || title.trim().isEmpty() || startTime == null || endTime == null){
            System.err.println("Error: invalid activity in file");
            return;
        }
        if (startTime.getTime() >= endTime.getTime()){
            System.err.println("Error: invalid activity in file");
            return;
        }

        Activity activity = null;
        try{
            switch (type.toLowerCase()){
                case "home":
                    activity = new HomeActivity(title, startTime, endTime, comment);
                    break;
                case "school":
                    activity = new SchoolActivity(title, startTime, endTime, comment);
                    break;
                case "other":
                    if (location.isEmpty()){
                        System.err.println("Error: invalid activity in file");
                        return;
                    }
                    activity = new OtherActivity(title, startTime, endTime, comment, location);
                    break;
                default:
                    System.err.println("Error: invalid activity in file");
                    return;
            }
        } catch(IllegalArgumentException err){
            System.err.println("Error: invalid activity in file");
            return;
        }

        activities.add(activity);
        int position = activities.size() - 1;
        String[] words = activity.getTitle().toLowerCase().split("[ ]+");
        for (int i = 0; i < words.length; i++){
            boolean seen = false;
            for (int earlier = 0; earlier < i; earlier++){
                if (words[i].equals(words[earlier])){
                    seen = true;
                    break;
                }
            }
            if (seen || words[i].isEmpty()){
                continue;
            }

            ArrayList<Integer> positions = titleIndex.get(words[i]);
            if (positions == null){
                positions = new ArrayList<Integer>();
                titleIndex.put(words[i], positions);
            }   
            positions.add(position);
        }
    }

    /**
     * Removes the surrounding quotes from a file value.
     *
     * @param value Value to strip quotes from.
     * @return Value without surrounding quotes.
     */
    private static String stripQuotes(String value){
        if (value.length() >= 2 && value.startsWith("\"") && value.endsWith("\"")){
            return value.substring(1, value.length() - 1);
        }
        return value;
    }

    /**
     * Loads the activity file, then runs the menu
     * until the user quits. The file name is the
     * first program argument. When it is omitted,
     * activities.txt is used.
     *
     * @param args command line arguments; args[0] is the data file
     */
    public static void main(String[] args) {
        if(args.length > 0){
            dataFile = args[0];
        } else {
            System.out.println("No filename provided, using default filename: activities.txt");
        }
        loadActivities(dataFile);


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
                            addActivity(HOME);
                            break;

                        case "school":
                            addActivity(SCHOOL);
                            break;

                        case "other":
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
                    saveActivities(dataFile);
                    active = false;
                    break;
                default:
                    System.err.println("Not a valid input, please try again.");
                    break;
            }
        }
    }
}
