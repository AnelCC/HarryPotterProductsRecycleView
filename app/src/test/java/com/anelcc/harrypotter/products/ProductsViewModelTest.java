package com.anelcc.harrypotter.products;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductsViewModelTest {
    public static final String TITLE = "Harry Potter Staff Scholastic";
    public static final String AUTHOR = "J.K. Rowling";
    public static final String IMAGE_URL = "http://i.ebayimg.com/00/$T2eC16F,!)kE9s4Z-Ue7BRb4ZE0oog~~_1.JPG?set_id=89040003C1";

    @Mock
    Product product;
    @Mock
    ProductViewModel productViewModel;

    private ProductsViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new ProductsViewModel(Collections.<Product>emptyList());
    }

    @After
    public void tearDown() {
        product = null;
        productViewModel = null;
    }


    @Test
    public void shouldReturnCorrectValuesFromTheListWhenTheListIsEmpty() {
        assertTrue(viewModel.getViewModelProducts().isEmpty());
    }

    @Test
    public void shouldReturnCorrectValuesFromTheList() {
        when(product.getTitle()).thenReturn(TITLE);
        when(product.getAuthor()).thenReturn(AUTHOR);
        when(product.getImageURL()).thenReturn(IMAGE_URL);
        viewModel = new ProductsViewModel(Collections.singletonList(product));

        assertEquals(Collections.singletonList(product).get(0).getTitle(), viewModel.getViewModelProducts().get(0).getTitle());
        assertEquals(Collections.singletonList(product).get(0).getAuthor(), viewModel.getViewModelProducts().get(0).getAuthor());
        assertEquals(Collections.singletonList(product).get(0).getImageURL(), viewModel.getViewModelProducts().get(0).getImage());
    }


    @Test
    public void shouldReturnCorrectValuesAfterUpdateTheFavoriteAsTrue() {
        when(product.getTitle()).thenReturn(TITLE);
        when(product.getAuthor()).thenReturn(AUTHOR);
        when(product.getImageURL()).thenReturn(IMAGE_URL);
        when(productViewModel.isFavorite()).thenReturn(false);
        viewModel = new ProductsViewModel(Collections.singletonList(product));

        assertFalse(viewModel.getViewModelProducts().get(0).isFavorite);

        when(productViewModel.isFavorite()).thenReturn(true);
        viewModel.updateProduct(productViewModel);

        assertTrue(viewModel.getViewModelProducts().get(0).isFavorite);
    }

    @Test
    public void shouldReturnCorrectValuesAfterUpdateTheFavoriteAsFalse() {
        when(product.getTitle()).thenReturn(TITLE);
        when(product.getAuthor()).thenReturn(AUTHOR);
        when(product.getImageURL()).thenReturn(IMAGE_URL);
        when(productViewModel.isFavorite()).thenReturn(true);
        viewModel = new ProductsViewModel(Collections.singletonList(product));

        assertFalse(viewModel.getViewModelProducts().get(0).isFavorite);

        when(productViewModel.isFavorite()).thenReturn(true);
        viewModel.updateProduct(productViewModel);

        assertTrue(viewModel.getViewModelProducts().get(0).isFavorite);


        when(productViewModel.isFavorite()).thenReturn(false);
        viewModel.updateProduct(productViewModel);

        assertFalse(viewModel.getViewModelProducts().get(0).isFavorite);
    }
}