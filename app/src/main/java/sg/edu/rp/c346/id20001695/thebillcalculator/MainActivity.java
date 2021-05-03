package sg.edu.rp.c346.id20001695.thebillcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    EditText amount;
    EditText numPax;
    EditText discount;
    ToggleButton noSVSbtn;
    ToggleButton gstBtn;
    RadioGroup payMethod;
    Button splitClick;
    Button resetClick;
    TextView totalBill;
    TextView eachPay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount = findViewById(R.id.amountEntered);
        numPax = findViewById(R.id.paxNum);
        discount = findViewById(R.id.discount);
        noSVSbtn = findViewById(R.id.noSVS);
        gstBtn = findViewById(R.id.gst);
        payMethod = findViewById(R.id.paymentMethod);
        splitClick = findViewById(R.id.splitBtn);
        resetClick = findViewById(R.id.resetBtn);
        totalBill = findViewById(R.id.billText);
        eachPay = findViewById(R.id.eachPaytext);

        splitClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amount.getText().toString().trim().length() != 0 && numPax.getText().toString().trim().length() != 0) {
                    double amt = 0.0;
                    if (!noSVSbtn.isChecked() && !gstBtn.isChecked()) {
                        amt = Double.parseDouble(amount.getText().toString());
                    } else if (noSVSbtn.isChecked() && !gstBtn.isChecked()) {
                        amt = Double.parseDouble(amount.getText().toString()) * 1.10;
                    } else if (!noSVSbtn.isChecked() && gstBtn.isChecked()) {
                        amt = Double.parseDouble(amount.getText().toString()) * 1.07;
                    } else {
                        amt = Double.parseDouble(amount.getText().toString()) * 1.17;
                    }

                    if (discount.getText().toString().trim().length() != 0) {
                        amt *= 1 - Double.parseDouble(discount.getText().toString()) / 100;
                    }

                    int checkedRadioId = payMethod.getCheckedRadioButtonId();
                    String mode = " in cash";
                    if(checkedRadioId == R.id.paynowPayment) {
                        mode = " via PayNow to 912345678";
                    }

                    totalBill.setText("Total Bill: $" + String.format("%.2f", amt));
                    int numPpl = Integer.parseInt(numPax.getText().toString());

                    if (numPpl != 1) {
                        eachPay.setText("Each Pays: $" + (amt / numPpl) + mode);
                    }
                    else {
                        eachPay.setText("Each Pays: $" + amt + mode);
                    }
                }
                else {
                    totalBill.setTextColor(0xffa4c639);
                    totalBill.setText("Did not enter any values for amount and num of pax");
                }
            }
        });

        resetClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount.setText("");
                numPax.setText("");
                noSVSbtn.setChecked(false);
                gstBtn.setChecked(false);
                discount.setText("");
                eachPay.setText("");
                totalBill.setText("");
            }
        });
    }
}