package ca.tetervak.dicegame.service.impl;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Random;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

class DiceImplTest {

    DiceImpl dice;

    @BeforeEach
    void setUp() {
        out.println("Starting test");
        dice = new DiceImpl(new Random(2));
    }

    @AfterEach
    void tearDown() {
    }

    @ParameterizedTest(name = "setting {0}")
    @ValueSource(ints = {0,7})
    @DisplayName("check set illegal value")
    void setValue_illegalValue(int value) {
        assertThrows(IllegalArgumentException.class, () -> dice.setValue(value));
    }

    @Test
    @DisplayName("check set legal value")
    void setValue_legalValue() {
        dice.setValue(5);
        assertEquals(5, dice.getValue());
        //assertThrows(IllegalArgumentException.class, () -> dice.setValue(0));
    }


    @DisplayName("check range after roll")
    @RepeatedTest(2)
    void roll() {
        for (int i = 1; i <= 10; i++) {
            dice.roll();
            assertTrue(dice.getValue() <= 6);
            assertTrue(dice.getValue() >= 1);
            int rand = dice.getValue();
            out.printf("random value %d = %d\n", i, rand);
        }
    }
}