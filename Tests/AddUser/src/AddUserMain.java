import mediatorClient.RemoteModel;

import java.rmi.Naming;

public class AddUserMain
{
  public static void main(String[] args)
  {
    RemoteModel model;
    String firstname, lastname, username, password;

    //fetching proxy object
    try {
      model = (RemoteModel) Naming.lookup("rmi://localhost:1099/ValhallaServer");
    }
    catch (Exception e)
    {
      System.out.println("Cannot find remote interface in registry.");
      e.printStackTrace();
    }

    //ADDING USER WITH CORRECT INPUTS
    firstname = "Bob";
    lastname = "Bobson";
    username = "MobsterBob";
    password = "mobbob_1234";
    System.out.println("Adding user with correct inputs.");
    try {
      model.createUser(firstname, lastname, username, password);
      System.out.println("Success");
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
    }

    //ADDING USER TWICE
    firstname = "Bob";
    lastname = "Bobson";
    username = "MobsterBob";
    password = "mobbob_1234";
    System.out.println("Adding user with correct inputs.");
    try {
      model.createUser(firstname, lastname, username, password);
      System.out.println("Success");
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
    }

    //ADDING USER WITH BLANK SPACE IN NAME
    firstname = " ";
    lastname = "Crews";
    username = "TerryCrews";
    password = "joeMama_";
    System.out.println("Adding user with blank space in firstname.");
    try {
      model.createUser(firstname, lastname, username, password);
      System.out.println("Success");
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
    }

    //ADDING USER WITH BLANK SPACE IN LASTNAME
    firstname = "Danny";
    lastname = " ";
    username = "DannyPhantom";
    password = "dan_Fan";
    System.out.println("Adding user with blank space in lastname.");
    try {
      model.createUser(firstname, lastname, username, password);
      System.out.println("Success");
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
    }

    //ADDING USER WITH EMPTY STRING IN USERNAME
    firstname = "John";
    lastname = "Tyler";
    username = "";
    password = "tyler_1234";
    System.out.println("Adding user with empty field in username.");
    try {
      model.createUser(firstname, lastname, username, password);
      System.out.println("Success");
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
    }

    //ADDING USER WITH EMPTY STRING IN PASSWORD
    firstname = "James";
    lastname = "Polk";
    username = "JamesPolk";
    password = "";
    System.out.println("Adding user with empty field in password.");
    try {
      model.createUser(firstname, lastname, username, password);
      System.out.println("Success");
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
    }

    //ADDING USER WITH SHORT PASSWORD
    firstname = "Millard";
    lastname = "Fillmore";
    username = "MillerFillmore";
    password = "m";
    System.out.println("Adding user with short password.");
    try {
      model.createUser(firstname, lastname, username, password);
      System.out.println("Success");
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
    }

    //ADDING USER WITH ILLEGAL CHARACTERS
    firstname = "Franklin";
    lastname = "Pierce";
    username = "FranklinPierce";
    password = "Frankie_{Pierce";
    System.out.println("Adding user with illegal characters in password");
    try {
      model.createUser(firstname, lastname, username, password);
      System.out.println("Success");
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
    }

    //ADDING USER WITH LONG PASSWORD
    firstname = "James";
    lastname = "Buchanan";
    username = "JamesBuchanan";
    password = "xX_J4m3s_Buch4n4n@Pr3s1d3ncy_Xx";
    System.out.println("Adding user with long password.");
    try {
      model.createUser(firstname, lastname, username, password);
      System.out.println("Success");
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
