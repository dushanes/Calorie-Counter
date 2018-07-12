package dushanes.caloriecounterub;

import android.content.ClipData;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.Console;

public class NewUserInfoInput extends AppCompatActivity {

    public EditText editTextAge;
    public EditText editTextName;
    public EditText editTextHeight;
    public EditText editTextWeight;

    public RadioGroup radioGroupGender;
    public RadioButton radioButtonGender;

    public Spinner spinner;

    public healthInfoDbHelper mDbHelper = new healthInfoDbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_info_input);

        editTextAge = findViewById(R.id.editTextAge);
        editTextName = findViewById(R.id.editTextName);
        editTextHeight = findViewById(R.id.editTextHeight);
        editTextWeight = findViewById(R.id.editTextWeight);

        radioGroupGender = findViewById(R.id.radioGroupGender);
        radioButtonGender = findViewById(radioGroupGender.getCheckedRadioButtonId());

        spinner = findViewById(R.id.spinnerActivityLevel);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.activityLevel, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        Button buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });

    }

    public void save(){
        SQLiteDatabase dbWrite = mDbHelper.getWritableDatabase();

        String name = editTextName.getText().toString().trim();
        String age = editTextAge.getText().toString().trim();
        String weight = editTextWeight.getText().toString().trim();
        String height = editTextHeight.getText().toString().trim();
        String gender = radioButtonGender.getText().toString().trim();
        String activity = (String) spinner.getSelectedItem();

        double heightMeters = Double.parseDouble(height) * 0.0254;
        double weightKg = Double.parseDouble(weight) * 0.4536;
        double heightCm = Double.parseDouble(height) * 2.54;

        double calories;
        double multiplier = 1.2;
        double bmi = weightKg/(heightMeters * heightMeters);

        if      (activity.equals("Sedentary"))      {multiplier = 1.2;}
        else if (activity.equals("Lightly Active")) {multiplier = 1.375;}
        else if (activity.equals("Active"))         {multiplier = 1.55;}
        else if (activity.equals("Very Active"))    {multiplier = 1.725;}

        if (gender.equals("Male")){
            calories = 10 * weightKg + 6.25 * heightCm - 5 * Double.parseDouble(age) + 5;
        }else{
            calories = 10 * weightKg + 6.25 * heightCm - 5 * Double.parseDouble(age) - 161;
        }

        //calories = calories * multiplier;
        calories = Math.floor(calories * multiplier);

        ContentValues content = new ContentValues();

        content.put(healthInfoContract.healthInfo.columnName, name);
        content.put(healthInfoContract.healthInfo.columnGender, gender);
        content.put(healthInfoContract.healthInfo.columnAge, age);
        content.put(healthInfoContract.healthInfo.columnWeight, weight);
        content.put(healthInfoContract.healthInfo.columnHeight, height);
        content.put(healthInfoContract.healthInfo.columnActivity, activity);
        content.put(healthInfoContract.healthInfo.columnCalories, calories);
        content.put(healthInfoContract.healthInfo.columnBmi, bmi);

        dbWrite.insert(healthInfoContract.healthInfo.tableName, null, content);
        startActivity(new Intent(this, MainMenu.class));
    }

    public void checkButtonGender(View v){

        radioButtonGender = findViewById(radioGroupGender.getCheckedRadioButtonId());
        Toast.makeText(this, "Gender selected: " + radioButtonGender.getText(), Toast.LENGTH_SHORT).show();
    }
}
