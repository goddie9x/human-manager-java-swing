package Util;

public class Conversion {
    private static final String WRONG_DATE_FORMAT = "1";

    public static String passwordToString(char[] password) {
        return new String(password);
    }

    public static String stringToDateForSql(String stringNeedTobeConvert) {
        String[] dateDdMYyy = stringNeedTobeConvert.trim().split("/");

        if (dateDdMYyy.length != 3) {
            return WRONG_DATE_FORMAT;
        }
        int day = Integer.parseInt(dateDdMYyy[0]);
        int month = Integer.parseInt(dateDdMYyy[1]);
        int year = Integer.parseInt(dateDdMYyy[2]);

        if (checkDay(day) && checkMonth(month) && checkYear(year)) {
            return convertDayMonthYearForSQL(day, month, year);
        }
        return WRONG_DATE_FORMAT;
    }

    private static boolean checkDay(int day) {
        return day > 0 && day < 31;
    }

    private static boolean checkMonth(int month) {
        return month > 0 && month < 13;
    }

    private static boolean checkYear(int year) {
        return year > 1900 && year < 2199;
    }

    public static String convertDayMonthYearForSQL(int day, int month, int year) {
        String sqlDayType = (day < 10) ? ("0" + day) : (day + "");
        String sqlMonthType = (month < 10) ? ("0" + month) : (month + "");
        String sqlYearType = year + "";
        
        return sqlYearType + "-" + sqlMonthType + "-" + sqlDayType;
    }
}
