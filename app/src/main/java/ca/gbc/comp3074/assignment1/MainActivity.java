package ca.gbc.comp3074.assignment1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

import ca.gbc.comp3074.assignment1.model.Payroll;

public class MainActivity extends AppCompatActivity {

    private TextView payOutput, overtimePayOutput, totalPayOutput, taxOutput;
    private EditText hourlyRateInput, hoursWorkedInput;
    private Button calculateButton;

    private Payroll payroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        payOutput = findViewById(R.id.payOutput);
        overtimePayOutput = findViewById(R.id.overtimePayOutput);
        totalPayOutput = findViewById(R.id.totalPayOutput);
        taxOutput = findViewById(R.id.taxOutput);

        hourlyRateInput = findViewById(R.id.hourlyRateEditText);
        hoursWorkedInput = findViewById(R.id.hoursWorkedEditText);

        calculateButton = findViewById(R.id.calculateButton);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double hoursWorked = Double.valueOf(hoursWorkedInput.getText().toString());
                double hourlyRate = Double.valueOf(hourlyRateInput.getText().toString());

                payroll = new Payroll(hoursWorked, hourlyRate);

                payOutput.setText(formatOutputToDollar(payroll.calculatePay()));
                overtimePayOutput.setText(formatOutputToDollar(payroll.calculateOvertimePay()));
                totalPayOutput.setText(formatOutputToDollar(payroll.calculateTotalPay()));
                taxOutput.setText(formatOutputToDollar(payroll.calculateTax()));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.about:
                startAboutActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void startAboutActivity() {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    public String formatOutputToDollar(double input){
        return String.format("$%.2f", input);
    }
}