package rohan.myappcompany.e_laundrysystemapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//import io.paperdb.Paper;
import rohan.myappcompany.e_laundrysystemapp.Models.Users;
import rohan.myappcompany.e_laundrysystemapp.prevalent.prevalent;

public class MainActivity extends AppCompatActivity {
    private Button joinowButton,LoginButton;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        joinowButton = (Button) findViewById(R.id.main_Join_now_btn);
        LoginButton = (Button) findViewById(R.id.main_login_btn);
      //  Paper.init(this);
        loadingBar = new ProgressDialog(this);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Login_Activity.class);
                startActivity(intent);
            }
        });

        joinowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Register_Activity.class);
                startActivity(intent);
            }
        });

  /*      String UserPhoneKey = Paper.book().read(prevalent.UserPhoneKey);
        String UserPasswordKey = Paper.book().read(prevalent.UserPasswordKey);

        if(UserPhoneKey != "" && UserPasswordKey!=""){
            if(!TextUtils.isEmpty(UserPhoneKey) && !TextUtils.isEmpty(UserPasswordKey)){
                AllowAccess(UserPhoneKey,UserPasswordKey);
                loadingBar.setTitle("Already Logged in Account");
                loadingBar.setMessage("please Wait while checking Credentials..");
                loadingBar.setCanceledOnTouchOutside(false);
                loadingBar.show();
            }

        }*/
    }

 /*   private void AllowAccess(final String phone, final String password) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child("Users").child(phone).exists()){
                    Users usersdata = dataSnapshot.child("Users").child(phone).getValue(Users.class);
                    if (usersdata.getPhone().equals(phone)){
                        if(usersdata.getPassword().equals(password)){
                            Toast.makeText(MainActivity.this, "Logged In Successfully... ", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();
                            Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                            startActivity(intent);
                        }else{
                            loadingBar.dismiss();
                            Toast.makeText(MainActivity.this, "Incorrect Password..", Toast.LENGTH_SHORT).show();

                        }
                    }


                }else{
                    Toast.makeText(MainActivity.this, "Invalid Credentials..PLease Try again with another Phone Number", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }*/
}