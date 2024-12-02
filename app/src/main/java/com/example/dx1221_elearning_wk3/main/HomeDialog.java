package com.example.dx1221_elearning_wk3.main;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.dx1221_elearning_wk3.mgp2d.core.GameActivity;

public class HomeDialog extends DialogFragment {
    private static boolean _isShowing = false;

    public static boolean isShowing() {
        return _isShowing;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        _isShowing = true;
        GameActivity.instance.setTimeScale(0); // Pause game

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Return to main menu?");
        builder.setPositiveButton("Yes", (dialog, which) -> {
            _isShowing = false;
            GameActivity.instance.finish(); // Close the game activity
        });
        builder.setNegativeButton("No", (dialog, which) -> {
            _isShowing = false;
            GameActivity.instance.setTimeScale(1); // Resume game
        });

        return builder.create();
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        _isShowing = false;
        GameActivity.instance.setTimeScale(1); // Resume game
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        _isShowing = false;
        GameActivity.instance.setTimeScale(1); // Resume game
    }
}
