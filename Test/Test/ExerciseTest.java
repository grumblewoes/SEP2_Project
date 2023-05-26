import mediator.Exercise;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExerciseTest {
    private Exercise exercise;

    @BeforeEach
    /**
     * 
     * 
     */
    public void setUp() {
        exercise = new Exercise(1, 50.0, 10, "Bicep Curls");
    }

    @Test
    /**
     * 
     * 
     */
    public void testExerciseToString() {
        String expected = "Exercise{" +
                "weight=50.0" +
                ", repetitions=10" +
                ", id=1" +
                ", name='Bicep Curls'" +
                '}';
        assertEquals(expected, exercise.toString());
    }

    @Test
    /**
     * 
     * 
     */
    public void testExerciseSettersAndGetters() {
        assertEquals(1, exercise.getId());
        exercise.setId(2);
        assertEquals(2, exercise.getId());

        assertEquals(50.0, exercise.getWeight(), 0.0);
        exercise.setWeight(60.0);
        assertEquals(60.0, exercise.getWeight(), 0.0);

        assertEquals(10, exercise.getRepetitions());
        exercise.setRepetitions(15);
        assertEquals(15, exercise.getRepetitions());

        assertEquals("Bicep Curls", exercise.getName());
        exercise.setName("Push Ups");
        assertEquals("Push Ups", exercise.getName());
    }

    @Test
    /**
     * 
     * 
     */
    public void testExerciseConstructor() {
        assertEquals(1, exercise.getId());
        assertEquals(50.0, exercise.getWeight(), 0.0);
        assertEquals(10, exercise.getRepetitions());
        assertEquals("Bicep Curls", exercise.getName());
    }

}


//    This test class contains three test methods:
//
//        testExerciseToString(): tests whether the toString() method of the Exercise class returns the expected output.
//        testExerciseSettersAndGetters(): tests whether the getter and setter methods of the Exercise class work as expected by setting and getting each attribute individually.
//        testExerciseConstructor(): tests whether the Exercise constructor initializes the object with the expected values.
//        Note that in this example, the test class is named ZombieFitnessTest to reflect the zombie-themed fitness application that is using the Exercise class. You can customize the name of the test class to fit your needs.
