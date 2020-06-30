package com.example.play2048;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public final static String ARRIBA = "Arriba";
    public final static String ABAJO = "Abajo";
    public final static String DERECHA = "Derecha";
    public final static String IZQUIERDA = "Izquierda";
    RelativeLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View fs = getWindow().getDecorView();
        fs.setOnTouchListener(new View.OnTouchListener() {

            float x1;
            float x2;
            float y1;
            float y2;

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction() == MotionEvent.ACTION_DOWN){

                    x1 = event.getX();
                    y1 = event.getY();

                }else if(event.getAction() == MotionEvent.ACTION_UP){

                    x2 = event.getX();
                    y2 = event.getY();
                    movimiento(x1, x2, y1, y2);
                }

                return false;
            }
        });


        container = findViewById(R.id.container);
        int cont = 0;
        int lef = 0;
        int topL = 0;


        for(int i=0; i<16; i++){
            lef += 200;
            cont++;
            TextView posicion = new TextView(getApplicationContext());
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(190,310);

            params.setMargins(lef,topL,0, 0);
            posicion.setLayoutParams(params);
            posicion.setText(i+"");
            posicion.setGravity(Gravity.CENTER);
            posicion.setTextSize(30);
            posicion.setBackgroundColor(Color.rgb(146, 106, 85));
            if(cont>3){
                cont=0;
                lef=0;
                topL += 320;
            }
            container.addView(posicion);

        }

    }

    private void movimiento(float x1, float x2, float y1, float y2) {
        float difx = x2 - x1;
        float dify = y2 - y1;

        String movimiento;

        if(Math.abs(difx) > Math.abs(dify)){
            if(difx > 0){
                movimiento = MainActivity.DERECHA;
            }else{
                movimiento = MainActivity.IZQUIERDA;
            }
        }else{
            if(dify > 0){
                movimiento = MainActivity.ABAJO;
            }else{
                movimiento = MainActivity.ARRIBA;
            }
        }
        Toast.makeText(getApplicationContext(), movimiento, Toast.LENGTH_SHORT).show();
    }
}
