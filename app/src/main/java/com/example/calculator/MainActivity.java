package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "CalculatorActivity";
    EditText first, second;
    TextView res;
    Calculator mcal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        first = findViewById(R.id.first);
        second = findViewById(R.id.second);
        res = findViewById(R.id.res);
        mcal = new Calculator();
    }

    public void onAdd(View view) {
        compute(Calculator.Operator.ADD);
    }


    public void onSub(View view) {
        compute(Calculator.Operator.SUB);
    }

    public void onDiv(View view) {
        try {
            compute(Calculator.Operator.DIV);
        } catch (IllegalArgumentException iae) {
            Log.e(TAG, "IllegalArgumentException", iae);
            res.setText(getString(R.string.computationError));

        }

    }

    public void onMul(View view) {
        compute(Calculator.Operator.MUL);
    }

    private void compute(Calculator.Operator operator) {
        double operandOne;
        double operandTwo;
        try {
            operandOne = getOperand(first);
            operandTwo = getOperand(second);
        } catch (NumberFormatException nfe) {
            Log.e(TAG, "NumberFormatException", nfe);
            res.setText(getString(R.string.computationError));
            return;
        }
        String result;
        switch (operator) {
            case ADD:
                result = String.valueOf(
                        mcal.add(operandOne, operandTwo));
                break;
            case SUB:
                result = String.valueOf(
                        mcal.sub(operandOne, operandTwo));
                break;
            case DIV:
                result = String.valueOf(
                        mcal.div(operandOne, operandTwo));
                break;
            case MUL:
                result = String.valueOf(
                        mcal.mul(operandOne, operandTwo));
                break;
            default:
                result = getString(R.string.computationError);
                break;
        }
        res.setText(result);

    }

    private double getOperand(EditText first) {
        String opertext = getOPerandText(first);
        return Double.valueOf(opertext);
    }

    private String getOPerandText(EditText first) {
        return  first.getText().toString();

    }

}
