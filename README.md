# Day Planner
---
## Problem

    We are making a daily organization tool that allows us to create different activities and store it in our program for us to keep track. We are making home activites, school activites, and other activites.

## Assumptions and Limitations

    - We are assuming that we use a 24 hour clock for this program
    - We are only using arrays so there is a max array size and it keeps that in check when creating new activities

## User Guide

    1. javac .\dayplanner\*.java
    2. java dayplanner.Dayplanner

    Menu Options:
    - "add": put "add" or "a" into the input to add a new activity
    - "search": put "search" or "s" into the input to search for an activity within a time range or a key word.
    - "quit": put "quit" or "q" into the input to quit the program

## Test Plan

    - tested for WW/WW/WW, WW:WW meaning usinging characters instead of integers for the time inputs
    - using days that are out of range depending on the months
    - creating max activities and trying to add more
    - searching using all blanks for inputs and searching using some blank inputs
    - Creating time ranges that are impossible like having end time come first or the year starting at 0 or before.
    - testing if it checks all keywords are inside the title like if only the last word of the word keys is inside the title would it match.
    - check if it promts the keywordCheck if it slightly matches the key word
    - Testing invalid inputs like putting "hoem" or "123" into activity type input

## Future Improvements

    - using an arraylist instead of a fixed array.
    - using a parent class "activity" for the activity classes to reduce code repetition.
    - add a way to save the activites when you close the program.