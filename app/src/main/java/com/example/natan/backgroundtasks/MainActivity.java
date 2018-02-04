package com.example.natan.backgroundtasks;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;


import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.natan.backgroundtasks.Network.NetworkUtils;
import com.example.natan.backgroundtasks.Pojo.Contacts;
import com.example.natan.backgroundtasks.Pojo.MyAdapter;

import org.json.JSONException;


import java.net.URL;
import java.util.List;

import static android.support.v7.widget.DividerItemDecoration.HORIZONTAL;


public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MyAdapter mMyAdapter;
    private ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressBar=findViewById(R.id.progressBar);
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration itemDecor = new DividerItemDecoration(this, HORIZONTAL);

        mRecyclerView.addItemDecoration(itemDecor);
        URL ur1=NetworkUtils.buildURl();
        new MyAsyncTask().execute(ur1);


    }


    class MyAsyncTask extends AsyncTask<URL, Void, List<Contacts>> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<Contacts> doInBackground(URL... urls) {


            try {
                List<Contacts> json = NetworkUtils.fetchContactData(urls[0]);
                return json;
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }


        @Override
        protected void onPostExecute(List<Contacts> contacts) {
            super.onPostExecute(contacts);
            mProgressBar.setVisibility(View.INVISIBLE);

            mMyAdapter = new MyAdapter(contacts, new MyAdapter.RecyclerViewClickListener() {
                @Override
                public void onClick(Contacts contacts) {
                    Toast.makeText(MainActivity.this, String.valueOf(contacts.getPhone()), Toast.LENGTH_SHORT).show();
                }
            });

            mRecyclerView.setAdapter(mMyAdapter);
            mMyAdapter.notifyDataSetChanged();
        }
    }
}
