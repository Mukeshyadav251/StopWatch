package com.example.mystudiostopwatch;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {
       private StopwatchViewModel ViewModel;
       private TextView timeText;
       private Button startbtn,stopbtn,resetbtn;

       @Override
    protected void onCreate(Bundle savedInstanceState){
           super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_main);

           timeText = findViewById(R.id.timeTextView);
           startbtn = findViewById(R.id.startButton);
           stopbtn = findViewById(R.id.stopButton);
           resetbtn = findViewById(R.id.resetButton);


           ViewModel = new ViewModelProvider (this).get(StopwatchViewModel.class);
             ViewModel.getTime().observe(this,Seconds ->{
                 long seconds = Seconds % 60;
                 long minutes = (Seconds / 60) % 60;
                 long hours = Seconds / 3600;

                 String timeFormatted;
                 if (hours > 0) {
                     timeFormatted = String.format("%02d:%02d:%02d", hours, minutes, seconds);
                 } else if (minutes > 0) {
                     timeFormatted = String.format("%02d:%02d", minutes, seconds);
                 } else {
                     timeFormatted = String.format("%02d", seconds);
                 }

                 timeText.setText(timeFormatted);

           });
             startbtn.setOnClickListener(v -> {ViewModel.startstopwatch();
               startbtn.setVisibility(View.GONE);
                 stopbtn.setVisibility(View.VISIBLE);
                  resetbtn.setVisibility(View.VISIBLE);
           });
             stopbtn.setOnClickListener(v -> {
                 ViewModel.stopstopwatch();
                  stopbtn.setVisibility(View.GONE);
                   startbtn.setVisibility(View.VISIBLE);
                    resetbtn.setVisibility(View.VISIBLE);
             });
             resetbtn.setOnClickListener(v -> {
                 ViewModel.resetstopwatch();
                  resetbtn.setVisibility(View.GONE);
                   startbtn.setVisibility(View.VISIBLE);
                    stopbtn.setVisibility(View.GONE);
             });
       }
       

}
