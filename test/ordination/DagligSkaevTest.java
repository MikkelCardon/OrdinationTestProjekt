package ordination;

import controller.Controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DagligSkaevTest {
    private Controller controller;

    @BeforeEach
    void setUp() {
        controller = Controller.getTestController();
        controller.createSomeObjects();
    }

    @Test
    void samletDosis() {
        //Arrange
        int expected = 9;
        //Act
        double actual = controller.getAllPatienter().get(1).getOrdinationer().get(0).doegnDosis() * controller.getAllPatienter().get(1).getOrdinationer().get(0).antalDage();
        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void doegnDosis() {
        //Arrange
        double expected = 7;
        //Act
        double actual = controller.getAllPatienter().get(1).getOrdinationer().get(1).doegnDosis();
        //Assert
        assertEquals(expected, actual);
    }
}