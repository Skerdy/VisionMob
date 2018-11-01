package it.visionmobya.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.apache.commons.net.io.Util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import it.visionmobya.R;
import it.visionmobya.fragments.ArticleDetailsDialog;
import it.visionmobya.fragments.ArticleRowFragment;
import it.visionmobya.fragments.ArticleRowsFragment;
import it.visionmobya.fragments.CloserDocumentFragment;
import it.visionmobya.fragments.RecyclerViewDialog;
import it.visionmobya.listener.OnNewRowListener;
import it.visionmobya.listener.OnSaveAndPrintButtonListener;
import it.visionmobya.models.Client;
import it.visionmobya.models.DocumentCategory;
import it.visionmobya.models.customModels.DocumentState;
import it.visionmobya.utils.CodesUtil;
import it.visionmobya.utils.FinanceHelper;
import it.visionmobya.utils.PaginationUtil.DocumentNavigationListener;
import it.visionmobya.utils.PaginationUtil.ListPagination;
import it.visionmobya.utils.PaginationUtil.Paginatiable;
import it.visionmobya.utils.TextViewHelper;
import it.visionmobya.utils.Utils;

public class OrdineClienteActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener , Paginatiable{

    //textview e meposhtem sherbejne si butona dhe jane te vendosur ne fudn te layout
    private TextView vedi_documentoTV, end_documentoTV, prev_documentoTV, new_documentoTV, next_documentoTV, save_and_print;

    private TextView imponibileTV, ivaTV, prezzo_totaleTV, articolo_numeroTV, bottom_document_type;

    private TextView cliente_nome_address_TV, cliente_telefoneTV, codice_pagamentoTV;

    private TextView top_title_type_counter;

    private LinearLayout four_buttons_layout;

