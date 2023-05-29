package modelServer.DAO.interfaces;

import mediator.Folder;
import mediator.FolderList;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Interface class used with methods connected only to Folder object
 *
 * @version Sprint 6 - 29.05.2023
 */
public interface IFolderDAO
{
  boolean createFolder(String username, String title) throws SQLException;
  boolean renameFolder(String username, int folderId, String newTitle)
      throws SQLException;
  boolean removeFolder(String username, int folderId) throws SQLException;
  FolderList getFolderList(String username) throws SQLException;

}
