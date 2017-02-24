package pets.sheltinder;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.CheckBox;
        import android.widget.EditText;
        import android.widget.TextView;

public class Register extends AppCompatActivity implements View.OnClickListener{
    private final String TAG = getClass().getSimpleName();

    EditText etUsername, etPassword, etConfirm;
    TextView tvFailure;
    Button bRegister;
    CheckBox cbShelter;

    DataBaseHelper helper = new DataBaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etConfirm = (EditText) findViewById(R.id.etConfirm);

        tvFailure = (TextView) findViewById(R.id.tvFailure);

        bRegister = (Button) findViewById(R.id.bRegister);

        cbShelter = (CheckBox) findViewById(R.id.cbShelter);

        bRegister.setOnClickListener(this);
        Log.d(TAG, "Loaded Class");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bRegister:

                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                String confirm = etConfirm.getText().toString();

                boolean isShelter = cbShelter.isChecked();

                if (username.length() == 0) {
                    tvFailure.setText(R.string.blank_username);
                    tvFailure.setVisibility(View.VISIBLE);
                } else if (password.length() == 0) {
                    tvFailure.setText(R.string.blank_password);
                    tvFailure.setVisibility(View.VISIBLE);
                } else {
                    if (password.compareTo(confirm) == 0) {
                        tvFailure.setVisibility(View.INVISIBLE);

                        //User registeredData = new User(username, password, isShelter);

                        Contact c = new Contact();
                        c.setUsername(username);
                        c.setPassword(password);
                        c.setAccessType(isShelter);

                        helper.insertContact(c);

                        startActivity(new Intent(this, Login.class));
                    } else {
                        tvFailure.setText(R.string.diff_passwords);
                        tvFailure.setVisibility(View.VISIBLE);
                    }
                }

                break;
        }
    }
}
