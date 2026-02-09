/**
 * Copyright © 2016-2026 The Thingsboard Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
