package com.example.natan.backgroundtasks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.natan.backgroundtasks.Pojo.Contacts;
import com.example.natan.backgroundtasks.Utils.PrefrencesKeys;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailActivity extends AppCompatActivity {
    TextView txt_name, txt_number;
    CircleImageView img_dp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        txt_name = findViewById(R.id.txt_detail_name);
        txt_number = findViewById(R.id.txt_detail_number);
        img_dp = findViewById(R.id.img_detail);

        Contacts contacts = getIntent().getParcelableExtra(PrefrencesKeys.Parcelable_key);

        if (contacts != null) {
            txt_name.setText(contacts.getName().toString());
            txt_number.setText(contacts.getPhone().toString());
            Picasso.with(this).load(contacts.getImage()).into(img_dp);

        }

    }
}
