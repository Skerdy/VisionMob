package it.visionmobya.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import it.visionmobya.R;
import it.visionmobya.models.customModels.DocumentState;
import it.visionmobya.recyclerView.adapters.ArticleRowsAdapter;

public class ArticleRowsFragment extends Fragment {

    public static final String FRAGMENT_ARGUMENTS = "articleAllRowsFragmentArguments";
    RecyclerView recyclerView;
    ArticleRowsAdapter articleRowsAdapter;
    private ArrayList<DocumentState> documentStates;
    private ArrayList<DocumentState> eliglibleDocumentStates;

    public static ArticleRowsFragment newInstance(ArrayList<DocumentState> documentState) {
        ArticleRowsFragment articleRowFragment = new ArticleRowsFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(FRAGMENT_ARGUMENTS, documentState);
        articleRowFragment.setArguments(args);
        return articleRowFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.eliglibleDocumentStates = new ArrayList<>();
        if(getArguments().getSerializable(FRAGMENT_ARGUMENTS)!=null){
            //nese ka nje dokument state per kete fragment atehere e marrim dhe e atachojme ne referencen publike te document state te fragmentit specifik
            documentStates =  getArguments().getParcelableArrayList(FRAGMENT_ARGUMENTS);
            for(DocumentState documentState: documentStates){
                if(documentState.isBindDirectly()){
                    eliglibleDocumentStates.add(documentState);
                }
            }
        }
        else {
            documentStates = new ArrayList<>();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.article_rows_fragment, container, false);
        //bejme inicializimin e gjithe komponenteve te deklaruara ne drawing time dhe i perdorim pasi behet draw ne onviewcreated
        initUI(view);
        return view;
    }

    private void initUI(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm1 = new LinearLayoutManager(getContext());
        llm1.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm1);
        articleRowsAdapter = new ArticleRowsAdapter(eliglibleDocumentStates,getContext());
        recyclerView.setAdapter(articleRowsAdapter);
    }
}
