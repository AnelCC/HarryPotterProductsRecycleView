package com.anelcc.harrypotter.detail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.anelcc.harrypotter.R;
import com.anelcc.harrypotter.databinding.ActivityDetailBinding;
import com.anelcc.harrypotter.products.Product;
import com.anelcc.harrypotter.products.ProductViewModel;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class ProductDetailsActivity extends AppCompatActivity implements HandlerDetailClickListener {
    public static final String PRODUCT_DETAILS = "product_details";
    public static final int CODE_REQUEST = 100;
    private ActivityDetailBinding binding;
    private ProductViewModel product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        product = intent.getParcelableExtra(Product.PRODUCT_KEY);
        binding.setProductViewModel(product);
        binding.setHandlerDetailClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (product != null) {
            Picasso.with(this).load(product.getImage())
                    .placeholder(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
                    .into(binding.detailImageProduct);
        }
    }

    @Override
    public void favoriteDetailClicked(ProductViewModel product) {
        this.product = product;
        this.product.setFavorite(!product.isFavorite());
        Intent resultIntent = new Intent();
        resultIntent.putExtra(PRODUCT_DETAILS, product);
        setResult(Activity.RESULT_OK, resultIntent);
    }
}



