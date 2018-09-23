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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.visionmobya.CSVModule.VisionFileManager;
import it.visionmobya.R;
import it.visionmobya.listener.OnClickListenerItem;
import it.visionmobya.models.Client;
import it.visionmobya.recyclerView.adapters.ClientListAdapter;

public class ClientListActivity extends AppCompatActivity implements OnClickListenerItem, View.OnClickListener {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private ClientListAdapter clientListAdapter;
    private List<Client> clients;
    private VisionFileManager visionFileManager;
    private TextView stato, zona, giro, localita;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_list);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Ricerca Cliente");
        toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_white_24); // your drawable
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        recyclerView = findViewById(R.id.rv_clientList);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm1 = new LinearLayoutManager(this);
        llm1.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm1);
        clientListAdapter = new ClientListAdapter(this, this, new ArrayList<Client>());
        recyclerView.setAdapter(clientListAdapter);

        stato = findViewById(R.id.stato);
        stato.setOnClickListener(this);
        zona = findViewById(R.id.zona);
        zona.setOnClickListener(this);
        giro = findViewById(R.id.giro);
        giro.setOnClickListener(this);
        localita = findViewById(R.id.localita);
        localita.setOnClickListener(this);

        initData();

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
                //   initSwipeToRefresh();
                Toast.makeText(ClientListActivity.this, "On Close!", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        return true;
    }

    @Override
    public void onClickClient(View v, int position) {
        Intent intent = new Intent(this, OrdineClienteActivity.class);
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
