package com.example.loginpage.dagger;

import com.example.loginpage.database.Repository.UserDBRepository;
import com.example.loginpage.viewModel.LogInVm;
import com.example.loginpage.viewModel.SignVM;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component
public interface ApplicationComponent {
    SignVM getSiginVm();
    LogInVm getLoginVm();

    UserDBRepository getUserRepository();
}
