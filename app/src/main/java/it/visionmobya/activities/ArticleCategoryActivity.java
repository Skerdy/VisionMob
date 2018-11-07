package it.visionmobya.activities;

import android.content.Intent;
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
import it.visionmobya.listener.OnArticleCategoryClickListener;
import it.visionmobya.models.ArticleCategory;
import it.visionmobya.recyclerView.adapters.ArticleCategoryAdapter;
import it.visionmobya.utils.CodesUtil;

public class ArticleCategoryActivity extends AppCompatActivity implements OnArticleCategoryClickListener {
    Toolbar toolbar;
    RecyclerView recyclerView;
    ArticleCategoryAdapter articleCategoryAdapter;
    List<ArticleCategory> articleCategories;
    VisionFileManager visionFileManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.document_type);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Archivi");
        toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_white_24); // your drawable
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        recyclerView = findViewById(R.id.rv_docType);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm1 = new LinearLayoutManager(this);
        llm1.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm1);
        articleCategoryAdapter = new ArticleCategoryAdapter(new ArrayList<ArticleCategory>(), this);
        recyclerView.setAdapter(articleCategoryAdapter);
        try {
            initData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initData() throws IOException {
        visionFileManager = VisionFileManager.getInstance();
        articleCategories = visionFileManager.getArticleCategories();
        articleCategoryAdapter.setArticleCategories(articleCategories);
    }

    @Override
    public void onArticleCategoryClicked(ArticleCategory articleCategory, int position) {
        Intent intent = new Intent(this, ArticleActivity.class);
        intent.putExtra(CodesUtil.ARTICLE_CATEGORY, articleCategory.getId());
        startActivity(intent);
    }
}
