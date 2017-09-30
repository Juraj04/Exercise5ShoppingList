package com.example.janik.exercise5shoppinglist;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.janik.exercise5shoppinglist.Classes.Item;

/**
 * Created by janik on 28.09.2017.
 */

public class AddItemDialog extends DialogFragment {

    public interface AddItemDialogListener{
        void onDialogPositiveClick(DialogFragment d, String name, int count, double price);
        void onDialogNegativeClick(DialogFragment d);
    }

    AddItemDialogListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            listener = (AddItemDialogListener) context;

        }catch(ClassCastException e){
            throw new ClassCastException(context.toString() + " something went wrong");

        }

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.add_item_dialog,null);

        builder.setView(dialogView)
                .setTitle("Add item")
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText etName = (EditText) dialogView.findViewById(R.id.editText);
                        EditText etCount = (EditText) dialogView.findViewById(R.id.editText2);
                        EditText etPrice = (EditText) dialogView.findViewById(R.id.editText3);

                        String name = etName.getText().toString();
                        int count = Integer.parseInt(etCount.getText().toString());
                        double price = Double.parseDouble(etPrice.getText().toString());


                        listener.onDialogPositiveClick(AddItemDialog.this, name, count, price);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onDialogNegativeClick(AddItemDialog.this);
                    }
                });

        return builder.create();
    }
}
