package it.visionmobya.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import it.visionmobya.R;

public class CloserDocumentFragment extends Fragment {

    private EditText accontoEuro, casualeDelTransporto, aspettoDeibeni, numroColli, transportoAcuraDi, dataTransporto, oraTransposto, note;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.closer_doc_fragment, container, false);
        //bejme inicializimin e gjithe komponenteve te deklaruara ne drawing time dhe i perdorim pasi behet draw ne onviewcreated
        initUI(view);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //nese document state nuk eshte null atehere fillo mbush tere komponentet me te dhenat e ruajtura perkatesisht

    }

    private void initUI(View view) {

        this.accontoEuro          = view.findViewById(R.id.accontoEuro);
        this.casualeDelTransporto = view.findViewById(R.id.casualeDelTransporto);
        this.aspettoDeibeni       = view.findViewById(R.id.aspettoDeibeni);
        this.numroColli           = view.findViewById(R.id.numroColli);
        this.transportoAcuraDi    = view.findViewById(R.id.transportoAcuraDi);
        this.dataTransporto       = view.findViewById(R.id.dataTransporto);
        this.oraTransposto        = view.findViewById(R.id.oraTransposto);
        this.note                 = view.findViewById(R.id.note);
    }


}
