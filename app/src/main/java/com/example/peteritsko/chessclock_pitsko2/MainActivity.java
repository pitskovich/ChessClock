package com.example.peteritsko.chessclock_pitsko2;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.os.CountDownTimer;
import android.widget.TextView;
import android.widget.ImageButton;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    ImageView clock1;
    ImageView clock2;
    ImageView emblem;

    ImageButton whiteKnight;
    ImageButton blackKnight;

    CountDownTimer timer1;
    CountDownTimer timer2;

    long startTime;
    long timeLeft1;
    long timeLeft2;

    TextView text1;
    TextView text2;
    TextView whiteText;
    TextView blackText;
    TextView whiteWinnerText;
    TextView blackWinnerText;

    boolean whiteTurn;
    boolean gameStarted;
    boolean gamePaused;
    boolean gameOver;

    int whiteWon;
    int blackWon;

    ConstraintLayout back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        back = (ConstraintLayout) findViewById(R.id.backID);
        back.getBackground().setAlpha(20);

        emblem = (ImageView) findViewById(R.id.imageView3);
        emblem.setColorFilter(Color.argb(15,0,0,0));

        whiteKnight = (ImageButton) findViewById(R.id.imageButton1);
        blackKnight = (ImageButton) findViewById(R.id.imageButton2);

        clock1 = (ImageView) findViewById(R.id.imageView);
        clock2 = (ImageView) findViewById(R.id.imageView2);

        clock1.setBackgroundColor(Color.argb(255,0,0,0));
        clock2.setBackgroundColor(Color.argb(255,0,0,0));

        text1 = (TextView) findViewById(R.id.textView);
        text2 = (TextView) findViewById(R.id.textView2);

        whiteText = (TextView) findViewById(R.id.textView4);
        blackText = (TextView) findViewById(R.id.textView3);
        whiteText.setTextColor(Color.argb(255,0,0,0));
        blackText.setTextColor(Color.argb(255,0,0,0));

        whiteWinnerText = (TextView) findViewById(R.id.textView6);
        blackWinnerText = (TextView) findViewById(R.id.textView5);
        whiteWinnerText.setTextColor(Color.argb(255,0,0,0));
        blackWinnerText.setTextColor(Color.argb(255,0,0,0));

        text1.setTextColor(Color.argb(255,255,255,255));
        text2.setTextColor(Color.argb(255,255,255,255));

        whiteWinnerText.setVisibility(View.GONE);
        blackWinnerText.setVisibility(View.GONE);

        whiteTurn = true;
        gameStarted = false;
        gamePaused = false;
        gameOver = false;

        startTime = 3600000;

        timeLeft1 = startTime;
        timeLeft2 = startTime;

        whiteWon = 0;
        blackWon = 0;

        timer1 = new CountDownTimer(timeLeft1,1000) {
            @Override
            public void onTick(long l) {
                if((l/60000) < 5)
                {
                    text1.setTextColor(Color.argb(255,255,0,0));
                }
                else
                {
                    text1.setTextColor(Color.argb(255,255,255,255));
                }
                if (((l/1000) % 60) < 10)
                {
                    text1.setText("" + ((l / 1000)/60) + ":" + "0" + ( (l / 1000) % 60 ) );
                    timeLeft1 = l;
                }
                else
                {
                    text1.setText("" + ((l / 1000) / 60) + ":" + ((l / 1000) % 60));
                    timeLeft1 = l;
                }
            }

            @Override
            public void onFinish() {
                blackWon++;
                blackText.setText("Black: " + blackWon);
                gameStarted = false;
                gameOver = true;
                blackWinnerText.setVisibility(View.VISIBLE);
            }
        };
    }
    public void pressWhite(View view)
    {
        if(whiteTurn && gameStarted && (!gamePaused) && (!gameOver))
        {
            timer1.cancel();
            timer2 = new CountDownTimer(timeLeft2,1000) {
                @Override
                public void onTick(long l) {
                    if((l/60000) < 5)
                    {
                        text2.setTextColor(Color.argb(255,255,0,0));
                    }
                    else
                    {
                        text2.setTextColor(Color.argb(255,255,255,255));
                    }
                    if (((l/1000) % 60) < 10)
                    {
                        text2.setText("" + ((l / 1000)/60) + ":" + "0" + ( (l / 1000) % 60 ) );
                        timeLeft2 = l;
                    }
                    else
                    {
                        text2.setText("" + ((l / 1000) / 60) + ":" + ((l / 1000) % 60));
                        timeLeft2 = l;
                    }
                }

                @Override
                public void onFinish() {
                    whiteWon++;
                    whiteText.setText("White: " + whiteWon);
                    gameStarted = false;
                    gameOver = true;
                    whiteWinnerText.setVisibility(View.VISIBLE);
                }
            }.start();
            whiteTurn = false;
        }
    }
    public void pressBlack(View view)
    {
        if((!whiteTurn) && gameStarted && (!gamePaused) && (!gameOver))
        {
            timer2.cancel();
            timer1 = new CountDownTimer(timeLeft1,1000) {
            @Override
            public void onTick(long l) {
                if((l/60000) < 5)
                {
                    text1.setTextColor(Color.argb(255,255,0,0));
                }
                else
                {
                    text1.setTextColor(Color.argb(255,255,255,255));
                }
                if (((l/1000) % 60) < 10)
                {
                    text1.setText("" + ((l / 1000)/60) + ":" + "0" + ( (l / 1000) % 60 ) );
                    timeLeft1 = l;
                }
                else
                {
                    text1.setText("" + ((l / 1000) / 60) + ":" + ((l / 1000) % 60));
                    timeLeft1 = l;
                }
            }

            @Override
            public void onFinish() {
                blackWon++;
                blackText.setText("Black: " + blackWon);
                gameStarted = false;
                gameOver = true;
                blackWinnerText.setVisibility(View.VISIBLE);
            }
        }.start();
            whiteTurn = true;
        }
    }
    public void startGame(View view)
    {
        if(!gameStarted && (!gameOver))
        {
            gameStarted = true;
            timer1.start();
        }
    }
    public void pauseGame(View view)
    {
        if(gameStarted && (!gameOver))
        {
            if(!gamePaused)
            {
                if(whiteTurn)
                {
                    timer1.cancel();
                    timer1 = new CountDownTimer(timeLeft1,1000) {
                        @Override
                        public void onTick(long l) {
                            if((l/60000) < 5)
                            {
                                text1.setTextColor(Color.argb(255,255,0,0));
                            }
                            else
                            {
                                text1.setTextColor(Color.argb(255,255,255,255));
                            }
                            if (((l/1000) % 60) < 10)
                            {
                                text1.setText("" + ((l / 1000)/60) + ":" + "0" + ( (l / 1000) % 60 ) );
                                timeLeft1 = l;
                            }
                            else
                            {
                                text1.setText("" + ((l / 1000) / 60) + ":" + ((l / 1000) % 60));
                                timeLeft1 = l;
                            }
                        }

                        @Override
                        public void onFinish() {
                            blackWon++;
                            blackText.setText("Black: " + blackWon);
                            gameStarted = false;
                            gameOver = true;
                            blackWinnerText.setVisibility(View.VISIBLE);
                        }
                    };
                    gamePaused = true;
                }
                else
                {
                    timer2.cancel();
                    timer2 = new CountDownTimer(timeLeft2,1000) {
                        @Override
                        public void onTick(long l) {
                            if((l/60000) < 5)
                            {
                                text2.setTextColor(Color.argb(255,255,0,0));
                            }
                            else
                            {
                                text2.setTextColor(Color.argb(255,255,255,255));
                            }
                            if (((l/1000) % 60) < 10)
                            {
                                text2.setText("" + ((l / 1000)/60) + ":" + "0" + ( (l / 1000) % 60 ) );
                                timeLeft2 = l;
                            }
                            else
                            {
                                text2.setText("" + ((l / 1000) / 60) + ":" + ((l / 1000) % 60));
                                timeLeft2 = l;
                            }
                        }

                        @Override
                        public void onFinish() {
                            whiteWon++;
                            whiteText.setText("White: " + whiteWon);
                            gameStarted = false;
                            gameOver = true;
                            whiteWinnerText.setVisibility(View.VISIBLE);
                        }
                    };
                    gamePaused = true;
                }
            }
            else
            {
                if(whiteTurn)
                {
                    timer1.start();
                    gamePaused = false;
                }
                else
                {
                    timer2.start();
                    gamePaused = false;
                }
            }
        }
    }
    public void restartGame(View view)
    {
        gameStarted = false;
        gamePaused = false;
        gameOver = false;
        whiteTurn = true;
        timeLeft1 = startTime;
        timeLeft2 = startTime;
        try {
            timer1.cancel();
            timer2.cancel();
        }
        catch(Exception e){

        }
        text1.setTextColor(Color.argb(255,255,255,255));
        text2.setTextColor(Color.argb(255,255,255,255));
        whiteWinnerText.setVisibility(View.GONE);
        blackWinnerText.setVisibility(View.GONE);
        timer1 = new CountDownTimer(timeLeft1,1000) {
            @Override
            public void onTick(long l) {
                if((l/60000) < 5)
                {
                    text1.setTextColor(Color.argb(255,255,0,0));
                }
                else
                {
                    text1.setTextColor(Color.argb(255,255,255,255));
                }
                if (((l/1000) % 60) < 10)
                {
                    text1.setText("" + ((l / 1000)/60) + ":" + "0" + ( (l / 1000) % 60 ) );
                    timeLeft1 = l;
                }
                else
                {
                    text1.setText("" + ((l / 1000) / 60) + ":" + ((l / 1000) % 60));
                    timeLeft1 = l;
                }
            }

            @Override
            public void onFinish() {
                blackWon++;
                blackText.setText("Black: " + blackWon);
                gameStarted = false;
                gameOver = true;
                blackWinnerText.setVisibility(View.VISIBLE);
            }
        };
        timer2 = new CountDownTimer(timeLeft2,1000) {
            @Override
            public void onTick(long l) {
                if((l/60000) < 5)
                {
                    text2.setTextColor(Color.argb(255,255,0,0));
                }
                else
                {
                    text2.setTextColor(Color.argb(255,255,255,255));
                }
                if (((l/1000) % 60) < 10)
                {
                    text2.setText("" + ((l / 1000)/60) + ":" + "0" + ( (l / 1000) % 60 ) );
                    timeLeft2 = l;
                }
                else
                {
                    text2.setText("" + ((l / 1000) / 60) + ":" + ((l / 1000) % 60));
                    timeLeft2 = l;
                }
            }

            @Override
            public void onFinish() {
                whiteWon++;
                whiteText.setText("White: " + whiteWon);
                gameStarted = false;
                gameOver = true;
                whiteWinnerText.setVisibility(View.VISIBLE);
            }
        };
        text1.setText("" + ((timeLeft1 / 1000)/60) + ":" + "0" + ( (timeLeft1 / 1000) % 60 ) );
        text2.setText("" + ((timeLeft2 / 1000)/60) + ":" + "0" + ( (timeLeft2 / 1000) % 60 ) );
    }
    public void addMinute (View view)
    {
        if(!gameStarted && (!gameOver))
        {
            timeLeft1+=60000;
            timeLeft2+=60000;
            timer1 = new CountDownTimer(timeLeft1,1000) {
                @Override
                public void onTick(long l) {
                    if((l/60000) < 5)
                    {
                        text1.setTextColor(Color.argb(255,255,0,0));
                    }
                    else
                    {
                        text1.setTextColor(Color.argb(255,255,255,255));
                    }
                    if (((l/1000) % 60) < 10)
                    {
                        text1.setText("" + ((l / 1000)/60) + ":" + "0" + ( (l / 1000) % 60 ) );
                        timeLeft1 = l;
                    }
                    else
                    {
                        text1.setText("" + ((l / 1000) / 60) + ":" + ((l / 1000) % 60));
                        timeLeft1 = l;
                    }
                }

                @Override
                public void onFinish() {
                    blackWon++;
                    blackText.setText("Black: " + blackWon);
                    gameStarted = false;
                    gameOver = true;
                    blackWinnerText.setVisibility(View.VISIBLE);
                }
            };
            timer2 = new CountDownTimer(timeLeft2,1000) {
                @Override
                public void onTick(long l) {
                    if((l/60000) < 5)
                    {
                        text2.setTextColor(Color.argb(255,255,0,0));
                    }
                    else
                    {
                        text2.setTextColor(Color.argb(255,255,255,255));
                    }
                    if (((l/1000) % 60) < 10)
                    {
                        text2.setText("" + ((l / 1000)/60) + ":" + "0" + ( (l / 1000) % 60 ) );
                        timeLeft2 = l;
                    }
                    else
                    {
                        text2.setText("" + ((l / 1000) / 60) + ":" + ((l / 1000) % 60));
                        timeLeft2 = l;
                    }
                }

                @Override
                public void onFinish() {
                    whiteWon++;
                    whiteText.setText("White: " + whiteWon);
                    gameStarted = false;
                    gameOver = true;
                    whiteWinnerText.setVisibility(View.VISIBLE);
                }
            };
            text1.setText("" + ((timeLeft1 / 1000)/60) + ":" + "0" + ( (timeLeft1 / 1000) % 60 ) );
            text2.setText("" + ((timeLeft2 / 1000)/60) + ":" + "0" + ( (timeLeft2 / 1000) % 60 ) );
        }
        if(gameStarted && gamePaused && whiteTurn)
        {
            timeLeft1+=60000;
            timer1 = new CountDownTimer(timeLeft1,1000) {
                @Override
                public void onTick(long l) {
                    if((l/60000) < 5)
                    {
                        text1.setTextColor(Color.argb(255,255,0,0));
                    }
                    else
                    {
                        text1.setTextColor(Color.argb(255,255,255,255));
                    }
                    if (((l/1000) % 60) < 10)
                    {
                        text1.setText("" + ((l / 1000)/60) + ":" + "0" + ( (l / 1000) % 60 ) );
                        timeLeft1 = l;
                    }
                    else
                    {
                        text1.setText("" + ((l / 1000) / 60) + ":" + ((l / 1000) % 60));
                        timeLeft1 = l;
                    }
                }

                @Override
                public void onFinish() {
                    blackWon++;
                    blackText.setText("Black: " + blackWon);
                    gameStarted = false;
                    gameOver = true;
                    blackWinnerText.setVisibility(View.VISIBLE);
                }
            };
            if (((timeLeft1/1000) % 60) < 10)
            {
                text1.setText("" + ((timeLeft1 / 1000)/60) + ":" + "0" + ( (timeLeft1 / 1000) % 60 ) );
            }
            else
            {
                text1.setText("" + ((timeLeft1 / 1000) / 60) + ":" + ((timeLeft1 / 1000) % 60));
            }
        }
        if(gameStarted && gamePaused && (!whiteTurn))
        {
            timeLeft2+=60000;
            timer2 = new CountDownTimer(timeLeft2,1000) {
                @Override
                public void onTick(long l) {
                    if((l/60000) < 5)
                    {
                        text2.setTextColor(Color.argb(255,255,0,0));
                    }
                    else
                    {
                        text2.setTextColor(Color.argb(255,255,255,255));
                    }
                    if (((l/1000) % 60) < 10)
                    {
                        text2.setText("" + ((l / 1000)/60) + ":" + "0" + ( (l / 1000) % 60 ) );
                        timeLeft2 = l;
                    }
                    else
                    {
                        text2.setText("" + ((l / 1000) / 60) + ":" + ((l / 1000) % 60));
                        timeLeft2 = l;
                    }
                }

                @Override
                public void onFinish() {
                    whiteWon++;
                    whiteText.setText("White: " + whiteWon);
                    gameStarted = false;
                    gameOver = true;
                    whiteWinnerText.setVisibility(View.VISIBLE);
                }
            };
            if (((timeLeft2/1000) % 60) < 10)
            {
                text2.setText("" + ((timeLeft2 / 1000)/60) + ":" + "0" + ( (timeLeft2 / 1000) % 60 ) );
            }
            else
            {
                text2.setText("" + ((timeLeft2 / 1000) / 60) + ":" + ((timeLeft2 / 1000) % 60));
            }
        }
    }
    public void subtractMinute(View view)
    {
        if(!gameStarted && (!gameOver))
        {
            if((timeLeft1 > 60000) && (timeLeft2 > 60000))
            {
                timeLeft1 -= 60000;
                timeLeft2 -= 60000;
            }
            timer1 = new CountDownTimer(timeLeft1,1000) {
                @Override
                public void onTick(long l) {
                    if((l/60000) < 5)
                    {
                        text1.setTextColor(Color.argb(255,255,0,0));
                    }
                    else
                    {
                        text1.setTextColor(Color.argb(255,255,255,255));
                    }
                    if (((l/1000) % 60) < 10)
                    {
                        text1.setText("" + ((l / 1000)/60) + ":" + "0" + ( (l / 1000) % 60 ) );
                        timeLeft1 = l;
                    }
                    else
                    {
                        text1.setText("" + ((l / 1000) / 60) + ":" + ((l / 1000) % 60));
                        timeLeft1 = l;
                    }
                }

                @Override
                public void onFinish() {
                    blackWon++;
                    blackText.setText("Black: " + blackWon);
                    gameStarted = false;
                    gameOver = true;
                    blackWinnerText.setVisibility(View.VISIBLE);
                }
            };
            timer2 = new CountDownTimer(timeLeft2,1000) {
                @Override
                public void onTick(long l) {
                    if((l/60000) < 5)
                    {
                        text2.setTextColor(Color.argb(255,255,0,0));
                    }
                    else
                    {
                        text2.setTextColor(Color.argb(255,255,255,255));
                    }
                    if (((l/1000) % 60) < 10)
                    {
                        text2.setText("" + ((l / 1000)/60) + ":" + "0" + ( (l / 1000) % 60 ) );
                        timeLeft2 = l;
                    }
                    else
                    {
                        text2.setText("" + ((l / 1000) / 60) + ":" + ((l / 1000) % 60));
                        timeLeft2 = l;
                    }
                }

                @Override
                public void onFinish() {
                    whiteWon++;
                    whiteText.setText("White: " + whiteWon);
                    gameStarted = false;
                    gameOver = true;
                    whiteWinnerText.setVisibility(View.VISIBLE);
                }
            };
            text1.setText("" + ((timeLeft1 / 1000)/60) + ":" + "0" + ( (timeLeft1 / 1000) % 60 ) );
            text2.setText("" + ((timeLeft2 / 1000)/60) + ":" + "0" + ( (timeLeft2 / 1000) % 60 ) );
        }
        if(gameStarted && gamePaused && whiteTurn)
        {
            if(timeLeft1 > 60000)
            {
                timeLeft1-=60000;
            }
            timer1 = new CountDownTimer(timeLeft1,1000) {
                @Override
                public void onTick(long l) {
                    if((l/60000) < 5)
                    {
                        text1.setTextColor(Color.argb(255,255,0,0));
                    }
                    else
                    {
                        text1.setTextColor(Color.argb(255,255,255,255));
                    }
                    if (((l/1000) % 60) < 10)
                    {
                        text1.setText("" + ((l / 1000)/60) + ":" + "0" + ( (l / 1000) % 60 ) );
                        timeLeft1 = l;
                    }
                    else
                    {
                        text1.setText("" + ((l / 1000) / 60) + ":" + ((l / 1000) % 60));
                        timeLeft1 = l;
                    }
                }

                @Override
                public void onFinish() {
                    blackWon++;
                    blackText.setText("Black: " + blackWon);
                    gameStarted = false;
                    gameOver = true;
                    blackWinnerText.setVisibility(View.VISIBLE);
                }
            };
            if (((timeLeft1/1000) % 60) < 10)
            {
                text1.setText("" + ((timeLeft1 / 1000)/60) + ":" + "0" + ( (timeLeft1 / 1000) % 60 ) );
            }
            else
            {
                text1.setText("" + ((timeLeft1 / 1000) / 60) + ":" + ((timeLeft1 / 1000) % 60));
            }
        }
        if(gameStarted && gamePaused && (!whiteTurn))
        {
            if(timeLeft2 > 60000)
            {
                timeLeft2-=60000;
            }
            timer2 = new CountDownTimer(timeLeft2,1000) {
                @Override
                public void onTick(long l) {
                    if((l/60000) < 5)
                    {
                        text2.setTextColor(Color.argb(255,255,0,0));
                    }
                    else
                    {
                        text2.setTextColor(Color.argb(255,255,255,255));
                    }
                    if (((l/1000) % 60) < 10)
                    {
                        text2.setText("" + ((l / 1000)/60) + ":" + "0" + ( (l / 1000) % 60 ) );
                        timeLeft2 = l;
                    }
                    else
                    {
                        text2.setText("" + ((l / 1000) / 60) + ":" + ((l / 1000) % 60));
                        timeLeft2 = l;
                    }
                }

                @Override
                public void onFinish() {
                    whiteWon++;
                    whiteText.setText("White: " + whiteWon);
                    gameStarted = false;
                    gameOver = true;
                    whiteWinnerText.setVisibility(View.VISIBLE);
                }
            };
            if (((timeLeft2/1000) % 60) < 10)
            {
                text2.setText("" + ((timeLeft2 / 1000)/60) + ":" + "0" + ( (timeLeft2 / 1000) % 60 ) );
            }
            else
            {
                text2.setText("" + ((timeLeft2 / 1000) / 60) + ":" + ((timeLeft2 / 1000) % 60));
            }
        }
    }
}
