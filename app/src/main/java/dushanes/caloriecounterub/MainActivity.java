package dushanes.caloriecounterub;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText editTextEmail;
    private EditText editTextPassword;

    private Button buttonLogin;
    private Button buttonForgotPassword;
    private Button buttonNewUser;

    private int loginAttempts = 0;
    private boolean allowed = true;

    accountDbHelper mDbHelper = new accountDbHelper(this);

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

    private void newUser(){
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        SQLiteDatabase dbWrite = mDbHelper.getWritableDatabase();
        SQLiteDatabase dbRead = mDbHelper.getReadableDatabase();

        String[] projection = {
                accountContract.accountInfo.columnId,
                accountContract.accountInfo.columnEmail,
                accountContract.accountInfo.columnPassword
        };

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) ){
            Toast.makeText(this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
            return;
        }


        ContentValues values = new ContentValues();

        values.put(accountContract.accountInfo.columnEmail, email);
        values.put(accountContract.accountInfo.columnPassword, password);

        String selection = accountContract.accountInfo.columnEmail + " = ?";
        String sort = accountContract.accountInfo.columnId + " DESC";
        String arg[] = {email};

        Cursor cursor = dbRead.query(
                accountContract.accountInfo.tableName,
                projection,
                selection,
                arg,
                null,
                null,
                sort
        );

        if (cursor.moveToFirst()){
            Toast.makeText(MainActivity.this, "Registration Unsuccessful, email already registered", Toast.LENGTH_SHORT).show();
        }else {
            long newUserId = dbWrite.insert(accountContract.accountInfo.tableName, null, values);
            Toast.makeText(MainActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
            cursor.close();
            startActivity(new Intent(this, NewUserInfoInput.class));
        }

    }

    private void login(){
        if (!allowed){
            Toast.makeText(MainActivity.this, "LOCKED OUT. Too many attempts", Toast.LENGTH_SHORT).show();
            return;
        }
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        SQLiteDatabase dbRead = mDbHelper.getReadableDatabase();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) ){
            Toast.makeText(this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
            return;
        }

        ContentValues values = new ContentValues();

        values.put(accountContract.accountInfo.columnEmail, email);
        values.put(accountContract.accountInfo.columnPassword, password);

        String[] projection = {
                accountContract.accountInfo.columnId,
                accountContract.accountInfo.columnEmail,
                accountContract.accountInfo.columnPassword
        };

        String selection = accountContract.accountInfo.columnEmail + " = ?";
        String sort = accountContract.accountInfo.columnId + " DESC";
        String arg[] = {email};

        Cursor cursor = dbRead.query(
                accountContract.accountInfo.tableName,
                projection,
                selection,
                arg,
                null,
                null,
                sort
        );

        if (cursor.moveToFirst()){
            String test = cursor.getString(2);
            if (test.equals(password)){
                Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, MainMenu.class));
            }else{
                Toast.makeText(MainActivity.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                loginAttempts++;
                if (loginAttempts > 5){
                    Toast.makeText(MainActivity.this, "Too many login attempts. LOCKED OUT.", Toast.LENGTH_SHORT).show();
                    allowed = false;
                }
            }
        }else {
            Toast.makeText(MainActivity.this, "Login failed, unregistered email.", Toast.LENGTH_SHORT).show();
        }

        cursor.close();
    }

    @Override
    public void onClick(View view){
        if (view == buttonForgotPassword) {
            //forgotPassword();
        }else if (view == buttonLogin) {
            login();
        }else if (view == buttonNewUser){
            newUser();
        }
    }
}
