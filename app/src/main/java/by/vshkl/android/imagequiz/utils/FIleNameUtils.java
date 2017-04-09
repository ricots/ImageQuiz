package by.vshkl.android.imagequiz.utils;

public class FIleNameUtils {

    public static String[] getFileNames(int id) {
        String[] images = new String[4];

        for (int i = 0; i < 4; i++) {
            StringBuilder sb = new StringBuilder("pic/");
            sb.append(id).append(".").append(i + 1).append(".jpg");
            images[i] = sb.toString();
        }

        return images;
    }
}
