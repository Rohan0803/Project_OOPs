package rohan.myappcompany.e_laundrysystemapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Main_Window extends AppCompatActivity {
    TextView Welcome,Select,Washing_text,Iron_text,DryCleaning_text;
    ImageView Washing,Iron,DryCleaning;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__window);

        Welcome = (TextView)findViewById(R.id.Welcome_text);
        Select = (TextView)findViewById(R.id.Selct_Options);
        Washing_text =(TextView)findViewById(R.id.washing_text);
        Iron_text = (TextView)findViewById(R.id.Iron_text);
        DryCleaning_text =(TextView)findViewById(R.id.DryCleaning_text);

        Washing = (ImageView) findViewById(R.id.washing);
        Iron =(ImageView)findViewById(R.id.Iron);
        DryCleaning = (ImageView)findViewById(R.id.DryCleaning);


        Washing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main_Window.this,RateCard_activity.class);
                intent.putExtra("name","washing_card");
                startActivity(intent);

            }
        });

        Iron.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main_Window.this,Rate_Iron.class);
                intent.putExtra("name","Iron_card");
                startActivity(intent);

            }
        });

        DryCleaning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main_Window.this,Dry_CleaningRate.class);
                intent.putExtra("name","DryCleaning_Rate_card");
                startActivity(intent);
            }
        });




    }




}