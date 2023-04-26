package viewModel;

import modelClient.Model;

public class ViewModelFactory {

	private CreateUserViewModel createUserViewModel;
	private LoginViewModel loginViewModel;
	private HomeViewModel homeViewModel;
	private ViewState viewState;

	public ViewModelFactory(Model model) {
		viewState = new ViewState();
		this.createUserViewModel=new CreateUserViewModel(model);
		this.loginViewModel = new LoginViewModel(model);
		homeViewModel = new HomeViewModel(model, viewState);
	}

	public CreateUserViewModel getCreateUserViewModel() {
		return createUserViewModel;
	}

	public LoginViewModel getLoginViewModel(){
		return loginViewModel;
	}

	public HomeViewModel getHomeViewModel() { return homeViewModel; }



}
