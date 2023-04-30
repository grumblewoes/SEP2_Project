package viewModel;

import modelClient.Model;

public class ViewModelFactory {

	private CreateUserViewModel createUserViewModel;
	private LoginViewModel loginViewModel;
	private FolderViewModel folderViewModel;
	private AddExerciseViewModel addExerciseViewModel;

	private ViewState viewState;

	public ViewModelFactory(Model model) {
		viewState = new ViewState();
		this.createUserViewModel=new CreateUserViewModel(model);
		this.loginViewModel = new LoginViewModel(model);
		this.folderViewModel=new FolderViewModel(model,viewState);
		this.addExerciseViewModel = new AddExerciseViewModel(model,viewState);
	}

	public FolderViewModel getFolderViewModel() {
		return folderViewModel;
	}

	public AddExerciseViewModel getAddExerciseViewModel() {
		return addExerciseViewModel;
	}

	public CreateUserViewModel getCreateUserViewModel() {
		return createUserViewModel;
	}

	public LoginViewModel getLoginViewModel(){
		return loginViewModel;
	}



}
