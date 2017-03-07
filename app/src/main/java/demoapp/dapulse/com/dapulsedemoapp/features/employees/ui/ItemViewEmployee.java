package demoapp.dapulse.com.dapulsedemoapp.features.employees.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import demoapp.dapulse.com.dapulsedemoapp.R;
import demoapp.dapulse.com.dapulsedemoapp.features.employees.models.Employee;

/**
 * Created by danfa on 25/02/2017.
 */

public class ItemViewEmployee extends FrameLayout {

    @BindView(R.id.container)
    ViewGroup container;

    @BindView(R.id.user_image)
    ImageView imageView;

    @BindView(R.id.user_name)
    TextView name;

    @BindView(R.id.job_title)
    TextView jobTitle;

    private Employee employee;

    public ItemViewEmployee(Context context) {
        super(context);
        init();
    }

    public ItemViewEmployee(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ItemViewEmployee(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ItemViewEmployee(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }


    private void init() {
        inflate(getContext(), R.layout.view_employee_item, this);
        if (!isInEditMode()) {
            ButterKnife.bind(this);
        }
    }


    public void showEmployee(Employee employee) {
        this.employee = employee;
        jobTitle.setText(employee.title);
        name.setText(employee.name);
        Picasso.with(getContext()).load(employee.profilePic).placeholder(R.mipmap.ic_launcher).into(imageView, new Callback() {
            @Override
            public void onSuccess() {
                Bitmap imageBitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                RoundedBitmapDrawable imageDrawable = RoundedBitmapDrawableFactory.create(getResources(), imageBitmap);
                imageDrawable.setCircular(true);
                imageDrawable.setCornerRadius(Math.max(imageBitmap.getWidth(), imageBitmap.getHeight()) / 2.0f);
                imageView.setImageDrawable(imageDrawable);
            }

            @Override
            public void onError() {

            }
        });
    }



}
