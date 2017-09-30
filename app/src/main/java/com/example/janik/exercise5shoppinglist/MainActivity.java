package com.example.janik.exercise5shoppinglist;

import android.app.DialogFragment;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.janik.exercise5shoppinglist.Adapters.ListItemAdapter;
import com.example.janik.exercise5shoppinglist.Classes.Item;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AddItemDialog.AddItemDialogListener{

    private ArrayList<Item> Items = new ArrayList<>();
    private ListView listview;
    private ListAdapter adapter;
    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHandler(this);

        this.Items = db.getAllItems();

        listview = (ListView) findViewById(R.id.listView);
        adapter = new ListItemAdapter(this, this.Items);
        listview.setAdapter(adapter);

        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                db.deleteItem(Items.get(position));
                Items.remove(position);
                adapter = new ListItemAdapter(parent.getContext(),Items);
                listview.setAdapter(adapter);
                Toast.makeText(parent.getContext(),getPrice() + "",Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add:
                AddItemDialog eDialog = new AddItemDialog();
                eDialog.show(getFragmentManager(),"Text Dialog");
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public void onDialogPositiveClick(DialogFragment d, String name, int count, double price) {
        long id = db.addItem(name, count, price);
        Item item = new Item(id, name, count, price);
        this.Items.add(item);
        Toast.makeText(this,this.getPrice() + "",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment d) {
        Toast.makeText(this,"Canceled",Toast.LENGTH_LONG).show();
    }

    public double getPrice(){
        double price = 0.0;
        for (Item i: this.Items){
            price += i.getPrice();
        }

        return price;
    }

}
