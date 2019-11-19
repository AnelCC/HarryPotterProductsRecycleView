package com.anelcc.harrypotter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.anelcc.harrypotter.databinding.ActivityMainBinding;
import com.anelcc.harrypotter.products.Product;
import com.anelcc.harrypotter.products.ProductViewModel;
import com.anelcc.harrypotter.products.ProductsAdapter;
import com.anelcc.harrypotter.products.ProductsViewModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private static final Type REVIEW_TYPE = new TypeToken<List<Product>>() {}.getType();
    private static final String PRODUCT_DETAILS = "product_details";
    private static final String UTF = "UTF-8";
    private static final int CODE_REQUEST = 100;

    private ActivityMainBinding binding;
    private ProductsViewModel productsViewModel;
    private ProductsAdapter productsAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        recyclerView = binding.productsList;
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        productsViewModel = new ProductsViewModel(loadModel());
        productsAdapter = new ProductsAdapter(productsViewModel.getViewModelProducts());
        recyclerView.setAdapter(productsAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case (CODE_REQUEST): {
                if (resultCode == Activity.RESULT_OK) {
                    ProductViewModel productViewModel = data.getParcelableExtra(PRODUCT_DETAILS);
                    productsViewModel.updateProduct(productViewModel);
                    productsAdapter.setProducts(productsViewModel.getViewModelProducts());
                    productsAdapter.notifyDataSetChanged();
                }
                break;
            }
        }
    }

    private List<Product> loadModel() {
        Gson gson = new Gson();
        List<Product> data = gson.fromJson(loadJSONFromAsset(), REVIEW_TYPE);
        return data;
    }

    public String loadJSONFromAsset() {
        String json;
        try {
            InputStream is = getAssets().open("products.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, UTF);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
