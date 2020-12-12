package rohan.myappcompany.e_laundrysystemapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.UserData;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//import io.paperdb.Paper;
import rohan.myappcompany.e_laundrysystemapp.Models.Users;
import rohan.myappcompany.e_laundrysystemapp.prevalent.prevalent;


public class Login_Activity extends AppCompatActivity {
    private EditText InputNumber,InputPassword;
    private Button Loginbtn;
    private ProgressDialog loadingBar;
    private String parentdBname = "Users";
  //  private CheckBox RememberMe;
    private TextView AdminLink,NotAdminLink;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        Loginbtn =(Button)findViewById(R.id.main_login_btn);
        InputNumber = (EditText)findViewById(R.id.Login_phone_number_input);
        InputPassword = (EditText) findViewById(R.id.Login_password_input);
        AdminLink = (TextView)findViewById(R.id.admin_panel_link);
        NotAdminLink = (TextView)findViewById(R.id.not_ADMIN_panel_link);
        loadingBar = new ProgressDialog(this);
      /*  RememberMe = (CheckBox)findViewById(R.id.remember_me_chkb);
        Paper.init(this);*/

        Loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginUser();
            }
        });

        AdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Loginbtn.setText("Login Admin");
                AdminLink.setVisibility(View.INVISIBLE);
                NotAdminLink.setVisibility(View.VISIBLE);
                parentdBname = "Admins";

            }
        });
        NotAdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Loginbtn.setText("Login");
                AdminLink.setVisibility(View.VISIBLE);
                NotAdminLink.setVisibility(View.INVISIBLE);
                parentdBname = "Users";

            }
        });


    }

    private void LoginUser() {
        String phone = InputNumber.getText().toString();
        String password = InputPassword.getText().toString();
        if(TextUtils.isEmpty(phone)){
            Toast.makeText(this, "Please Enter Your Phone Number...", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please Enter a Password...", Toast.LENGTH_SHORT).show();
        }else{
            loadingBar.setTitle("Login Account");
            loadingBar.setMessage("please Wait while checking Credentials..");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();
            AllowAccessToAccount(phone,password);
        }



    }

    private void AllowAccessToAccount(final String phone, final String password) {
        // paper liberary use to save users onces logged in.
       /* if(RememberMe.isChecked()){
            Paper.book().write(prevalent.UserPhoneKey,phone);
            Paper.book().write(prevalent.UserPasswordKey,password);

        }*/

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(parentdBname).child(phone).exists()){
                    Users usersdata = dataSnapshot.child(parentdBname).child(phone).getValue(Users.class);
                    if (usersdata.getPhone().equals(phone)){
                        if(usersdata.getPassword().equals(password)){
                            if(parentdBname.equals("Admins")){
                                Toast.makeText(Login_Activity.this, "Welcome Admin You are Logged In Successfully... ", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                                Intent intent = new Intent(Login_Activity.this,AdminPanel_Activity.class);
                                startActivity(intent);
                            }else if(parentdBname.equals("Users")){
                                Toast.makeText(Login_Activity.this, "Logged In Successfully... ", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                                Intent intent = new Intent(Login_Activity.this,Main_Window.class);
                                prevalent.CurrentOnlineUsers = usersdata;
                                startActivity(intent);

                            }
                        }else{
                            loadingBar.dismiss();
                            Toast.makeText(Login_Activity.this, "Incorrect Password..", Toast.LENGTH_SHORT).show();

                        }
                    }
                    

                }else{
                    Toast.makeText(Login_Activity.this, "Invalid Credentials..PLease Try again with another Phone Number", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}