package com.mhamed.mymoviecompanion;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class CustomDialog extends AppCompatDialogFragment {
    CustomDialogInterface dialogInterface;
    EditText critique;
    NumberPicker numberPicker;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);
        builder.setView(view)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String cr = critique.getText().toString();
                        int n = numberPicker.getValue();
                        dialogInterface.applyTexts(cr, n);
                    }
                });


        critique = view.findViewById(R.id.critique);
        numberPicker = view.findViewById(R.id.note);
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(20);
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        dialogInterface = (CustomDialogInterface) context;

    }

    public interface CustomDialogInterface {
        void applyTexts(String critique, int note);

    }
}
