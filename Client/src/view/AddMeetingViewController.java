package view;

import javafx.fxml.FXML;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.layout.Region;
import util.Logger;
import viewModel.AddMeetingViewModel;
import viewModel.ViewModel;

import java.time.LocalDate;
import java.util.ArrayList;

public class AddMeetingViewController extends ViewController
{
  @FXML DatePicker dateOfMeeting;
  @FXML Label errorLabel, coachUsername;
  private AddMeetingViewModel addMeetingViewModel;
  private ViewHandler viewHandler;
  private ArrayList<LocalDate> dates;
  private ArrayList<LocalDate> getTakenDates(LocalDate date) {
    if(date.isEqual(LocalDate.now()))
      dates=addMeetingViewModel.getTakenDates();

    return this.dates;
  }
  @Override public void init(ViewHandler viewHandler, ViewModel viewModel,
      Region root)
  {
    this.viewHandler=viewHandler;
    this.addMeetingViewModel=(AddMeetingViewModel) viewModel;
    this.root=root;

    errorLabel.textProperty().bind( addMeetingViewModel.getErrorProperty() );
    coachUsername.textProperty().bind(addMeetingViewModel.getCoachProperty());
    dateOfMeeting.valueProperty().bindBidirectional(addMeetingViewModel.getDateOfMeeting());
    this.dates=null;
    dateOfMeeting.setDayCellFactory(picker -> new DateCell() {

      @Override
      public void updateItem(LocalDate date, boolean empty) {
        super.updateItem(date, empty);

        ArrayList<LocalDate> dates = getTakenDates(date);

        if (date.isBefore(LocalDate.now().plusDays(1)) || date.isAfter(LocalDate.now().plusDays(30))) {
          setDisable(true);
          setStyle("-fx-background-color: #f0f0f0;"); // change disabled date color
        }
        else if(dates.contains(date)){
          setDisable(true);
          setStyle("-fx-background-color: #fee0f0;");
        }
      }
    });
  }
  @FXML private void submit(){
    boolean ans = addMeetingViewModel.sendMeetingRequest();
    if(ans)
      viewHandler.openView("home");
  }
  @FXML private void cancel(){
    viewHandler.openView("home");
  }
  @Override public void reset()
  {
    dates.clear();
    addMeetingViewModel.clear();
  }
}
