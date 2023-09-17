package org.example.rules;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenericRulesTest {

    @Test
    void should_return_undefined_when_string_null() {
        GenericRules genericRules = new GenericRules();
        String str = null;
        String fStr = genericRules.handle(str);
        assertEquals("undefined", fStr);
    }

}