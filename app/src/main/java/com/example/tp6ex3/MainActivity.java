package com.example.tp6ex3;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView cardScheduler, cardNotes;
    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardScheduler = findViewById(R.id.cardScheduler);
        cardNotes = findViewById(R.id.cardNotes);

        cardScheduler.setOnClickListener(this);
        cardNotes.setOnClickListener(this);


        MainActivity.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return MainActivity.context;
    }

    public static int getPx(Context context, int dimensionDp) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dimensionDp * density + 0.5f);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.cardScheduler) {
            startActivity(new Intent(this, SchedulerActivity.class));
        } else if (id == R.id.cardNotes) {0
            startActivity(new Intent(this, NotesActivity.class));
        }
    }
    }

