package it.visionmobya.fragments;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import it.visionmobya.R;
import it.visionmobya.models.customModels.DocumentState;

public class ArticleDetailsDialog extends Dialog {

    private Context context;
    private TextView article_name, quantity_value, unit_of_measure, prezzo_unit, sconto_value, sconto_percentuale, imponibile, prezzo_totale;
    private DocumentState documentState;
    private Button indietro;

    public ArticleDetailsDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.article_details_dialog);
        this.context = context;
        initUI();
    }

    private void initUI() {
        article_name = findViewById(R.id.article_name);
        quantity_value = findViewById(R.id.quantity_value);
        unit_of_measure = findViewById(R.id.unit_of_measure);
        prezzo_unit = findViewById(R.id.prezzo_unit);
        sconto_value = findViewById(R.id.sconto_value);
        sconto_percentuale = findViewById(R.id.sconto_percentuale);
        imponibile = findViewById(R.id.imponibile);
        prezzo_totale = findViewById(R.id.prezzo_totale);
        indietro = findViewById(R.id.indietro);

        indietro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hide();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.hide();
    }

    private void bindDataToUI() {
        setTitle(documentState.getArticle().getCodiceArticolo());
        this.article_name.setText(documentState.getArticle().getDescrizione());
        this.quantity_value.setText(documentState.getQuantita().toString());
        this.unit_of_measure.setText(documentState.getArticle().getCodiceUnitaDiMisura());
        this.prezzo_unit.setText(documentState.getPrezzoUnitario().toString());
        this.sconto_value.setText(documentState.getScontoValue().toString());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(").append(documentState.getScontoPercentuale()).append(" %").append(")");
        this.sconto_percentuale.setText(stringBuilder.toString());

        stringBuilder = new StringBuilder();
        stringBuilder.append(documentState.getImponibile().toString()).append(" Euro");
        this.imponibile.setText(stringBuilder.toString());


        stringBuilder = new StringBuilder();
        stringBuilder.append(documentState.getPrezzoTotaleArticle().toString()).append(" Euro");
        this.prezzo_totale.setText(stringBuilder.toString());
    }

    public DocumentState getDocumentState() {
        return documentState;
    }

    public void setDocumentState(DocumentState documentState) {
        this.documentState = documentState;
        bindDataToUI();
    }
}
