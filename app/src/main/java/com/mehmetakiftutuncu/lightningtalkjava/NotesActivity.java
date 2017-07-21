package com.mehmetakiftutuncu.lightningtalkjava;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NotesActivity extends AppCompatActivity {
    private TextView mEmptyMessage;
    private RecyclerView mRecyclerView;

    private List<String> mNotes;
    private NoteAdapter mNoteAdapter;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        mEmptyMessage = (TextView) findViewById(R.id.emptyMessage);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        final Random random = new Random();
        ((FloatingActionButton) findViewById(R.id.addNoteButton)).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                addNote("Random note " + random.nextInt(1000));
            }
        });

        setNotes();
    }

    private void addNote(String note) {
        if (mNotes == null) {
            mNotes = new ArrayList<>();
        }
        mNotes.add(note);

        setNotes();
    }

    private void setNotes() {
        boolean empty = mNotes == null || mNotes.isEmpty();

        mEmptyMessage.setVisibility(empty ? View.VISIBLE : View.GONE);
        mRecyclerView.setVisibility(empty ? View.GONE : View.VISIBLE);

        if (!empty) {
            if (mNoteAdapter == null) {
                mNoteAdapter = new NoteAdapter(mNotes);
            }

            mRecyclerView.setAdapter(mNoteAdapter);
            mNoteAdapter.setNotes(mNotes);
        }
    }
}
