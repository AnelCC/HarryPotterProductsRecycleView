package com.anelcc.harrypotter.products;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductViewModelTest {
    public static final String TITLE = "Harry Potter Staff Scholastic";
    public static final String AUTHOR = "J.K. Rowling";
    public static final String IMAGE_URL = "http://i.ebayimg.com/00/$T2eC16F,!)kE9s4Z-Ue7BRb4ZE0oog~~_1.JPG?set_id=89040003C1";

    @Mock
    Product product;

    private ProductViewModel model;

    @Before
    public void setUp() throws Exception {
        when(product.getTitle()).thenReturn(TITLE);
        when(product.getAuthor()).thenReturn(AUTHOR);
        when(product.getImageURL()).thenReturn(IMAGE_URL);
        model = new ProductViewModel(product);
    }

    @After
    public void tearDown() {
        model = null;
    }

    @Test
    public void shouldReturnTheCorrectTitle() {
        assertEquals(TITLE, model.getTitle());
    }

    @Test
    public void shouldReturnTheCorrectAuthor() {
        assertEquals(AUTHOR, model.getAuthor());
    }

    @Test
    public void shouldReturnTheCorrectImage() {
        assertEquals(IMAGE_URL, model.getImage());
    }
}