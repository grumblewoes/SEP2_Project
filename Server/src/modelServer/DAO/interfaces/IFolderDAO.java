package modelServer.DAO.interfaces;

import mediator.Folder;
import mediator.FolderList;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IFolderDAO
{
  boolean createFolder(String username, String title) throws SQLException;
  boolean renameFolder(String username, int folderId, String newTitle) throws  SQLException;
  boolean removeFolder(String username,  int folderId) throws SQLException;
  FolderList getFolderList(String username) throws  SQLException;

}
