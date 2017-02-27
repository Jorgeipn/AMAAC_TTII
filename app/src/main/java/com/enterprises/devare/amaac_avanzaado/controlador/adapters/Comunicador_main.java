package com.enterprises.devare.amaac_avanzaado.controlador.adapters;

import android.content.res.Resources;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.enterprises.devare.amaac_avanzaado.R;
import com.enterprises.devare.amaac_avanzaado.modelo.db.DBHelper;
import com.enterprises.devare.amaac_avanzaado.modelo.db.DataManager;
import com.enterprises.devare.amaac_avanzaado.modelo.metodos.MethodsManager;
import com.enterprises.devare.amaac_avanzaado.modelo.Pictograma;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

//<editor-fold desc="IMPORTAR ETIQUETAS">
import static com.enterprises.devare.amaac_avanzaado.modelo.Pictograma.CAT_ALIMENTOS;
import static com.enterprises.devare.amaac_avanzaado.modelo.Pictograma.CAT_ANIMALES;
import static com.enterprises.devare.amaac_avanzaado.modelo.Pictograma.CAT_FAMILIA;
import static com.enterprises.devare.amaac_avanzaado.modelo.Pictograma.CAT_LUGARES;
import static com.enterprises.devare.amaac_avanzaado.modelo.Pictograma.CAT_PROFESIONES;
import static com.enterprises.devare.amaac_avanzaado.modelo.Pictograma.CAT_VERBOS;
import static com.enterprises.devare.amaac_avanzaado.modelo.Pictograma.TIPO_PIC_NORMAL;
import static com.enterprises.devare.amaac_avanzaado.modelo.Pictograma.TIPO_PIC_SELECCIONADO;
//</editor-fold>

import static com.enterprises.devare.amaac_avanzaado.modelo.utilidades.Utilidades.getBackground_CardView;


public class Comunicador_main extends AppCompatActivity implements TextToSpeech.OnInitListener{

    //<editor-fold desc="DECLARACIÓN DE VARIABLES">
    private RecyclerView recycler,recycler2,recycler3,recycler4,recycler5,recycler6,recyclerView_frase;
    private CardViewAdaptador adapter;
    private TextToSpeech tts;

    //</editor-fold>

