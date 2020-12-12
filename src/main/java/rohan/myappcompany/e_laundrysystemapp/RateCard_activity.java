package rohan.myappcompany.e_laundrysystemapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RateCard_activity extends AppCompatActivity {
    TextView Rate_card,Washing_text;
    CheckBox Shirts,Pants,Jackets,Suits;
    Button next;
    int amount  = 0;
    ArrayList ItemsSelected;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_card_activity);

        Rate_card = (TextView)findViewById(R.id.Rate_card_Text);
        Washing_text = (TextView)findViewById(R.id.R_Washing);
        Shirts = (CheckBox)findViewById(R.id.Shirts_Chbk);
        Pants = (CheckBox)findViewById(R.id.pants_Chbk);
        Jackets = (CheckBox)findViewById(R.id.jackets_Chbk);
        Suits = (CheckBox)findViewById(R.id.suits_Chbk);
        next = (Button)findViewById(R.id.Proceed);



        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringBuilder bill = new StringBuilder();
                ItemsSelected = new ArrayList();


                if(Shirts.isChecked()){
                    amount = amount + 5;
                    ItemsSelected.add('\n'+ " Shirts For Washing" + '\n');
                }
                if(Pants.isChecked()){
                    amount = amount + 6;
                    ItemsSelected.add('\n'+"Pants For Washing"  + '\n');
                }
                if(Jackets.isChecked()){
                    amount = amount + 10;
                    ItemsSelected.add('\n'+"Jackets For Washing" + '\n');
                }
                if(Suits.isChecked()){
                    amount = amount + 15;
                    ItemsSelected.add('\n'+"Suits For Washing" + '\n');
                }






                if(!Shirts.isChecked() &&  !Pants.isChecked() && !Jackets.isChecked() && !Suits.isChecked()){
                    Toast.makeText(RateCard_activity.this, "Please Select Items..", Toast.LENGTH_SHORT).show();


                }else {
                    bill.append("Total amount : " + amount);
                    Toast.makeText(RateCard_activity.this,bill.toString()+" Rupees", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RateCard_activity.this,Order_Summery_Activity.class);
                    intent.putExtra("Amount",amount);
                    intent.putExtra("Items",ItemsSelected);
                    startActivity(intent);
                }




            }
        });






    }
}