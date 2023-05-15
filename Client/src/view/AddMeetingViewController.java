package view;

import javafx.fxml.FXML;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import viewModel.AddMeetingViewModel;
import viewModel.ViewModel;

import java.time.LocalDate;

public class AddMeetingViewController extends ViewController
{
  @FXML DatePicker dateOfMeeting;
  @FXML Label errorLabel, coachUsername;
  private AddMeetingViewModel addMeetingViewModel;
  private ViewHandler viewHandler;
  @Override public void init(ViewHandler viewHandler, ViewModel viewModel,
      Region root)
  {
    this.viewHandler=viewHandler;
    this.addMeetingViewModel=(AddMeetingViewModel) viewModel;
    this.root=root;

    errorLabel.textProperty().bind( addMeetingViewModel.getErrorProperty() );
    coachUsername.textProperty().bind(addMeetingViewModel.getCoachProperty());
    dateOfMeeting.valueProperty().bindBidirectional(addMeetingViewModel.getDateOfMeeting());
    dateOfMeeting.setDayCellFactory(picker -> new DateCell() {
      @Override
      public void updateItem(LocalDate date, boolean empty) {
        super.updateItem(date, empty);

        if (date.isBefore(LocalDate.now().plusDays(1)) || date.isAfter(LocalDate.now().plusDays(30))) {
          setDisable(true);
          setStyle("-fx-background-color: #f0f0f0;"); // change disabled date color
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
    addMeetingViewModel.clear();
  }
}
