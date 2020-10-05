package com.example.final_exam_02;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

public class MyView extends View {
    int mChance = 3;
    int mScore = 0;

    private Thread myThread = null;
    Random random;
    float x,y;

    public MyView(Context context){
        super(context);

        myThread = new Thread(new RandomThread());
        myThread.start();
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        Paint p = new Paint();
        p.setTextSize(50);
        p.setColor(Color.BLUE);
        canvas.drawText("기회 : "+mChance, 0, 50, p);
        canvas.drawText("점수 : "+mScore, 0, 100, p);

        //사각형
        p.setColor(Color.RED);
        canvas.drawRect(x,y,x+100,y+100,p);

        invalidate();
    }

    public  class RandomThread implements Runnable{
        @Override
        public void run(){
            while(true) {
                while (mChance != 0) { //기회가 0번 남은게 아니면
                    random = new Random();
                    x = random.nextInt(620);
                    y = random.nextInt(730) +150; //위에 text 자리 더하기

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return;
                    }
                }
            }
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(event.getX()>=x && event.getX()<=x+100 && event.getY() >=y && event.getY() <= y+100){ //네모 사이에 클릭된다면
                    mScore += 1;
                }else{
                    mChance = mChance -1;
                }
        }
        return true;
    }

}
