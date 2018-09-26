package it.visionmobya.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

import it.visionmobya.CSVModule.VisionFileManager;
import it.visionmobya.activities.OrdineClienteActivity;
import it.visionmobya.R;
import it.visionmobya.controllers.AliquoteController;
import it.visionmobya.listener.OnArticleClickListener;
import it.visionmobya.listener.OnNewRowListener;
import it.visionmobya.models.Article;
import it.visionmobya.models.Vat;
import it.visionmobya.models.customModels.DocumentState;
import it.visionmobya.recyclerView.adapters.ArticleAdapter;
import it.visionmobya.utils.PaginationUtil.DocumentNavigationListener;

public class ArticleRowFragment extends Fragment  implements OnArticleClickListener, View.OnClickListener, OnNewRowListener , DocumentNavigationListener {

    public static final String FRAGMENT_ARGUMENTS = "articleRowFragmentArguments";
    private static final String FRAGMENT_ARGUMENTS_NUMERO = "articoloNumero" ;

    private EditText article_quantitaET, article_descrizioneET, prezzo_unitarioET;
    private TextView numero_articoloTV, codice_articoloTV, unita_di_misuraTV, codice_ivaTV, sconto_percentualeTV, imponibileTV, sconto_valueTV, articolo_numero_bottomTV, prezzo_totaleTV, article_nameTV ;
    private LinearLayout select_article;
    private Article article;
    private DocumentState documentState = null;
    private int articolo_numero ;
    private Double brutoPrice =0.0;

    public static ArticleRowFragment newInstance(DocumentState documentState){
        ArticleRowFragment articleRowFragment = new ArticleRowFragment();
        Bundle args = new Bundle();
        args.putSerializable(FRAGMENT_ARGUMENTS, documentState);
        articleRowFragment.setArguments(args);
        return articleRowFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //sa here qe hapet nej fragment row i ri regjistrojme on new row listener tek aktiviteti ordine cliente
        ((OrdineClienteActivity)getActivity()).setOnNewRowListener(this);
        ((OrdineClienteActivity)getActivity()).setDocumentNavigationListener(this);
        if(getArguments().getSerializable(FRAGMENT_ARGUMENTS)!=null){
            //nese ka nje dokument state per kete fragment atehere e marrim dhe e atachojme ne referencen publike te document state te fragmentit specifik
            documentState = (DocumentState) getArguments().getSerializable(FRAGMENT_ARGUMENTS);
        }
        this.articolo_numero = documentState.getNumerArticolo();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.article_row_fragment, container, false);
        //bejme inicializimin e gjithe komponenteve te deklaruara ne drawing time dhe i perdorim pasi behet draw ne onviewcreated
        initUI(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //nese document state nuk eshte null atehere fillo mbush tere komponentet me te dhenat e ruajtura perkatesisht
        if(documentState.isBindDirectly())
        bindDocumentStateWithUI(documentState);
        else{
            this.numero_articoloTV.setText("" + articolo_numero);
            this.articolo_numero_bottomTV.setText(""+ articolo_numero);
        }

    }

    private void bindDocumentStateWithUI(DocumentState documentState){
        this.article_quantitaET.setText(documentState.getQuantita().toString());
        this.article_descrizioneET.setText(documentState.getDescrizione());
        this.prezzo_unitarioET.setText(documentState.getPrezzoUnitario().toString());
        this.numero_articoloTV.setText(documentState.getNumerArticolo().toString());
        this.codice_articoloTV.setText(documentState.getArticle().getCodiceArticolo());
        this.unita_di_misuraTV.setText(documentState.getArticle().getCodiceUnitaDiMisura());
        this.codice_ivaTV.setText(documentState.getCodiceIva());
        this.sconto_percentualeTV.setText(documentState.getScontoPercentuale().toString());
        this.imponibileTV.setText(documentState.getImponibile().toString());
        this.sconto_valueTV.setText(documentState.getScontoValue().toString());
        this.articolo_numero_bottomTV.setText(documentState.getNumerArticolo().toString());
        this.prezzo_totaleTV.setText(documentState.getPrezzoTotaleArticle().toString());
        this.article_nameTV.setText(documentState.getArticle().getDescrizione());
    }

