package rohan.myappcompany.e_laundrysystemapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import rohan.myappcompany.e_laundrysystemapp.Models.AdminOrders;

public class AdminPanel_Activity extends AppCompatActivity {
    private RecyclerView Orderlist;
    private DatabaseReference ordersref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel_);

        ordersref = FirebaseDatabase.getInstance().getReference().child("Orders");
        Orderlist = findViewById(R.id.Orderlist);
        Orderlist.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<AdminOrders> options =
                new FirebaseRecyclerOptions.Builder<AdminOrders>()
                .setQuery(ordersref,AdminOrders.class)
                .build();

        FirebaseRecyclerAdapter<AdminOrders,AdminOrdersViewHolder> adapter =
                new FirebaseRecyclerAdapter<AdminOrders, AdminOrdersViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull AdminOrdersViewHolder adminOrdersViewHolder, int i, @NonNull AdminOrders adminOrders) {
                        adminOrdersViewHolder.Username.setText("Name :"+adminOrders.getName());
                        adminOrdersViewHolder.UserPhoneNumber.setText("Phone :"+adminOrders.getPhone());
                        adminOrdersViewHolder.Usertotalprice.setText("Total Price :"+adminOrders.getTotal_Amount());
                        adminOrdersViewHolder.date.setText("pickup at :"+adminOrders.getDate()+ "Time: "+ adminOrders.getTime());
                        adminOrdersViewHolder.Address.setText("pickup address :"+adminOrders.getHouseno()+","+adminOrders.getCity()+","+adminOrders.getState()
                        +","+adminOrders.getPincode());
                        adminOrdersViewHolder.order.setText("Items :"+adminOrders.getOrders());
                        adminOrdersViewHolder.status.setText("Status :"+adminOrders.getStatus());
                        adminOrdersViewHolder.confirm.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                CharSequence options[] = new CharSequence[]{
                                        "Yes",
                                        "No"
                                };
                                AlertDialog.Builder builder = new AlertDialog.Builder(AdminPanel_Activity.this);
                                builder.setTitle("Have You Completed this Order Successfully");
                                builder.setItems(options, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int i) {
                                        if(i==0){
                                            String uID = getRef(i).getKey();
                                            RemoveOrder(uID);

                                        }else{
                                            finish();
                                        }

                                    }
                                });
                                builder.show();

                            }
                        });

                    }

                    @NonNull
                    @Override
                    public AdminOrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orders_layout,parent,false);
                        return new AdminOrdersViewHolder(view);
                    }
                };
        Orderlist.setAdapter(adapter);
        adapter.startListening();

    }



    public static class AdminOrdersViewHolder extends RecyclerView.ViewHolder{

        public TextView Username,UserPhoneNumber,Usertotalprice,date,Address,order,status;
        public Button confirm;

        public AdminOrdersViewHolder(@NonNull View itemView) {
            super(itemView);

            Username = itemView.findViewById(R.id.Order_user_name);
            UserPhoneNumber = itemView.findViewById(R.id.Order_PhoneNumber);
            Usertotalprice = itemView.findViewById(R.id.Order_Total_price);
            date = itemView.findViewById(R.id.Order_Date_text);
            Address = itemView.findViewById(R.id.Order_Address_State_City);
            order = itemView.findViewById(R.id.Order_Pickup);
            confirm = itemView.findViewById(R.id.Confirmed_btn);
            status = itemView.findViewById(R.id.status);
        }
    }

    private void RemoveOrder(String uID) {
        ordersref.child(uID).removeValue();
    }
}