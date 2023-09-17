package org.example.rules;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GenericRulesTest {

    @Test
    void should_throw_IllegalStateException_when_coordinates_not_filled() {
        GenericRules genericRules = new GenericRules();
        Exception e = assertThrows(IllegalStateException.class, () ->
                genericRules.handle(null, 180)
        );
        assertTrue(e.getMessage().contains("all parameters required"));
    }
}
