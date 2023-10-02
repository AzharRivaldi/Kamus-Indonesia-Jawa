package com.azhar.kamusindojawa.activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.azhar.kamusindojawa.R;
import com.azhar.kamusindojawa.adapter.JawaIndonesiaAdapter;
import com.azhar.kamusindojawa.database.DatabaseAccess;
import com.azhar.kamusindojawa.model.ModelKamus;

import java.util.ArrayList;

public class JawaIndonesiaActivity extends AppCompatActivity {

    ArrayList<ModelKamus> modelKamusArrayList = new ArrayList<>();
    JawaIndonesiaAdapter jawaIndonesiaAdapter;
    DatabaseAccess databaseAccess;
    RecyclerView rvListData;
    Toolbar toolbar;
    EditText etSearchData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jawa);

        toolbar = findViewById(R.id.toolbar);
        etSearchData = findViewById(R.id.etSearchData);
        rvListData = findViewById(R.id.rvListData);

        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        setDatabase();
        setRecyclerView();

        etSearchData.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String strKeyword = etSearchData.getText().toString();
                strKeyword = strKeyword.replaceAll("'","");
                getDataSearch(strKeyword);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void setDatabase() {
        databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();

        modelKamusArrayList = databaseAccess.getKamusJawa();
        databaseAccess.close();
    }

    private void setRecyclerView() {
        rvListData.setLayoutManager(new LinearLayoutManager(this));
        jawaIndonesiaAdapter = new JawaIndonesiaAdapter(this, modelKamusArrayList);
        rvListData.setAdapter(jawaIndonesiaAdapter);
        rvListData.setHasFixedSize(true);
    }

    public void getDataSearch(String keyword){
        databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        modelKamusArrayList = databaseAccess.getSearchJawa(keyword);
        databaseAccess.close();

        setRecyclerView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
