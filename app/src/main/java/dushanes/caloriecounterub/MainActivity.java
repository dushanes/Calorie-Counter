package dushanes.caloriecounterub;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText editTextEmail;
    private EditText editTextPassword;

    private Button buttonLogin;
    private Button buttonForgotPassword;
    private Button buttonNewUser;

    private int  loginAttempts = 0;
    private boolean allowed = true;

    Context context = MainActivity.this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLogin = findViewById(R.id.buttonLogin);
        buttonForgotPassword = findViewById(R.id.buttonForgotPassword);
        buttonNewUser = findViewById(R.id.buttonNewUser);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);

        buttonNewUser.setOnClickListener(this);
        buttonForgotPassword.setOnClickListener(this);
        buttonLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){

        if (view == buttonForgotPassword) {
            //forgotPassword();
        }else if (view == buttonLogin) {
            Intent intent = new Intent(this, MainMenu.class);
            loginTasks loginProcess = new loginTasks(editTextEmail, editTextPassword, allowed, loginAttempts, intent, context);
            loginProcess.run();
        }else if (view == buttonNewUser){
            Intent intent = new Intent(this, NewUserInfoInput.class);
            newUserTask newUserProcess = new newUserTask(editTextEmail, editTextPassword, intent, context);
            newUserProcess.run();
        }
    }



}
