package com.example.janik.exercise5shoppinglist.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.janik.exercise5shoppinglist.Classes.Item;
import com.example.janik.exercise5shoppinglist.R;

import java.util.ArrayList;

/**
 * Created by janik on 28.09.2017.
 */

public class ListItemAdapter extends ArrayAdapter<Item> {

    private Context context;
    private ArrayList<Item> items;

    public ListItemAdapter(Context context, ArrayList<Item> objects) {
        super(context, R.layout.list_item_detail,objects);
        this.context = context;
        this.items = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item_detail, parent, false);

        TextView twName = (TextView) rowView.findViewById(R.id.textView);
        TextView twCount = (TextView) rowView.findViewById(R.id.textView2);
        TextView twPrice = (TextView) rowView.findViewById(R.id.textView3);
        //TextView twId = (TextView) rowView.findViewById(R.id.textView4);

        Item i = this.items.get(position);
        twName.setText(i.getName());
        twCount.setText(Integer.toString(i.getCount()));
        twPrice.setText(Double.toString(i.getPrice()));
        //twId.setText(Long.toString(i.getId()));

        return rowView;
    }
}
