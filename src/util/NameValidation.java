package util;

import exceptions.InvalidNameException;
import jdk.swing.interop.SwingInterOpUtils;

import java.util.regex.Pattern;

public class NameValidation {

    public static boolean isWord(String input) {

        for (char c : input.toCharArray()) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }
}
