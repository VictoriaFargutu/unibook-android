package com.fargutuvictoria.unibook.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fargutuvictoria.unibook.R;
import com.fargutuvictoria.unibook.ui.home.HomeActivity;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private EditText mUsername;
    private EditText mPassword;
    private Button mLoginBtn;

    LoginContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        mPresenter = new LoginPresenter(this);

        mUsername = findViewById(R.id.usernameEdit);
        mPassword = findViewById(R.id.passwordEdit);
        mLoginBtn = findViewById(R.id.loginBtn);

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.doLogin(mUsername.getText().toString(), mPassword.getText().toString());
            }
        });
    }

    @Override
    public void goToNextActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