    private RecyclerViewDialog recyclerViewDialog;
    private ArticleDetailsDialog articleDetailsDialog;
    private Toolbar toolbar;
    private ArrayList<DocumentState> documentStates;
    private FragmentTransaction fragmentTransaction;
    private OnNewRowListener onNewRowListener;
    private int currentDocumentPosition;
    private String activityTitle;
    private Client selectedClient;
    private DocumentCategory documentCategory;
    private Date pickedDate;
    private ListPagination<DocumentState> listPagination;
    private DocumentNavigationListener documentNavigationListener;
    private OnSaveAndPrintButtonListener onSaveAndPrintButtonListener;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ordine_cliente);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        initUI();
        setupDatePicker();
        initArticleFragmentAndDocumentStates();
    }

    private void showArticleRowFragment(DocumentState documentState, boolean addToBackStack){
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        ArticleRowFragment articleRowFragment = ArticleRowFragment.newInstance(documentState);

        if(addToBackStack) {
            fragmentTransaction.addToBackStack("ArticleRowFragment");
            if(fragmentTransaction.isAddToBackStackAllowed()) {
                Log.d("BackStack", "U be add ne backstack Article Row");
            }
        }

        fragmentTransaction.replace(R.id.fragmentContainer, articleRowFragment, "ArticleRowFragment" + documentState.getNumerArticolo());
        fragmentTransaction.commit();
        getSupportFragmentManager().executePendingTransactions();
        Log.d("BackStack", " Count = " + getSupportFragmentManager().getFragments().size() );
    }

    public void showParticularArticleRowFragment(int position){
        currentDocumentPosition = position;
        listPagination.invalidate(currentDocumentPosition);
        showArticleRowFragment(documentStates.get(currentDocumentPosition), false);
    }

    public void deleteParticularArticleRowFragment(int position){
        documentStates.remove(position);
        currentDocumentPosition = documentStates.size()-1;
        listPagination.invalidate(currentDocumentPosition);
        getSupportFragmentManager().beginTransaction().remove(getSupportFragmentManager().findFragmentByTag("ArticleRowFragment" + (position+1)));

    }

    private void showArticleRowsFragment(ArrayList<DocumentState> documentState){
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        ArticleRowsFragment articleRowsFragment = ArticleRowsFragment.newInstance(documentState);
      //  if(getSupportFragmentManager().findFragmentByTag("ArticleRowsFragment")==null)
        fragmentTransaction.addToBackStack("ArticleRowsFragment");
        fragmentTransaction.replace(R.id.fragmentContainer, articleRowsFragment, "ArticleRowsFragment");
        fragmentTransaction.commit();
        getSupportFragmentManager().executePendingTransactions();
    }

    private void showCloseDocumento(){
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        CloserDocumentFragment closerDocumentFragment =  CloserDocumentFragment.newInstance(documentCategory, pickedDate, selectedClient);
        fragmentTransaction.addToBackStack("CloseDocumentFragment");
        if(fragmentTransaction.isAddToBackStackAllowed()){
            Log.d("BackStack", "U be add ne backstack Article CloseDocumentFragment");
        }
        fragmentTransaction.replace(R.id.fragmentContainer, closerDocumentFragment, "CloseDocumentFragment");
        fragmentTransaction.commit();
        getSupportFragmentManager().executePendingTransactions();
        Log.d("BackStack", " Count = " + getSupportFragmentManager().getFragments().size() );
        showPrintAndSaveButton();
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
        bottom_document_type = findViewById(R.id.bottom_document_type);

        save_and_print = findViewById(R.id.save_and_print);
        four_buttons_layout = findViewById(R.id.four_buttons_layout);

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
        articleDetailsDialog = new ArticleDetailsDialog(this);

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
                showCloseDocumento();
            }
        });

        //marrim klientin dhe mbushim te dhenat e nevojshme
        getDataFromIntent();
    }



    @Override
    public void onBackPressed() {
        Log.d("BackStack", " BackStack Count = " + getSupportFragmentManager().getBackStackEntryCount() );
        if(getSupportFragmentManager().getBackStackEntryCount()<=1){
            confirmBackNavigation();
        }
          else {
                 if(getSupportFragmentManager().findFragmentByTag("CloseDocumentFragment")!=null && getSupportFragmentManager().findFragmentByTag("CloseDocumentFragment").isVisible()){
                       resetBottomButtons();
                   }

                getSupportFragmentManager().popBackStack();
        }
    }

    private void showPrintAndSaveButton(){
      this.four_buttons_layout.setVisibility(View.GONE);
        this.save_and_print.setVisibility(View.VISIBLE);
        this.save_and_print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onSaveAndPrintButtonListener!=null){
                    onSaveAndPrintButtonListener.onSaveAndPrintClicked(documentStates);
                }
            }
        });
    }

    private void resetBottomButtons(){
        this.four_buttons_layout.setVisibility(View.VISIBLE);
        this.save_and_print.setVisibility(View.GONE);
    }

    private void initArticleFragmentAndDocumentStates(){
        documentStates = new ArrayList<>();
        currentDocumentPosition = 0;
        DocumentState documentState = new DocumentState();
        //ky flag do behet true vetem brenda fragmentit ShowArticle pasi nje document state do behet bind pasi
        //te kete kaluar filtrat e fushave jobosh qe kerkohen per te shkuar ne nje article dokument te ri
        documentState.setBindDirectly(false);
        documentState.setNumerArticolo(currentDocumentPosition+1);
        documentStates.add(documentState);
        showArticleRowFragment(documentState, true);

        //bejme setup Paginatable Articles, basicly do beje invoke hide both next and previous
        setupPaginatableArticles();
        //bejme setup panelin bottom
        setupBottomPanel();
    }

    //krijon nje dokument te ri te njejten instance e fut tek arraylista dhe ia kalon mbrapsht fragmentit qe e ka thirrur
    //duke realizuar opsionin save pasi kemi te bejme me te njejten reference objekti
    public DocumentState createNewDocument(){
        DocumentState documentState = new DocumentState();
        currentDocumentPosition = currentDocumentPosition+1;
        documentState.setBindDirectly(false);
        documentState.setNumerArticolo(currentDocumentPosition+1);
        documentStates.add(documentState);
        showArticleRowFragment(documentState, false);

        //bej update bottom calculations per articles list size
        updateBottomCalculations();

        listPagination.invalidate(currentDocumentPosition);

        return documentState;
    }

    //hap fragmentin article duke marre next document nga lista e dokumenteve
    private void nextDocument(){
        if(documentNavigationListener!=null){
            documentNavigationListener.onNavigationChanged();
        }
        if(documentStates!=null && !documentStates.isEmpty()){
            if(currentDocumentPosition<documentStates.size()-1){
                ++currentDocumentPosition;
                showArticleRowFragment(documentStates.get(currentDocumentPosition), false);
            }
        }
        listPagination.invalidate(currentDocumentPosition);
    }


    //hap fragmentin article duke marre previous document nga lista e dokumenteve
    private void previousDocument(){
        if(documentNavigationListener!=null){
            documentNavigationListener.onNavigationChanged();
        }
        if(documentStates!=null && !documentStates.isEmpty()){
            if(currentDocumentPosition>0){
                --currentDocumentPosition;
                showArticleRowFragment(documentStates.get(currentDocumentPosition), false);
            }

        }
        listPagination.invalidate(currentDocumentPosition);
    }

    public void setupBottomPanel(){
        bottom_document_type.setText(TextViewHelper.getOrderClientBottomDocumentType(documentCategory));
        updateBottomCalculations();
    }

    public void updateBottomCalculations(){
      int documentsSize = documentStates.size();
      articolo_numeroTV.setText("" + documentsSize);
      Double[] values = FinanceHelper.calculateImponibileIvaTotale(documentStates);
      imponibileTV.setText(Utils.doubleToSringFormat(values[0]));
      ivaTV.setText(Utils.doubleToSringFormat(values[1]));
      prezzo_totaleTV.setText(Utils.doubleToSringFormat(values[2]));
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
    public void setDialogAdapter(RecyclerView.Adapter<it.visionmobya.recyclerView.viewholders.ArticleViewHolder> adapter){
        recyclerViewDialog.setAdapter(adapter);
    }


    public void showArticleDetailsDialog(DocumentState documentState){
        articleDetailsDialog.setDocumentState(documentState);
        articleDetailsDialog.show();
    }

    public void hideArticleDetailsDialog(){
        articleDetailsDialog.hide();
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

    private void setupPaginatableArticles(){
       listPagination = new ListPagination<>(documentStates, this, currentDocumentPosition);
    }


    @Override
    public void onFirstIndex() {
        Log.d("Navigation" , "OnFirstIndex");
        prev_documentoTV.setVisibility(View.INVISIBLE);
        next_documentoTV.setVisibility(View.VISIBLE);
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d("BackStack", "Resume Count = " + getSupportFragmentManager().getFragments().size() );
    }

    @Override
    public void onLastIndex() {
        Log.d("Navigation" , "onLastIndex");
        next_documentoTV.setVisibility(View.INVISIBLE);
        prev_documentoTV.setVisibility(View.VISIBLE);
    }

    @Override
    public void showBoth() {
        Log.d("Navigation" , "showBoth");
        prev_documentoTV.setVisibility(View.VISIBLE);
        next_documentoTV.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideBoth() {
        Log.d("Navigation" , "hideBoth");
        prev_documentoTV.setVisibility(View.INVISIBLE);
        next_documentoTV.setVisibility(View.INVISIBLE);
    }

    public void setDocumentNavigationListener(DocumentNavigationListener documentNavigationListener) {
        this.documentNavigationListener = documentNavigationListener;
    }

    public void setOnSaveAndPrintButtonListener(OnSaveAndPrintButtonListener onSaveAndPrintButtonListener) {
        this.onSaveAndPrintButtonListener = onSaveAndPrintButtonListener;
    }
}

