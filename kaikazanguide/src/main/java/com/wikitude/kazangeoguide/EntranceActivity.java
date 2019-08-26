package com.wikitude.kazangeoguide;

import com.wikitude.kazangeoguide.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.widget.Button;

public class EntranceActivity extends AppCompatActivity implements View.OnClickListener
{
    Button btn1,btn2;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrance);
        btn1 = (Button)findViewById(R.id.button);
        btn2 = (Button)findViewById(R.id.button2);

    }

    public void onClick(View v){

        if(v.getId() == R.id.button){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        }else if(v.getId() == R.id.button2){
            Intent intent2 = new Intent(this, ClassifierActivity.class);
            startActivity(intent2);
        }

    }

}