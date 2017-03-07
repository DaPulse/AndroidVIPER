package demoapp.dapulse.com.dapulsedemoapp.base;

import android.os.Bundle;

import java.util.List;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by danfa on 24/02/2017.
 */

public abstract class BasePresenter {
    private CompositeSubscription subscriptions = new CompositeSubscription();


    public void addSubscription(Subscription subscription) {
        subscriptions.add(subscription);
    }

    public void onResume() {

    }
    public void unsubscribe() {
        subscriptions.unsubscribe();
    }

    public abstract RestoreView getRestoreView();



    public interface RestoreView {
        Bundle saveToBundle(Bundle bundle);
        void restoreData(Bundle bundle);
        boolean canRestore(Bundle savedInstanceState);
    }
}
