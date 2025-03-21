package ordination;

import controller.Controller;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PNTest {
    private PN pn;
    private static Controller controller;


    @BeforeAll
    static void setUpController() {
        controller = Controller.getTestController();
        controller.createSomeObjects();
    }

    @BeforeEach
    void setUp() {
        Laegemiddel laegemiddel = controller.getAllLaegemidler().get(0);
        Patient patient = controller.opretPatient("11223344", "Mikkel", 86);

        pn = controller.opretPNOrdination(LocalDate.of(2025,01,10), LocalDate.of(2025, 01, 20), patient, laegemiddel, 2);
    }

    @Test
    void givDosisFørStartDato() {
        boolean actual = pn.givDosis(LocalDate.of(2025, 01, 9));
        assertFalse(actual);
    }

    @Test
    void givDosisEfterSlutDato() {
        boolean actual = pn.givDosis(LocalDate.of(2025, 01, 21));
        assertFalse(actual);
    }

    @Test
    void givDosisPåGrænsen() {
        boolean actual = pn.givDosis(LocalDate.of(2025, 01, 10));
        assertTrue(actual);

        boolean actual2 = pn.givDosis(LocalDate.of(2025, 01, 20));
        assertTrue(actual2);
    }


    @Test
    void doegnDosis3Givninger() {
        pn.givDosis(LocalDate.of(2025, 01, 10));
        pn.givDosis(LocalDate.of(2025, 01, 11));
        pn.givDosis(LocalDate.of(2025, 01, 12));

        double expected = ((3*2*1.0)/ pn.antalDage());
        //Forventer 3 * dosis(2) / antal dage fra start til slut
        assertEquals(expected, pn.doegnDosis(), 0.000001);
        assertEquals(3, pn.getAntalGangeGivet());
    }

    @Test
    void doegnDosis3GivningerMedEnFejl() {
        pn.givDosis(LocalDate.of(2025, 01, 10));
        pn.givDosis(LocalDate.of(2025, 01, 11));
        pn.givDosis(LocalDate.of(2025, 01, 21));

        double expected = ((2*2*1.0)/ pn.antalDage());
        //Forventer 3 * dosis(2) / antal dage fra start til slut
        assertEquals(expected, pn.doegnDosis(), 0.000001);
        assertEquals(2, pn.getAntalGangeGivet());
    }


    @Test
    void getDatoerGivet() {
        LocalDate date1 = LocalDate.of(2025, 01, 10);
        LocalDate date2 = LocalDate.of(2025, 01, 11);
        LocalDate date3 = LocalDate.of(2025, 01, 12);
        pn.givDosis(date1);
        pn.givDosis(date2);
        pn.givDosis(date3);
        ArrayList<LocalDate> datoer = new ArrayList<>(List.of(date1, date2, date3));

        assertEquals(datoer, pn.getDatoerGivet());
    }

    @Test
    void getDatoerGivetMedFejl() {
        LocalDate date1 = LocalDate.of(2025, 01, 10);
        LocalDate date2 = LocalDate.of(2025, 01, 22);
        LocalDate date3 = LocalDate.of(2025, 01, 12);
        pn.givDosis(date1);
        pn.givDosis(date2);
        pn.givDosis(date3);
        ArrayList<LocalDate> datoer = new ArrayList<>(List.of(date1, date3));

        assertEquals(datoer, pn.getDatoerGivet());
    }


    @Test
    void samletDosis() {
        pn.givDosis(LocalDate.of(2025, 01, 10));
        pn.givDosis(LocalDate.of(2025, 01, 11));
        pn.givDosis(LocalDate.of(2025, 01, 21));
        //Expected 2*2
        assertEquals(4, pn.samletDosis());
    }

}