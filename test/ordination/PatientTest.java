package ordination;

import controller.Controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PatientTest {
    Controller controller;

    @BeforeEach
    void setUp() {
        controller = Controller.getTestController();
    }

    @Test
    void opretPatientUnder0Kg() {
        assertThrows(RuntimeException.class, () -> new Patient("1809006243", "Dean Mauer", 0));
        assertThrows(RuntimeException.class, () -> new Patient("1809006243", "Dean Mauer", -1));

        String navn = "Dean Mauer";
        Patient actual = null;

        for (Patient patient : controller.getAllPatienter()) {
            if (patient.getNavn().equals(navn)){
                actual = patient;
            }
        }
        assertEquals(null, actual);
    }
}