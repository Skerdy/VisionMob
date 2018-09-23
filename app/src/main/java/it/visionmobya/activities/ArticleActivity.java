package it.visionmobya.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.visionmobya.CSVModule.VisionFileManager;
import it.visionmobya.R;
import it.visionmobya.models.Article;
import it.visionmobya.recyclerView.adapters.ArticleAdapter;
import it.visionmobya.utils.CodesUtil;

public class ArticleActivity extends AppCompatActivity {


    Toolbar toolbar;
    RecyclerView recyclerView;
    ArticleAdapter articleAdapter;
    List<Article> articles;
    VisionFileManager visionFileManager;
    String articleCategoryId;

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.document_type);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Articolo");
        toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_white_24); // your drawable
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        articleCategoryId = getIntent().getStringExtra(CodesUtil.ARTICLE_CATEGORY);
        recyclerView = findViewById(R.id.rv_docType);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm1 = new LinearLayoutManager(this);
        llm1.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm1);
        articleAdapter = new ArticleAdapter(new ArrayList<Article>(), null);
        recyclerView.setAdapter(articleAdapter);

        new GetArcticlesTask(this).execute();
    }

    private void initData() throws IOException {
        visionFileManager = VisionFileManager.getInstance();
        articles = visionFileManager.getArticlesWithCategoryId(articleCategoryId);
    }


    private class GetArcticlesTask extends AsyncTask<Void, Void, Void>{

        private Context context ;
        private ProgressDialog progressDialog;

        public GetArcticlesTask(Context context){
            this.context = context;
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("Loading Articles");
            progressDialog.setIndeterminate(false);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                initData();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            articleAdapter.setArticle(articles);
            progressDialog.dismiss();
        }
    }
}
