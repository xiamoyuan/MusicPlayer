package cn.tedu.musicplayer.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.tedu.musicplayer.R;

public class activity_lead extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lead);
        Button Btn1 =findViewById(R.id.button1);
        Btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity_lead.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
