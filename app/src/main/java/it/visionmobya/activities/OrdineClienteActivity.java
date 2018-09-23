package it.visionmobya.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import it.visionmobya.CSVModule.VisionFileManager;
import it.visionmobya.R;
import it.visionmobya.fragments.ArticleRowFragment;
import it.visionmobya.fragments.ArticleRowsFragment;
import it.visionmobya.fragments.CloserDocumentFragment;
import it.visionmobya.fragments.RecyclerViewDialog;
import it.visionmobya.listener.OnNewRowListener;
import it.visionmobya.models.Article;
import it.visionmobya.models.customModels.DocumentState;

public class OrdineClienteActivity extends AppCompatActivity  {

    //textview e meposhtem sherbejne si butona dhe jane te vendosur ne fudn te layout
    private TextView vedi_documentoTV, end_documentoTV, prev_documentoTV, new_documentoTV, next_documentoTV;

    private TextView imponibileTV, ivaTV, prezzo_totaleTV, articolo_numeroTV;

    private TextView cliente_nomeTV, cliente_telefoneTV, codice_pagamentoTV;

    private RecyclerViewDialog recyclerViewDialog;
    private Toolbar toolbar;
    private ArrayList<DocumentState> documentStates;
    private FragmentTransaction fragmentTransaction;
    private OnNewRowListener onNewRowListener;
    private int currentDocumentPosition = 0;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ordine_cliente);
        initUI();
        documentStates = new ArrayList<>();
        showArticleRowFragment(null, 1);
        checkNavigationValidity();
    }

    private void showArticleRowFragment(DocumentState documentState, int articoloNumero){
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        ArticleRowFragment articleRowFragment = ArticleRowFragment.newInstance(documentState, articoloNumero);
        fragmentTransaction.replace(R.id.fragmentContainer, articleRowFragment, "ArticleRowFragment");
        fragmentTransaction.commitAllowingStateLoss();
    }

    private void showArticleRowsFragment(ArrayList<DocumentState> documentState){
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        ArticleRowsFragment articleRowsFragment = ArticleRowsFragment.newInstance(documentState);
        fragmentTransaction.replace(R.id.fragmentContainer, articleRowsFragment, "ArticleRowsFragment");
        fragmentTransaction.commitAllowingStateLoss();
    }

    private void showCloserDocumento(){
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        CloserDocumentFragment closerDocumentFragment = new CloserDocumentFragment();
        fragmentTransaction.replace(R.id.fragmentContainer, closerDocumentFragment, "closerDocumentFragment");
        fragmentTransaction.commitAllowingStateLoss();
    }

    private void initUI(){

        vedi_documentoTV = findViewById(R.id.vedi_documento);
        end_documentoTV = findViewById(R.id.end_documento);
        prev_documentoTV = findViewById(R.id.prev_documento);
        new_documentoTV = findViewById(R.id.new_row);
        imponibileTV = findViewById(R.id.imponibile);
        ivaTV = findViewById(R.id.iva);
        prezzo_totaleTV = findViewById(R.id.prezzo_totale);
        articolo_numeroTV = findViewById(R.id.articolo_numero);
        cliente_nomeTV = findViewById(R.id.cliente_nome_adress);
        cliente_telefoneTV = findViewById(R.id.cliente_telefono);
        codice_pagamentoTV = findViewById(R.id.codice_pagamento);
        next_documentoTV = findViewById(R.id.next_documento);

        //inicializimi i toolbarit
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_white_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        prev_documentoTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                previousDocument();
            }
        });

        next_documentoTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextDocument();
            }
        });

        //inicializimi i dialogut
        recyclerViewDialog = new RecyclerViewDialog(this);

        new_documentoTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNewRowListener.onNewRowClicked();
            }
        });

        vedi_documentoTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showArticleRowsFragment(documentStates);
            }
        });

        end_documentoTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCloserDocumento();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        for(int i = 0; i < getSupportFragmentManager().getBackStackEntryCount(); ++i) {
            getSupportFragmentManager().popBackStack();
        }
    }

    //krijon nje dokument te ri dhe rrit counterin me 1
    public void createNewDocument(DocumentState documentState) {
      //si fillim ruajme gjendjen e artikullit tek document states
        documentStates.add(documentState);
        currentDocumentPosition = currentDocumentPosition+1;
        showArticleRowFragment(null, documentState.getNumerArticolo()+1);
        checkNavigationValidity();
    }

    private void nextDocument(){
        if(documentStates!=null && !documentStates.isEmpty()){
            if(currentDocumentPosition<documentStates.size()-1){
                ++currentDocumentPosition;
                showArticleRowFragment(documentStates.get(currentDocumentPosition), documentStates.get(currentDocumentPosition).getNumerArticolo());
            }

        }
        checkNavigationValidity();

    }

    private void previousDocument(){
        if(documentStates!=null && !documentStates.isEmpty()){
            if(currentDocumentPosition>0){
                --currentDocumentPosition;
                showArticleRowFragment(documentStates.get(currentDocumentPosition), documentStates.get(currentDocumentPosition).getNumerArticolo());
            }

        }
        checkNavigationValidity();

    }

    private void checkNavigationValidity(){
        //per momentin i bejme invisible butonat next dhe previous por ne te ardhmen do behen shadow
        if(documentStates.size()<=1){
            prev_documentoTV.setVisibility(View.INVISIBLE);
            next_documentoTV.setVisibility(View.INVISIBLE);
        }
        //perndryshe kontrollojme rastet skaje
        else {
            if (currentDocumentPosition == 0 && documentStates.size()>1) {
                prev_documentoTV.setVisibility(View.INVISIBLE);

            } else {
                prev_documentoTV.setVisibility(View.VISIBLE);
            }
            if (currentDocumentPosition > documentStates.size()-1) {
                next_documentoTV.setVisibility(View.INVISIBLE);
            } else {
                next_documentoTV.setVisibility(View.VISIBLE);
            }
        }
    }


    public void updateBottomCalculations(){

    }

    //metode qe thirret nga fragmenti i article row
    public void showDialog(){
        recyclerViewDialog.show();
    }

    //metode qe thirret nga fragmenti i article row
    public void hideDialog(){
        recyclerViewDialog.hide();
    }

    //metode qe thirret nga fragmenti i article row
    public void setDialogAdapter(RecyclerView.Adapter adapter){
        recyclerViewDialog.setAdapter(adapter);
    }

    public void setOnNewRowListener(OnNewRowListener onNewRowListener) {
        this.onNewRowListener = onNewRowListener;
    }
}

