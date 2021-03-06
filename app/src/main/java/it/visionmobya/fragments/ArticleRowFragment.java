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
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import it.visionmobya.CSVModule.VisionFileManager;
import it.visionmobya.R;
import it.visionmobya.activities.OrdineClienteActivity;
import it.visionmobya.listener.OnArticleClickListener;
import it.visionmobya.listener.OnNewRowListener;
import it.visionmobya.listener.OnUpdateDocumentStateListener;
import it.visionmobya.models.Article;
import it.visionmobya.models.Client;
import it.visionmobya.models.customModels.DocumentState;
import it.visionmobya.recyclerView.adapters.ArticleAdapter;
import it.visionmobya.utils.DocumentStateHelper;
import it.visionmobya.utils.PaginationUtil.DocumentNavigationListener;
import it.visionmobya.utils.Utils;
import it.visionmobya.utils.ValidatorHelper;

public class ArticleRowFragment extends Fragment implements OnArticleClickListener, View.OnClickListener, OnNewRowListener, DocumentNavigationListener, OnUpdateDocumentStateListener {

    public static final String FRAGMENT_ARGUMENTS = "articleRowFragmentArguments";
    private static final String FRAGMENT_ARGUMENTS_NUMERO = "articoloNumero";
    private static final String FRAGMENT_ARGUMENTS_CLIENT = "clientshowArticleRow";

    private EditText article_quantitaET, article_descrizioneET, prezzo_unitarioET, sconto_percentualeET, unita_di_misuraET;
    private TextView numero_articoloTV, codice_articoloTV, codice_ivaTV, imponibileTV, sconto_valueTV, articolo_numero_bottomTV, prezzo_totaleTV, article_nameTV;
    private LinearLayout select_article;
    private Article article;
    private DocumentState documentState = null;
    private int articolo_numero;
    private Double brutoPrice = 0.0;
    private OnUpdateDocumentStateListener onUpdateDocumentStateListener = this;
    private Client selectedClient;

