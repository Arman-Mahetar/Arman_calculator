package com.example.arman_calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Display screen
    TextView tvMainDisplay;

    // Numbers to calculate
    double number1 = 0;
    double number2 = 0;
    double result = 0;

    // What operation to do (+, -, ×, ÷)
    String operation = "";

    // Current number being typed
    String currentNumber = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connect to the display screen
        tvMainDisplay = findViewById(R.id.tvMainDisplay);

        // Connect all buttons and set what happens when clicked
        setupButtons();
    }

    private void setupButtons() {

        // Number buttons (0-9)
        findViewById(R.id.btn0).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { addNumber("0"); }
        });

        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { addNumber("1"); }
        });

        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { addNumber("2"); }
        });

        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { addNumber("3"); }
        });

        findViewById(R.id.btn4).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { addNumber("4"); }
        });

        findViewById(R.id.btn5).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { addNumber("5"); }
        });

        findViewById(R.id.btn6).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { addNumber("6"); }
        });

        findViewById(R.id.btn7).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { addNumber("7"); }
        });

        findViewById(R.id.btn8).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { addNumber("8"); }
        });

        findViewById(R.id.btn9).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { addNumber("9"); }
        });

        // Decimal point button
        findViewById(R.id.btnDecimal).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { addDecimal(); }
        });

        // Operation buttons (+, -, ×, ÷)
        findViewById(R.id.btnPlus).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { chooseOperation("+"); }
        });

        findViewById(R.id.btnMinus).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { chooseOperation("-"); }
        });

        findViewById(R.id.btnMultiply).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { chooseOperation("×"); }
        });

        findViewById(R.id.btnDivide).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { chooseOperation("÷"); }
        });

        // Equals button (=)
        findViewById(R.id.btnEquals).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { calculateResult(); }
        });

        // Clear button (AC)
        findViewById(R.id.btnClear).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { clearEverything(); }
        });

        // Plus/Minus button (±)
        findViewById(R.id.btnPlusMinus).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { changeSign(); }
        });

        // Percent button (%)
        findViewById(R.id.btnPercent).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { calculatePercent(); }
        });
    }

    // Add a number to the display
    private void addNumber(String digit) {
        if (currentNumber.equals("0")) {
            currentNumber = digit;  // Replace 0 with new digit
        } else {
            currentNumber = currentNumber + digit;  // Add digit to current number
        }
        tvMainDisplay.setText(currentNumber);
    }

    // Add decimal point
    private void addDecimal() {
        if (!currentNumber.contains(".")) {  // Only add if no decimal already
            currentNumber = currentNumber + ".";
            tvMainDisplay.setText(currentNumber);
        }
    }

    // Choose what operation to do (+, -, ×, ÷)
    private void chooseOperation(String op) {
        number1 = Double.parseDouble(currentNumber);  // Save first number
        operation = op;  // Remember which operation
        currentNumber = "0";  // Ready for second number
    }

    // Calculate the final result
    private void calculateResult() {
        number2 = Double.parseDouble(currentNumber);  // Get second number

        // Do the math based on operation
        if (operation.equals("+")) {
            result = number1 + number2;
        } else if (operation.equals("-")) {
            result = number1 - number2;
        } else if (operation.equals("×")) {
            result = number1 * number2;
        } else if (operation.equals("÷")) {
            if (number2 != 0) {  // Don't divide by zero!
                result = number1 / number2;
            } else {
                tvMainDisplay.setText("Error");
                return;
            }
        }

        // Show result on screen
        currentNumber = formatNumber(result);
        tvMainDisplay.setText(currentNumber);

        // Reset for next calculation
        operation = "";
    }

    // Clear everything and start over
    private void clearEverything() {
        number1 = 0;
        number2 = 0;
        result = 0;
        operation = "";
        currentNumber = "0";
        tvMainDisplay.setText("0");
    }

    // Change number from positive to negative or vice versa
    private void changeSign() {
        if (!currentNumber.equals("0")) {
            if (currentNumber.startsWith("-")) {
                currentNumber = currentNumber.substring(1);  // Remove minus sign
            } else {
                currentNumber = "-" + currentNumber;  // Add minus sign
            }
            tvMainDisplay.setText(currentNumber);
        }
    }

    // Convert number to percentage (divide by 100)
    private void calculatePercent() {
        double num = Double.parseDouble(currentNumber);
        num = num / 100;
        currentNumber = formatNumber(num);
        tvMainDisplay.setText(currentNumber);
    }

    // Make numbers look nice (remove unnecessary .0)
    private String formatNumber(double num) {
        if (num == (int) num) {
            return String.valueOf((int) num);  // Show as whole number
        } else {
            return String.valueOf(num);  // Show with decimals
        }
    }
}