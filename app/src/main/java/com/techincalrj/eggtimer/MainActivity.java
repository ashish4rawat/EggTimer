package com.techincalrj.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

        SeekBar ashishSeekBar ;
        TextView ashishText;
        Button ashishButton;
        CountDownTimer MyCountDownTimer;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);


                //Chinging the time with seekBar change
                ashishButton = (Button)findViewById(R.id.ashishButton);
                ashishText  = (TextView)findViewById(R.id.ashishText);
                ashishSeekBar = (SeekBar)findViewById(R.id.ashishSeekBar);
                ashishSeekBar.setMax(600000);

                timeReset();

                ashishSeekBar.setOnSeekBarChangeListener(
                        new SeekBar.OnSeekBarChangeListener() {
                                @Override
                                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {


                                                ashishText.setText(timeFormat(i));

                                }

                                @Override
                                public void onStartTrackingTouch(SeekBar seekBar) {

                                }

                                @Override
                                public void onStopTrackingTouch(SeekBar seekBar) {

                                }
                        }

                );


        }

        public void setTimer(View view)
        {

                if(ashishButton.getText().toString()=="Stop")
                {
                        Log.i("Info","Stop Clicked");
                        MyCountDownTimer.cancel();
                        timeReset();


                }
                else {


                        ashishText = (TextView) findViewById(R.id.ashishText);
                        String str = ashishText.getText().toString();
                        long futureSeconds = Long.valueOf(ashishSeekBar.getProgress());

                      MyCountDownTimer = new CountDownTimer(futureSeconds, 1) {

                                @Override
                                public void onTick(long l) {

                                        ashishSeekBar.setEnabled(false);
                                        ashishText.setText(timeFormat(l));

                                        ashishButton.setText("Stop");


                                }

                                @Override
                                public void onFinish() {

                                        MediaPlayer mplayer = MediaPlayer.create(MainActivity.this, R.raw.airhorn);
                                        mplayer.start();
                                        timeReset();

                                }
                        }.start();


                }




        }
        public void timeReset(){

                ashishText.setText(timeFormat(15000));
                ashishSeekBar.setEnabled(true);
                ashishSeekBar.setProgress(15000);
                ashishButton.setText("Go");



        }



       public String timeFormat(long mili){

               long seconds = mili/1000;
               long s = seconds % 60;
               long m = (seconds / 60) % 60;
               //long h = (seconds / (60 * 60)) % 24;
               return String.format("%02d:%02d",m,s);


       }



}
