package it.visionmobya.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import it.visionmobya.R;
import it.visionmobya.fragments.ArticleRowFragment;
import it.visionmobya.fragments.ArticleRowsFragment;
import it.visionmobya.fragments.CloserDocumentFragment;
import it.visionmobya.fragments.RecyclerViewDialog;
import it.visionmobya.listener.OnNewRowListener;
import it.visionmobya.models.Client;
import it.visionmobya.models.DocumentCategory;
import it.visionmobya.models.customModels.DocumentState;
import it.visionmobya.utils.CodesUtil;
import it.visionmobya.utils.TextViewHelper;

public class OrdineClienteActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    //textview e meposhtem sherbejne si butona dhe jane te vendosur ne fudn te layout
    private TextView vedi_documentoTV, end_documentoTV, prev_documentoTV, new_documentoTV, next_documentoTV;

    private TextView imponibileTV, ivaTV, prezzo_totaleTV, articolo_numeroTV;

    private TextView cliente_nome_address_TV, cliente_telefoneTV, codice_pagamentoTV;

    private TextView top_title_type_counter;

    private RecyclerViewDialog recyclerViewDialog;
    private Toolbar toolbar;
    private ArrayList<DocumentState> documentStates;
    private FragmentTransaction fragmentTransaction;
    private OnNewRowListener onNewRowListener;
    private int currentDocumentPosition = 0;
    private String activityTitle;
    private Client selectedClient;
    private DocumentCategory documentCategory;
    private Date pickedDate;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ordine_cliente);
        initUI();
        setupDatePicker();
        documentStates = new ArrayList<>();
        showArticleRowFragment(null, 1);
        checkNavigationValidity();
    }

    private void showArticleRowFragment(DocumentState documentState, int articoloNumero){
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        ArticleRowFragment articleRowFragment = ArticleRowFragment.newInstance(documentState, articoloNumero);
        fragmentTransaction.addToBackStack("ArticleRowFragment");
        fragmentTransaction.replace(R.id.fragmentContainer, articleRowFragment, "ArticleRowFragment");
        fragmentTransaction.commitAllowingStateLoss();
    }

    private void showArticleRowsFragment(ArrayList<DocumentState> documentState){
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        ArticleRowsFragment articleRowsFragment = ArticleRowsFragment.newInstance(documentState);
        fragmentTransaction.addToBackStack("ArticleRowsFragment");
        fragmentTransaction.replace(R.id.fragmentContainer, articleRowsFragment, "ArticleRowsFragment");
        fragmentTransaction.commitAllowingStateLoss();
    }

    private void showCloserDocumento(){
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        CloserDocumentFragment closerDocumentFragment = new CloserDocumentFragment();
        fragmentTransaction.addToBackStack("closerDocumentFragment");
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
        cliente_nome_address_TV = findViewById(R.id.cliente_nome_adress);
        cliente_telefoneTV = findViewById(R.id.cliente_telefono);
        codice_pagamentoTV = findViewById(R.id.codice_pagamento);
        next_documentoTV = findViewById(R.id.next_documento);
        top_title_type_counter = findViewById(R.id.title_ordine_cliente);
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

        //marrim klientin dhe mbushim te dhenat e nevojshme
        getDataFromIntent();
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(getSupportFragmentManager().getBackStackEntryCount()==0){
            confirmBackNavigation();
        }
        else {
            for (int i = 0; i < getSupportFragmentManager().getBackStackEntryCount(); ++i) {
                getSupportFragmentManager().popBackStack();
            }
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

    private void getDataFromIntent(){
        if(getIntent().getBundleExtra(CodesUtil.CLIENT_LIST_TO_CLIENT_ORDER)!=null){
            //marrim klientin
            if(getIntent().getBundleExtra(CodesUtil.CLIENT_LIST_TO_CLIENT_ORDER).getSerializable(CodesUtil.CLIENT_ARGUMENT)!=null){
                Client selectedClient = (Client) getIntent().getBundleExtra(CodesUtil.CLIENT_LIST_TO_CLIENT_ORDER).getSerializable(CodesUtil.CLIENT_ARGUMENT);
                setupActivityClientData(selectedClient);
                this.selectedClient = selectedClient;
            }
            //marrim daten e fundit qe ka zgjedhur agjenti
            if(getIntent().getBundleExtra(CodesUtil.CLIENT_LIST_TO_CLIENT_ORDER).getSerializable(CodesUtil.DATE_ARGUMENT)!=null){
                this.pickedDate = (Date) getIntent().getBundleExtra(CodesUtil.CLIENT_LIST_TO_CLIENT_ORDER).getSerializable(CodesUtil.DATE_ARGUMENT);
            }
            //marrim documentCategoryn
            if(getIntent().getBundleExtra(CodesUtil.CLIENT_LIST_TO_CLIENT_ORDER).getSerializable(CodesUtil.DOCUMENT_TYPE_ARGUMENT)!=null){
                DocumentCategory documentCategory = (DocumentCategory)getIntent().getBundleExtra(CodesUtil.CLIENT_LIST_TO_CLIENT_ORDER).getSerializable(CodesUtil.DOCUMENT_TYPE_ARGUMENT);
                setupActivityDocumentData(documentCategory);
                this.documentCategory= documentCategory;
            }




        }
    }

    //bejme bind cdo view qe i perket data te klientit te zgjedhur
    private void setupActivityClientData(Client client){
      activityTitle = TextViewHelper.getOrderClientToolbarTitle(client);
      getSupportActionBar().setTitle(activityTitle);
      cliente_telefoneTV.setText(client.getTelefono().trim());
      codice_pagamentoTV.setText(client.getCodicePagamento().trim());
      cliente_nome_address_TV.setText(TextViewHelper.getClientAddressOrderClient(client));
    }

    private void setupActivityDocumentData(DocumentCategory documentCategory){
        if(pickedDate==null)
           setTopTitleText(documentCategory, new Date());
        else{
            setTopTitleText(documentCategory, pickedDate);
        }
    }

    private void setTopTitleText(DocumentCategory documentCategory, Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = dateFormat.format(date);
        top_title_type_counter.setText(TextViewHelper.generateClientListTitle(documentCategory, dateStr));
    }

    private void setupDatePicker(){
        Calendar now = Calendar.getInstance();
        final DatePickerDialog dpd = DatePickerDialog.newInstance(
                OrdineClienteActivity.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );

        top_title_type_counter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dpd.show(getFragmentManager(), "Please pick a date!");
            }
        });
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, monthOfYear, dayOfMonth);
        Date date = calendar.getTime();
        this.pickedDate = date;
        setTopTitleText(documentCategory, date);
    }



    private void confirmBackNavigation(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this, R.style.AlertDialogBox);
        alertDialog.setTitle("Back Pressed");
        alertDialog.setMessage("Are you sure? Data might be lost!");
        alertDialog.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                OrdineClienteActivity.super.onBackPressed();
            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }



}

