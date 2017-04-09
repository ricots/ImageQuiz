package by.vshkl.android.imagequiz.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import by.vshkl.android.imagequiz.R;

public class DialogUtils {

    public static void showEmptyNameAlert(final Context context) {
        new AlertDialog.Builder(context)
                .setTitle(R.string.dialog_empty_name_title)
                .setMessage(R.string.dialog_empty_name_message)
                .setPositiveButton(R.string.dialog_empty_name_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
    }
}
