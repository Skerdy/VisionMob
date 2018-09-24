package it.visionmobya.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.visionmobya.CSVModule.VisionFileManager;
import it.visionmobya.R;
import it.visionmobya.listener.OnClientClickListener;
import it.visionmobya.models.Client;
import it.visionmobya.models.DocumentCategory;
import it.visionmobya.recyclerView.adapters.ClientListAdapter;
import it.visionmobya.utils.CodesUtil;
import it.visionmobya.utils.TextViewHelper;

public class ClientListActivity extends AppCompatActivity implements OnClientClickListener, View.OnClickListener {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private ClientListAdapter clientListAdapter;
    private List<Client> clients;
    private VisionFileManager visionFileManager;
    private TextView stato, zona, giro, localita, top_title;
    private DocumentCategory selectedDocumentCategory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_list);
        initUI();
        initData();

    }

    private void initUI(){
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.rv_clientList);
        zona = findViewById(R.id.zona);
        stato = findViewById(R.id.stato);
        giro = findViewById(R.id.giro);
        localita = findViewById(R.id.localita);
        top_title = findViewById(R.id.title_client_list);

        stato.setOnClickListener(this);
        zona.setOnClickListener(this);
        giro.setOnClickListener(this);
        localita.setOnClickListener(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Ricerca Cliente");
        toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_white_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm1 = new LinearLayoutManager(this);
        llm1.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm1);

        clientListAdapter = new ClientListAdapter(this, this, new ArrayList<Client>());
        recyclerView.setAdapter(clientListAdapter);
    }

    private void initData()  {
        visionFileManager = VisionFileManager.getInstance();
        clients = visionFileManager.getClients();
        clientListAdapter.setClients(clients);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);

        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("Filter", "TextSubmit : " + query );
              //  clients = clientListAdapter.clients;
             //   clientListAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("Filter", "TextChange : " + newText );
                    clientListAdapter.getFilter().filter(newText);
                return false;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                Toast.makeText(ClientListActivity.this, "On Close!", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        return true;
    }


    private void getDocumentFromIntent(){
        if(getIntent().getBundleExtra(CodesUtil.DOCUMENT_TYPE_TO_CLIENT_LIST)!=null){
            if(getIntent().getBundleExtra(CodesUtil.DOCUMENT_TYPE_TO_CLIENT_LIST).getSerializable(CodesUtil.DOCUMENT_TYPE_ARGUMENT)!=null){
                DocumentCategory documentCategory = (DocumentCategory) getIntent().getBundleExtra(CodesUtil.DOCUMENT_TYPE_TO_CLIENT_LIST).getSerializable(CodesUtil.DOCUMENT_TYPE_ARGUMENT);
                this.selectedDocumentCategory = documentCategory;
            }
        }
    }

    private void bindDocumentDataToActivity(DocumentCategory documentCategory){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String todayDate = dateFormat.format(new Date());
        String documentType = documentCategory.getDescrizioneDocumento();
        String documentCounter = ""+ (Integer.parseInt(documentCategory.getConttatoreDocumento())+1);
        top_title.setText(TextViewHelper.generateClientListTitle(documentType, documentCounter, todayDate ));
    }


    @Override
    public void onClickClient(Client client, int position) {
        Intent intent = new Intent(this, OrdineClienteActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(CodesUtil.CLIENT_ARGUMENT, client);
        intent.putExtra(CodesUtil.CLIENT_LIST_TO_CLIENT_ORDER, bundle);
        startActivity(intent);
        finish();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.stato:
                Toast.makeText(this, "Under developing mode", Toast.LENGTH_SHORT).show();
                break;
            case R.id.zona:
                Toast.makeText(this, "Under developing mode", Toast.LENGTH_SHORT).show();
                break;
            case R.id.giro:
                Toast.makeText(this, "Under developing mode", Toast.LENGTH_SHORT).show();
                break;
            case R.id.localita:
                Toast.makeText(this, "Under developing mode", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
