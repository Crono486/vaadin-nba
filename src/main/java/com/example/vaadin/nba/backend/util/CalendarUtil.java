package com.example.vaadin.nba.backend.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarUtil {

    private static String dateFormat = "MM/dd/yyyy";

    public static int getCurrentSeason() {
        int currentSeason;
        if (Calendar.getInstance().get(Calendar.MONTH) > 5) {
            currentSeason = Calendar.getInstance().get(Calendar.YEAR);
        } else {
            currentSeason = Calendar.getInstance().get(Calendar.YEAR) - 1;
        }
        return currentSeason;
    }

    public static String getDateString() {
        return getDateString(dateFormat, 0);
    }

    public static String getDateString(String pattern) {
        return getDateString(pattern, 0);
    }

    public static String getDateString(int shiftDays) {
        return getDateString(dateFormat, shiftDays);
    }

    public static String getDateString(String pattern, int shiftDays) {
        Calendar cal = new GregorianCalendar();
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        dateFormat.setTimeZone(cal.getTimeZone());
        cal.add(Calendar.DAY_OF_YEAR, shiftDays);
        return dateFormat.format(cal.getTime());
    }

    public static String getAgeString(String birthday) {

        LocalDate today = LocalDate.now();
        LocalDate bday = LocalDate.parse(birthday.substring(0, birthday.indexOf("T")));
        Period yearAge = Period.between(bday, today);
        LocalDate lastBday = bday.plusYears(yearAge.getYears());
        long daysAge = ChronoUnit.DAYS.between(lastBday, today);
        return yearAge.get(ChronoUnit.YEARS) + "y / " + daysAge + "d";
    }

    public static String getBirthdayString(String birthday) {
        LocalDate bday = LocalDate.parse(birthday.substring(0, birthday.indexOf("T")));
        return bday.format(DateTimeFormatter.ofPattern(dateFormat));
    }
}
