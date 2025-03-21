package controller;

import ordination.*;
import storage.Storage;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    private static Controller controller;
    Patient anders;
    Laegemiddel morfin;

    @BeforeAll
    static void beforeAll() {
        controller = Controller.getTestController();
        controller.createSomeObjects();
    }

    @BeforeEach
    void setUp() {
        anders = controller.opretPatient("1809006243", "Anders Bo Jensen", 95.6);
        morfin = controller.opretLaegemiddel("Morfin", 2, 4, 6, "ml");
    }

    @Test
    void opretPNOrdination() {
        //Arrange
        //Act
        //Assert
        assertInstanceOf(PN.class, controller.opretPNOrdination(LocalDate.of(2025, 03, 14), LocalDate.of(2025, 03, 22), anders, morfin, 2));
    }

    @Test
    void opretDagligFastOrdination() {
        // Arrange
        // Act
        // Assert
        assertInstanceOf(DagligFast.class, controller.opretDagligFastOrdination(LocalDate.of(2025, 03, 14), LocalDate.of(2025, 03, 22), anders, morfin, 2, 2, 0, 1));

    }


    @Test
    void opretDagligSkaevOrdination() {
        double[] an = {0.5, 1, 2.5, 3};
        LocalTime[] kl = {LocalTime.of(12, 0), LocalTime.of(12, 40), LocalTime.of(16, 0), LocalTime.of(18, 45)};
        assertInstanceOf(DagligSkaev.class, controller.opretDagligSkaevOrdination(LocalDate.of(2025, 03, 14), LocalDate.of(2025, 03, 22), anders, morfin, kl, an));

    }

    //Tester ordinationPNAnvendt-----------------------------------------------------------------
    @Test
    void ordinationPNAnvendt() {
        //Arrange
        PN pn = controller.opretPNOrdination(LocalDate.of(2025, 3, 14), LocalDate.of(2025, 3, 22), anders, morfin, 2);
        //Act
        controller.ordinationPNAnvendt(pn, LocalDate.of(2025, 3, 15));
        //Assert
        assertTrue(pn.getDatoerGivet().contains(LocalDate.of(2025, 3, 15)));
    }
    //Tester nedre grænse
    @Test
    void ordinationPNAnvendtNedreGrænse() {
        //Arrange
        PN pn = controller.opretPNOrdination(LocalDate.of(2025, 3, 14), LocalDate.of(2025, 3, 22), anders, morfin, 2);
        //Act
        controller.ordinationPNAnvendt(pn, LocalDate.of(2025, 3, 14));
        //Assert
        assertTrue(pn.getDatoerGivet().contains(LocalDate.of(2025, 3, 14)));
    }
    //Tester øvre grænse
    @Test
    void ordinationPNAnvendtØvreGrænse() {
        //Arrange
        PN pn = controller.opretPNOrdination(LocalDate.of(2025, 3, 14), LocalDate.of(2025, 3, 22), anders, morfin, 2);
        //Act
        controller.ordinationPNAnvendt(pn, LocalDate.of(2025, 3, 22));
        //Assert
        assertTrue(pn.getDatoerGivet().contains(LocalDate.of(2025, 3, 22)));
    }

    //Tester anbefaletDosisPrDoegn--------------------------------------------------
    @Test
    void anbefaletDosisPrDoegn() {
        //Arrange
        //Act
        double anbefaletDosis = controller.anbefaletDosisPrDoegn(anders, morfin);
        //Assert
        assertEquals(382.4, anbefaletDosis, 0.001);
    }


    //antalOrdinationerPrVaegtPrLaegemiddel-----------------------------------------------------------
    @Test
    void ORGTest() {
        //Arrange
        double minVaegt = 50;
        double maxVaegt = 70;
        //Act
        int antal = controller.antalOrdinationerPrVaegtPrLaegemiddel(minVaegt, maxVaegt, controller.getAllLaegemidler().get(0));
        //Assert
        assertEquals(antal, 1);
    }

    @Test
    void grænseVærdierNedre() {
        //Arrange
        double minVaegt = 60;
        double maxVaegt = 63.3;
        //Act
        int antal = controller.antalOrdinationerPrVaegtPrLaegemiddel(minVaegt, maxVaegt, controller.getAllLaegemidler().get(0));
        //Assert
        assertEquals(antal, 0);
    }

    @Test
    void grænseVærdierØvre() {
        //Arrange
        double minVaegt = 60;
        double maxVaegt = 63.4;
        //Act
        int antal = controller.antalOrdinationerPrVaegtPrLaegemiddel(minVaegt, maxVaegt, controller.getAllLaegemidler().get(0));
        //Assert
        assertEquals(antal, 1);
    }
}