    //<editor-fold desc="DECLARACIÓN DE VARIABLES DE REFERENCIA">
    MethodsManager methodsManager=new MethodsManager();
    private List<Pictograma> PIC_GUARDADO=new ArrayList<Pictograma>();
    DataManager datos=new DataManager();
    private DBHelper db=new DBHelper(this);
    //</editor-fold>


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_comunicador);

        tts = new TextToSpeech( this, this );
        datos.inicializar_pictogramas(this);

        inicializarComponentesUI();
        inicializarAdaptador();
    }

    //<editor-fold desc="MÉTODO inicializarComponentesUI()">
    private void inicializarComponentesUI() {

        recycler =          (RecyclerView) findViewById(R.id.reciclador_card);
        recycler2=          (RecyclerView) findViewById(R.id.reciclador_card_2);
        recycler3=          (RecyclerView) findViewById(R.id.reciclador_card_3);
        recycler4=          (RecyclerView) findViewById(R.id.reciclador_card_4);
        recycler5=          (RecyclerView) findViewById(R.id.reciclador_card_5);
        recycler6=          (RecyclerView) findViewById(R.id.reciclador_card_6);
        recyclerView_frase= (RecyclerView) findViewById(R.id.reciclador_card_item_frase);

    }
    //</editor-fold>

    //<editor-fold desc="MÉTODO inicializarAdaptador()">
    private void inicializarAdaptador() {

        InitAdapter(recycler ,db.getCategoria_Pictogramas(CAT_VERBOS));
        InitAdapter(recycler2,db.getCategoria_Pictogramas(CAT_ALIMENTOS));
        InitAdapter(recycler3,db.getCategoria_Pictogramas(CAT_FAMILIA));
        InitAdapter(recycler4,db.getCategoria_Pictogramas(CAT_PROFESIONES));
        InitAdapter(recycler5,db.getCategoria_Pictogramas(CAT_LUGARES));
        InitAdapter(recycler6,db.getCategoria_Pictogramas(CAT_ANIMALES));

    }
    //</editor-fold>

    //<editor-fold desc="MÉTODO InitAdapter()">
    public void InitAdapter(RecyclerView mRecyclerView, List<Pictograma> items){

        assert mRecyclerView != null;
        adapter = new CardViewAdaptador(items);
        setupRecyclerView(mRecyclerView, adapter, TIPO_PIC_NORMAL);

    }
    //</editor-fold>

    //<editor-fold desc="MÉTODO Init_Adapter_Seleccionados">
    public void Init_Adapter_Seleccionados(RecyclerView mRecyclerView , List<Pictograma> picto_seleccionados){

        assert mRecyclerView != null;
        adapter = new CardViewAdaptador(picto_seleccionados);
        setupRecyclerView( mRecyclerView,adapter,TIPO_PIC_SELECCIONADO);

    }
    //</editor-fold>

    //<editor-fold desc="MÉTODO setupRecyclerView()">
    private void setupRecyclerView(@NonNull RecyclerView recyclerView, CardViewAdaptador items, int tipo) {

        recyclerView.setAdapter(items);
        if (tipo==0){recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        }else
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
          //recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
          //recyclerView.setLayoutManager( new GridLayoutManager(this, 3,GridLayoutManager.VERTICAL, false));
          //recyclerView.setLayoutManager( new GridLayoutManager(this, 3,GridLayoutManager.HORIZONTAL, false));

    }
    //</editor-fold>

    //<editor-fold desc="MÉTODO onCreateOptionsMenu()">
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_comunicador_frases, menu);
        return true;

    }
    //</editor-fold>

    //<editor-fold desc="MÉTODO onOptionsItemSelected()">
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.item_borrar_frase:
                PIC_GUARDADO=methodsManager.DeleteAll(PIC_GUARDADO,this);
                Init_Adapter_Seleccionados(recyclerView_frase,PIC_GUARDADO);
                return true;

            case R.id.item_reproducir_frase:
                String x= methodsManager.Reproducir_Frase(PIC_GUARDADO);
                speak(x);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
    //</editor-fold>

    //<editor-fold desc="MÉTODOS DE LA CLASE TEXT TO SPEECH">
    @Override
    public void onInit(int status) {
        if ( status == TextToSpeech.LANG_MISSING_DATA | status == TextToSpeech.LANG_NOT_SUPPORTED ) {
            Toast toast= Toast.makeText(Comunicador_main.this,"ola", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void speak( String str ) {
        if (str==""){
            speak("Selecciona un pictograma para formar una oración");
        }else{
            tts.speak( str, TextToSpeech.QUEUE_FLUSH, null );
            tts.setSpeechRate( 0.0f );
            tts.setPitch( 0.0f );
        }

    }

    protected void onDestroy() {
        if ( tts != null ) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
    //</editor-fold>


    //<editor-fold desc="CLASE ADAPTADOR CardViewAdaptador">
    public class CardViewAdaptador  extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

        private List<Pictograma> mValues;

        public CardViewAdaptador(List<Pictograma> items) {
            this.mValues = items;
        }

        //<editor-fold desc="MÉTODO onCreateViewHolder">
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view;

            switch (viewType){

                case TIPO_PIC_NORMAL:
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_view_categoria, parent, false);
                    return new CardViewHolder(view);

                case TIPO_PIC_SELECCIONADO:
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_view_frase, parent, false);
                    return new FraseViewHolder(view);

            }
            return null;

        }
        //</editor-fold>

        //<editor-fold desc="MÉTODO onBindViewHolder()">
        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            Pictograma object= mValues.get(position);

            if (object != null) {
                switch (object.getTipo()) {

                    case TIPO_PIC_NORMAL:
                        ((CardViewHolder) holder).mVTextView.setText(object.nombre);
                        ((CardViewHolder) holder).mVImage.setImageResource(object.idDrawable);
                        ((CardViewHolder) holder).cv.setCardBackgroundColor(getBackground_CardView(object.getCategoria()));
                        break;

                    case TIPO_PIC_SELECCIONADO:
                        ((FraseViewHolder) holder).mNombreViewFrase.setText(object.nombre);
                        ((FraseViewHolder) holder).mImageViewFrase.setImageResource(object.getIdDrawable());
                        ((FraseViewHolder) holder).cv.setCardBackgroundColor(getBackground_CardView(object.getCategoria()));
                        break;

                }
            }
            //holder.mVTextView.setText(object.nombre);
            //holder.mVImage.setBackgroundResource(getBackground2(items.get(position).categoria));
            //holder.mVTextView.setTextColor(getBackground(items.get(position).categoria));

        }
        //</editor-fold>

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        //<editor-fold desc="MÉTODO getItemViewType()">
        @Override
        public int getItemViewType(int position) {

            if (mValues != null) {
                final  Pictograma object = mValues.get(position);
                if (object != null) {
                    return object.getTipo();
                }
            }
            return 0;
        }
        //</editor-fold>

        //<editor-fold desc="CLASE CardViewHolder">
        public  class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

            private ImageView mVImage;
            private TextView mVTextView;
            private CardView cv;


            public CardViewHolder(View itemView) {
                super(itemView);

                cv=(CardView) itemView.findViewById(R.id.cv_pictograma);
                mVImage=(ImageView) itemView.findViewById(R.id.iv_card);
                mVTextView=(TextView) itemView.findViewById(R.id.tv_nombre);
                itemView.setOnClickListener(this);

            }

            @Override
            public void onClick(View v) {

                int posicision=getAdapterPosition();
                Pictograma mPictograma=mValues.get(posicision);
                methodsManager.Init_Toast(v,mPictograma,null);

                Locale locSpanish = new Locale("spa", "MEX");
                tts.setLanguage(locSpanish);
                speak(mPictograma.getNombre());

                PIC_GUARDADO=methodsManager.Guardar(TIPO_PIC_SELECCIONADO,mPictograma.getCategoria(),mPictograma.nombre,mPictograma.getIdDrawable(),PIC_GUARDADO);
                Init_Adapter_Seleccionados(recyclerView_frase,PIC_GUARDADO);
            }

        }
        //</editor-fold>

        //<editor-fold desc="CLASE FraseViewHolder">
        public class FraseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

            public CardView cv;
            public  final View mView;
            public  final ImageView mImageViewFrase;
            public  final TextView mNombreViewFrase;

            public FraseViewHolder(View view) {
                super(view);

                mView = view;

                mImageViewFrase=(ImageView) view.findViewById(R.id.iv_card_frase);
                mNombreViewFrase=(TextView) view.findViewById(R.id.tv_nombre_frase);
                cv=(CardView) view.findViewById(R.id.cv_pictograma_frase);

                view.setOnClickListener(this);

            }

            @Override
            public void onClick(View v) {

                int position = getAdapterPosition();
                PIC_GUARDADO = methodsManager.Delete_Picto_Seleccionado(position,PIC_GUARDADO);
                Init_Adapter_Seleccionados(recyclerView_frase,PIC_GUARDADO);

            }
        }
        //</editor-fold>

               @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

    }
    //</editor-fold>


}

