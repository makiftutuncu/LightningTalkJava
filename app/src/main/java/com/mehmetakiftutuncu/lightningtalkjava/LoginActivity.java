package com.mehmetakiftutuncu.lightningtalkjava;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private static final String[] DUMMY_CREDENTIALS = new String[]{"akif:test"};

    private UserLoginTask mAuthTask;

    private EditText mUsernameView;
    private EditText mPasswordView;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUsernameView = (EditText) findViewById(R.id.username);
        mPasswordView = (EditText) findViewById(R.id.password);

        ((Button) findViewById(R.id.login_button)).setOnClickListener(new OnClickListener() {
            @Override public void onClick(View view) {
                attemptLogin();
            }
        });
    }

    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        mUsernameView.setError(null);
        mPasswordView.setError(null);

        String username = mUsernameView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(username)) {
            mUsernameView.setError(getString(R.string.error_invalid_username));
            focusView = mUsernameView;
            cancel = true;
        } else if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            mAuthTask = new UserLoginTask(new User(username, password));
            mAuthTask.execute((Void) null);
        }
    }

    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {
        private final User mUser;

        UserLoginTask(User user) {
            mUser = user;
        }

        @Override protected Boolean doInBackground(Void... params) {
            String value = mUser.getUsername() + ":" + mUser.getPassword();

            for (String credential : DUMMY_CREDENTIALS) {
                if (credential.equals(value)) return true;
            }

            return false;
        }

        @Override protected void onPostExecute(final Boolean success) {
            mAuthTask = null;

            if (success) {
                startActivity(new Intent(LoginActivity.this, NotesActivity.class));
                finish();
            } else {
                Toast.makeText(LoginActivity.this, getString(R.string.error_login_failed), Toast.LENGTH_LONG).show();
            }
        }

        @Override protected void onCancelled() {
            mAuthTask = null;
        }
    }
}
