package rohan.myappcompany.e_laundrysystemapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.HashSet;

public class Register_Activity extends AppCompatActivity {
    private Button createAccountButtton;
    EditText Inputname,InputPhoneNumber,InputPassowrd;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);

        createAccountButtton =(Button)findViewById(R.id.main_Register_btn);
        Inputname = (EditText)findViewById(R.id.Register_Username_input);
        InputPhoneNumber = (EditText)findViewById(R.id.Register_phone_number_input);
        InputPassowrd = (EditText) findViewById(R.id.Register_password_input);
        loadingBar = new ProgressDialog(this);

        createAccountButtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount();
            }
        });

    }

    private void createAccount() {
        String name = Inputname.getText().toString();
        String phone = InputPhoneNumber.getText().toString();
        String password = InputPassowrd.getText().toString();

        if(TextUtils.isEmpty(name)){
            Toast.makeText(this, "Please Enter Your Name...", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(phone)){
            Toast.makeText(this, "Please Enter Your Phone Number...", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please Enter a Password...", Toast.LENGTH_SHORT).show();
        }
        else{
            loadingBar.setTitle("Create Account");
            loadingBar.setMessage("please Wait while checking Credentials..");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();
            ValidatePhoneNumber(name,phone,password);
        }

    }

    private void ValidatePhoneNumber(final String name, final String phone, final String password) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!(dataSnapshot.child("Users").child(phone).exists())){
                    HashMap<String,Object> UserDataMap = new HashMap<>();
                    UserDataMap.put("Phone",phone);
                    UserDataMap.put("Password",password);
                    UserDataMap.put("Name",name);

                    RootRef.child("Users").child(phone).updateChildren(UserDataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(Register_Activity.this, "Congratulations..Your Account Has been Created Sucessfully.. ", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();
                                        Intent intent = new Intent(Register_Activity.this,Login_Activity.class);
                                        startActivity(intent);

                                    }else{
                                        loadingBar.dismiss();
                                        Toast.makeText(Register_Activity.this, "Somthing Went Wrong.. Please Try Again After Some time..", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });



                }else{
                    Toast.makeText(Register_Activity.this, "This "+phone+" Phone number already Exists..", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(Register_Activity.this, "Please Try Again Using Another Phone Number..", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Register_Activity.this,MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}