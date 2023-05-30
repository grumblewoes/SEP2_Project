package viewModel;

import modelClient.Model;

/**
 * 
 * A class that returns instances of viewmodel subclasses upon request when switching screens
 * 
 * @author Damian Trafialek
 * @version 1.0
 */
public class ViewModelFactory {

	private CreateUserViewModel createUserViewModel;
	private LoginViewModel loginViewModel;
	private AddExerciseViewModel addExerciseViewModel;
	private HomeViewModel homeViewModel;
	private ManageFolderViewModel manageFolderViewModel;
	private ManageSpecificExercisesViewModel manageSpecificExercisesViewModel;
	private ProfileViewModel profileViewModel;
	private LeaderboardViewModel leaderboardViewModel;
	//private ManageCoachViewModel manageCoachViewModel;

	private AddFriendViewModel addFriendViewModel;
	private EditRosterViewModel editRosterViewModel;
	private AddMeetingViewModel addMeetingViewModel;
/**
 * 1-argument constructor 
 * initializes all instances of viewmodel subclasses along with setting model and initialising viewstate
 * 
 * @param model for the model layer of MVVM; communicates with the server
 *        
 */
	public ViewModelFactory(Model model) {
		ViewState viewState = new ViewState();
		this.createUserViewModel=new CreateUserViewModel(model);
		this.loginViewModel = new LoginViewModel(model,viewState);
		addExerciseViewModel = new AddExerciseViewModel(model, viewState);
		homeViewModel = new HomeViewModel(model, viewState);
		manageFolderViewModel = new ManageFolderViewModel(model, viewState);
		manageSpecificExercisesViewModel = new ManageSpecificExercisesViewModel(model, viewState);
		profileViewModel = new ProfileViewModel(model, viewState);
		addMeetingViewModel = new AddMeetingViewModel(model,viewState);
		leaderboardViewModel= new LeaderboardViewModel(model);
		this.addFriendViewModel = new AddFriendViewModel(model,viewState);
		editRosterViewModel = new EditRosterViewModel(model, viewState);
	}

/**
 * 
 * getter for CreateUserViewModel class
 *
 * @return instance of CreateUserViewModel
 *        
 */
	public CreateUserViewModel getCreateUserViewModel() {
		return createUserViewModel;
	}

/**
 * 
 * getter for LoginViewModel class
 *
 * @return instance of LoginViewModel
 *        
 */
	public LoginViewModel getLoginViewModel(){ return loginViewModel;	}


    /**
     * 
     * getter for HomeViewModel class
     *
     * @return instance of HomeViewModel
     *        
     */
    public HomeViewModel getHomeViewModel() { return  homeViewModel; }
    /**
     * 
     * getter for ManageFolderViewModel class
     *
     * @return instance of ManageFolderViewModel
     *        
     */
    public ManageFolderViewModel getManageFolderViewModel() { return  manageFolderViewModel; }
/**
 * 
 * getter for AddExerciseViewModel class
 *
 * @return instance of AddExerciseViewModel
 *        
 */
	public AddExerciseViewModel getAddExerciseViewModel() { return  addExerciseViewModel; }
    /**
     * 
     * getter for ManageSpecificExercisesViewModel class
     *
     * @return instance of ManageSpecificExercisesViewModel
     *        
     */
    public ManageSpecificExercisesViewModel getManageSpecificExercisesViewModel() { return  manageSpecificExercisesViewModel; }
    /**
     * 
     * getter for ProfileViewModel class
     *
     * @return instance of ProfileViewModel
     *        
     */
    public ProfileViewModel getProfileViewModel() { return  profileViewModel; }
/**
 * 
 * getter for AddFriendViewModel class
 *
 * @return instance of AddFriendViewModel
 *        
 */
	public AddFriendViewModel getAddFriendViewModel()
	{
		return addFriendViewModel;
	}

/**
 * 
 * getter for AddMeetingViewModel class
 *
 * @return instance of AddMeetingViewModel
 *        
 */
	public AddMeetingViewModel getAddMeetingViewModel()
	{
		return addMeetingViewModel;
	}
/**
 * 
 * getter for EditRosterViewModel class
 *
 * @return instance of EditRosterViewModel
 *        
 */
	public EditRosterViewModel getEditRosterViewModel(){
		return editRosterViewModel;
	}

/**
 * 
 * getter for LeaderboardViewModel class
 *
 * @return instance of LeaderboardViewModel
 *        
 */
	public LeaderboardViewModel getLeaderboardViewModel()
	{
		return leaderboardViewModel;
	}


}
