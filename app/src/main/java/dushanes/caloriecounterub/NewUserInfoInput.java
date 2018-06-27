package dushanes.caloriecounterub;

import android.content.ClipData;
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

public class NewUserInfoInput extends AppCompatActivity {

    public EditText editTextAge;
    public EditText editTextName;
    public EditText editTextHeight;
    public EditText editTextWeight;

    public RadioGroup radioGroupGender;
    public RadioButton radioButtonGender;

    public Spinner spinner;

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

                //radioButtonGender = findViewById(radioGroupGender.getCheckedRadioButtonId());
                //radioButton = findViewById(radioGroup.getCheckedRadioButtonId());

                String name = editTextName.getText().toString().trim();
                String age = editTextAge.getText().toString().trim();
                String weight = editTextWeight.getText().toString().trim();
                String height = editTextHeight.getText().toString().trim();
                String gender = radioButtonGender.toString();
                String activity = (String) spinner.getSelectedItem();

            }
        });

    }

    public void checkButtonGender(View v){

        radioButtonGender = findViewById(radioGroupGender.getCheckedRadioButtonId());
        Toast.makeText(this, "Gender selected: " + radioButtonGender.getText(), Toast.LENGTH_SHORT).show();
    }
}
