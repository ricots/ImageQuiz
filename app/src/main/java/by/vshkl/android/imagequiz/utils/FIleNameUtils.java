package by.vshkl.android.imagequiz.utils;

public class FIleNameUtils {

    public static String[][] getFileNames(int id) {
        String[][] images = new String[2][2];

        int k = 1;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                StringBuilder sb = new StringBuilder("pic/");
                sb.append(id).append(".").append(k).append(".jpg");
                images[i][j] = sb.toString();
                k++;
            }
        }

        return images;
    }
}
