package com.enterprises.devare.amaac_avanzaado.controlador.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.enterprises.devare.amaac_avanzaado.R;
import com.enterprises.devare.amaac_avanzaado.modelo.dummy.GuiaPadreContent;


/**
 * A fragment representing a single Tecnica detail screen.
 * This fragment is either contained in a {@link }
 * in two-pane mode (on tablets) or a {@link }
 * on handsets.
 */
public class guiapadreDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ID_ARTICULO = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private GuiaPadreContent.GuiaPadre itemDetallado;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public guiapadreDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ID_ARTICULO)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            itemDetallado = GuiaPadreContent.ITEM_MAP.get(getArguments().getString(ID_ARTICULO));
/*
            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(itemDetallado.titulo);//el contenido dentro ded  i barra
            }
            */
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //View v = inflater.inflate(R.layout.tecnica_detail_guia_de_uso, container, false);
        View v = inflater.inflate(R.layout.fragmento_detalle_articulo, container, false);


        // Show the dummy content as text in a TextView.
        if (itemDetallado != null) {
            //((TextView) v.findViewById(R.id.tecnica_detail_guia_de_uso)).setText(itemDetallado.descripcion);

            ((TextView) v.findViewById(R.id.titulo)).setText(itemDetallado.titulo);
            ((TextView) v.findViewById(R.id.fecha)).setText(itemDetallado.fecha);
            ((TextView) v.findViewById(R.id.contenido)).setText(itemDetallado.descripcion);

            ImageView myImageView = (ImageView)v.findViewById(R.id.imagen);
// supossing to have an image called ic_play inside my drawables.
            myImageView.setImageResource(itemDetallado.idImagen);




        }

        return v;
    }
}