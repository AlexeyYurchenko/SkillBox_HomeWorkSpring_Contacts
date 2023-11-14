package org.example.util;

import java.util.regex.Pattern;

public class PersonValidator {
    private static final Pattern namePattern =
            Pattern.compile("^[А-ЯЁа-яё]+(?:-[А-ЯЁа-яё]+)? [А-ЯЁа-яё]+(?: [А-ЯЁа-яё]+)*$");
    private static final Pattern phonePattern =
            Pattern.compile("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$");
    private static final Pattern emailPattern =
            Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                    + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");

    public static boolean isName(String input) {
        return namePattern.matcher(input).matches();
    }

    public static boolean isNumber(String input) {
        return phonePattern.matcher(input).matches();
    }

    public static boolean isEmail(String input) {
        return emailPattern.matcher(input).matches();
    }
}