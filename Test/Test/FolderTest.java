import static org.junit.jupiter.api.Assertions.*;

import mediator.Folder;
import org.junit.jupiter.api.Test;

class FolderTest {

    @Test
    void testZOMBIE() {
        // Zero Test
        Folder folder = new Folder(1, "Folder 1", "John");
        assertEquals(1, folder.getId());
        assertEquals("Folder 1", folder.getTitle());
        assertEquals("John", folder.getOwner());

        // One Test
        folder.setTitle("Folder 2");
        assertEquals("Folder 2", folder.getTitle());

        // Many Test
        folder.setId(2);
        folder.setOwner("Jane");
        assertEquals(2, folder.getId());
        assertEquals("Jane", folder.getOwner());

        // Boundary Test
        Folder folder2 = new Folder(Integer.MAX_VALUE, "", "");
        assertEquals(Integer.MAX_VALUE, folder2.getId());
        assertEquals("", folder2.getTitle());
        assertEquals("", folder2.getOwner());
    }

}

//    Zero Test: Test the constructor and getters to ensure that the object is properly initialized and the getters return the expected values.
//        One Test: Test the setter method to ensure that the object can be modified and the getter returns the expected value.
//        Many Test: Test the setter methods with different values to ensure that the object can be modified and the getter returns the expected value.
//        Boundary Test: Test the object with the maximum integer value for the id field, and empty strings for the title and owner fields, to ensure that the object can handle extreme input values.