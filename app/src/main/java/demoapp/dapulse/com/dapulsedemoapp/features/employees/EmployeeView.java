package demoapp.dapulse.com.dapulsedemoapp.features.employees;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import demoapp.dapulse.com.dapulsedemoapp.R;
import demoapp.dapulse.com.dapulsedemoapp.base.BasePresenter;
import demoapp.dapulse.com.dapulsedemoapp.features.employees.models.Employee;
import demoapp.dapulse.com.dapulsedemoapp.features.employees.ui.AdapterEmployees;
import demoapp.dapulse.com.dapulsedemoapp.features.employees.ui.ItemViewEmployee;
import demoapp.dapulse.com.dapulsedemoapp.utils.SpacesItemDecoration;
import rx.functions.Action1;

/**
 * Created by danfa on 24/02/2017.
 */

public class EmployeeView implements EmployeesVIP.View{

    private static final String EMPLOYEE_DATA = "employee_data";
    private final AdapterEmployees adapter;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Nullable
    @BindView(R.id.view_switcher)
    ViewSwitcher viewSwitcher;

    @BindView(R.id.page_title)
    TextView pageTitle;

    @Nullable
    @BindView(R.id.employee_view)
    ItemViewEmployee managerView;

    @BindDimen(R.dimen.item_space)
    int space;

    public EmployeeView(AppCompatActivity activity, AdapterEmployees adapter) {
        this.adapter = adapter;
        ButterKnife.bind(this, activity);
        recyclerView.setLayoutManager(new GridLayoutManager(activity, 3));
        recyclerView.addItemDecoration(new SpacesItemDecoration(space));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onUsersLoaded(ArrayList<Employee> employees) {
        if (viewSwitcher != null && viewSwitcher.getCurrentView().getId() == R.id.progress_bar) {
            viewSwitcher.showNext();
        }
        adapter.setData(employees);
    }

    public void registerEmployeeListClicks(Action1<Employee> clickAction) {
         adapter.registerForClicks(clickAction);
    }


    @Override
    public void showManagerLayout(Employee manager, Action1<Employee> clickAction) {
        if (managerView != null) {
            managerView.showEmployee(manager);
            managerView.setOnClickListener(view -> clickAction.call(manager));
        }

        setPageTitle(manager.department);
    }

    @Override
    public void setPageTitle(String title) {
        pageTitle.setText(title);
    }

    @Override
    public Bundle saveToBundle(Bundle bundle) {
        ArrayList<Employee> data = adapter.getData();
        if (data != null) {
            bundle.putParcelableArrayList(EMPLOYEE_DATA, data);
        }
        return bundle;
    }

    @Override
    public void restoreData(Bundle bundle) {
        Log.d("DemoApp", "data restored");
        ArrayList<Employee> employees = bundle.getParcelableArrayList(EMPLOYEE_DATA);
        onUsersLoaded(employees);
    }

    @Override
    public boolean canRestore(Bundle savedInstanceState) {
        return savedInstanceState.containsKey(EMPLOYEE_DATA);
    }
}
