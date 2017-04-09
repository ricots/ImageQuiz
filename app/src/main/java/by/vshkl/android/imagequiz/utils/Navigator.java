package by.vshkl.android.imagequiz.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import by.vshkl.android.imagequiz.R;
import by.vshkl.android.imagequiz.ui.fragment.InitFragment;
import by.vshkl.android.imagequiz.ui.fragment.QuizFragment;
import by.vshkl.android.imagequiz.ui.fragment.RatingFragment;

public class Navigator {

    public static void showInit(FragmentActivity activity) {
        replaceFragment(activity, InitFragment.newInstance(), false);
    }

    public static void showQuiz(FragmentActivity activity) {
        replaceFragment(activity, QuizFragment.newInstance(), false);
    }

    public static void showRating(FragmentActivity activity) {
        replaceFragment(activity, RatingFragment.newInstance(), true);
    }

    private static void replaceFragment(FragmentActivity activity, Fragment fragment, boolean addToBackStack) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_root_container, fragment, fragment.getTag());
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.getTag());
        } else {
            for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
                fragmentManager.popBackStack();
            }
        }
        fragmentTransaction.commit();
    }
}
