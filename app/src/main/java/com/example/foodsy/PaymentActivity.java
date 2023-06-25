package com.example.foodsy;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class PaymentActivity extends AppCompatActivity {

    private RadioGroup paymentOptions;
    private FrameLayout cardLayout;
    private EditText cardNumberEditText;
    private EditText expiryMonthEditText;
    private EditText expiryYearEditText;
    private EditText cvvEditText;
    private Button orderButton;

    private static final int CARD_NUMBER_GROUP_SIZE = 4;
    private static final char CARD_NUMBER_SEPARATOR = ' ';
    private static final int EXPIRY_MONTH_LENGTH = 2;
    private static final int EXPIRY_YEAR_LENGTH = 2;
    private static final char EXPIRY_DATE_SEPARATOR = '/';
    private static final int MAX_CARD_NUMBER_LENGTH = 19;
    private static final int MAX_EXPIRY_MONTH_LENGTH = 2;
    private static final int MAX_EXPIRY_YEAR_LENGTH = 2;
    private static final int MAX_CVV_LENGTH = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        paymentOptions = findViewById(R.id.payment_options);
        cardLayout = findViewById(R.id.card_layout);
        cardNumberEditText = findViewById(R.id.card_number_edit_text);
        expiryMonthEditText = findViewById(R.id.expiry_month_edit_text);
        expiryYearEditText = findViewById(R.id.expiry_year_edit_text);
        cvvEditText = findViewById(R.id.cvv_edit_text);
        orderButton = findViewById(R.id.order_button);
        // ...

        cardNumberEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not used
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateFields();
            }

            @Override
            public void afterTextChanged(Editable s) {
                formatCardNumber();
            }

            private void formatCardNumber() {
                String cardNumber = getCardNumberWithoutSpaces();
                StringBuilder formattedCardNumber = new StringBuilder();

                for (int i = 0; i < cardNumber.length(); i++) {
                    if (i > 0 && i % CARD_NUMBER_GROUP_SIZE == 0) {
                        formattedCardNumber.append(CARD_NUMBER_SEPARATOR);
                    }
                    formattedCardNumber.append(cardNumber.charAt(i));
                }

                cardNumberEditText.removeTextChangedListener(this);

                // Limit the card number length to the maximum allowed length
                if (formattedCardNumber.length() > MAX_CARD_NUMBER_LENGTH) {
                    formattedCardNumber.delete(MAX_CARD_NUMBER_LENGTH, formattedCardNumber.length());
                }

                cardNumberEditText.setText(formattedCardNumber.toString());
                cardNumberEditText.setSelection(formattedCardNumber.length());
                cardNumberEditText.addTextChangedListener(this);
            }

            private String getCardNumberWithoutSpaces() {
                return cardNumberEditText.getText().toString().replaceAll("\\s+", "");
            }
        });

        expiryMonthEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not used
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateFields();
            }

            @Override
            public void afterTextChanged(Editable s) {
                formatExpiryMonth();
            }

            private void formatExpiryMonth() {
                String expiryMonth = expiryMonthEditText.getText().toString().trim();
                if (expiryMonth.length() > MAX_EXPIRY_MONTH_LENGTH) {
                    expiryMonthEditText.setText(expiryMonth.substring(0, MAX_EXPIRY_MONTH_LENGTH));
                    expiryMonthEditText.setSelection(MAX_EXPIRY_MONTH_LENGTH);
                }
                validateFields();
            }
        });

        expiryYearEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not used
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateFields();
            }

            @Override
            public void afterTextChanged(Editable s) {
                formatExpiryYear();
            }

            private void formatExpiryYear() {
                String expiryYear = expiryYearEditText.getText().toString().trim();
                if (expiryYear.length() > MAX_EXPIRY_YEAR_LENGTH) {
                    expiryYearEditText.setText(expiryYear.substring(0, MAX_EXPIRY_YEAR_LENGTH));
                    expiryYearEditText.setSelection(MAX_EXPIRY_YEAR_LENGTH);
                }
                validateFields();
            }
        });

        cvvEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not used
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateFields();
            }

            @Override
            public void afterTextChanged(Editable s) {
                validateCvv();
            }

            private void validateCvv() {
                String cvv = cvvEditText.getText().toString().trim();
                if (cvv.length() > MAX_CVV_LENGTH) {
                    cvvEditText.setText(cvv.substring(0, MAX_CVV_LENGTH));
                    cvvEditText.setSelection(MAX_CVV_LENGTH);
                }
                validateFields();
            }
        });

        paymentOptions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.cash_option) {
                    cardLayout.setVisibility(View.GONE);
                    orderButton.setEnabled(true);
                } else if (checkedId == R.id.card_option) {
                    cardLayout.setVisibility(View.VISIBLE);
                    orderButton.setEnabled(false);
                }
            }
        });

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeOrder();
            }
        });
    }

    private void validateFields() {
        boolean isCardNumberValid = cardNumberEditText.getText().toString().trim().length() == MAX_CARD_NUMBER_LENGTH;
        boolean isExpiryMonthValid = expiryMonthEditText.getText().toString().trim().length() == EXPIRY_MONTH_LENGTH;
        boolean isExpiryYearValid = expiryYearEditText.getText().toString().trim().length() == EXPIRY_YEAR_LENGTH;
        boolean isCvvValid = cvvEditText.getText().toString().trim().length() == MAX_CVV_LENGTH;

        orderButton.setEnabled(isCardNumberValid && isExpiryMonthValid && isExpiryYearValid && isCvvValid);
    }

    private void placeOrder() {
        int selectedPaymentOption = paymentOptions.getCheckedRadioButtonId();

        if (selectedPaymentOption == R.id.cash_option) {
            Toast.makeText(this, "Order placed with cash payment", Toast.LENGTH_SHORT).show();
        } else if (selectedPaymentOption == R.id.card_option) {
            String cardNumber = cardNumberEditText.getText().toString().trim();
            String expiryMonth = expiryMonthEditText.getText().toString().trim();
            String expiryYear = expiryYearEditText.getText().toString().trim();
            String cvv = cvvEditText.getText().toString().trim();

            if (cardNumber.length() != MAX_CARD_NUMBER_LENGTH) {
                cardNumberEditText.setError("Invalid card number");
            } else if (expiryMonth.length() != EXPIRY_MONTH_LENGTH) {
                expiryMonthEditText.setError("Invalid month");
            } else if (expiryYear.length() != EXPIRY_YEAR_LENGTH) {
                expiryYearEditText.setError("Invalid year");
            } else if (cvv.length() != MAX_CVV_LENGTH) {
                cvvEditText.setError("Invalid CVV");
            } else {
                // Perform payment processing here
                Toast.makeText(this, "Payment processed successfully", Toast.LENGTH_SHORT).show();

                // Navigate to the order confirmation screen
                Intent intent = new Intent(PaymentActivity.this, ReceiptActivity.class);
                startActivity(intent);
            }
        }
    }
}
