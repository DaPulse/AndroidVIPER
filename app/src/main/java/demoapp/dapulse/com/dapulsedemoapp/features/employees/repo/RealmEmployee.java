package demoapp.dapulse.com.dapulsedemoapp.features.employees.repo;

import io.realm.RealmObject;

/**
 * Created by ofertour on 06/02/2017.
 */

public class RealmEmployee extends RealmObject {

    public Integer id;

    public String name;

    public String phone;

    public String email;

    public String title;

    public String profilePic;

    public Integer managerId;

    public String department;

    public Boolean isManager;


}
