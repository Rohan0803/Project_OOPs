package rohan.myappcompany.e_laundrysystemapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.badge.BadgeUtils;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import rohan.myappcompany.e_laundrysystemapp.prevalent.prevalent;

public class Confirmation_Activity extends AppCompatActivity {

    TextView Confirmation_text,Message_text,Time_text,review;
    RatingBar ratingBar;
    Button done;
    String rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_);
        Confirmation_text = (TextView)findViewById(R.id.Confirmed);
        Message_text = (TextView)findViewById(R.id.Message);
        Time_text = (TextView)findViewById(R.id.Time);
        review = (TextView)findViewById(R.id.Review_text);
        ratingBar  = (RatingBar)findViewById(R.id.Rate_bar);
        done = (Button)findViewById(R.id.done);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rate = String.valueOf(ratingBar.getRating());
                if(rate==null){
                    Toast.makeText(Confirmation_Activity.this, "please Rate Your Views..", Toast.LENGTH_SHORT).show();
                }else {
                    saveReview();
                }
            }
        });







    }

    private void saveReview() {
        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Review").child(prevalent.CurrentOnlineUsers.getPhone());
        HashMap<String,Object> review = new HashMap<>();
        review.put("Review",rate);

        ref.updateChildren(review).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(Confirmation_Activity.this, "Thank You For Rating\n Hope,We will See you again..", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),Login_Activity.class);
                startActivity(intent);
            }
        });
    }


}