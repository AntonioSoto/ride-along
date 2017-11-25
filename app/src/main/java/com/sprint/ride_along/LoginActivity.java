package com.sprint.ride_along;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sprint.ride_along.tasks.DriverInfoTask;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void login(View view){

        if( areTextFieldsEmpty() ){

            Toast.makeText(
                    this,
                    "Faltan campos por completar",
                    Toast.LENGTH_SHORT
            ).show();
        }
        else if( isStudentIdValid() ){

            Intent intent = new Intent(this, SelectActivity.class);
            String studentId = ((EditText) findViewById(R.id.login_Matricula)).getText().toString();
            intent.putExtra("studentId", studentId);
            startActivity(intent);
        }
        else{

            Toast.makeText(
                    this,
                    "La matrícula no es correcta",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    private boolean areTextFieldsEmpty(){

        String studentId = ((EditText) findViewById(R.id.login_Matricula)).getText().toString();
        String password = ((EditText) findViewById(R.id.login_Password)).getText().toString();

        return studentId.compareTo("")==0 ||
                password.compareTo("")==0;
    }

    private boolean isStudentIdValid(){

        String studentId = ((EditText) findViewById(R.id.login_Matricula)).getText().toString();

        String regex = "a\\d{7}";
        Pattern pattern = Pattern.compile(regex);

        return pattern.matcher(studentId).find();
    }

}
