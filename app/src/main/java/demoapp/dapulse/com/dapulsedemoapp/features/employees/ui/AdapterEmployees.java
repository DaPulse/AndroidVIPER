package demoapp.dapulse.com.dapulsedemoapp.features.employees.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import demoapp.dapulse.com.dapulsedemoapp.R;
import demoapp.dapulse.com.dapulsedemoapp.features.employees.models.Employee;
import rx.functions.Action1;

/**
 * Created by danfa on 24/02/2017.
 */

public class AdapterEmployees extends RecyclerView.Adapter<AdapterEmployees.ViewHolder> {


    private final LayoutInflater inflater;
    private ArrayList<Employee> data = new ArrayList<>();

    private Action1<Employee> callback;


    public AdapterEmployees(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    public void setData(ArrayList<Employee> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void registerForClicks(Action1<Employee> subscriber) {
        this.callback = subscriber;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item_employee, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.itemView.setOnClickListener(view1 -> {
            int adapterPosition = viewHolder.getAdapterPosition();
            if (callback != null && adapterPosition != RecyclerView.NO_POSITION) {
                callback.call(data.get(adapterPosition));
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Employee employee = data.get(position);
        holder.employeeView.showEmployee(employee);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public ArrayList<Employee> getData() {
        return data;
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.employee_view)
        ItemViewEmployee employeeView;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
