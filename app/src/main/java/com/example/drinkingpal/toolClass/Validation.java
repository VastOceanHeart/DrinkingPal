package com.example.drinkingpal.toolClass;

import java.util.ArrayList;

/**
 * Validation class: Mainly using to check that whether user's input is a valid input.
 *
 * @author Wenxiao
 * @version 20/05/2022
 */
public class Validation {

    /**
     * Check if the user has selected a feature that is still in development
     *
     * @param input    User's input string which needs to be checked
     * @param addition Viable options
     * @return true--If the function corresponding to the option entered by the user has been developed; false--If the function corresponding to the option entered by the user has not been developed
     */
    public static boolean checkAvailableOption(String input, ArrayList<Integer> addition) {
        //if the input is null or empty, directly return false
        if (!checkEmpty(input))
            return false;
            //if the input is not between min and max, print a reminding and return false
        else if (!addition.contains(Integer.parseInt(input))) {
            System.out.println("\n[This feature is under development]");
            System.out.print(">>>Enter again: ");
            return false;
        } else
            return true;
    }

    /**
     * checkEmpty method, which is used to check if the user enter a blank string(A input full of space also will be regard as a blank input) or null
     *
     * @param input User's input string which needs to be checked
     * @return true--If user enter a valid string which is not empty; false--If user enter an invalid string which is empty or full of spaces or null.
     */
    public static boolean checkEmpty(String input) {
        //If string name is a blank string, print a reminding and return false.
        if (input == null ||
                input.equals("null") ||
                input.trim().isEmpty()) {
            System.out.println("\n[Error, the input cannot be blank or null]");
            System.out.print(">>>Please enter again:");
            return false;
        }

        //If the input is not an empty string or null, return false
        else
            return true;
    }

    /**
     * checkDate method, which is used to check if the user enter a valid time in the form of "HH:MM"
     *
     * @param time User's input string which needs to be checked
     * @return true--If user enter a valid date; false--If user enter an invalid date.
     */
    public static boolean checkTime(String time) {
        // match the date format
        String[] HourAndMinute = time.split(":");
        boolean validResult;
        if (HourAndMinute.length != 2) {
            validResult = false;
        } else if (HourAndMinute[0].length() > 2 || HourAndMinute[1].length() > 2) {
            validResult = false;
        } else if (Integer.parseInt(HourAndMinute[0]) > 23 || Integer.parseInt(HourAndMinute[1]) >= 60) {
            validResult = false;
        } else {
            validResult = true;
        }
        return validResult;
    }

    /**
     * checkLength method, which is used to check whether the user enter a valid string among the given length
     *
     * @param length The given length of the string, the length of the string should less or equal than the given length
     * @param input  User's input string which needs to be checked
     * @return true--if user enter a valid string whose length is in given length range; false--if user enter an invalid string whose length is out of the given range.
     */
    public static boolean checkLength(int length, String input) {
        //return false if the length of the input is greater than given length
        if (input.length() < length) {
            System.out.println("\n[Error, the length of input at least owns " + length + " characters]");
            System.out.print(">>>Enter again: ");
            return false;
        }

        //return true if the length of the input is in given length range
        else
            return true;
    }

    /**
     * checkNonNegativeInteger method, which is used to check if user enter a non-negative integer.
     *
     * @param input User's input string which needs to be checked
     * @return true--If user enter a valid string which is a non-negative integer; false--If user enter an invalid string which is a negative integer or even not an integer.
     */
    public static boolean checkNonNegativeInteger(String input) {
        //if the input is null or empty, directly return false
        if (!checkEmpty(input))
            return false;

        //judge that if the input is a purly integer without any other characters
        for (int index = 0; index < input.length(); index++) {
            //If the input is not a purly integer, set the flag to true to continue the while loop
            if (!Character.isDigit(input.charAt(index))) {
                System.out.println("\n[Error, you must enter a non-negative integer]");
                System.out.print(">>>Enter again: ");
                return false;
            }
        }

        //If the input is a purly non-negative integer, return true
        return true;
    }

    /**
     * checkNonSymbol method, which is used to check if user enter a string which does not include any symbol.
     *
     * @param input User's input string which needs to be checked
     * @return true--If user enter a valid string without any symbol; false--If user enter an invalid string which includes symbols.
     */
    public static boolean checkNonSymbol(String input) {
        //if the input is null or empty, directly return false
        if (!checkEmpty(input))
            return false;

        //check that whether the input is belong to "A"~"Z" or "a"~"z" or "0"~"9"
        for (int index = 0; index < input.length(); index++) {
            //The Character.isLetterOrDigit('ʼ') will also return true, so add an additional condition: input.charAt(index) == 'ʼ'
            if (!Character.isLetterOrDigit(input.charAt(index)) ||
                    input.charAt(index) == 'ʼ') {
                //if the input is illegal, print a reminding and require the user to enter again and return a false
                System.out.println("\n[Error, the input must be english alphabet or digit]");
                System.out.print(">>>Enter again: ");
                return false;
            }
        }

        //If the input is a string which only includes digits and letters, return true
        return true;
    }

    /**
     * checkRange method, which is used to check if user enter an integer between given range.
     *
     * @param min           Lower limit of the range (minimum is zero)
     * @param max           Upper limit of the range
     * @param attributeName The name of the attribute which need to be checked
     * @param input         User's input string which needs to be checked
     * @return true--If user enter a valid integer string in given range; false--If user enter an invalid string or the parameter min is greater than max.
     */
    public static boolean checkRange(int min, int max, String attributeName, String input) {
        //if the input is null or empty, directly return false
        if (!checkEmpty(input))
            return false;

        //If the min is greater than max, print an error and return false
        if (min > max) {
            System.out.println("\n[Error, the min is greater than max]");
            return false;
        }

        //If the input is not a non-negative integer, to avoid crash, directly return false
        if (!checkNonNegativeInteger(input))
            return false;

        //if the input is not between min and max, print a reminding and return false
        if (Integer.parseInt(input) < min ||
                Integer.parseInt(input) > max) {
            System.out.println("\n[Error, the " + attributeName + " must be between " + min + " and " + max + "]");
            System.out.print(">>>Enter again: ");
            return false;
        }

        //if the input is between min and max, return true
        else {
            return true;
        }
    }

    /**
     * Determine if the incoming start time is before the end time
     *
     * @param starting Start time
     * @param ending   End time
     * @return If the start time is after the end time, return false; Else return ture
     */
    public static boolean checkStartAndEndTimePoint(String starting, String ending) {
        //
        String[] startHourAndMinute = starting.split(":");
        String[] endHourAndMinute = ending.split(":");
        boolean validResult = true;
        if (Integer.parseInt(startHourAndMinute[0]) > Integer.parseInt(endHourAndMinute[0]))
            validResult = false;
        else if (Integer.parseInt(startHourAndMinute[0]) == Integer.parseInt(endHourAndMinute[0]) && Integer.parseInt(startHourAndMinute[1]) >= Integer.parseInt(endHourAndMinute[1]))
            validResult = false;
        else
            validResult = true;
        return validResult;
    }
}