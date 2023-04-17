package modelServer;

public interface Model
{
    boolean createUser(String firstName, String lastName, String username, String password, int height, int weight);
}