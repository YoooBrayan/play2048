package com.example.play2048;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.logica.Juego;
import com.example.logica.Posicion;

public class MainActivity extends AppCompatActivity {

    public final static String ARRIBA = "Arriba";
    public final static String ABAJO = "Abajo";
    public final static String DERECHA = "Derecha";
    public final static String IZQUIERDA = "Izquierda";
    RelativeLayout container;
    TextView puntaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Juego juego = new Juego();


        final Posicion[][][] tablero = {juego.getMatriz()};

        container = findViewById(R.id.container);
        puntaje = findViewById(R.id.puntaje);

        rellenar(tablero[0], juego);

        int m;
        final boolean[] b = {true};
        final boolean[] w = {true};

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
                    //movimiento(x1, x2, y1, y2);

                    float difx = x2 - x1;
                    float dify = y2 - y1;

                    String movimiento;

                        if(Math.abs(difx) > Math.abs(dify)){
                            if(difx > 0){
                                juego.movimientoDerecha();
                                b[0] = juego.continuidad();
                                w[0] = juego.win();
                                movimiento = MainActivity.DERECHA;
                            }else{
                                juego.movimientoIzquierda();
                                b[0] = juego.continuidad();
                                w[0] = juego.win();
                                movimiento = MainActivity.IZQUIERDA;
                            }
                        }else{
                            if(dify > 0){
                                juego.movimientoAbajo();
                                b[0] = juego.continuidad();
                                w[0] = juego.win();
                                movimiento = MainActivity.ABAJO;
                            }else{
                                juego.movimientoArriba();
                                b[0] = juego.continuidad();
                                w[0] = juego.win();
                                movimiento = MainActivity.ARRIBA;
                            }
                        }
                        //Toast.makeText(getApplicationContext(), movimiento, Toast.LENGTH_SHORT).show();

                        tablero[0] = juego.getMatriz();
                        rellenar(tablero[0], juego);
                        puntaje.setText(juego.getPuntaje()+"");


                    if(w[0]){
                        System.out.println("Ganaste");
                    }else if(!b[0]){
                        System.out.println("Perdiste");
                    }

                }

                return false;
            }
        });

    }

    public void rellenar(Posicion[][] tablero, Juego juego){

        int cont = 0;
        int lef = 0;
        int topL = 0;

        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                lef += 200;
                cont++;
                TextView posicion = new TextView(getApplicationContext());
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(190,310);

                params.setMargins(lef,topL,0, 0);
                posicion.setLayoutParams(params);
                posicion.setText(tablero[j][i].getNumero()+"");
                posicion.setGravity(Gravity.CENTER);
                posicion.setTextSize(30);
                /*if(juego.getNuevo()[0] == j && juego.getNuevo()[1] == i){
                    posicion.setBackgroundColor(Color.rgb(61, 40, 28));
                }*///else{
                    posicion.setBackgroundColor(Color.rgb(196, 186, 175));
                //}

                if(cont>3){
                    cont=0;
                    lef=0;
                    topL += 320;
                }
                container.addView(posicion);

            }

        }

    }

    /*private void movimiento(float x1, float x2, float y1, float y2) {
        float difx = x2 - x1;
        float dify = y2 - y1;

        String movimiento;

        if(Math.abs(difx) > Math.abs(dify)){
            if(difx > 0){
                juego.movimientoDerecha();
                b = juego.continuidad();
                w = juego.win();
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
    }*/
}
