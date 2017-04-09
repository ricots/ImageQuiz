package by.vshkl.android.imagequiz.ui.activity;

import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;

import by.vshkl.android.imagequiz.R;

public class MainActivity extends MvpAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
