package com.enterprises.devare.amaac_avanzaado.modelo.utilidades;

import com.enterprises.devare.amaac_avanzaado.R;

public class Utilidades {

    //<editor-fold desc="MÉTODO getBackground_CardView">
    public static int getBackground_CardView(int categoria){
        int d;

        switch (categoria){
            case 1:
                d = 0xff00E5FF;
                break;
            case 2:
                d = 0xffFFEA00;
                break;
            case 3:
                d = 0xff607D8B;
                break;
            case 4:
                d = 0xff64FFDA;
                break;
            case 5:
                d = 0xffCE93D8;
                break;
            case 6:
                d = 0xffFF4081;
                break;
            default:
                d = 0xffFFFFFF;
                break;
        }

        return d;
    }
    //</editor-fold>

    //<editor-fold desc="MÉTODO getBackground">
    public static int getBackground(int categoria){
        int d;

        switch (categoria){
            case 1:
                d = R.drawable.pic_border_verbos;
                break;
            case 2:
                d = R.drawable.pic_border_alimentos;
                break;
            case 3:
                d = R.drawable.pic_border_familia;
                break;
            case 4:
                d = R.drawable.pic_border_profesiones;
                break;
            case 5:
                d = R.drawable.pic_border_lugares;
                break;
            case 6:
                d = R.drawable.pic_border_animales;
                break;
            default:
                d = R.drawable.pic_border;
                break;
        }

        return d;
    }
    //</editor-fold>

}
