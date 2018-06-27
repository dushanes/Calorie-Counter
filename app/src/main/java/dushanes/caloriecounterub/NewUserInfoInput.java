package dushanes.caloriecounterub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class NewUserInfoInput extends AppCompatActivity {

    public EditText editTextAge;
    public EditText editTextName;;
    public EditText editTextHeight;
    public EditText editTextWeight;;

    private RadioGroup radioGroup;
    private RadioButton radioButton;

    private RadioGroup radioGroupGender;
    private RadioButton radioButtonGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_info_input);

        editTextAge = (EditText) findViewById(R.id.editTextAge);
        editTextName = (EditText) findViewById(R.id.editTextName);;
        editTextHeight = (EditText) findViewById(R.id.editTextHeight);
        editTextWeight = (EditText) findViewById(R.id.editTextWeight);;

        radioGroup = findViewById(R.id.radioGroup);
        //radioButton = findViewById(R.id.radioButtonSed);

        Button buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                int radioIdGender = radioGroupGender.getCheckedRadioButtonId();

                radioButtonGender = findViewById(radioIdGender);
                radioButton = findViewById(radioId);

                String name = editTextName.getText().toString().trim();
                String age = editTextAge.getText().toString().trim();
                String weight = editTextWeight.getText().toString().trim();
                String height = editTextHeight.getText().toString().trim();
                String gender = radioButtonGender.toString();
                String activity = radioButton.toString();

            }
        });

    }

    public void checkButton(View v){
        int radioId = radioGroup.getCheckedRadioButtonId();

        radioButton = findViewById(radioId);
        Toast.makeText(this, "Activity level selected: " + radioButton.getText(), Toast.LENGTH_SHORT).show();
    }

    public void checkButtonGender(View v){
        int radioId = radioGroupGender.getCheckedRadioButtonId();

        radioButtonGender = findViewById(radioId);
        Toast.makeText(this, "Activity level selected: " + radioButtonGender.getText(), Toast.LENGTH_SHORT).show();
    }
}
