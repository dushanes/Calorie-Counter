package dushanes.caloriecounterub;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import dushanes.caloriecounterub.Database.accountContract;
import dushanes.caloriecounterub.Database.accountDbHelper;

public class newUserTask implements Runnable {

    EditText editTextEmail;
    EditText editTextPassword;
    Context context;
    Intent intent;

    public newUserTask(EditText editTextEmail, EditText editTextPassword,
                      Intent intent, Context context) {
        this.editTextEmail = editTextEmail;
        this.editTextPassword = editTextPassword;
        this.context = context;
        this.intent = intent;
    }

    @Override
    public void run() {
        newUser();
    }

    public void newUser(){
        user person;
        accountDbHelper mDbHelper = new accountDbHelper(context);


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
            Toast.makeText(context, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(context, "Registration Unsuccessful, email already registered", Toast.LENGTH_SHORT).show();
        }else {
            long newUserId = dbWrite.insert(accountContract.accountInfo.tableName, null, values);
            Toast.makeText(context, "Registration Successful", Toast.LENGTH_SHORT).show();
            person = new user((int)newUserId, email);
            intent.putExtra("user", person);
            context.startActivity(intent);

        }
        cursor.close();
    }
}
