package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class project extends AppCompatActivity {
    private EditText noteInput;
    private LinearLayout noteListLayout;
    private List<String> notes;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_main);

        noteInput = findViewById(R.id.note_input);
        Button saveButton = findViewById(R.id.save_button);
        noteListLayout = findViewById(R.id.note_list_layout);
        notes = new ArrayList<>();

        saveButton.setOnClickListener(v -> saveNote());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cut:
                cutNote();
                return true;
            case R.id.copy:
                copyNote();
                return true;
            case R.id.paste:
                pasteNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveNote() {
        String note = noteInput.getText().toString().trim();
        if (!note.isEmpty()) {
            notes.add(note);
            addNoteToLayout(note);
            noteInput.setText("");
        } else {
            Toast.makeText(this, "Please enter a note", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("SetTextI18n")
    private void addNoteToLayout(String note) {
        TextView noteTextView = new TextView(this);
        noteTextView.setText(notes.size() + ". " + note);
        noteTextView.setPadding(0, 10, 0, 10);
        noteListLayout.addView(noteTextView);
    }

    private void cutNote() {
        String note = noteInput.getText().toString().trim();
        if (!note.isEmpty()) {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("Note", note);
            clipboard.setPrimaryClip(clip);

            noteInput.setText("");
            Toast.makeText(this, "Note cut", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No text to cut", Toast.LENGTH_SHORT).show();
        }
    }

    private void copyNote() {
        String note = noteInput.getText().toString().trim();
        if (!note.isEmpty()) {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("Note", note);
            clipboard.setPrimaryClip(clip);

            Toast.makeText(this, "Note copied", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No text to copy", Toast.LENGTH_SHORT).show();
        }
    }

    private void pasteNote() {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        if (clipboard.hasPrimaryClip()) {
            ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);
            String pasteText = item.getText().toString();

            noteInput.setText(pasteText);
            Toast.makeText(this, "Note pasted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Clipboard is empty", Toast.LENGTH_SHORT).show();
        }
    }

    public
    void onback(View view) {
    }
}
