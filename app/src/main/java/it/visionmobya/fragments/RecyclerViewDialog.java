package it.visionmobya.fragments;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import it.visionmobya.R;
import it.visionmobya.recyclerView.adapters.ArticleAdapter;

public class RecyclerViewDialog extends Dialog {

    private RecyclerView recyclerView;
    private SearchView searchView;
    private ImageView backArrow;
    private RelativeLayout relativeLayout;
    private Context context;
    private boolean searchExpanded = false;

    public RecyclerViewDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.recyclerview_layout);
        this.context = context;
        initDialogUI();
    }

    private void initDialogUI() {
        recyclerView = findViewById(R.id.recycler_view);
        backArrow = findViewById(R.id.back_arrow);
        searchView = findViewById(R.id.search_view);
        relativeLayout = findViewById(R.id.top_panel);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backArrow.setVisibility(View.GONE);
                searchExpanded = true;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ((ArticleAdapter) recyclerView.getAdapter()).getFilter().filter(newText);
                return false;
            }
        });

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            /*    if(!searchExpanded) {
                    backArrow.setVisibility(View.GONE);
                    searchView.onActionViewExpanded();
                }*/
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                searchExpanded = false;
                backArrow.setVisibility(View.VISIBLE);
                searchView.onActionViewCollapsed();
                return false;
            }
        });

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hide();
            }
        });
    }

    public void setTitle(String title) {
        setTitle(title);
    }

    public RecyclerView.Adapter getAdapter() {
        return recyclerView.getAdapter();
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }


}
