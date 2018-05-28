package com.fargutuvictoria.unibook.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fargutuvictoria.commons.model.User;
import com.fargutuvictoria.unibook.R;
import com.fargutuvictoria.unibook.auth.UserSession;
import com.fargutuvictoria.unibook.ui.home.HomeActivity;

public class MyProfileActivity extends AppCompatActivity {
    private TextView firstNameText;
    private TextView lastNameText;
    private TextView emailText;
    private EditText passwordText;
    private TextView repeatPasswordTextView;
    private EditText repeatPasswordText;
    private Button changePassword;
    private TextView doneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        Toolbar myToolbar = findViewById(R.id.my_profile_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        firstNameText = findViewById(R.id.first_name_text);
        lastNameText = findViewById(R.id.last_name_text);
        emailText = findViewById(R.id.email_text);
        passwordText = findViewById(R.id.password_text);
        repeatPasswordTextView = findViewById(R.id.repeat_password_text_view);
        repeatPasswordText = findViewById(R.id.repeat_password_text);
        changePassword = findViewById(R.id.change_password);
        doneButton = findViewById(R.id.done_button);
        repeatPasswordText.setVisibility(View.GONE);
        repeatPasswordTextView.setVisibility(View.GONE);
        changePassword.setVisibility(View.GONE);

        User user = UserSession.getInstance().getLoggedInUser();

        firstNameText.setText(user.getFirstName());
        lastNameText.setText(user.getLastName());
        emailText.setText(user.getEmail());

        passwordText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passwordText.setText("");
                passwordText.setCursorVisible(true);
                repeatPasswordTextView.setVisibility(View.VISIBLE);
                repeatPasswordText.setVisibility(View.VISIBLE);
                changePassword.setVisibility(View.VISIBLE);
            }
        });

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
