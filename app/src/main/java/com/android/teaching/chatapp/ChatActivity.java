package com.android.teaching.chatapp;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    private ListView listView;
    private MyAdapter myAdapter;
    private FirebaseDatabase database;
    private ChatFirebaseInteractor chatFirebaseInteractor;
    DatabaseReference ref;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    Bandas bandas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        boolean hasConnectivity = networkInfo != null && networkInfo.isConnectedOrConnecting();
        if (hasConnectivity){

            String token = FirebaseInstanceId.getInstance().getToken();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference databaseReference = database.getReference("message");
            databaseReference.setValue(token);

            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference myRef = firebaseDatabase.getReference("bandas");
            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot nodoBanda : dataSnapshot.getChildren()){
                        Bandas bandas = nodoBanda.getValue(Bandas.class);
                        if (bandas != null){

                        }else {

                        }
                    }
                }





                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });



        }else {
            Toast.makeText(this,"You don´t have Internet connection",Toast.LENGTH_LONG).show();
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intentIcon = new Intent(this, NewMessageActivity.class);
        startActivity(intentIcon);
        return super.onOptionsItemSelected(item);
    }

    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return chatFirebaseInteractor.getBandas().size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.list_item, viewGroup, false);
            TextView textView = rowView.findViewById(R.id.username);
            TextView textView2 = rowView.findViewById(R.id.text);

            return rowView;
        }
    }
}
