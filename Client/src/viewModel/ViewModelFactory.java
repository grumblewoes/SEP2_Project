package viewModel;

import modelClient.Model;

public class ViewModelFactory {

	private CreateUserViewModel createUserViewModel;
	private Model model;

	public ViewModelFactory(Model model) {

	}

	public CreateUserViewModel getCreateUserViewModel() {
		return createUserViewModel;
	}

}
