package com.example.calculatorbyyesare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView resultTv,SolutionTv;
    MaterialButton button_c, button_openBracket,button_closeBracket;
    MaterialButton button_divide,button_minus,button_add,button_multiply,button_equal;
    MaterialButton button_Zero,button_one,button_two,button_three,button_four,button_five,button_six,button_seven,button_eight,button_nine;
    MaterialButton button_AC,button_dot;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv = findViewById(R.id.result_tv);
        SolutionTv = findViewById(R.id.Solution_tv);
        assignId(button_AC,R.id.button_AC );
        assignId(button_c,R.id.button_c );
        assignId(button_openBracket,R.id.button_openBracket );
        assignId(button_closeBracket,R.id.button_closeBracket );
        assignId(button_equal,R.id.button_equal );
        assignId(button_divide,R.id.button_divide );
        assignId(button_add,R.id.button_add );
        assignId(button_minus,R.id.button_minus );
        assignId(button_multiply,R.id.button_multiply );
        assignId(button_one,R.id.button_one );
        assignId(button_two,R.id.button_two );
        assignId(button_three,R.id.button_three );
        assignId(button_four,R.id.button_four );
        assignId(button_five,R.id.button_five );
        assignId(button_six,R.id.button_six );
        assignId(button_seven,R.id.button_seven );
        assignId(button_eight,R.id.button_eight );
        assignId(button_nine,R.id.button_nine );
        assignId(button_Zero,R.id.button_Zero );
        assignId(button_dot,R.id.button_dot );

    }
    void assignId(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        MaterialButton button = (MaterialButton) v;
        String buttonText = button.getText().toString();
        String dataToCalculate = SolutionTv.getText().toString();

        if(buttonText.equals("AC")){
            SolutionTv.setText("");
            resultTv.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            SolutionTv.setText(resultTv.getText());
            return;
        }
        if(buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        }else{
            dataToCalculate = dataToCalculate + buttonText;

        }

        SolutionTv.setText(dataToCalculate);
        String finalResult = getResult(dataToCalculate);

        if(!finalResult.equals("err")){
            resultTv.setText(finalResult);
        }



    }
    String getResult(String data){
       try {
           Context context = Context.enter();
           context.setOptimizationLevel(-1);
           Scriptable scriptable = context.initStandardObjects();
           String finalResult = context.evaluateString(scriptable,data,"javascript",1,null).toString();
           if(finalResult.endsWith(".0")){
               finalResult = finalResult.replace(".0" , "");
           }
           return finalResult;
       }
       catch (Exception e){
           return "err";
       }
    }
}