package pntc.service;

import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Stack;

@UtilityClass
public class StringsCleaner {

    public List<String> cleanStringsBrackets(List<String> inputStrings) {
        return inputStrings.stream()
                .map(string -> removeOuterChars(string, getMaxOuterBracketsNumber(string)))
                .toList();
    }

    public String cleanStringBrackets(String inputString) {
        return removeOuterChars(inputString, getMaxOuterBracketsNumber(inputString));
    }

    public String removeOuterAlphabetPairs(String inputString) {
        return removeOuterChars(inputString, getOuterMatchingPairsNumber(inputString));
    }


    private String removeOuterChars(String inputString, int charsToRemovePerSide) {
        if (inputString == null) {
            return "";
        }
        int startIndex = Math.max(0, charsToRemovePerSide);
        int endIndex = Math.max(0, inputString.length() - charsToRemovePerSide);
        return inputString.substring(startIndex, endIndex);
    }

    private int getOuterMatchingPairsNumber(String inputString) {
        if(inputString.length()<2){
            return 0;
        }
        String upperCaseString = inputString.toUpperCase();
        int outerCharsToRemove = 0;
        while (outerCharsMatch(upperCaseString, outerCharsToRemove)
                && outerCharsToRemove < inputString.length() / 2) {
            outerCharsToRemove++;
        }
        return outerCharsToRemove;
    }

    private boolean outerCharsMatch(String inputString, int charIndex) {
        int leftChar = inputString.charAt(charIndex);
        int rightChar = inputString.charAt(inputString.length() - 1 - charIndex);
        return isUpperCaseChar(leftChar) && isUpperCaseChar(rightChar)
                && leftChar < rightChar
                && Math.abs(65 - leftChar) == Math.abs(90 - rightChar);
    }

    private boolean isUpperCaseChar(int charAscii) {
        return charAscii >= 65 && charAscii <= 90;
    }

    private Integer getMaxOuterBracketsNumber(String string) {
        if (string.length() < 2) {
            return 0;
        }
        int maxOuterBrackets = 0;
        for (int i = 0; i < string.length() / 2; i++) {
            if (string.charAt(i) == '(' && string.charAt(string.length() - 1 - i) == ')'
                    && bracketsAreValid(string.substring(i + 1, string.length() - i - 1))) {
                maxOuterBrackets++;
            } else {
                return maxOuterBrackets;
            }
        }
        return maxOuterBrackets;
    }

    private boolean bracketsAreValid(String originalString) {
        Stack<Boolean> openBrackets = new Stack<>();
        for (char character : originalString.toCharArray()) {
            if (character == '(') {
                openBrackets.add(true);
            } else if (character == ')') {
                if (openBrackets.isEmpty()) {
                    return false;
                }
                openBrackets.pop();
            }
        }
        return openBrackets.isEmpty();
    }
}
