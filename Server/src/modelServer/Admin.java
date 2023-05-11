package modelServer;

import com.google.gson.Gson;
import mediator.Exercise;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Admin implements Runnable

{
  private Model model;
  private String username, password;

  public Admin(Model model)
  {
    this.model = model;
    this.username = "admin";
    this.password = "1234";
  }

  public boolean validateLogin(String username, String password)
  {
    if (username.equals(this.username) && password.equals(this.password))
      return true;

    System.out.println("Incorrect credentials, try again.");
    return false;
  }

  @Override public void run()
  {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter username: ");
    String username = scanner.nextLine();

    System.out.print("Enter password: ");
    String password = scanner.nextLine();

    while (!validateLogin(username, password))
    {

      System.out.print("Enter username: ");
      username = scanner.nextLine();

      System.out.print("Enter password: ");
      password = scanner.nextLine();
    }

    while (true)
    {

      System.out.println("1) Add an exercise");
      System.out.println("2) Remove an exercise");

        int choice = 0;
        try
        {
          choice = scanner.nextInt();
        }
        catch (InputMismatchException e ) {

          scanner.next();
        }


        switch (choice)
        {
          case 1:
            scanner.nextLine();
            System.out.print("Enter the title of the exercise: ");
            String name = scanner.nextLine();

            if (!name.trim().isEmpty())
            {
              try
              {
                model.addExercise(name);

              }
              catch (Exception e)
              {
                System.out.println(
                    "An error occurred while trying to add this exercise" + e.getMessage());
              }
            }
            else {
              System.out.println("It cannot be empty");
            }
              break;


          case 2:

            System.out.print("Enter the title of the exercise to remove: ");
            scanner.nextLine();
            String name2 = scanner.nextLine();

            try
            {
              model.removeExercise(name2);
            }
            catch (Exception e)
            {
              System.out.println(
                  "An error occurred while trying to remove this exercise" + e.getMessage());
            }
            break;

          default:
                  try
                  {
                    throw new InputMismatchException("Invalid input");
                  }
                  catch (InputMismatchException e)
                  {
                    System.out.println("You can type only 1 or 2");
                  }

        }
      }

    }
  }


