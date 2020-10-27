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

    TextView text_view_screen;

    boolean dot_is_pressed, operator_is_pressed, equal_is_pressed, number_button_pressed;

    Button[] number_buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text_view_screen = findViewById(R.id.screen);

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

        number_buttons = new Button[]{btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9};

        dot_is_pressed = false;
        operator_is_pressed = false;
        equal_is_pressed = false;
        number_button_pressed = false;
    }

    @Override
    public void onClick(View view)
    {
        for (Button number_button : number_buttons)
        {
            number_button_pressed = view.getId() == number_button.getId();
//        Reset the Text View if a number button is pressed after pressing equal button
            if (equal_is_pressed && number_button_pressed)
            {
                text_view_screen.setText("");
                equal_is_pressed = false;
            }
            else if (equal_is_pressed && !number_button_pressed)
            {
                equal_is_pressed = false;
            }
            else if (!number_button_pressed)
            {
                dot_is_pressed = false;
            }
//        Allow to press operator buttons after pressing number button
            operator_is_pressed = false;
        }

        switch (view.getId())
        {
//            Number buttons
            case R.id.one:
                text_view_screen.setText(String.format("%s1", text_view_screen.getText()));
                break;
            case R.id.two:
                text_view_screen.setText(String.format("%s2", text_view_screen.getText()));
                break;
            case R.id.three:
                text_view_screen.setText(String.format("%s3", text_view_screen.getText()));
                break;
            case R.id.four:
                text_view_screen.setText(String.format("%s4", text_view_screen.getText()));
                break;
            case R.id.five:
                text_view_screen.setText(String.format("%s5", text_view_screen.getText()));
                break;
            case R.id.six:
                text_view_screen.setText(String.format("%s6", text_view_screen.getText()));
                break;
            case R.id.seven:
                text_view_screen.setText(String.format("%s7", text_view_screen.getText()));
                break;
            case R.id.eight:
                text_view_screen.setText(String.format("%s8", text_view_screen.getText()));
                break;
            case R.id.nine:
                text_view_screen.setText(String.format("%s9", text_view_screen.getText()));
                break;
            case R.id.zero:
                text_view_screen.setText(String.format("%s0", text_view_screen.getText()));
                break;

            case R.id.dot:
//                 Make dot button can only be pressed once
                if (!dot_is_pressed)
                {
                    text_view_screen.setText(String.format("%s.", text_view_screen.getText()));
                    dot_is_pressed = true;
                }
                break;

//            Operator button handler
            case R.id.add:
                if (!operator_is_pressed)
                {
                    text_view_screen.setText(String.format("%s+", text_view_screen.getText()));
                    operator_is_pressed = true;
//                    dot_is_pressed = false;
                }
                break;
            case R.id.subtract:
                if (!operator_is_pressed)
                {
                    text_view_screen.setText(String.format("%s-", text_view_screen.getText()));
                    operator_is_pressed = true;
//                    dot_is_pressed = false;
                }
                break;
            case R.id.divide:
                if (!operator_is_pressed)
                {
                    text_view_screen.setText(String.format("%s/", text_view_screen.getText()));
                    operator_is_pressed = true;
//                    dot_is_pressed = false;
                }
                break;
            case R.id.multiply:
                if (!operator_is_pressed)
                {
                    text_view_screen.setText(String.format("%s*", text_view_screen.getText()));
                    operator_is_pressed = true;
//                    dot_is_pressed = false;
                }
                break;
            case R.id.equal:
                try
                {
                    if (text_view_screen.getText() != "")
                    {
                        text_view_screen.setText(formatted_result(result_from_math_expressions()));

                        equal_is_pressed = true;
                        operator_is_pressed = false;
//                    dot_is_pressed = false;
                    }
                } catch (Exception e)
                {
                    text_view_screen.setText("Error!");

                }
                break;

//                Remove buttons
            case R.id.ce:
                String current_screen_text = text_view_screen.getText().toString();

                if (current_screen_text.length() > 1)
                {
                    text_view_screen.setText(remaining_entries(current_screen_text));
                }

                break;

            case R.id.c:
                text_view_screen.setText("");
                break;

            case R.id.bs:
                current_screen_text = text_view_screen.getText().toString();

                if (current_screen_text.length() > 1)
                {
                    current_screen_text = current_screen_text.substring(0, current_screen_text.length() - 1);
                    text_view_screen.setText(current_screen_text);
                }
                else
                {
                    text_view_screen.setText("");
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
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
        return convert((String) text_view_screen.getText());
    }
}