package util;

import exceptions.InvalidNameException;

public class NameValidation {

    public static boolean isWord(String input) {

        for (char c : input.toCharArray()) {
            if (!Character.isLetter(c)) {
                throw new InvalidNameException("Ime nije uneto kako treba!");
            }
        }
        return true;
    }
}
