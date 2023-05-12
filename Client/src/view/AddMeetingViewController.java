package view;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import viewModel.AddMeetingViewModel;
import viewModel.ViewModel;

public class AddMeetingViewController extends ViewController
{
  @FXML DatePicker dateOfMeeting;
  @FXML Label errorLabel;
  private AddMeetingViewModel addMeetingViewModel;
  private ViewHandler viewHandler;
  @Override public void init(ViewHandler viewHandler, ViewModel viewModel,
      Region root)
  {
    this.viewHandler=viewHandler;
    this.addMeetingViewModel=(AddMeetingViewModel) viewModel;
    this.root=root;

    errorLabel.textProperty().bind( addMeetingViewModel.getErrorProperty() );
    dateOfMeeting.valueProperty().bindBidirectional(addMeetingViewModel.getDateOfMeeting());
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
