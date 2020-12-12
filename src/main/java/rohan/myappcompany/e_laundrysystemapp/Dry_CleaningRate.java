package rohan.myappcompany.e_laundrysystemapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Dry_CleaningRate extends AppCompatActivity {

    TextView Rate_DC_card,Washing_DC_text;
    CheckBox Shirts_DC,Pants_DC,Jackets_DC,Suits_DC;
    Button next_DC;
    int amount  = 0;
    ArrayList ItemsSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dry__cleaning_rate);

        Rate_DC_card = (TextView)findViewById(R.id.DryCleaning_text);
        Washing_DC_text = (TextView)findViewById(R.id.R_DC);
        Shirts_DC = (CheckBox)findViewById(R.id.Shirts_DC_Chbk);
        Pants_DC = (CheckBox)findViewById(R.id.pants_DC_Chbk);
        Jackets_DC = (CheckBox)findViewById(R.id.jackets_DC_Chbk);
        Suits_DC = (CheckBox)findViewById(R.id.suits_DC_Chbk);
        next_DC= (Button)findViewById(R.id.Proceed_DC);

        next_DC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int amount  = 0;
                StringBuilder bill = new StringBuilder();
                ItemsSelected = new ArrayList();

                if(Shirts_DC.isChecked()){
                    amount = amount + 20;
                    ItemsSelected.add('\n'+" Shirts For Dry Cleaning" + '\n');

                }
                if(Pants_DC.isChecked()){
                    amount = amount + 20;
                    ItemsSelected.add('\n'+"Pants For Dry Cleaning" + '\n');
                }
                if(Jackets_DC.isChecked()){
                    amount = amount + 50;
                    ItemsSelected.add('\n'+"Jackets For Dry Cleaning" + '\n');
                }
                if(Suits_DC.isChecked()){
                    amount = amount + 50;
                    ItemsSelected.add('\n'+"Suits For Dry Cleaning" + '\n');
                }

                if(!Shirts_DC.isChecked() && !Pants_DC.isChecked() && !Jackets_DC.isChecked() && !Suits_DC.isChecked()){

                    Toast.makeText(Dry_CleaningRate.this, "Please Select Items... ", Toast.LENGTH_SHORT).show();

                }else{

                    bill.append("Total amount : " + amount);
                    Toast.makeText(Dry_CleaningRate.this, bill.toString() +" Rupees", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Dry_CleaningRate.this,Order_Summery_Activity.class);
                    intent.putExtra("Amount",amount);
                    intent.putExtra("Items",ItemsSelected);
                    startActivity(intent);
                }

            }
        });

    }
}