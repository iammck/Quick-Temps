package com.mck.localweathernow.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import com.mck.localweathernow.R;


/**
 * Shows a dialog box that informs the user that this app requires perms to work.
 * Created by Michael on 5/16/2016.
 */
public class RequiresPermissionsRationaleDialogFragment extends DialogFragment {
    public static final String TAG = "ReqPermDialogFrag";
    private RequiresPermissionsRationaleCallback callback;

    public static RequiresPermissionsRationaleDialogFragment
    newInstance(RequiresPermissionsRationaleCallback requiresPermissionsRationaleCallback) {
        RequiresPermissionsRationaleDialogFragment result =
                new RequiresPermissionsRationaleDialogFragment();
        result.callback = requiresPermissionsRationaleCallback;
        return result;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use a builder to construct the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View layout = inflater.inflate(R.layout.permissions_required, null);
        builder .setView(layout)
                .setPositiveButton(R.string.try_again, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        callback.requiresPermissionsRationaleTryAgain();
                    }
                })
                .setNegativeButton(R.string.exit, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        getActivity().finish();
                    }
                })
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        callback.requiresPermissionsRationaleTryAgain();
                    }
                });
        return builder.create();
    }

    public interface RequiresPermissionsRationaleCallback {
        void requiresPermissionsRationaleTryAgain();
    }


}