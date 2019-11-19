package com.anelcc.harrypotter.products;

import java.util.ArrayList;
import java.util.List;

public class ProductsViewModel {

    public List<ProductViewModel> products;

    public ProductsViewModel(List<Product> products) {
        this.products = createViewModelList(products);
    }

    private List<ProductViewModel> createViewModelList(List<Product> products) {
        List<ProductViewModel> productViewModelsProduct = new ArrayList<>();
        for (Product product : products) {
            productViewModelsProduct.add(new ProductViewModel(product));
        }
        return productViewModelsProduct;
    }

    public List<ProductViewModel> getViewModelProducts() {
        return products;
    }

    public void updateProduct(ProductViewModel viewModel) {
        products.get(viewModel.getPosition()).setFavorite(viewModel.isFavorite());
    }
}
