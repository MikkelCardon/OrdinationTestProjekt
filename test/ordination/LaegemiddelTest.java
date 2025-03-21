package ordination;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LaegemiddelTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void getEnhed() {
    }

    @Test
    void getNavn() {
    }
    /**
     Tester for negativ vægt (-10)
     */
    @Test
    public void testNegative() {
        // Arrange
        Laegemiddel laegemiddel = new Laegemiddel("TestMedicin", 1.0, 1.5, 2.0, "mg");
        double vaegt = -10.0;

        // Act
        double result = laegemiddel.anbefaletDosisPrDoegn(vaegt);


         assertEquals(-10.0, result, 0.001);
    }
    /**
     Tester 0 som vægt (0)
     */
    @Test
    public void testNul() {
        // Arrange
        Laegemiddel laegemiddel = new Laegemiddel("TestMedicin", 1.0, 1.5, 2.0, "mg");
        double vaegt = 0.0;

        // Act
        double result = laegemiddel.anbefaletDosisPrDoegn(vaegt);

        assertEquals(0.0, result, 0.001);
    }
    /**
     Tester lig under første grænseværdi (24.9)
     */
    @Test
    public void test25Minus() {
        // Arrange
        Laegemiddel laegemiddel = new Laegemiddel("TestMedicin", 1.0, 1.5, 2.0, "mg");
        double vaegt = 24.9;

        // Act
        double result = laegemiddel.anbefaletDosisPrDoegn(vaegt);

        // Assert
        // Forventer let dosis: 1.0 * 24.9 = 24.9
        assertEquals(24.9, result, 0.001);
    }
    /**
     Tester første grænseværdi (25)
     */
    @Test
    public void test25(){
        //Arrange
        Laegemiddel laegemiddel = new Laegemiddel("TestMedicin", 1.0,1.5,2.0,"mg");
        double vaegt = 25.0;

        // Act

        double result = laegemiddel.anbefaletDosisPrDoegn(vaegt);

        assertEquals(37.5, result, 0.001);

    }
    /**
    Tester første lige over første grænseværdi (25.1)
     */
    @Test
    public void test25Plus (){
        // Arrange
        Laegemiddel laegemiddel = new Laegemiddel("TestMedicin", 1.0, 1.5, 2.0, "mg");
        double vaegt = 25.1;

        // Act
        double result = laegemiddel.anbefaletDosisPrDoegn(vaegt);
        assertEquals(37.65, result, 0.01);

    }
    /**
     Tester ækvivalens klassens værdi, her 70kg
     */
    @Test
    public void testEquivalenceClass (){
        // Arrange
        Laegemiddel laegemiddel = new Laegemiddel("TestMedicin", 1.0, 1.5, 2.0, "mg");
        double vaegt = 70.0;

        // Act
        double result = laegemiddel.anbefaletDosisPrDoegn(vaegt);
        assertEquals(105, result, 0.001);

    }
    /**
     Tester lige under grænseværdi (119.9)
     */
    @Test
    public void test120Minus (){
        // Arrange
        Laegemiddel laegemiddel = new Laegemiddel("TestMedicin", 1.0, 1.5, 2.0, "mg");
        double vaegt = 119.9;

        // Act
        double result = laegemiddel.anbefaletDosisPrDoegn(vaegt);
        assertEquals(179.85, result, 0.001);

    }
    /**
     Tester  grænseværdi (120)
     */
    @Test
    public void test120 (){
        // Arrange
        Laegemiddel laegemiddel = new Laegemiddel("TestMedicin", 1.0, 1.5, 2.0, "mg");
        double vaegt = 120;

        // Act
        double result = laegemiddel.anbefaletDosisPrDoegn(vaegt);
        assertEquals(180, result, 0.001);

    }
    /**
     Tester lige over grænseværdi (120.1)
     */
    @Test
    public void test120Plus (){
        // Arrange
        Laegemiddel laegemiddel = new Laegemiddel("TestMedicin", 1.0, 1.5, 2.0, "mg");
        double vaegt = 120.1;

        // Act
        double result = laegemiddel.anbefaletDosisPrDoegn(vaegt);
        assertEquals(240.2, result, 0.001);

    }




}