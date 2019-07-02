package gsoc.mihaela.claudia.diosan.gsoc_2019_python_library_for_real_time_data_visualization.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import gsoc.mihaela.claudia.diosan.gsoc_2019_python_library_for_real_time_data_visualization.R;

public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        Button btnBack = (Button) findViewById(R.id.btn_back_about_us);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AboutUsActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
