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

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class EntityNameValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "Device_01",
            "GarageDoor",
            "door-123",
            "Test Device.1",
            "device 01"
    })
    void validNamesOk(String name) {
        assertTrue(EntityNameValidator.isValid(name));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   "})
    void invalidNullOrBlank(String name) {
        assertFalse(EntityNameValidator.isValid(name));
    }

    @ParameterizedTest
    @ValueSource(strings = {"device@home", "sensor#1", "!@#$%"})
    void invalidChars(String name) {
        assertFalse(EntityNameValidator.isValid(name));
    }

    @Test
    void lengthLimitsCheck() {
        assertTrue(EntityNameValidator.isValid("A"));

        String maxLength = "a".repeat(255);
        assertTrue(EntityNameValidator.isValid(maxLength));

        String overMax = "a".repeat(256);
        assertFalse(EntityNameValidator.isValid(overMax));
    }

    @Test
    void normalizeStuff() {
        assertEquals("Device_01", EntityNameValidator.normalize("Device  01"));
        assertEquals("Device_01", EntityNameValidator.normalize("  Device   01  "));
        assertEquals("Multi_word_name", EntityNameValidator.normalize("Multi   word   name"));
        assertEquals("Test", EntityNameValidator.normalize("  Test  "));
        assertNull(EntityNameValidator.normalize(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"A", "Device"})
    void lenOk(String name) {
        assertTrue(EntityNameValidator.hasValidLength(name));
        String maxLength = "a".repeat(255);
        assertTrue(EntityNameValidator.hasValidLength(maxLength));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   "})
    void lenBad(String name) {
        String overMax = "a".repeat(256);
        assertFalse(EntityNameValidator.hasValidLength(name));
        assertFalse(EntityNameValidator.hasValidLength(overMax));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Device_01", "test-name", "Test Device.1"})
    void charsOk(String name) {
        assertTrue(EntityNameValidator.containsOnlyValidCharacters(name));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"test@device", "device#1"})
    void charsBad(String name) {
        assertFalse(EntityNameValidator.containsOnlyValidCharacters(name));
    }
}
