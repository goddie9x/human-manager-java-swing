package Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validatetion {

    public static boolean isPhoneNumber(String phone) {
        Pattern p = Pattern.compile("0[0-9]{8,13}$");
        Matcher m = p.matcher(phone);

        return (m.matches());
    }

    public static boolean checkLengthMoreThanMin(String text, int minLength) {
        return text.length() >= minLength;
    }

    public static boolean checkLengthLessThanMax(String text, int maxLength) {
        return text.length() <= maxLength;
    }

    public static boolean checkLengthInRange(
            String text, int minLength, int maxLength) {
        int textLenght = text.length();
        return textLenght <= maxLength && textLenght >= minLength;
    }

    public static boolean checkAccountName(String accountName) {
        Pattern p = Pattern.compile(
                "^(?=.{4,30}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$");
        Matcher m = p.matcher(accountName);
        return (m.matches());
    }
}
