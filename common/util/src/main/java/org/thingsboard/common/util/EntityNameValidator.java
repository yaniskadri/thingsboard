package org.thingsboard.common.util;

public class EntityNameValidator {

    private static final int MIN_LENGTH = 1;
    private static final int MAX_LENGTH = 255;
    private static final String VALID_PATTERN = "^[a-zA-Z0-9_\\-\\s.]+$";

    public static boolean isValid(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        String trimmed = name.trim();
        return trimmed.length() >= MIN_LENGTH &&
               trimmed.length() <= MAX_LENGTH &&
               trimmed.matches(VALID_PATTERN);
    }

    public static String normalize(String name) {
        if (name == null) {
            return null;
        }
        String cleaned = name.trim().replaceAll("\\s+", "_");
        return cleaned.replaceAll("_+", "_");
    }

    public static boolean hasValidLength(String name) {
        if (name == null) {
            return false;
        }
        int length = name.trim().length();
        return length >= MIN_LENGTH && length <= MAX_LENGTH;
    }

    public static boolean containsOnlyValidCharacters(String name) {
        if (name == null) {
            return false;
        }
        return name.matches(VALID_PATTERN);
    }
}
