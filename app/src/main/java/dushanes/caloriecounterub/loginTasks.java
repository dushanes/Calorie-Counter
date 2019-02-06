package dushanes.caloriecounterub;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import dushanes.caloriecounterub.Database.accountContract;
import dushanes.caloriecounterub.Database.accountDbHelper;
import dushanes.caloriecounterub.Database.healthInfoContract;
import dushanes.caloriecounterub.Database.healthInfoDbHelper;

public class loginTasks implements Runnable {
    EditText editTextEmail;
    EditText editTextPassword;
    boolean allowed;
    int loginAttempts;
    Context context;
    Intent intent;

    public loginTasks(EditText editTextEmail, EditText editTextPassword,
                      boolean allowed, int loginAttempts, Intent intent, Context context) {
        this.editTextEmail = editTextEmail;
        this.editTextPassword = editTextPassword;
        this.allowed = allowed;
        this.loginAttempts = loginAttempts;
        this.context = context;
        this.intent = intent;
    }

    @Override
    public void run() {
        login();
    }

    public user login(){

        user person;
        accountDbHelper mDbHelper = new accountDbHelper(context);
        SQLiteDatabase dbRead = mDbHelper.getReadableDatabase();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (!allowed){
            Toast.makeText(context, "LOCKED OUT. Too many attempts", Toast.LENGTH_SHORT).show();
            return null;
        }

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) ){
            Toast.makeText(context, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
            return null;
        }

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
                Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show();
                person = new user(Integer.parseInt(cursor.getString(0)), cursor.getString(1));
                grabInfo(person, context);
                intent.putExtra("user", person);
                context.startActivity(intent);
            }else{
                Toast.makeText(context, "Incorrect Password", Toast.LENGTH_SHORT).show();
                loginAttempts++;
                if (loginAttempts > 5){
                    Toast.makeText(context, "Too many login attempts. LOCKED OUT.", Toast.LENGTH_SHORT).show();
                    allowed = false;
                }
            }
        }else {
            Toast.makeText(context, "Login failed, unregistered email.", Toast.LENGTH_SHORT).show();
        }

        cursor.close();
        return null;
    }

    private void grabInfo(user user, Context context){
        healthInfoDbHelper hDbHelper = new healthInfoDbHelper(context);
        SQLiteDatabase hDbRead = hDbHelper.getReadableDatabase();

        String[] projection = {
                healthInfoContract.healthInfo.columnId,
                healthInfoContract.healthInfo.columnName,
                healthInfoContract.healthInfo.columnCalories
        };

        String selection = healthInfoContract.healthInfo.columnId + " = ?";
        String sort = accountContract.accountInfo.columnId + " DESC";
        String arg[] = {Integer.toString(user.getId())};

        Cursor cursor = hDbRead.query(
                healthInfoContract.healthInfo.tableName,
                projection,
                selection,
                arg,
                null,
                null,
                sort
        );
        if (cursor.moveToFirst()) {
            user.setName(cursor.getString(1));
            user.setCalories(cursor.getInt(2));
        }

        cursor.close();
    }

}
