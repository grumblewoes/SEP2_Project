
import mediator.Exercise;
import mediator.ExerciseList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ExerciseListTest {

    private ExerciseList exerciseList;

    @Before
    public void setUp() {
        exerciseList = new ExerciseList();
        exerciseList.add(new Exercise(1, 50.0, 10, "Bicep Curls"));
        exerciseList.add(new Exercise(2, 60.0, 12, "Squats"));
        exerciseList.add(new Exercise(3, 30.0, 8, "Push Ups"));
        exerciseList.add(new Exercise(4, 70.0, 15, "Deadlifts"));
    }

    @Test
    public void testExerciseListAddAndGet() {
        assertEquals(4, exerciseList.size());

        Exercise expected = new Exercise(5, 80.0, 8, "Bench Press");
        exerciseList.add(expected);

        assertEquals(expected, exerciseList.get(4));
    }

    @Test
    public void testExerciseListFilterByName()
    {
        ExerciseList filteredList = exerciseList.filterByName("Bicep Curls");
        assertEquals(1, filteredList.size());
        assertEquals(new Exercise(1, 50.0, 10, "Bicep Curls").getName(), filteredList.get(0).getName());

        filteredList = exerciseList.filterByName("Squats");
        assertEquals(1, filteredList.size());
        assertEquals(new Exercise(2, 60.0, 12, "Squats").getName(), filteredList.get(0).getName());

        filteredList = exerciseList.filterByName("Push Ups");
        assertEquals(1, filteredList.size());
        assertEquals(new Exercise(3, 30.0, 8, "Push Ups").getName(), filteredList.get(0).getName());

        filteredList = exerciseList.filterByName("Deadlifts");
        assertEquals(1, filteredList.size());
        assertEquals(new Exercise(4, 70.0, 15, "Deadlifts").getName(), filteredList.get(0).getName());

        filteredList = exerciseList.filterByName("Invalid Exercise");
        assertEquals(0, filteredList.size());

        filteredList = exerciseList.filterByName(null);
        assertEquals(0, filteredList.size());
    }

    @Test
    public void testExerciseListToString() {
        assertDoesNotThrow(()->exerciseList.toString());
    }
}

//
//    This test class contains three test methods:
//
//        testExerciseListAddAndGet(): tests whether the add() and get() methods of the ExerciseList class work as expected by adding an exercise to the list and getting it by index.
//        testExerciseListFilterByName(): tests whether the filterByName() method of the ExerciseList class returns the expected filtered list for different input names.
//        testExerciseListToString(): tests whether the toString() method of the ExerciseList class returns the expected output.
//        Note that in this example, the test class is named ZombieFitnessTest to reflect the zombie-themed fitness application that is using the ExerciseList class. You can customize the name of the test class to fit your needs.