package gsoc.mihaela.claudia.diosan.gsoc_2019_python_library_for_real_time_data_visualization.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import gsoc.mihaela.claudia.diosan.gsoc_2019_python_library_for_real_time_data_visualization.R;

public class LoginActivity extends AppCompatActivity {


    // Views
   // TextView createAccount =  findViewById(R.id.create_account);
      TextView forgotPassword = findViewById(R.id.forgot_password);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

      /*  createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });*/

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });
    }

 /*   public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.about_us:
                Intent intent1 = new Intent(this, AboutUsActivity.class);
                this.startActivity(intent1);
                break;
            case R.id.statistics:
                Intent intent = new Intent(this, StatisticsActivity.class);
                this.startActivity(intent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }*/


}
