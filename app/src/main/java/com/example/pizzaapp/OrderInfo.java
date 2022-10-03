package com.example.pizzaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OrderInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Context context = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_info);

        // set total value
        TextView total = (TextView) findViewById(R.id.total_price);
        double totalPrice = getIntent().getExtras().getDouble("total");
        total.setText("Your total is: $" + String.format("%.2f", totalPrice));

        // fetch args passed to activity
        String instructions = getIntent().getExtras().getString("instructions");
        String name = getIntent().getExtras().getString("name");
        String address = getIntent().getExtras().getString("address");
        String phone = getIntent().getExtras().getString("phone");
        String email = getIntent().getExtras().getString("email");

        // set info for personal info
        TextView pInfo = (TextView) findViewById(R.id.pInfo);
        pInfo.setText("Thanks for your order!\nName: " + name + "\nAddress: " + address + "\nPhone: " + phone + "\nEmail: " + email + "\nSpecial Instructions: " + instructions);

        // handle return to other screen
        Button returnBtn = (Button) findViewById(R.id.returnBtn);
        returnBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}