    public static ArticleRowFragment newInstance(DocumentState documentState, Client client) {
        ArticleRowFragment articleRowFragment = new ArticleRowFragment();
        Bundle args = new Bundle();
        args.putSerializable(FRAGMENT_ARGUMENTS, documentState);
        args.putSerializable(FRAGMENT_ARGUMENTS_CLIENT, client);
        articleRowFragment.setArguments(args);
        return articleRowFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("FragNav", "Article Row on Create , savedInstanceState null = " + (savedInstanceState == null));
        //sa here qe hapet nej fragment row i ri regjistrojme on new row listener tek aktiviteti ordine cliente
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        ((OrdineClienteActivity) getActivity()).setOnNewRowListener(this);
        ((OrdineClienteActivity) getActivity()).setDocumentNavigationListener(this);
        if (getArguments().getSerializable(FRAGMENT_ARGUMENTS) != null) {
            //nese ka nje dokument state per kete fragment atehere e marrim dhe e atachojme ne referencen publike te document state te fragmentit specifik
            documentState = (DocumentState) getArguments().getSerializable(FRAGMENT_ARGUMENTS);
            selectedClient = (Client) getArguments().getSerializable(FRAGMENT_ARGUMENTS_CLIENT);
        }
        this.articolo_numero = documentState.getNumerArticolo();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("FragNav", "Article Row on onCreateView , savedInstanceState null = " + (savedInstanceState == null));
        View view = inflater.inflate(R.layout.article_row_fragment, container, false);
        //bejme inicializimin e gjithe komponenteve te deklaruara ne drawing time dhe i perdorim pasi behet draw ne onviewcreated
        initUI(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("FragNav", "Article Row on onViewCreated , savedInstanceState null = " + (savedInstanceState == null));

        //nese document state nuk eshte null atehere fillo mbush tere komponentet me te dhenat e ruajtura perkatesisht
        if (documentState.isBindDirectly() && documentState.getArticle() != null) {
            bindDocumentStateWithUI(documentState);
            Log.d("SkerdiTask", "U be bind dokument state me numer :" + documentState.getNumerArticolo());

        } else {
            Log.d("SkerdiTask", "Nuk U be bind dokument state me numer :" + documentState.getNumerArticolo() + " sepse isBindDirectly = " + documentState.isBindDirectly() + " dhe article eshte null ? " + (documentState.getArticle() == null));
            this.numero_articoloTV.setText("" + articolo_numero);
            this.articolo_numero_bottomTV.setText("" + articolo_numero);
        }
        attachEditTextsOnTextChangedListeners();
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("FragNav", "Article Row on onSaveInstanceState , outState null = " + (outState == null));
    }

    private void bindDocumentStateWithUI(DocumentState documentState) {
        this.article_quantitaET.setText(documentState.getQuantita().toString());
        this.article_descrizioneET.setText(documentState.getDescrizione());
        this.prezzo_unitarioET.setText(documentState.getPrezzoUnitario().toString());
        this.numero_articoloTV.setText(documentState.getNumerArticolo().toString());
        this.codice_articoloTV.setText(documentState.getArticle().getCodiceArticolo());
        this.unita_di_misuraET.setText(documentState.getArticle().getCodiceUnitaDiMisura());
        this.codice_ivaTV.setText(documentState.getArticle().getCodiceIvaVendite());
        this.sconto_percentualeET.setText(documentState.getScontoPercentuale().toString());
        this.imponibileTV.setText(Utils.doubleToSringFormat(documentState.getImponibile()));
        this.sconto_valueTV.setText(Utils.doubleToSringFormat(documentState.getScontoValue()));
        this.articolo_numero_bottomTV.setText(documentState.getNumerArticolo().toString());
        this.prezzo_totaleTV.setText(Utils.doubleToSringFormat(documentState.getPrezzoTotaleArticle()));
        this.article_nameTV.setText(documentState.getArticle().getDescrizione());
    }

    private void initUI(View view) {
        this.article_quantitaET = view.findViewById(R.id.article_quantita);
        this.article_descrizioneET = view.findViewById(R.id.article_descrizione);
        this.prezzo_unitarioET = view.findViewById(R.id.prezzo_unit);
        this.numero_articoloTV = view.findViewById(R.id.articolo_numero);
        this.codice_articoloTV = view.findViewById(R.id.article_id);
        this.unita_di_misuraET = view.findViewById(R.id.unita_misura);
        this.codice_ivaTV = view.findViewById(R.id.codice_iva);
        this.sconto_percentualeET = view.findViewById(R.id.sconto_percentuale);
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
        numero_articoloTV.setText("" + articolo_numero);
        articolo_numero_bottomTV.setText("" + articolo_numero);

    }

    private void selectArticle() {
        List<Article> articles = VisionFileManager.getInstance().getArticles();
        ((OrdineClienteActivity) getActivity()).setDialogAdapter(new ArticleAdapter(articles, this));
        ((OrdineClienteActivity) getActivity()).showDialog();
    }


    //lexo prezzo unitario qe futet si input dhe ruaje ne document state
    private Double readPrezzoUnitario() {
        Double prezzo_unitario = 0.0;
        if (prezzo_unitarioET.getText() != null && !prezzo_unitarioET.getText().toString().isEmpty()) {
            prezzo_unitario = Double.valueOf(prezzo_unitarioET.getText().toString());
        }
        documentState.setPrezzoUnitario(prezzo_unitario);
        return prezzo_unitario;
    }

    //lexo quantita qe futet si input dhe ruaje ne document state
    private Double readQuantita() {
        Double quantita = 0.0;
        if (article_quantitaET.getText() != null && !article_quantitaET.getText().toString().isEmpty()) {
            quantita = Double.valueOf(article_quantitaET.getText().toString());
        }
        documentState.setQuantita(quantita);
        return quantita;
    }

    private String readUnitaDiMisura() {
        String unita_di_misura = "";
        if (unita_di_misuraET.getText() != null) {
            unita_di_misura = unita_di_misuraET.getText().toString().trim();
        }
        documentState.setUnitaDiMisura(unita_di_misura);
        return unita_di_misura;
    }


    private Double readScontoPercentuale() {
        Double scontoPercentuale = 0.0;
        if (!sconto_percentualeET.getText().toString().isEmpty()) {
            scontoPercentuale = Double.valueOf(sconto_percentualeET.getText().toString());
        }
        documentState.setScontoPercentuale(scontoPercentuale);
        return scontoPercentuale;
    }

    public void reBindDataAfterBackPressedWithFirstDocumentRow(DocumentState documentState) {
        this.documentState = documentState;
        Log.d("SkerdiBind", " U be bind me documentState : " + documentState.getNumerArticolo());
        bindDocumentStateWithUI(documentState);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.select_article:
                selectArticle();
                break;
        }
    }

