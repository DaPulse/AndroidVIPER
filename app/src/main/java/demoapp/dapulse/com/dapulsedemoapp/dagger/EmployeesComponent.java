package demoapp.dapulse.com.dapulsedemoapp.dagger;

import dagger.Component;
import demoapp.dapulse.com.dapulsedemoapp.features.employees.ui.ActivityBaseEmployee;
import demoapp.dapulse.com.dapulsedemoapp.features.employees.ui.ActivityTopLevelManagement;

@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = {EmployeesModule.class})
public interface EmployeesComponent {
    void inject(ActivityBaseEmployee activity);

    void inject(ActivityTopLevelManagement topLevelManagementActivity);
}
