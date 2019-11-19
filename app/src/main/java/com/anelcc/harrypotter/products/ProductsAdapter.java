package com.anelcc.harrypotter.products;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.anelcc.harrypotter.MainActivity;
import com.anelcc.harrypotter.R;
import com.anelcc.harrypotter.databinding.ProductItemBinding;
import com.anelcc.harrypotter.detail.ProductDetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import static com.anelcc.harrypotter.products.Product.PRODUCT_KEY;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> implements HandlerClickListener {
    public static final int CODE_REQUEST = 100;
    private List<ProductViewModel> products;
    private Context context;
    private int position;

    public ProductsAdapter(List<ProductViewModel> products) {
        this.products = products;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ProductItemBinding itemBinding;

        public ViewHolder(ProductItemBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.itemBinding = itemRowBinding;
        }

        public void bind(ProductViewModel product) {
            itemBinding.setProductViewModel(product);
            itemBinding.executePendingBindings();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ProductItemBinding itemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.product_item, parent, false);

        return new ViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(products.get(position));
        this.position = position;
        products.get(position).setPosition(position);
        context = holder.itemBinding.getRoot().getContext();
        holder.itemBinding.setHandlerClickListener(this);

        Picasso.with(holder.itemBinding.getRoot().getContext()).load(products.get(position).getImage())
                .placeholder(R.drawable.ic_loading)
                .error(R.drawable.ic_error)
                .into(holder.itemBinding.imageProduct);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void itemClicked(ProductViewModel product) {
        Intent intent = new Intent(context, ProductDetailsActivity.class);
        intent.putExtra(PRODUCT_KEY, product);
        ((MainActivity) context).startActivityForResult(intent, CODE_REQUEST);
    }

    @Override
    public void favoriteClicked(ProductViewModel product) {
        product.setFavorite(!product.isFavorite);
    }

    public void setProducts(List<ProductViewModel> products) {
        this.products = products;
    }
}
