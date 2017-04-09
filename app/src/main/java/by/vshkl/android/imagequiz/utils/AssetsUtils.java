package by.vshkl.android.imagequiz.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import by.vshkl.android.imagequiz.mvp.model.QuizItem;

public class AssetsUtils {

    public static Bitmap getBitmap(Context context, String fileName) {
        AssetManager assetManager = context.getAssets();
        InputStream inputStream = null;

        try {
            inputStream = assetManager.open(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return BitmapFactory.decodeStream(inputStream);
    }

    public static List<QuizItem> readInfoFromAsset(Context context, String fileName) {
        AssetManager assetManager = context.getAssets();
        InputStream inputStream = null;
        List<QuizItem> lines = null;

        try {
            inputStream = assetManager.open(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (inputStream != null) {
            lines = new ArrayList<>(20);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            try {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    lines.add(new QuizItem(line));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return lines != null ? lines : Collections.<QuizItem>emptyList();
    }
}
