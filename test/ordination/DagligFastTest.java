package ordination;
import controller.Controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.Storage;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class DagligFastTest {
    private Controller controller;
    private Storage storage;


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
        double expected = 3;
        //Act
        double actual = controller.getAllPatienter().get(1).getOrdinationer().get(0).doegnDosis();
        //Assert
        assertEquals(expected, actual);
    }
}