package modelServer;

public class ModelManager implements Model
{
    private TraineeList traineeList;
    public ModelManager() {
        this.traineeList = new TraineeList();

    }
    @Override public boolean createUser(String firstName, String lastName,
                                        String username, String password, int height, int weight)
    {

            if (!trainee.getUserName().equals(traineeList.getUserByUsername(username))) {
            traineeList.addUser(trainee);
            System.out.println(traineeList);
            return true;
        }
        return false;
    }
}