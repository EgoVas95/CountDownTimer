package vasilyev.egor.timer;

import android.icu.util.TimeUnit;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TimeUtils;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    private static final long SECOND = 1000;
    private long now = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Calendar calendar = Calendar.getInstance();
        calendar.set( 2018, 9, 10, 18, 20, 00 );

        long dayX = calendar.getTimeInMillis();
        textView = findViewById( R.id.date );
        now = Calendar.getInstance().getTimeInMillis();

        long timeForCntDwnTmr = dayX - now;
        CountDownTimer countDownTimer = new CountDownTimer( timeForCntDwnTmr, SECOND ) {
            @Override
            public void onTick(long millisUntilFinished) {
                textView.setText( createDataString( millisUntilFinished ) );
            }

            @Override
            public void onFinish() {
                textView.setText("Yay");
            }
        }.start();
    }

    String createDataString( long time ){
        int days = (int) java.util.concurrent.TimeUnit.MILLISECONDS.toDays(time);
        long hours = (int) java.util.concurrent.TimeUnit.MILLISECONDS.toHours(time)
                - java.util.concurrent.TimeUnit.DAYS.toHours(days);

        long minutes = (int) java.util.concurrent.TimeUnit.MILLISECONDS.toMinutes(time)
                - java.util.concurrent.TimeUnit.DAYS.toMinutes(days)
                - java.util.concurrent.TimeUnit.HOURS.toMinutes(hours);

        long seconds = (int) java.util.concurrent.TimeUnit.MILLISECONDS.toSeconds(time)
                - java.util.concurrent.TimeUnit.DAYS.toSeconds(days)
                - java.util.concurrent.TimeUnit.HOURS.toSeconds(hours)
                - java.util.concurrent.TimeUnit.MINUTES.toSeconds(minutes);

        return String.format("%d дней %d часов %d минут %d секунд", days, hours, minutes, seconds);
    }

}
