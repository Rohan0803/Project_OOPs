package rohan.myappcompany.e_laundrysystemapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Order_Summery_Activity extends AppCompatActivity {
    TextView AmountText,AmountNumber,OrderText;
    int amountno;
    Button Next;
    String am,Items;
    ArrayList<String> arrayList;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order__summery_);

        AmountNumber = (TextView)findViewById(R.id.Amount_Number);
        AmountText = (TextView)findViewById(R.id.Amount_text);
        OrderText = (TextView)findViewById(R.id.Order_text);
        Next = (Button)findViewById(R.id.Next_btn);

        final Intent intent = getIntent();
        amountno = intent.getIntExtra("Amount",0);

        am = Integer.toString(amountno);
        AmountNumber.setText(am);

        arrayList = (ArrayList<String>) getIntent().getSerializableExtra("Items");
        OrderText.setText(String.valueOf(arrayList)+ '\n');

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(),Address_Activity.class);
                intent1.putExtra("Amount",String.valueOf(amountno));
                intent1.putExtra("Orders",(arrayList));
                startActivity(intent1);

            }
        });






    }
}