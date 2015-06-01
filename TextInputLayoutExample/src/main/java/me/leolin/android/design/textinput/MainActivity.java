package me.leolin.android.design.textinput;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private Button buttonSubmit;
    private TextInputLayout inputLayoutPassword;
    private TextInputLayout inputLayoutAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputLayoutAccount = (TextInputLayout) findViewById(R.id.textInputAccount);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputPassword);
        buttonSubmit = (Button) findViewById(R.id.btnSubmit);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputLayoutAccount.setError(null);
                inputLayoutPassword.setError(null);

                if (TextUtils.isEmpty(inputLayoutAccount.getEditText().getText().toString())) {
                    inputLayoutAccount.setError("You must provide an Account");
                    inputLayoutAccount.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(inputLayoutPassword.getEditText().getText().toString())) {
                    inputLayoutPassword.setError("You must provide Password");
                    inputLayoutPassword.requestFocus();
                    return;
                }
            }
        });
    }


}