    private void initUI(View view){
        this.article_quantitaET = view.findViewById(R.id.article_quantita);
        this.article_descrizioneET = view.findViewById(R.id.article_descrizione);
        this.prezzo_unitarioET = view.findViewById(R.id.prezzo_unit);
        this.numero_articoloTV = view.findViewById(R.id.articolo_numero);
        this.codice_articoloTV = view.findViewById(R.id.article_id);
        this.unita_di_misuraTV = view.findViewById(R.id.unita_misura);
        this.codice_ivaTV = view.findViewById(R.id.codice_iva);
        this.sconto_percentualeTV= view.findViewById(R.id.sconto_percentuale);
        this.imponibileTV = view.findViewById(R.id.imponibile);
        this.sconto_valueTV = view.findViewById(R.id.sconto_value);
        this.articolo_numero_bottomTV = view.findViewById(R.id.articolo_numero_bottom);
        this.prezzo_totaleTV = view.findViewById(R.id.prezzo_totale);
        this.select_article = view.findViewById(R.id.select_article);
        this.article_nameTV = view.findViewById(R.id.article_name);
        select_article.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectArticle();
            }
        });
        numero_articoloTV.setText(""+articolo_numero);
        articolo_numero_bottomTV.setText(""+articolo_numero);

        article_quantitaET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    calculateImponibileValue();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        prezzo_unitarioET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    calculateImponibileValue();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void selectArticle(){
        List<Article> articles = VisionFileManager.getInstance().getArticles();
        ((OrdineClienteActivity)getActivity()).setDialogAdapter(new ArticleAdapter(  articles, this));
        ((OrdineClienteActivity)getActivity()).showDialog();
    }


    private void calculateImponibile(){
        if(prezzo_unitarioET.getText()!=null && prezzo_unitarioET.getText().toString()!=null && !prezzo_unitarioET.getText().toString().isEmpty()){
            if(article_quantitaET.getText()!=null && article_quantitaET.getText().toString()!=null &&!article_quantitaET.getText().toString().isEmpty()){
                Double prezzo_unitario = Double.valueOf(prezzo_unitarioET.getText().toString());
                documentState.setPrezzoUnitario(prezzo_unitario);
                Double quantita = Double.valueOf(article_quantitaET.getText().toString());
                documentState.setQuantita(quantita);
                Double sconto = Double.valueOf(sconto_valueTV.getText().toString().trim());
                documentState.setScontoValue(sconto);
                Double imponibile = prezzo_unitario*quantita-sconto;
                documentState.setImponibile(imponibile);
                this.imponibileTV.setText(imponibile.toString());
            }
        }
    }

    private void calculateBrutoPrice(){
        readPrezzoUnitario();
        readQuantita();
        this.brutoPrice = documentState.getPrezzoUnitario()* documentState.getQuantita();

    }

    private void calculateImponibileValue(){
        calculateBrutoPrice();
        calculateScontoValue();
        Double imponibile = this.brutoPrice - documentState.getScontoValue();
        documentState.setImponibile(imponibile);
        imponibileTV.setText(imponibile.toString());
    }

    //lexo prezzo unitario qe futet si input dhe ruaje ne document state
    private Double readPrezzoUnitario(){
        Double prezzo_unitario = 0.0;
        if(prezzo_unitarioET.getText()!=null && !prezzo_unitarioET.getText().toString().isEmpty()){
                prezzo_unitario = Double.valueOf(prezzo_unitarioET.getText().toString());
        }
        documentState.setPrezzoUnitario(prezzo_unitario);
        return prezzo_unitario;
    }

    //lexo quantita qe futet si input dhe ruaje ne document state
    private Double readQuantita(){
        Double quantita = 0.0;
        if(article_quantitaET.getText()!=null && !article_quantitaET.getText().toString().isEmpty()){
            quantita = Double.valueOf(article_quantitaET.getText().toString());
        }
        documentState.setQuantita(quantita);
        return quantita;
    }

    private Double showScontoPercentuale(){
        Double scontoPercentuale = 0.0;
        if(article.getPercentualeDiSconto1().trim().isEmpty()) {
            scontoPercentuale = 10.0;
        }
        else{
            scontoPercentuale = Double.valueOf(article.getPercentualeDiSconto1());
        }
        sconto_percentualeTV.setText(scontoPercentuale.toString());
        documentState.setScontoPercentuale(scontoPercentuale);
        return scontoPercentuale;
    }

    private Double calculateScontoValue(){
        Double scontoValue = 0.0;
        scontoValue = this.brutoPrice*(documentState.getScontoPercentuale()/100);
        documentState.setScontoValue(scontoValue);
        sconto_valueTV.setText(scontoValue.toString());
        return scontoValue;
    }




    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.select_article :
                selectArticle();
                break;
        }
    }

    private void storeDocumentState(boolean createNewDoc){
        boolean flag = true;

        String descrizione = article_descrizioneET.getText().toString().trim();
        String codiceIva = codice_ivaTV.getText().toString().trim();

        Double quantita;
        if( article_quantitaET.getText().toString().isEmpty()){
            quantita = 0.0;
        }
        else{
            quantita = Double.valueOf(article_quantitaET.getText().toString());
        }


        Double prezzoUnitario ;

        if(prezzo_unitarioET.getText().toString().isEmpty()){
            prezzoUnitario = 0.0;
        }
        else{
            prezzoUnitario = Double.valueOf(prezzo_unitarioET.getText().toString());
        }

        //imponibile check
        Double imponibile = 0.0;
        if(imponibileTV.getText().toString().isEmpty()){
            flag = false;
            Toast.makeText(getActivity(), "Something went wrong while saving data [imponibile]", Toast.LENGTH_LONG).show();
        }
        else{
            imponibile = Double.valueOf(imponibileTV.getText().toString());
        }


        //scontoValue check
        Double scontoValue = 0.0;
        if(sconto_valueTV.getText().toString().isEmpty()){
            flag = false;
            Toast.makeText(getActivity(), "Something went wrong while saving data [scontoValue]", Toast.LENGTH_LONG).show();
        }
        else{
             scontoValue = Double.valueOf(sconto_valueTV.getText().toString());
        }


        //prezzoTotaleArticle check
        Double prezzoTotaleArticle = 0.0;
        if(sconto_valueTV.getText().toString().isEmpty()){
            flag = false;
            Toast.makeText(getActivity(), "Something went wrong while saving data [prezzo_totale]", Toast.LENGTH_LONG).show();
        }
        else{
            prezzoTotaleArticle = Double.valueOf(prezzo_totaleTV.getText().toString());
        }

        //scontoPercentuale check
        Double scontoPercentuale = 0.0;
        if(sconto_valueTV.getText().toString().isEmpty()){
            flag = false;
            Toast.makeText(getActivity(), "Something went wrong while saving data [scontoPercentuale]", Toast.LENGTH_LONG).show();
        }
        else{
            scontoPercentuale = Double.valueOf(sconto_percentualeTV.getText().toString());
        }


        //bejme save gjithe ndryshimet e bera per ate artikull dhe vetem atehere mund te bejme create new Document
        documentState.setDescrizione(descrizione);
        documentState.setCodiceIva(codiceIva);
        documentState.setQuantita(quantita);
        documentState.setPrezzoUnitario(prezzoUnitario);
        documentState.setImponibile(imponibile);
        documentState.setScontoValue(scontoValue);
        documentState.setPrezzoTotaleArticle(prezzoTotaleArticle);
        documentState.setScontoPercentuale(scontoPercentuale);
        documentState.setArticle(article);

        //e bejme bind directly true pasi kur te rihapet ndryshimet jane ruajtur dhe nuk ka property bosh
        documentState.setBindDirectly(true);
        if(createNewDoc && flag)
        ((OrdineClienteActivity)getActivity()).createNewDocument();
        else{
            Log.d("New Document" , "Cannot create new Document because some fields were not saved accordingly");
        }
    }


    @Override
    public void onNewRowClicked() {
        if(isDataValid())
        storeDocumentState(true);
    }

    private boolean isDataValid(){
        boolean flag = true;
        if(article==null){
            Toast.makeText(getActivity(),"Please select an Article!", Toast.LENGTH_LONG).show();
        }
        if (article_quantitaET.getText().toString().trim().isEmpty()) {
            flag = false;
            article_quantitaET.setError("Please insert quantita!");
        }
        if (prezzo_unitarioET.getText().toString().trim().isEmpty()) {
            flag = false;
            prezzo_unitarioET.setError("Please insert unit price!");
        }
        if (prezzo_unitarioET.getText().toString().trim().isEmpty()) {
            flag = false;
            prezzo_unitarioET.setError("Please insert unit price!");
        }
        return flag;
    }

    @Override
    public void onArticleClicked(Article article, int position) {
        ((OrdineClienteActivity)getActivity()).hideDialog();
        this.article = article;
        Vat articleVat = AliquoteController.getVatWithId(article.getCodiceIvaVendite());
        this.article_nameTV.setText(article.getDescrizione());
        this.codice_articoloTV.setText(article.getCodiceArticolo());
        this.unita_di_misuraTV.setText(article.getCodiceUnitaDiMisura());
        this.codice_ivaTV.setText(article.getCodiceIvaVendite());
        showScontoPercentuale();

    }

    @Override
    public void onNavigationChanged() {
        storeDocumentState(false);
    }
}
