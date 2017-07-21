package com.mehmetakiftutuncu.lightningtalkjava;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteViewHolder> {
    private List<String> notes;

    public NoteAdapter(List<String> notes) {
        setNotes(notes);
    }

    @Override public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View noteLayout = layoutInflater.inflate(R.layout.note_layout, parent, false);

        return new NoteViewHolder(noteLayout);
    }

    @Override public void onBindViewHolder(NoteViewHolder holder, int position) {
        String note = notes.get(position);

        holder.setNote(note);
    }

    @Override public int getItemCount() {
        return notes == null ? 0 : notes.size();
    }

    public void setNotes(List<String> notes) {
        this.notes = notes;

        notifyDataSetChanged();
    }
}
