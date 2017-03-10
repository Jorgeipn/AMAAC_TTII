package com.enterprises.devare.amaac_avanzaado.controlador.fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.enterprises.devare.amaac_avanzaado.R;
import com.enterprises.devare.amaac_avanzaado.modelo.dummy.TecnicaRelajacionContent;

public class TecnicaDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ID_ARTICULO = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private TecnicaRelajacionContent.TecnicaRelajacion itemDetalldo;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TecnicaDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ID_ARTICULO)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            itemDetalldo = TecnicaRelajacionContent.ITEM_MAP.get(getArguments().getString(ID_ARTICULO));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(itemDetalldo.titulo);//el contenido dentro ded  i barra
            }
        }


    }

    TextView titulo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tecnica_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (itemDetalldo != null) {
            ((TextView) v.findViewById(R.id.contenido)).setText(itemDetalldo.descripcion);
            ((TextView) v.findViewById(R.id.titulo)).setText(itemDetalldo.titulo);
            titulo= (TextView) v.findViewById(R.id.fecha);
            titulo.setText(itemDetalldo.fecha);

            ImageView myImageView = (ImageView)v.findViewById(R.id.imagen);
// supossing to have an image called ic_play inside my drawables.
            myImageView.setImageResource(itemDetalldo.idImagen);

            ImageView go_to_video= (ImageView) v.findViewById(R.id.go_to_video);

            go_to_video.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v) {
                    Log.d("url", titulo.getText().toString());

                    String link = titulo.getText().toString();
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(link)));
                }

            });



        }

        return v;
    }







}







