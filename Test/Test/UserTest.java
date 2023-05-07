import mediator.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testUser() {
        User user = new User(180, 75, "John", "Doe", "johndoe", "Male", true);

        assertEquals(180, user.getHeight());
        assertEquals(75, user.getWeight());
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals("johndoe", user.getUsername());
        assertEquals("Male", user.getGender());
        assertTrue(user.isShareProfile());

        user.setHeight(175);
        user.setWeight(80);
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.setUsername("janedoe");
        user.setGender("Female");
        user.setShareProfile(false);

        assertEquals(175, user.getHeight());
        assertEquals(80, user.getWeight());
        assertEquals("Jane", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals("janedoe", user.getUsername());
        assertEquals("Female", user.getGender());
        assertFalse(user.isShareProfile());
    }
}

//    This test creates a User object, checks that all its attributes have been properly set through the constructor, then modifies those attributes with their respective setter methods and checks that the modifications have been successful.