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
        Trainee trainee = null;
        try{
            trainee = new Trainee(firstName, lastName, username, password, height, weight);
        }catch (Exception e){
            return false;
        }

            if (!trainee.getUserName().equals(traineeList.getUserByUsername(username))) {
            traineeList.addUser(trainee);
            System.out.println(traineeList);
            return true;
        }
        return false;
    }

    @Override public boolean login(String username, String password) {
        Trainee trainee = (Trainee) traineeList.getUserByUsername(username);

        //if trainee doesn't exist on the list
        if (trainee == null)
            System.out.println("Server Model Manager: User does not appear to be in the system.");

        else {
            if (trainee.getPassword().equals(password))
            {
                System.out.println("Server Model Manager: User logged in.");
                return true;
            }
            System.out.println("Server Model Manager: Incorrect password.");
        }
        return false;
    }
}