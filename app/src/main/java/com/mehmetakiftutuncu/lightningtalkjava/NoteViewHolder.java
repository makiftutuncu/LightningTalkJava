package com.mehmetakiftutuncu.lightningtalkjava;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class NoteViewHolder extends RecyclerView.ViewHolder {
    private TextView mNote;

    public NoteViewHolder(View noteLayout) {
        super(noteLayout);

        mNote = (TextView) noteLayout.findViewById(R.id.note);
    }

    public void setNote(String note) {
        if (mNote != null) {
            mNote.setText(note);
        }
    }
}
