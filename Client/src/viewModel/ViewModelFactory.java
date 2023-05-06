package viewModel;

import modelClient.Model;

public class ViewModelFactory {

	private CreateUserViewModel createUserViewModel;
	private LoginViewModel loginViewModel;
	private EditTraineeInfoViewModel editTraineeInfoViewModel;
	private AddExerciseViewModel addExerciseViewModel;
	private HomeViewModel homeViewModel;
	private ManageFolderViewModel manageFolderViewModel;

	public ViewModelFactory(Model model) {
		ViewState viewState = new ViewState();
		this.createUserViewModel=new CreateUserViewModel(model);
		this.loginViewModel = new LoginViewModel(model);
		editTraineeInfoViewModel = new EditTraineeInfoViewModel(model, viewState);
		addExerciseViewModel = new AddExerciseViewModel(model, viewState);
		homeViewModel = new HomeViewModel(model, viewState);
		manageFolderViewModel = new ManageFolderViewModel(model, viewState);
	}

	public CreateUserViewModel getCreateUserViewModel() {
		return createUserViewModel;
	}

	public LoginViewModel getLoginViewModel(){
		return loginViewModel;
	}

	public AddExerciseViewModel getAddExerciseViewModel() {
		return addExerciseViewModel;
	}


}
