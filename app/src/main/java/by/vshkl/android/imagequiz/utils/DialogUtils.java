package by.vshkl.android.imagequiz.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import by.vshkl.android.imagequiz.R;
import by.vshkl.android.imagequiz.ui.listener.LifeRefillListener;

public class DialogUtils {

    public static void showEmptyNameDialog(final Context context) {
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

    public static void showLifeRefillDialog(final Context context, final LifeRefillListener listener) {
        new AlertDialog.Builder(context)
                .setTitle(R.string.dialog_refill_life_title)
                .setMessage(R.string.dialog_refill_life_message)
                .setPositiveButton(R.string.dialog_refill_life_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onConfirmLifeRefill();
                    }
                })
                .setNegativeButton(R.string.dialog_refill_life_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create().show();
    }

    public static void showNetworkTurnOnDialog(final Context context) {
        new AlertDialog.Builder(context)
                .setTitle(R.string.dialog_network_title)
                .setMessage(R.string.dialog_network_message)
                .setPositiveButton(R.string.dialog_network_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
    }
}
