package com.shamihamza.architectureexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddEditNoteActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "com.shamihamza.architectureexample.EXTRA_ID";
    public static final String EXTRA_TITLE = "com.shamihamza.architectureexample.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "com.shamihamza.architectureexample.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY = "com.shamihamza.architectureexample.EXTRA_PRIORITY";
    EditText et_textTitle, et_textDescription;
    NumberPicker nb_priority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        initViews();
        nb_priority.setMinValue(1);
        nb_priority.setMaxValue(10);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Note");
            et_textTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            et_textDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            nb_priority.setValue(intent.getIntExtra(EXTRA_PRIORITY, 1));

        } else
            setTitle("Add Note");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveNote() {
        String title = et_textTitle.getText().toString();
        String description = et_textDescription.getText().toString();
        int priority = nb_priority.getValue();

        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "Pleas insert title and description", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_PRIORITY, priority);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }
        setResult(RESULT_OK, data);
        finish();
    }

    private void initViews() {
        et_textTitle = findViewById(R.id.et_textTitle);
        et_textDescription = findViewById(R.id.et_textDescription);
        nb_priority = findViewById(R.id.nb_priority);
    }
}