    private void storeDocumentState(boolean createNewDoc) {
        boolean flag = true;

        String descrizione = article_descrizioneET.getText().toString().trim();
        String codiceIva = codice_ivaTV.getText().toString().trim();

        Double quantita;
        if (article_quantitaET.getText().toString().isEmpty()) {
            quantita = 0.0;
        } else {
            quantita = Double.valueOf(article_quantitaET.getText().toString());
        }


        Double prezzoUnitario;

        if (prezzo_unitarioET.getText().toString().isEmpty()) {
            prezzoUnitario = 0.0;
        } else {
            prezzoUnitario = Double.valueOf(prezzo_unitarioET.getText().toString());
        }

        //imponibile check
        Double imponibile = 0.0;
        if (imponibileTV.getText().toString().isEmpty()) {
            flag = false;
        } else {
            imponibile = Double.valueOf(imponibileTV.getText().toString());
        }


        //scontoValue check
        Double scontoValue = 0.0;
        if (sconto_valueTV.getText().toString().isEmpty()) {
            flag = false;
        } else {
            scontoValue = Double.valueOf(sconto_valueTV.getText().toString());
        }


        //prezzoTotaleArticle check
        Double prezzoTotaleArticle = 0.0;
        if (sconto_valueTV.getText().toString().isEmpty()) {
            flag = false;
        } else {
            prezzoTotaleArticle = Double.valueOf(prezzo_totaleTV.getText().toString());
        }

        //scontoPercentuale check
        Double scontoPercentuale = 0.0;
        if (sconto_valueTV.getText().toString().isEmpty()) {
            flag = false;
        } else {
            scontoPercentuale = Double.valueOf(sconto_percentualeET.getText().toString());
        }


        //bejme save gjithe ndryshimet e bera per ate artikull dhe vetem atehere mund te bejme create new Document
        documentState.setDescrizione(descrizione);
        documentState.setQuantita(quantita);
        documentState.setPrezzoUnitario(prezzoUnitario);
        documentState.setImponibile(imponibile);
        documentState.setScontoValue(scontoValue);
        documentState.setPrezzoTotaleArticle(prezzoTotaleArticle);
        documentState.setScontoPercentuale(scontoPercentuale);
        if (article != null)
            documentState.setArticle(article);

        //e bejme bind directly true pasi kur te rihapet ndryshimet jane ruajtur dhe nuk ka property bosh
        documentState.setBindDirectly(true);
        if (createNewDoc && flag)
            ((OrdineClienteActivity) getActivity()).createNewDocument();
        else {
            Log.d("New Document", "Cannot create new Document because some fields were not saved accordingly");
        }
    }


    @Override
    public void onNewRowClicked() {
        if (isDataValid())
            storeDocumentState(true);
    }

