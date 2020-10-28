package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.calculatorapp.ConvertStringToMathExpression.convert;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    Button btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9;
    Button btn_ce, btn_c, btn_bs;
    Button btn_add, btn_subtract, btn_multiply, btn_divide, btn_equal, btn_dot, btn_lunisolar;

    TextView TextView_screen;

    boolean dot_is_pressed, operator_is_pressed, equal_is_pressed,
            any_number_button_pressed, any_math_operator_pressed;

    String test;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView_screen = findViewById(R.id.screen);

        btn_ce = (Button) findViewById(R.id.ce);
        btn_ce.setOnClickListener(this);

        btn_c = (Button) findViewById(R.id.c);
        btn_c.setOnClickListener(this);

        btn_bs = (Button) findViewById(R.id.bs);
        btn_bs.setOnClickListener(this);

        btn_add = (Button) findViewById(R.id.add);
        btn_add.setOnClickListener(this);

        btn_subtract = (Button) findViewById(R.id.subtract);
        btn_subtract.setOnClickListener(this);

        btn_multiply = (Button) findViewById(R.id.multiply);
        btn_multiply.setOnClickListener(this);

        btn_divide = (Button) findViewById(R.id.divide);
        btn_divide.setOnClickListener(this);

        btn_equal = (Button) findViewById(R.id.equal);
        btn_equal.setOnClickListener(this);

        btn_lunisolar = (Button) findViewById(R.id.lunisolar);
        btn_lunisolar.setOnClickListener(this);

        btn_dot = (Button) findViewById(R.id.dot);
        btn_dot.setOnClickListener(this);

        btn_0 = (Button) findViewById(R.id.zero);
        btn_0.setOnClickListener(this);

        btn_1 = (Button) findViewById(R.id.one);
        btn_1.setOnClickListener(this);

        btn_2 = (Button) findViewById(R.id.two);
        btn_2.setOnClickListener(this);

        btn_3 = (Button) findViewById(R.id.three);
        btn_3.setOnClickListener(this);

        btn_4 = (Button) findViewById(R.id.four);
        btn_4.setOnClickListener(this);

        btn_5 = (Button) findViewById(R.id.five);
        btn_5.setOnClickListener(this);

        btn_6 = (Button) findViewById(R.id.six);
        btn_6.setOnClickListener(this);

        btn_7 = (Button) findViewById(R.id.seven);
        btn_7.setOnClickListener(this);

        btn_8 = (Button) findViewById(R.id.eight);
        btn_8.setOnClickListener(this);

        btn_9 = (Button) findViewById(R.id.nine);
        btn_9.setOnClickListener(this);

        dot_is_pressed = false;
        operator_is_pressed = false;
        equal_is_pressed = false;
        any_number_button_pressed = false;
        any_math_operator_pressed = false;
    }

    @Override
    public void onClick(View view)
    {
        if (view.getTag() != null)
        {
            String tag = view.getTag().toString();
            any_number_button_pressed = tag.equals("number") || tag.equals("dot");
            any_math_operator_pressed = tag.equals("math_operator");

            // Reset screen when press a number button after equal button
            if (any_number_button_pressed)
            {
                if (equal_is_pressed)
                {
                    TextView_screen.setText("");
                }
            }
            else if (any_math_operator_pressed && operator_is_pressed)
            {
                backspace();
            }
            operator_is_pressed = false; // Allow to press operator button after inputting a number
            equal_is_pressed = false; // Reset equal pressing status
        }
        else
        {
            // Allow to press dot button after inputting a not-number character
            if (dot_is_pressed)
            {
                dot_is_pressed = false;
            }

            // Allow to continue from the last result on the screen after inputting a not-number character
            if (equal_is_pressed)
            {
                equal_is_pressed = false;
            }
        }

        switch (view.getId())
        {
//            Number buttons
            case R.id.one:
                TextView_screen.setText(String.format("%s1", TextView_screen.getText()));
                break;
            case R.id.two:
                TextView_screen.setText(String.format("%s2", TextView_screen.getText()));
                break;
            case R.id.three:
                TextView_screen.setText(String.format("%s3", TextView_screen.getText()));
                break;
            case R.id.four:
                TextView_screen.setText(String.format("%s4", TextView_screen.getText()));
                break;
            case R.id.five:
                TextView_screen.setText(String.format("%s5", TextView_screen.getText()));
                break;
            case R.id.six:
                TextView_screen.setText(String.format("%s6", TextView_screen.getText()));
                break;
            case R.id.seven:
                TextView_screen.setText(String.format("%s7", TextView_screen.getText()));
                break;
            case R.id.eight:
                TextView_screen.setText(String.format("%s8", TextView_screen.getText()));
                break;
            case R.id.nine:
                TextView_screen.setText(String.format("%s9", TextView_screen.getText()));
                break;
            case R.id.zero:
                TextView_screen.setText(String.format("%s0", TextView_screen.getText()));
                break;

            case R.id.dot:
//                 Make dot button can only be pressed once
                if (!dot_is_pressed)
                {
                    TextView_screen.setText(String.format("%s.", TextView_screen.getText()));
                    dot_is_pressed = true;
                }
                break;

//            Operator button handler
            case R.id.add:
                if (!operator_is_pressed)
                {
                    TextView_screen.setText(String.format("%s+", TextView_screen.getText()));
                    operator_is_pressed = true;
//                    dot_is_pressed = false;
                }
                break;
            case R.id.subtract:
                if (!operator_is_pressed)
                {
                    TextView_screen.setText(String.format("%s-", TextView_screen.getText()));
                    operator_is_pressed = true;
//                    dot_is_pressed = false;
                }
                break;
            case R.id.divide:
                if (!operator_is_pressed)
                {
                    TextView_screen.setText(String.format("%s/", TextView_screen.getText()));
                    operator_is_pressed = true;
//                    dot_is_pressed = false;
                }
                break;
            case R.id.multiply:
                if (!operator_is_pressed)
                {
                    TextView_screen.setText(String.format("%s*", TextView_screen.getText()));
                    operator_is_pressed = true;
//                    dot_is_pressed = false;
                }
                break;
            case R.id.equal:
                try
                {
                    if (TextView_screen.getText() != "")
                    {
                        TextView_screen.setText(formatted_result(result_from_math_expressions()));

                        equal_is_pressed = true;
                        operator_is_pressed = false;
//                    dot_is_pressed = false;
                    }
                } catch (Exception e)
                {
                    TextView_screen.setText("Error");

                }
                break;

//                Remove buttons
            case R.id.ce:
                String current_screen_text = TextView_screen.getText().toString();

                if (current_screen_text.length() > 1)
                {
                    TextView_screen.setText(remaining_entries(current_screen_text));
                }

                break;

            case R.id.c:
                TextView_screen.setText("");
                break;

            case R.id.bs:
                backspace();
                // Fix bug: duplicate backspace when press math operators after BS button
                operator_is_pressed = false;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }

    private void backspace()
    {
        String current_screen_text = TextView_screen.getText().toString();
        if (current_screen_text.length() > 1)
        {
            TextView_screen.setText(
                    current_screen_text.substring(0, current_screen_text.length() - 1)
            );
        }
        else
        {
            TextView_screen.setText("");
        }
    }

    private String remaining_entries(String expression)
    {
        for (int i = expression.length() - 1; i > 0; i--)
        {
            if (expression.charAt(i) == '+'
                    || expression.charAt(i) == '-'
                    || expression.charAt(i) == '*'
                    || expression.charAt(i) == '/')
            {
                return expression.substring(0, i + 1);
            }
        }
        return "";
    }

    private String formatted_result(double result)
    {
        if (result == (int) result)
        {
            return String.valueOf((int) result);
        }
        else
        {
            return String.valueOf(result);
        }

    }

    private double result_from_math_expressions()
    {
        return convert((String) TextView_screen.getText());
    }
}