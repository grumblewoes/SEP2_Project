import static org.junit.jupiter.api.Assertions.*;

import mediator.Folder;
import mediator.FolderList;
import org.junit.jupiter.api.Test;

class FolderListTest {

    @Test
    void testZOMBIE() {
        // Zero Test
        FolderList folderList = new FolderList();
        assertEquals(0, folderList.size());

        // One Test
        Folder folder = new Folder(1, "Folder 1", "John");
        folderList.add(folder);
        assertEquals(1, folderList.size());
        assertEquals(folder, folderList.get(0));

        // Many Test
        Folder folder2 = new Folder(2, "Folder 2", "Jane");
        folderList.add(folder2);
        assertEquals(2, folderList.size());
        assertEquals(folder2, folderList.get(1));

        // Boundary Test
        Folder folder3 = new Folder(Integer.MAX_VALUE, "", "");
        folderList.add(folder3);
        assertEquals(Integer.MAX_VALUE, folderList.get(2).getId());
    }

}

//    Zero Test: Test the constructor and size method to ensure that the object is properly initialized and the list is empty.
//        One Test: Test the add and get methods to ensure that a single folder can be added and retrieved from the list.
//        Many Test: Test the add and get methods to ensure that multiple folders can be added and retrieved from the list.
//        Boundary Test: Test the object with the maximum integer value for the id field, and empty strings for the title and owner fields, to ensure that the object can handle extreme input values.