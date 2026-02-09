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
    @ValueSource(strings = {"A", "Device", "a".repeat(255)})
    void lenOk(String name) {
        assertTrue(EntityNameValidator.hasValidLength(name));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "a".repeat(256)})
    void lenBad(String name) {
        assertFalse(EntityNameValidator.hasValidLength(name));
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
