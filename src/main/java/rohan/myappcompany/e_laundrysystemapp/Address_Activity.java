package rohan.myappcompany.e_laundrysystemapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import rohan.myappcompany.e_laundrysystemapp.prevalent.prevalent;

public class Address_Activity extends AppCompatActivity {

    TextView ADDAddress;
    EditText UserName, Phone, State, City, pincode, HouseNO;
    Button confirmBtn;
    private String Totalamount;
    ArrayList<String> arrayList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_);

        Totalamount = getIntent().getStringExtra("Amount");
        arrayList = (ArrayList<String>) getIntent().getSerializableExtra("Orders");


        ADDAddress = (TextView) findViewById(R.id.Add_Location);
        UserName = (EditText) findViewById(R.id.UserName);
        Phone = (EditText) findViewById(R.id.Phone);
        State = (EditText) findViewById(R.id.State);
        City = (EditText) findViewById(R.id.City);
        pincode = (EditText) findViewById(R.id.Pincode);
        HouseNO = (EditText) findViewById(R.id.HouseNumber);
        confirmBtn = (Button) findViewById(R.id.confirm);






        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
            }
        });


    }



    private void check() {
        if (TextUtils.isEmpty(UserName.getText().toString())) {
            Toast.makeText(this, "PLease provide Your Full name", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(Phone.getText().toString())) {
            Toast.makeText(this, "PLease provide Your Phone Number", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(State.getText().toString())) {
            Toast.makeText(this, "Please Provide Your State", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(City.getText().toString())){
            Toast.makeText(this, "Please Provide Your City name", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(pincode.getText().toString())) {
            Toast.makeText(this, "Sorry, We Don't Serve In your Location..." +
                    "Currently, Our Service is avaliable at pincode: 263153 ", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(HouseNO.getText().toString())) {
            Toast.makeText(this, "PLease provide Your House Number", Toast.LENGTH_SHORT).show();
        } else {
            ConfirmOrder();
        }



    }

    private void ConfirmOrder() {
        final String saveCurrentDate, saveCurrentTime;
        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat CurrentDate = new SimpleDateFormat("MMM dd , YYYY");
        saveCurrentDate = CurrentDate.format(calForDate.getTime());

        SimpleDateFormat CurrentTime = new SimpleDateFormat("HH : MM : SS a");
        saveCurrentTime = CurrentTime.format(calForDate.getTime());

        final DatabaseReference ordersRef = FirebaseDatabase.getInstance().getReference()
                .child("Orders")
                .child(prevalent.CurrentOnlineUsers.getPhone());

        HashMap<String, Object> ordermap = new HashMap<>();
        ordermap.put("Total_Amount", Totalamount);
        ordermap.put("Name",UserName.getText().toString());
        ordermap.put("Phone",Phone.getText().toString());
        ordermap.put("state",State.getText().toString());
        ordermap.put("City",City.getText().toString());
        ordermap.put("pincode",pincode.getText().toString());
        ordermap.put("Houseno",HouseNO.getText().toString());
        ordermap.put("Orders",String.valueOf(arrayList));
        ordermap.put("date",saveCurrentDate);
        ordermap.put("Time",saveCurrentTime);
        ordermap.put("status","Not Confirmed");

        ordersRef.updateChildren(ordermap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Address_Activity.this, "Address Added SuccessFully...", Toast.LENGTH_SHORT).show();
                    Toast.makeText(Address_Activity.this, "Your Request is Placed Successfully..", Toast.LENGTH_SHORT).show();
                    Intent intent2 = new Intent(Address_Activity.this,Confirmation_Activity.class);
                    intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent2);
                    finish();

                }

            }
        });



    }

}
