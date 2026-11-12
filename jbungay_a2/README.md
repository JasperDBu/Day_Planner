# Day Planner

---

## Problem

Day Planner keeps track of home, school, and other activities. Assignment 2 stores every activity in one list, loads that list from a file when the program starts, saves it when the program exits, and uses a title-keyword index to speed up search.

---

## Assumptions and Limitations

- Times use a 24-hour clock.
- An activity start time must be strictly before its end time.
- There is no fixed cap on the number of activities. The Assignment 1 arrays of length 10 were replaced by one arraylist.
- Activities are not deleted. It is rebuilt from the titles each time the program starts.
- Title search matches whole words. program does not match programming.
- A search time window keeps an activity only when the activity lies inside that window. With both bounds set, the activity must start at or after the search start and end at or before the search end. A blank bound is ignored.
- The data file uses straight double quotes around each value.

---

## User Guide

From this folder:


javac dayplanner\*.java
java dayplanner.DayPlanner activities.txt


To regenerate the Javadoc web pages:


javadoc -encoding UTF-8 -charset UTF-8 -docencoding UTF-8 -d javadoc -author -version dayplanner\*.java


Open javadoc/index.html to browse the package.

If no file name is given, the program uses 'activities.txt'. The first run can name a file that does not exist yet. The file is created when the program quits. sample_activities.txt is a small starter file in the assignment format.

Menu commands, in any letter case:

- add or a adds one activity. The type is home, school, or other.
- search or s searches by type, title keywords, and an optional time window. Leave the type blank to search every activity.
- quit or q saves every activity and exits.

bye and exit are not commands. The program prints a warning and waits for another command.

A blank title, a blank location on an other activity, a date that is not real, or an end time that is not after the start time prints a warning and returns to the menu. Run add or search again to enter a new value.

---

## File Format

Activities are separated by a blank line.


type = "home"
title = "Work on Java Programming"
start = "2026/9/10, 17:30"
end = "2026/9/10, 19:00"
comment = "With John Doe"

type = "other"
title = "Buy Programming in Java"
start = "2026/9/12, 9:00"
end = "2026/9/12, 10:00"
comment = "Also buy stationery"
location = "Indigo in Guelph"

---

## Test Plan

These are the major conditions considered for testing, not a list of individual test runs.

### Commands

- Reasonable forms are accepted in any letter case: add and a, search and s, quit and q. Quit, QUIT, and Q all end the program.
- Unreasonable commands, such as bye and exit, get a warning and the menu is shown again.

### Adding an activity

- A valid home activity, a valid school activity, and a valid other activity with a location.
- A title that is empty.
- A date or time that is not real, such as month 13, a day that month does not have, hour 24, or letters instead of numbers.
- A start time that is after the end time, and a start time that is equal to the end time.
- An other activity with an empty location.
- An activity type that is not reasonable, such as hoem or 123. HOME and School are accepted.

### Searching

- The activity is not in the list.
- The only match is at the start of the list.
- The only match is at the end of the list.
- The match is somewhere between the two ends.
- One activity matches, and more than one activity matches.
- Several title keywords, tried in different orders.
- The same keyword in different letter case, such as Java, JAVA, and java.
- A partial word does not match. program does not match programming.
- No title keywords, so the whole list is searched.
- A type filter for home, school, or other, and a blank type so every activity is included.
- A time window with both bounds, with only a start time, with only an end time, and with both left blank.
- Type, keywords, and a time window used together.
- A search type that is not reasonable, and a search time that is not a real time.

### File loading

- A valid file loads every activity and the title index can be used right away.
- A missing file prints a warning, starts with no activities, and still opens the menu.
- An empty file loads nothing and does not crash.
- A bad record is skipped. This includes a bad type, an empty title, a time that is not real, a start time that is not before the end time, an other activity with no location, and a line with no equals sign. Valid records in the same file are kept.

### File saving

- Saving one activity.
- Saving a home activity, a school activity, and an other activity. Only the other activity includes a location.
- Starting the program again with that file loads the same activities.

### Title index

- One keyword finds every activity whose title contains that whole word.
- Two keywords each have their own position list, and only the positions in both lists are searched.
- A keyword that is not in the index, including a partial word, finds nothing.
- An activity added from the menu can be found before the program quits and the file is loaded again.

---

## Future Improvements:

    I still feel like my code looks ugly so maybe in the future I might want to add more comments and maybe move functions to their own method like asking for inputs.

