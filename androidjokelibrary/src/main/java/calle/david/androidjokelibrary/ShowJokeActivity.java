package calle.david.androidjokelibrary;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;



public class ShowJokeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_joke_activity);
        TextView jokeTitle = findViewById(R.id.joke_title);
        TextView jokeBody = findViewById(R.id.joke_body);

        jokeTitle.setText(getIntent().getStringExtra("jokeTitle"));
        jokeBody.setText(getIntent().getStringExtra("jokeBody"));

    }

}
