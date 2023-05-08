package modelServer;

import java.util.Scanner;

public class AdminTask implements Runnable
{
  private Model model;
  private String username, password;
  private Scanner scan;

  public AdminTask(Model model) {
    this.model = model;
    username = "admin";
    password = "123456";
    scan = new Scanner(System.in);
  }

  public boolean validateLogin(String username, String password) {
    if (username.equals(this.username) && password.equals(this.password))
      return true;

    System.out.println("Incorrect, please try again.");
    return false;
  }

  @Override public void run()
  {
    do {
      System.out.print("Enter username >> ");
      String username = scan.nextLine();
      System.out.print("Enter password >> ");
      String password = scan.nextLine();
    }
    while (!validateLogin(username, password));

    System.out.println("What would you like to do?\n1 - Add Coach\n2 - Remove Coach\nType the number of your request >> ");
    String choice = scan.nextLine();

    String name;

    switch (choice) {
      case "1":
        System.out.print("Enter username for Coach >> ");
        String coachUsername = scan.nextLine();

        System.out.println("Enter password for Coach >> ");
        String coachPassword = scan.nextLine();

        System.out.println("Enter first name >> ");
        String coachName = scan.nextLine();

        System.out.println("Enter last name >> ");
        String coachLName = scan.nextLine();

        System.out.println("Enter height >> ");
        double coachHeight = scan.nextDouble();

        System.out.println("Enter weight >> ");
        double coachWeight = scan.nextDouble();

        System.out.println("Enter PB for bench press >> ");
        int pbBench = scan.nextInt();

        System.out.println("Enter PB for squat >> ");
        int pbSquat = scan.nextInt();

        System.out.println("Enter PB for deadlift >> ");
        int pbLift = scan.nextInt();


        if (model.addCoach(coachUsername, coachPassword, coachName, coachLName, coachHeight, coachWeight, pbBench, pbSquat, pbLift, true))
        {
          System.out.println("Coach was added to the system.");
        }
        else {
          System.out.println("An error occurred while trying to add coach.");
        }
        break;

      case "2":
        System.out.println("Enter the name of the Coach you'd like to remove >> ");
        name = scan.nextLine();

        if (model.removeCoach(name))
          System.out.println("Removal succeeded.");
        else {
          System.out.println("An error occurred while trying to remove coach.");
        }
        break;
    }
  }
}
