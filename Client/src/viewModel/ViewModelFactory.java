package viewModel;

import modelClient.Model;

public class ViewModelFactory {

	private CreateUserViewModel createUserViewModel;
	private LoginViewModel loginViewModel;
	private EditTraineeInfoViewModel editTraineeInfoViewModel;
	private AddExerciseViewModel addExerciseViewModel;
	private HomeViewModel homeViewModel;
	private ManageFolderViewModel manageFolderViewModel;
	private ManageSpecificExercisesViewModel manageSpecificExercisesViewModel;
	private ProfileViewModel profileViewModel;

	public ViewModelFactory(Model model) {
		ViewState viewState = new ViewState();
		this.createUserViewModel=new CreateUserViewModel(model);
		this.loginViewModel = new LoginViewModel(model,viewState);
		editTraineeInfoViewModel = new EditTraineeInfoViewModel(model, viewState);
		addExerciseViewModel = new AddExerciseViewModel(model, viewState);
		homeViewModel = new HomeViewModel(model, viewState);
		manageFolderViewModel = new ManageFolderViewModel(model, viewState);
		manageSpecificExercisesViewModel = new ManageSpecificExercisesViewModel(model, viewState);
		profileViewModel = new ProfileViewModel(model, viewState);
	}

	public CreateUserViewModel getCreateUserViewModel() {
		return createUserViewModel;
	}

	public LoginViewModel getLoginViewModel(){ return loginViewModel;	}


    public HomeViewModel getHomeViewModel() { return  homeViewModel; }
    public ManageFolderViewModel getManageFolderViewModel() { return  manageFolderViewModel; }
	public AddExerciseViewModel getAddExerciseViewModel() { return  addExerciseViewModel; }
    public ManageSpecificExercisesViewModel getManageSpecificExercisesViewModel() { return  manageSpecificExercisesViewModel; }
    public ProfileViewModel getProfileViewModel() { return  profileViewModel; }
}
