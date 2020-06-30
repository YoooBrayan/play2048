package com.example.logica;

/**
 *
 * @author YO
 */
public class Juego {

    Posicion matriz[][];
    int puntaje = 0;
    int[] nuevo = new int[2];

    public Juego() {
        this.matriz = new Posicion[4][4];

        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                this.matriz[j][i] = new Posicion();
            }
        }
        /*this.matriz[0][0] = new Posicion(2, false);
        this.matriz[1][0] = new Posicion(8, false);
        this.matriz[2][0] = new Posicion(4, false);
        this.matriz[3][0] = new Posicion(16, false);

        this.matriz[0][1] = new Posicion(128, false);
        this.matriz[1][1] = new Posicion(2, false);
        this.matriz[2][1] = new Posicion(64, false);
        this.matriz[3][1] = new Posicion(8, false);

        this.matriz[0][2] = new Posicion(1024, false);
        this.matriz[1][2] = new Posicion(2, false);
        this.matriz[2][2] = new Posicion(64, false);
        this.matriz[3][2] = new Posicion(8, false);

        this.matriz[0][3] = new Posicion(2, false);
        this.matriz[1][3] = new Posicion(16, false);
        this.matriz[2][3] = new Posicion(8, false);
        this.matriz[3][3] = new Posicion(0, false);*/

        this.matriz[(int) (Math.random() * 4)][(int) (Math.random() * 4)].setNumero((int) (Math.random() * 4) < 3 ? 2 : 4);

        /*int randomx;
        int randomy;
        do {
            randomx = (int) (Math.random() * 4);
            randomy = (int) (Math.random() * 4);
        } while (this.matriz[randomx][randomy].getNumero() != 0);
        this.matriz[randomx][randomy].setNumero((int) (Math.random() * 4) < 3 ? 2 : 4);*/
        generarNumero();
    }

    public Posicion[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(Posicion[][] matriz) {
        this.matriz = matriz;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public int[] getNuevo() {
        return nuevo;
    }

    public void setNuevo(int[] nuevo) {
        this.nuevo = nuevo;
    }

    public void movimientoAbajo() {

        int aux = 0;
        int num = 0;
        boolean b = false;

        for (int j = 0; j < 4; j++) {

            for (int i = 3; i >= 0; i--) {
                if (i != 0) {
                    aux = i;
                    aux--;
                }
                if (this.matriz[i][j].getNumero() != 0 && aux != i) {
                    num = this.matriz[i][j].getNumero();
                    if (this.matriz[aux][j].getNumero() == num) {
                        this.puntaje += num * 2;
                        this.matriz[i][j].setNumero(num * 2);
                        this.matriz[i][j].setEstado(true);
                        this.matriz[aux][j].setNumero(0);
                        b = true;
                    }
                } else {
                    if (i != 0) {
                        while (this.matriz[aux][j].getNumero() == 0 && aux != 0) {
                            aux--;
                        }
                        if (this.matriz[aux][j].getNumero() != 0) {
                            this.matriz[i][j].setNumero(this.matriz[aux][j].getNumero());
                            this.matriz[aux][j].setNumero(0);
                            b = true;
                        }
                    }
                }

                if (i < 3) {

                    if (this.matriz[i + 1][j].getNumero() == this.matriz[i][j].getNumero() && !this.matriz[i + 1][j].getEstado() && !this.matriz[i][j].getEstado() && this.matriz[i][j].getNumero() != 0) {
                        this.puntaje += num * 2;
                        this.matriz[i + 1][j].setNumero(this.matriz[i + 1][j].getNumero() * 2);
                        this.matriz[i + 1][j].setEstado(true);
                        this.matriz[i][j].setNumero(0);
                        b = true;

                    } else if (this.matriz[i + 1][j].getNumero() == 0 && this.matriz[i][j].getNumero() != 0) {
                        this.matriz[i + 1][j].setNumero(this.matriz[i][j].getNumero());
                        this.matriz[i][j].setNumero(0);
                        b = true;
                    }
                }

            }
        }

        actualizarEstados();
        if (b) {
            generarNumero();
        }
        //return validar(matrizA);
    }

    public void movimientoArriba() {

        int aux = 0;
        int num = 0;
        boolean b = false;

        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                if (i != 3) {
                    aux = i;
                    aux++;
                }
                if (this.matriz[i][j].getNumero() != 0 && aux != i) {
                    num = this.matriz[i][j].getNumero();
                    if (this.matriz[aux][j].getNumero() == num) {
                        this.puntaje += num * 2;
                        this.matriz[i][j].setNumero(num * 2);
                        this.matriz[i][j].setEstado(true);
                        this.matriz[aux][j].setNumero(0);
                        b = true;
                    }
                } else {
                    if (i != 3) {
                        while (this.matriz[aux][j].getNumero() == 0 && aux != 3) {
                            aux++;
                        }
                        if (this.matriz[aux][j].getNumero() != 0) {
                            this.matriz[i][j].setNumero(this.matriz[aux][j].getNumero());
                            this.matriz[aux][j].setNumero(0);
                            b = true;
                        }

                    }
                }

                if (i > 0) {

                    if (this.matriz[i - 1][j].getNumero() == this.matriz[i][j].getNumero() && !this.matriz[i - 1][j].getEstado() && !this.matriz[i][j].getEstado() && this.matriz[i][j].getNumero() != 0) {
                        this.puntaje += num * 2;
                        this.matriz[i - 1][j].setNumero(this.matriz[i - 1][j].getNumero() * 2);
                        this.matriz[i - 1][j].setEstado(true);
                        this.matriz[i][j].setNumero(0);

                    } else if (this.matriz[i - 1][j].getNumero() == 0 && this.matriz[i][j].getNumero() != 0) {
                        this.matriz[i - 1][j].setNumero(this.matriz[i][j].getNumero());
                        this.matriz[i][j].setNumero(0);
                        b = true;
                    }
                }

            }

        }

        actualizarEstados();
        if (b) {
            generarNumero();
        }
        //return validar(matrizA);
    }

    public void movimientoIzquierda() {

        int aux = 0;
        int num = 0;
        boolean b = false;

        for (int i = 0; i < 4; i++) {

            for (int j = 0; j < 4; j++) {
                if (j != 3) {
                    aux = j;
                    aux++;
                }
                if (this.matriz[i][j].getNumero() != 0 && aux != j) {
                    num = this.matriz[i][j].getNumero();
                    if (this.matriz[i][aux].getNumero() == num) {
                        this.puntaje += num * 2;
                        this.matriz[i][j].setNumero(num * 2);
                        this.matriz[i][j].setEstado(true);
                        this.matriz[i][aux].setNumero(0);
                        b = true;
                    }
                } else {
                    if (j != 3) {
                        while (this.matriz[i][aux].getNumero() == 0 && aux != 3) {
                            aux++;
                        }
                        if (this.matriz[i][aux].getNumero() != 0) {
                            this.matriz[i][j].setNumero(this.matriz[i][aux].getNumero());
                            this.matriz[i][aux].setNumero(0);
                            b = true;
                        }

                    }
                }

                if (j > 0) {

                    if (this.matriz[i][j - 1].getNumero() == this.matriz[i][j].getNumero() && !this.matriz[i][j - 1].getEstado() && !this.matriz[i][j].getEstado() && this.matriz[i][j].getNumero() != 0) {
                        this.puntaje += num * 2;
                        this.matriz[i][j - 1].setNumero(this.matriz[i][j - 1].getNumero() * 2);
                        this.matriz[i][j - 1].setEstado(true);
                        this.matriz[i][j].setNumero(0);

                    } else if (this.matriz[i][j - 1].getNumero() == 0 && this.matriz[i][j].getNumero() != 0) {
                        this.matriz[i][j - 1].setNumero(this.matriz[i][j].getNumero());
                        this.matriz[i][j].setNumero(0);
                        b = true;
                    }
                }

            }

        }

        actualizarEstados();
        if (b) {
            generarNumero();
        }
        //return validar(matrizA);

    }

    public void movimientoDerecha() {

        int aux = 0;
        int num = 0;
        boolean b = false;

        for (int i = 0; i < 4; i++) {

            for (int j = 3; j >= 0; j--) {
                if (j != 0) {
                    aux = j;
                    aux--;
                }
                if (this.matriz[i][j].getNumero() != 0 && aux != j) {
                    num = this.matriz[i][j].getNumero();
                    if (this.matriz[i][aux].getNumero() == num) {
                        this.puntaje += num * 2;
                        this.matriz[i][j].setNumero(num * 2);
                        this.matriz[i][j].setEstado(true);
                        this.matriz[i][aux].setNumero(0);
                        b = true;
                    }
                } else {
                    if (j != 0) {
                        while (this.matriz[i][aux].getNumero() == 0 && aux != 0) {
                            aux--;
                        }
                        if (this.matriz[i][aux].getNumero() != 0) {
                            this.matriz[i][j].setNumero(this.matriz[i][aux].getNumero());
                            this.matriz[i][aux].setNumero(0);
                            b = true;
                        }
                    }
                }

                if (j < 3) {

                    if (this.matriz[i][j + 1].getNumero() == this.matriz[i][j].getNumero() && !this.matriz[i][j + 1].getEstado() && !this.matriz[i][j].getEstado() && this.matriz[i][j].getNumero() != 0) {
                        this.puntaje += num * 2;
                        this.matriz[i][j + 1].setNumero(this.matriz[i][j + 1].getNumero() * 2);
                        this.matriz[i][j + 1].setEstado(true);
                        this.matriz[i][j].setNumero(0);
                        b = true;

                    } else if (this.matriz[i][j + 1].getNumero() == 0 && this.matriz[i][j].getNumero() != 0) {
                        this.matriz[i][j + 1].setNumero(this.matriz[i][j].getNumero());
                        this.matriz[i][j].setNumero(0);
                        b = true;
                    }
                }

            }

        }

        actualizarEstados();
        if (b) {
            generarNumero();
        }
        //return validar(matrizA);

    }

    public void mostrar() {

        String mostrar = "";

        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                mostrar += this.matriz[j][i].getNumero() + " ";
            }
            mostrar += "\n";
        }

        System.out.println(mostrar);
    }

    public void generarNumero() {

        if (validarEspacios()) {
            String posiciones = "";
            int cont = 0;
            int randomx;
            int randomy;
            do {
                randomx = (int) (Math.random() * 4);
                randomy = (int) (Math.random() * 4);
                String posicion = randomx + "" + randomy;
                posiciones += " " + posicion;
                cont++;
            } while (this.matriz[randomx][randomy].getNumero() != 0);

            this.matriz[randomx][randomy].setNumero((int) (Math.random() * 4) < 3 ? 2 : 4);
            nuevo[0] = randomx;
            nuevo[1] = randomy;

        }

    }

    public void actualizarEstados() {
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                this.matriz[j][i].setEstado(false);
            }
        }

    }

    public boolean validar(Posicion[][] matrizA, Posicion[][] matrizS) {

        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                if (matrizA[i][j].getNumero() != matrizS[i][j].getNumero()) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean validarMovimientoVertical() {

        Posicion[][] matrizA = new Posicion[4][4];
        Posicion[][] matrizS = new Posicion[4][4];

        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                matrizA[j][i] = new Posicion(this.matriz[j][i].getNumero(), false);
                matrizS[j][i] = new Posicion(this.matriz[j][i].getNumero(), false);
            }
        }

        int aux = 0;
        int num = 0;

        for (int j = 0; j < 4; j++) {

            for (int i = 3; i >= 0; i--) {
                if (i != 0) {
                    aux = i;
                    aux--;
                }
                if (matrizS[i][j].getNumero() != 0 && aux != i) {
                    num = matrizS[i][j].getNumero();
                    if (matrizS[aux][j].getNumero() == num) {
                        matrizS[i][j].setNumero(num * 2);
                        matrizS[i][j].setEstado(true);
                        matrizS[aux][j].setNumero(0);
                    }
                } else {
                    if (i != 0) {
                        while (matrizS[aux][j].getNumero() == 0 && aux != 0) {
                            aux--;
                        }
                        matrizS[i][j].setNumero(matrizS[aux][j].getNumero());
                        matrizS[aux][j].setNumero(0);
                    }
                }

                if (i < 3) {

                    if (matrizS[i + 1][j].getNumero() == matrizS[i][j].getNumero() && !matrizS[i + 1][j].getEstado() && !matrizS[i][j].getEstado() && matrizS[i][j].getNumero() != 0) {
                        matrizS[i + 1][j].setNumero(matrizS[i + 1][j].getNumero() * 2);
                        matrizS[i + 1][j].setEstado(true);
                        matrizS[i][j].setNumero(0);

                    } else if (matrizS[i + 1][j].getNumero() == 0) {
                        matrizS[i + 1][j].setNumero(matrizS[i][j].getNumero());
                        matrizS[i][j].setNumero(0);
                    }
                }

            }

        }

        actualizarEstados();
        return validar(matrizA, matrizS);

    }

    public boolean validarMovimientoHorizontal() {

        Posicion[][] matrizA = new Posicion[4][4];
        Posicion[][] matrizS = new Posicion[4][4];

        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                matrizA[j][i] = new Posicion(this.matriz[j][i].getNumero(), false);
                matrizS[j][i] = new Posicion(this.matriz[j][i].getNumero(), false);
            }
        }

        int aux = 0;
        int num = 0;

        for (int i = 0; i < 4; i++) {

            for (int j = 0; j < 4; j++) {
                if (j != 3) {
                    aux = j;
                    aux++;
                }
                if (matrizS[i][j].getNumero() != 0 && aux != j) {
                    num = matrizS[i][j].getNumero();
                    if (matrizS[i][aux].getNumero() == num) {
                        matrizS[i][j].setNumero(num * 2);
                        matrizS[i][j].setEstado(true);
                        matrizS[i][aux].setNumero(0);
                    }
                } else {
                    if (j != 3) {
                        while (matrizS[i][aux].getNumero() == 0 && aux != 3) {
                            aux++;
                        }
                        matrizS[i][j].setNumero(matrizS[i][aux].getNumero());
                        matrizS[i][aux].setNumero(0);
                    }
                }

                if (j > 0) {

                    if (matrizS[i][j - 1].getNumero() == matrizS[i][j].getNumero() && !matrizS[i][j - 1].getEstado() && !matrizS[i][j].getEstado() && matrizS[i][j].getNumero() != 0) {
                        matrizS[i][j - 1].setNumero(matrizS[i][j - 1].getNumero() * 2);
                        matrizS[i][j - 1].setEstado(true);
                        matrizS[i][j].setNumero(0);

                    } else if (matrizS[i][j - 1].getNumero() == 0) {
                        matrizS[i][j - 1].setNumero(matrizS[i][j].getNumero());
                        matrizS[i][j].setNumero(0);
                    }
                }

            }

        }

        actualizarEstados();
        return validar(matrizA, matrizS);

    }

    public boolean continuidad() {

        String cadena = "";

        cadena += " " + validarMovimientoHorizontal();
        cadena += " " + validarMovimientoVertical();

        return cadena.contains("true");
    }

    public boolean validarEspacios() {
        boolean b = false;

        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                if (this.matriz[j][i].getNumero() == 0) {
                    return true;
                }
            }
        }
        return b;
    }

    public boolean win() {
        boolean b = false;

        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                if (this.matriz[j][i].getNumero() == 2048) {
                    return true;
                }
            }
        }
        return b;
    }

}
