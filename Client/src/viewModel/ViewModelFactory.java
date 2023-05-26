package viewModel;

import modelClient.Model;

/**
 * 
 * 
 * 
 * @author 
 * @version 
 */
public class ViewModelFactory {

	private CreateUserViewModel createUserViewModel;
	private LoginViewModel loginViewModel;
	private EditTraineeInfoViewModel editTraineeInfoViewModel;
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
 * 
 * 
 * @param model 
 *        
 */
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
		addMeetingViewModel = new AddMeetingViewModel(model,viewState);
		leaderboardViewModel= new LeaderboardViewModel(model);

		this.addFriendViewModel = new AddFriendViewModel(model,viewState);

		editRosterViewModel = new EditRosterViewModel(model, viewState);
		//manageCoachViewModel = new ManageCoachViewModel(model, viewState);
	}

/**
 * 
 * 
 *
 * @return 
 *        
 */
	public CreateUserViewModel getCreateUserViewModel() {
		return createUserViewModel;
	}

/**
 * 
 * 
 *
 * @return 
 *        
 */
	public LoginViewModel getLoginViewModel(){ return loginViewModel;	}


    /**
     * 
     * 
     *
     * @return 
     *        
     */
    public HomeViewModel getHomeViewModel() { return  homeViewModel; }
    /**
     * 
     * 
     *
     * @return 
     *        
     */
    public ManageFolderViewModel getManageFolderViewModel() { return  manageFolderViewModel; }
/**
 * 
 * 
 *
 * @return 
 *        
 */
	public AddExerciseViewModel getAddExerciseViewModel() { return  addExerciseViewModel; }
    /**
     * 
     * 
     *
     * @return 
     *        
     */
    public ManageSpecificExercisesViewModel getManageSpecificExercisesViewModel() { return  manageSpecificExercisesViewModel; }
    /**
     * 
     * 
     *
     * @return 
     *        
     */
    public ProfileViewModel getProfileViewModel() { return  profileViewModel; }
/**
 * 
 * 
 *
 * @return 
 *        
 */
	public AddFriendViewModel getAddFriendViewModel()
	{
		return addFriendViewModel;
	}

/**
 * 
 * 
 *
 * @return 
 *        
 */
	public AddMeetingViewModel getAddMeetingViewModel()
	{
		return addMeetingViewModel;
	}
/**
 * 
 * 
 *
 * @return 
 *        
 */
	public EditRosterViewModel getEditRosterViewModel(){
		return editRosterViewModel;
	}

/**
 * 
 * 
 *
 * @return 
 *        
 */
	public LeaderboardViewModel getLeaderboardViewModel()
	{
		return leaderboardViewModel;
	}

/**
 * 
 * 
 *
 * @return 
 *        
 */
	//	public ViewModel getManageCoachViewModel() {
//		return manageCoachViewModel;
//	}
}