    private boolean isDataValid() {
        boolean flag = true;
        if (article == null) {
            Toast.makeText(getActivity(), "Please select an Article!", Toast.LENGTH_LONG).show();
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
        ((OrdineClienteActivity) getActivity()).hideDialog();
        //pasi zgjedhim artikulin e bejme save ne document state dhe japim komanden e update ui me te dhenat nga document state
        DocumentStateHelper.selectArticleAction(documentState, article, selectedClient);
        if (ValidatorHelper.isDocumentEligibleForCalculations(article, article_quantitaET, unita_di_misuraET, prezzo_unitarioET, sconto_percentualeET)) {
            onUpdateDocumentStateListener.onDocumentStateUpdate(true);
        } else {
            updateUserInterfaceWithChanges(true);
        }
        this.article = article;


    }

    private void updateUserInterfaceWithChanges(boolean articleClicked) {

        //kjo vlere do jete gjtihmone jo null  prandaj e bejme reset sa here qe bejme ndryshimet
        this.numero_articoloTV.setText(documentState.getNumerArticolo().toString());
        this.articolo_numero_bottomTV.setText(documentState.getNumerArticolo().toString());

        //nese Artikulli i ketij document state nuk eshte null shfaq ne ekran ndryshimet
        if (documentState.getArticle() != null) {
            this.article_nameTV.setText(documentState.getArticle().getDescrizione().trim());
            this.codice_articoloTV.setText(documentState.getArticle().getCodiceArticolo().trim());
            if (articleClicked) {
                this.unita_di_misuraET.setText(documentState.getArticle().getCodiceUnitaDiMisura().trim());
            }
            this.codice_ivaTV.setText(documentState.getArticle().getCodiceIvaVendite());
        } else {
            Log.d("UpdateUI", "Nuk u be update i UI pas zjgedhjes se artikullit sepse artikulli i ruajtur ne doc state eshte null! ");
        }

        //shfaqim descrizione
        if (documentState.getDescrizione() != null) {
            this.article_descrizioneET.setText(documentState.getDescrizione());
        }

        //shfaqim prezzo unitario
        if (articleClicked) {
            if (documentState.getPrezzoUnitario() != null) {
                this.prezzo_unitarioET.setText(documentState.getPrezzoUnitario().toString());
            }
            //shfaqim sconto percentuale
            if (documentState.getScontoPercentuale() != null) {
                this.sconto_percentualeET.setText(documentState.getScontoPercentuale().toString());
            }

        }

        //shfaqim imponibile value
        if (documentState.getImponibile() != null) {
            this.imponibileTV.setText(Utils.doubleToSringFormat(documentState.getImponibile()));
        }

        //shfaqim sconto value
        if (documentState.getScontoValue() != null) {
            this.sconto_valueTV.setText(Utils.doubleToSringFormat(documentState.getScontoValue()));
        }

        //shfaqim total prezzo pas llogaritjeve te bera
        if (documentState.getPrezzoTotaleArticle() != null) {
            this.prezzo_totaleTV.setText(Utils.doubleToSringFormat(documentState.getPrezzoTotaleArticle()));
        }

    }

    @Override
    public void onNavigationChanged() {
        storeDocumentState(false);
    }

    @Override
    public void onDocumentStateUpdate(boolean valid) {
        if (!DocumentStateHelper.calculateTotalArticoloPrice(documentState)) {
            Toast.makeText(getActivity(), "Calculations for total price failed because no Iva with that id was found", Toast.LENGTH_LONG);
        } else {
            if (!documentState.isBindDirectly()) {
                documentState.setBindDirectly(true);
            }
        }

        updateUserInterfaceWithChanges(valid);
        ((OrdineClienteActivity) getActivity()).updateBottomCalculations();
    }


    private void attachEditTextsOnTextChangedListeners() {

        //degjojme ndryshimin e quantita
        article_quantitaET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                //shtojme quantita tek document state
                readQuantita();
                //nese ka prezzo unitario bej update
                if (ValidatorHelper.isDocumentEligibleForCalculations(article, article_quantitaET, unita_di_misuraET, prezzo_unitarioET, sconto_percentualeET)) {
                    onUpdateDocumentStateListener.onDocumentStateUpdate(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        //degjojme ndryhsimin e prexzo unitario
        prezzo_unitarioET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //shtojme prezzo unitario tek document state
                readPrezzoUnitario();
                if (ValidatorHelper.isDocumentEligibleForCalculations(article, article_quantitaET, unita_di_misuraET, prezzo_unitarioET, sconto_percentualeET)) {
                    onUpdateDocumentStateListener.onDocumentStateUpdate(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });


        //degjojme ndryshimin e sconto percentuale

        sconto_percentualeET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                readScontoPercentuale();
                if (ValidatorHelper.isDocumentEligibleForCalculations(article, article_quantitaET, unita_di_misuraET, prezzo_unitarioET, sconto_percentualeET)) {
                    onUpdateDocumentStateListener.onDocumentStateUpdate(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        //degjojme ndryshimin e unita dimisura
        unita_di_misuraET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                readUnitaDiMisura();
                if (ValidatorHelper.isDocumentEligibleForCalculations(article, article_quantitaET, unita_di_misuraET, prezzo_unitarioET, sconto_percentualeET)) {
                    onUpdateDocumentStateListener.onDocumentStateUpdate(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }


}
