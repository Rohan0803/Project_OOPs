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

public class Rate_Iron extends AppCompatActivity {
    TextView RateIron_card,WashingIron_text;
    CheckBox ShirtsIron,PantsIron,JacketsIron,SuitsIron;
    Button nextIron;
    int amount  = 0;
    ArrayList ItemsSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate__iron);

        RateIron_card = (TextView)findViewById(R.id.Rate_Iron_Text);
        WashingIron_text = (TextView)findViewById(R.id.RI_Washing);
        ShirtsIron = (CheckBox)findViewById(R.id.ShirtsIron_Chbk);
        PantsIron = (CheckBox)findViewById(R.id.pantsIron_Chbk);
        JacketsIron = (CheckBox)findViewById(R.id.jacketsIron_Chbk);
        SuitsIron = (CheckBox)findViewById(R.id.suitsIron_Chbk);
        nextIron = (Button)findViewById(R.id.ProceedIron);

        nextIron.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int amount  = 0;
                StringBuilder bill = new StringBuilder();
                ItemsSelected = new ArrayList();

                if(ShirtsIron.isChecked()){
                    amount = amount + 5;
                    ItemsSelected.add('\n'+" Shirts For Iron" + '\n');
                }
                if(PantsIron.isChecked()){
                    amount = amount + 6;
                    ItemsSelected.add('\n'+"Pants For Iron" + '\n');
                }
                if(JacketsIron.isChecked()){
                    amount = amount + 20;
                    ItemsSelected.add('\n'+"Jackets For Iron" + '\n');
                }
                if(SuitsIron.isChecked()){
                    amount = amount + 20;
                    ItemsSelected.add('\n'+"Suits For Iron " + '\n');
                }

                if(!ShirtsIron.isChecked() && !PantsIron.isChecked() && !JacketsIron.isChecked() && !SuitsIron.isChecked()){
                    Toast.makeText(Rate_Iron.this, "Please Select Items..", Toast.LENGTH_SHORT).show();
                }else {

                    bill.append("Total amount : " + amount);
                    Toast.makeText(Rate_Iron.this, bill.toString() + " Rupees", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Rate_Iron.this,Order_Summery_Activity.class);
                    intent.putExtra("Amount",amount);
                    intent.putExtra("Items",ItemsSelected);
                    startActivity(intent);
                }
            }
        });

    }
}