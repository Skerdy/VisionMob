package it.visionmobya.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.visionmobya.CSVModule.VisionFileManager;
import it.visionmobya.R;
import it.visionmobya.listener.OnClickListenerItem;
import it.visionmobya.models.DocumentCategory;
import it.visionmobya.recyclerView.adapters.DocumentTypeAdapter;


public class DocumentTypeActivity extends AppCompatActivity implements OnClickListenerItem {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private DocumentTypeAdapter documentTypeAdapter;
    private List<DocumentCategory> documentCategories;
    private VisionFileManager visionFileManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.document_type);
        initUI();
        documentTypeAdapter = new DocumentTypeAdapter(new ArrayList<DocumentCategory>(), this);
        recyclerView.setAdapter(documentTypeAdapter);
        initData();
    }

    private void initUI(){
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.rv_docType);


        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm1 = new LinearLayoutManager(this);
        llm1.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm1);

        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_white_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Tipo documento");
    }

    private void initData() {
        visionFileManager = VisionFileManager.getInstance();
        documentCategories = visionFileManager.getDocumentCategories();
        documentTypeAdapter.setDocumentCategories(documentCategories);
    }

    @Override
    public void onClickClient(View v, int position) {
        Intent intent = new Intent(this, ClientListActivity.class);
        startActivity(intent);
    }
}
