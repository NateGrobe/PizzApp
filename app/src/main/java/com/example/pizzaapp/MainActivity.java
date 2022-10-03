package com.example.pizzaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    double sizePrice = 0;
    double topPrice = 0;
    double cheesePrice = 0;
    double deliveryPrice = 0;
    double total = 0;

    // determines size price based on id
    public double getSizePrice(int id) {
        switch (id) {
            case R.id.small:
                return 5.50;
            case R.id.medium:
                return 7.99;
            case R.id.large:
                return 9.50;
            case R.id.xLarge:
                return 11.38;
            default:
                return 0.0;
        }
    }

    // determines topping price based on id
    public double getToppingPrice(int id) {
        double price;
        switch (id) {
            case 0:
                price = 0;
                break;
            case 1:
            case 2:
            case 6:
            case 8:
            case 9:
                price =  5.0;
                break;
            case 3:
                price = 7.0;
                break;
            case 4:
            case 10:
                price = 8.0;
                break;
            case 5:
                price = 10.0;
                break;
            case 7:
                price = 9.0;
                break;
            default:
                return 0.0;
        }
        return price;
    }


    // updates total value on main activity
     public void updateTotal() {
       TextView totalTV = (TextView) findViewById(R.id.total);
       total = sizePrice + topPrice + cheesePrice + deliveryPrice;
       totalTV.setText("$" + String.format("%.2f", total));
     }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Context context = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // populate spinner
        Spinner toppings = (Spinner) findViewById(R.id.topping);
        ArrayAdapter<CharSequence> adpt = ArrayAdapter.createFromResource(this, R.array.toppingList, android.R.layout.simple_list_item_1);
        adpt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toppings.setAdapter(adpt);

        // event listener for size
        RadioGroup sizes = (RadioGroup) findViewById(R.id.size);
        sizes.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                sizePrice = getSizePrice(i);
                updateTotal();
            }
        });

        // event listener for topping
        Spinner topping = (Spinner) findViewById(R.id.topping);
        topping.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                topPrice = getToppingPrice(i);
                updateTotal();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                topPrice = 0.0;
            }
        });

        // event listener for cheese
        CheckBox cheeseBox = (CheckBox) findViewById(R.id.xtraCheese);
        cheeseBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                cheesePrice = b ? 5.0 : 0.0;
                updateTotal();
            }
        });

        // event listener for delivery
        CheckBox deliveryBox = (CheckBox) findViewById(R.id.delivery);
        deliveryBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                deliveryPrice = b ? 5.0 : 0.0;
                updateTotal();
            }
        });

        // set onclick for submit button
        Button submitBtn = (Button) findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // get values to pass to other activity
                EditText instructions = findViewById(R.id.instructions);
                EditText name = findViewById(R.id.name);
                EditText address = findViewById(R.id.address);
                EditText phone = findViewById(R.id.phone);
                EditText email = findViewById(R.id.email);

                // create intent and pass args
                Intent intent = new Intent(context, OrderInfo.class);
                intent.putExtra("total", total);
                intent.putExtra("instructions", instructions.getText().toString());
                intent.putExtra("name", name.getText().toString());
                intent.putExtra("address", address.getText().toString());
                intent.putExtra("phone", phone.getText().toString());
                intent.putExtra("email", email.getText().toString());

                // start new activity
                startActivity(intent);
            }
        });
    }

}