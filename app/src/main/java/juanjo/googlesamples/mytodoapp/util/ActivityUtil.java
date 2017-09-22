package juanjo.googlesamples.mytodoapp.util;


import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by juanjoberenguer on 22/9/17.
 */

public class ActivityUtil {
    public static void addFragmentToActivity(@NonNull FragmentManager manager,
                                             @NonNull Fragment fragment, int container){

        checkNotNull(manager);
        checkNotNull(fragment);

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(container,fragment);
        transaction.commit();
    }
}
