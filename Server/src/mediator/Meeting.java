package mediator;

import java.time.LocalDate;

public class Meeting
{
  private String traineeUsername, coachUsername;
  private LocalDate dateOfMeeting;

  public Meeting(String coachUsername, LocalDate dateOfMeeting) {
    setTraineeUsername(null);
    setDateOfMeeting(dateOfMeeting);
    setCoachUsername(coachUsername);
  }
  public Meeting(LocalDate dateOfMeeting) {
    setTraineeUsername(null);
    setDateOfMeeting(dateOfMeeting);
    setCoachUsername(null);
  }


  public String getTraineeUsername()
  {
    return traineeUsername;
  }

  public void setTraineeUsername(String traineeUsername)
  {
    this.traineeUsername = traineeUsername;
  }

  public String getCoachUsername()
  {
    return coachUsername;
  }

  public void setCoachUsername(String coachUsername)
  {
    this.coachUsername = coachUsername;
  }

  public LocalDate getDateOfMeeting()
  {
    return dateOfMeeting;
  }

  public void setDateOfMeeting(LocalDate dateOfMeeting)
  {
    this.dateOfMeeting = dateOfMeeting;
  }
}