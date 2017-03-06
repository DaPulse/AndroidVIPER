package demoapp.dapulse.com.dapulsedemoapp.dagger;

import dagger.Component;
import demoapp.dapulse.com.dapulsedemoapp.features.employees.base.BaseEmployeeActivity;

@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = {EmployeesModule.class})
public interface EmployeesComponent {
    void inject(BaseEmployeeActivity activity);
}
