package demoapp.dapulse.com.dapulsedemoapp.features.employees.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Employee implements Parcelable{

@SerializedName("id")
@Expose
public Integer id;
@SerializedName("name")
@Expose
public String name;
@SerializedName("phone")
@Expose
public String phone;
@SerializedName("email")
@Expose
public String email;
@SerializedName("title")
@Expose
public String title;
@SerializedName("profile_pic")
@Expose
public String profilePic;
@SerializedName("manager_id")
@Expose
public Integer managerId;
@SerializedName("department")
@Expose
public String department;
@SerializedName("is_manager")
@Expose
public Boolean isManager;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeString(this.phone);
        dest.writeString(this.email);
        dest.writeString(this.title);
        dest.writeString(this.profilePic);
        dest.writeValue(this.managerId);
        dest.writeString(this.department);
        dest.writeValue(this.isManager);
    }

    public Employee() {
    }

    protected Employee(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.phone = in.readString();
        this.email = in.readString();
        this.title = in.readString();
        this.profilePic = in.readString();
        this.managerId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.department = in.readString();
        this.isManager = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public static final Creator<Employee> CREATOR = new Creator<Employee>() {
        @Override
        public Employee createFromParcel(Parcel source) {
            return new Employee(source);
        }

        @Override
        public Employee[] newArray(int size) {
            return new Employee[size];
        }
    };
}