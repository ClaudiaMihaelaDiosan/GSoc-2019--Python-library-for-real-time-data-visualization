package gsoc.mihaela.claudia.diosan.gsoc_2019_python_library_for_real_time_data_visualization.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;

import gsoc.mihaela.claudia.diosan.gsoc_2019_python_library_for_real_time_data_visualization.R;

public class RegisterActivity extends AppCompatActivity {

    private CheckedTextView checkedTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.volunteer_linear_layout);
        linearLayout.setVisibility(View.GONE);
        checkedTextView = (CheckedTextView) findViewById(R.id.is_volunteer);
        checkedTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkedTextView.setChecked(!checkedTextView.isChecked());
                if (checkedTextView.isChecked()) linearLayout.setVisibility(View.VISIBLE);
                else linearLayout.setVisibility(View.GONE);
            }
        });
    }
}
