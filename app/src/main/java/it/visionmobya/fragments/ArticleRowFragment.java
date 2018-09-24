package it.visionmobya.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
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

public class ArticleRowFragment extends Fragment  implements OnArticleClickListener, View.OnClickListener, OnNewRowListener {

    public static final String FRAGMENT_ARGUMENTS = "articleRowFragmentArguments";
    private static final String FRAGMENT_ARGUMENTS_NUMERO = "articoloNumero" ;

    private EditText article_quantitaET, article_descrizioneET, prezzo_unitarioET;
    private TextView numero_articoloTV, codice_articoloTV, unita_di_misuraTV, codice_ivaTV, sconto_percentualeTV, imponibileTV, sconto_valueTV, articolo_numero_bottomTV, prezzo_totaleTV, article_nameTV ;
    private LinearLayout select_article;
    private Article article;
    private DocumentState documentState = null;
    private int articolo_numero ;

    public static ArticleRowFragment newInstance(DocumentState documentState, int articoloNumero){
        ArticleRowFragment articleRowFragment = new ArticleRowFragment();
        Bundle args = new Bundle();
        args.putSerializable(FRAGMENT_ARGUMENTS, documentState);
        args.putInt(FRAGMENT_ARGUMENTS_NUMERO, articoloNumero);
        articleRowFragment.setArguments(args);
        return articleRowFragment;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //sa here qe hapet nej fragment row i ri regjistrojme on new row listener tek aktiviteti ordine cliente
        ((OrdineClienteActivity)getActivity()).setOnNewRowListener(this);

        if(getArguments().getSerializable(FRAGMENT_ARGUMENTS)!=null){
            //nese ka nje dokument state per kete fragment atehere e marrim dhe e atachojme ne referencen publike te document state te fragmentit specifik
            documentState = (DocumentState) getArguments().getSerializable(FRAGMENT_ARGUMENTS);
        }
        this.articolo_numero = getArguments().getInt(FRAGMENT_ARGUMENTS_NUMERO);
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
        if(documentState!=null)
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
                    calculateImponibile();
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
                    calculateImponibile();
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
                Double quantita = Double.valueOf(article_quantitaET.getText().toString());
                Double imponibile = prezzo_unitario*quantita;
                this.imponibileTV.setText(imponibile.toString());
            }
        }

    }




    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.select_article :
                selectArticle();
                break;
        }
    }

    private void storeDocumentState(){
        String descrizione =article_descrizioneET.getText().toString();
        String codiceIva = codice_ivaTV.getText().toString();
        Double quantita = Double.valueOf(article_quantitaET.getText().toString());
        Double imponibile = Double.valueOf(imponibileTV.getText().toString());
        Double scontoValue = Double.valueOf(sconto_valueTV.getText().toString());
        Double prezzoTotaleArticle = Double.valueOf(prezzo_totaleTV.getText().toString());
        Double prezzoUnitario = Double.valueOf(prezzo_unitarioET.getText().toString());
        Integer numerArticolo = Integer.valueOf(numero_articoloTV.getText().toString());
        Double scontoPercentuale = Double.valueOf(sconto_percentualeTV.getText().toString());

        DocumentState documentState = DocumentState.builder().article(article)
                .codiceIva(codiceIva)
                .quantita(quantita)
                .imponibile(imponibile)
                .scontoValue(scontoValue)
                .prezzoTotaleArticle(prezzoTotaleArticle)
                .prezzoUnitario(prezzoUnitario)
                .numerArticolo(articolo_numero)
                .scontoPercentuale(scontoPercentuale).build();
        ((OrdineClienteActivity)getActivity()).createNewDocument(documentState);
    }


    @Override
    public void onNewRowClicked() {
        if(isDataValid())
        storeDocumentState();
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
        Vat articleVat = AliquoteController.getVatWithId(article.getCodiceIvaVendite());
        this.article_nameTV.setText(article.getDescrizione());
        this.codice_articoloTV.setText(article.getCodiceArticolo());
        this.unita_di_misuraTV.setText(article.getCodiceUnitaDiMisura());
        this.codice_ivaTV.setText(article.getCodiceIvaVendite());
        // this.sconto_percentualeTV.setText(article.getPercentualeDiSconto1());
        this.article = article;
    }
}
