package com.teamtreehouse.oslist;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import java.util.concurrent.TimeUnit;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.CountDownTimer;
import android.app.NotificationManager;
import android.widget.ProgressBar;
import android.util.Log;
//import java.util.logging.Handler;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")
public class Clock extends MainActivity {

    Button start, done, pause, resume, stopAlert;
    TextView time;
    EditText hour,minute,second;
    MediaPlayer stopClick;
    ProgressBar timerProgress;
    NotificationManager notimanager;
    String hr,min,sec;
    CounterClass timer;
    Handler mHandler;
    //int mProgressStatus=0;
    long totalTime, mProgressStatus=0;
    boolean resetGuard=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clock_layout);

        getSupportActionBar().setTitle("Timer");
        //*** Declaring and Connecting ID ***//
        // Button id is connected
        start= (Button)findViewById(R.id.buttonOne);
        done= (Button)findViewById(R.id.buttonTwo);
        pause= (Button)findViewById(R.id.buttonPause);
        resume=(Button)findViewById(R.id.buttonResume);
        stopAlert=(Button)findViewById(R.id.stopRing);

        //EditText id is connected
        hour=(EditText)findViewById(R.id.hrtext);
        minute=(EditText)findViewById(R.id.mintext);
        second=(EditText)findViewById(R.id.sectext);

        //TextView id is connected
        time=(TextView)findViewById(R.id.timeText);

        //Invisible for Textview
        time.setVisibility(View.INVISIBLE);

        //Invisible for Buttons
        pause.setVisibility(View.INVISIBLE);
        resume.setVisibility(View.INVISIBLE);
        stopAlert.setVisibility(View.INVISIBLE);

        //Listner for start button
        start.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        //resetGuard set to true
                        resetGuard=true;

                        //Keyboard dismiss
                        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(second.getWindowToken(), 0);

                        //Invisible for EditTexts
                        hour.setVisibility(View.INVISIBLE);
                        minute.setVisibility(View.INVISIBLE);
                        second.setVisibility(View.INVISIBLE);

                        //Visibility for Button
                        start.setVisibility(View.INVISIBLE);
                        pause.setVisibility(View.VISIBLE);

                        //Visible for TextView
                        time.setVisibility(View.VISIBLE);

                        //*** Getting time ***//
                        //Hour
                        if (hour.getText().toString().trim().length() == 0) {
                            hr = "0";
                        } else {
                            hr = hour.getText().toString();
                        }

                        //Minute
                        if (minute.getText().toString().trim().length() == 0) {
                            min = "0";
                        } else {
                            min = minute.getText().toString();
                        }

                        //Second
                        if (second.getText().toString().trim().length() == 0) {
                            sec = "0";
                        } else {
                            sec = second.getText().toString();
                        }

                        //*** Time calculation ***//
                        int hou = Integer.parseInt(hr);
                        int minu = Integer.parseInt(min);
                        int secd = Integer.parseInt(sec);

                        hou = hou * 3600000;
                        minu = minu * 60000;
                        secd = secd * 1000;

                        totalTime = hou + minu + secd;

                        //******* Setting timer(CHECK BACK AGAIN)
                        if (hou >= 0 || minu >= 0 || secd >= 0) {
                            //CounterClass
                            timer = new CounterClass(totalTime, 1000);
                        }
                        timer.start();
                    }
                }
        );

        //Pause button Listner
        pause.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        //Visibility for Button
                        pause.setVisibility(View.INVISIBLE);
                        resume.setVisibility(View.VISIBLE);

                        timer.cancel();
                    }
                }

        );

        //Resume button Listner
        resume.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        //Visibility for Button
                        resume.setVisibility(View.INVISIBLE);
                        pause.setVisibility(View.VISIBLE);

                        timer = new CounterClass(totalTime,1000);
                        timer.start();
                    }
                }

        );

        //Done button Listner
        done.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        if(resetGuard){
                            timer.cancel();
                        }

                        //Invisible for Textview
                        time.setVisibility(View.INVISIBLE);

                        //Visible for EditTexts
                        hour.setVisibility(View.VISIBLE);
                        minute.setVisibility(View.VISIBLE);
                        second.setVisibility(View.VISIBLE);

                        //Visibility of button
                        resume.setVisibility(View.INVISIBLE);
                        pause.setVisibility(View.INVISIBLE);
                        start.setVisibility(View.VISIBLE);

                        //Notification cancel
                        notimanager.cancel(2);
                    }
                }
        );

        //StopAlert listner
        stopAlert.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        //Visibility for Button
                        pause.setVisibility(View.VISIBLE);
                        done.setVisibility(View.VISIBLE);
                        time.setVisibility(View.VISIBLE);

                        //Invisibility for Button
                        stopAlert.setVisibility(View.INVISIBLE);

                        //Delete Notification
                        notimanager.cancel(1);
                        notimanager.cancel(2);

                        stopClick.stop();
                    }
                }

        );
    }
    /***CountDownClass override***/
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @SuppressLint("NewApi")
    public class CounterClass extends CountDownTimer{

        //Constructor
        public CounterClass(long millisInFuture, long countDownInterval){

            super(millisInFuture, countDownInterval);
        }

        //onFinish
        public void onFinish(){

            time.setTextColor(Color.BLACK);
            time.setText("Times Up!!!");


            //Invisibility for button
            pause.setVisibility(View.INVISIBLE);
            done.setVisibility(View.INVISIBLE);
            time.setVisibility(View.INVISIBLE);

            //Visibility for button
            stopAlert.setVisibility(View.VISIBLE);

            //Setting Up AlertSound
            stopClick = MediaPlayer.create(Clock.this,R.raw.loudalarm);
            stopClick.start();

            //Setting up Notification
            NotificationCompat.Builder notification= new NotificationCompat.Builder(Clock.this)
                    .setSmallIcon(R.drawable.creme_brelee)
                    .setContentTitle("Time is up!!!")
                    .setContentText("Stop Cooking :)");

            //Vibrate
            long [] vibrate= {0,100,200,300};
            notification.setVibrate(vibrate);

            //Notification Action
            PendingIntent myPendingIntent;
            Intent notiIntent = new Intent();
            Context myContext= getApplicationContext();

            notiIntent.setClass(myContext, Clock.class);
            notiIntent.putExtra("ID", 1);
            myPendingIntent= PendingIntent.getActivity(myContext,0,notiIntent,0);

            notification.setContentIntent(myPendingIntent);


            //Put notification in action
            notimanager= (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
            notimanager.notify(1,notification.build());
        }

        //onTick
        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        @SuppressLint("NewApi")
        public void onTick(long millisUntilFinished){
            totalTime= millisUntilFinished;
            String hms= String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(totalTime),
                    TimeUnit.MILLISECONDS.toMinutes(totalTime) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(totalTime)),
                    TimeUnit.MILLISECONDS.toSeconds(totalTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(totalTime)));
            System.out.print(hms);

            //***Setting up Notification
            NotificationCompat.Builder notiStatus= new NotificationCompat.Builder(Clock.this)
                    .setSmallIcon(R.drawable.creme_brelee)
                    .setContentTitle("Cooking Done in: " + hms);

            //Notification Action
            PendingIntent myPendingIntent;
            Intent notiIntent = new Intent();
            Context myContext= getApplicationContext();

            notiIntent.setClass(myContext, Clock.class);
            notiIntent.putExtra("ID", 2);
            myPendingIntent= PendingIntent.getActivity(myContext,0,notiIntent,0);

            notiStatus.setContentIntent(myPendingIntent);
            //Put notification in action
            notimanager= (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
            notimanager.notify(2,notiStatus.build());

            //Text color 5s before time up!!
            if(totalTime<= 11000){
                time.setTextColor(Color.RED);

            }

            time.setText(hms);
        }
    }

    @Override
    public void onBackPressed(){
        Log.d("CDA","onBackPressed Called");
        Intent setIntent= new Intent(Intent.ACTION_MAIN);
        setIntent.addCategory(Intent.CATEGORY_HOME);
        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(setIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}






