package service;

import model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link StoreCheckout} class.
 */
class StoreCheckoutTest {

    /** The instance of the class under test. */
    private StoreCheckout storeCheckout;

    @BeforeEach
    void setUp() {
        storeCheckout = new StoreCheckout();
    }

    @Test
    void testShouldReturnZeroForEmptyCart() {
        assertEquals(BigDecimal.ZERO, storeCheckout.calculateTotal(Collections.emptyList()));
    }

    @Test
    void testShouldCalculateSingleApple() {
        List<Product> cart = List.of(Product.APPLE);
        assertEquals(new BigDecimal("0.60"), storeCheckout.calculateTotal(cart));
    }

    @Test
    void shouldCalculateMultipleItems() {
        // [Apple, Apple, Orange, Apple] => £2.05
        List<Product> cart = Arrays.asList(
                Product.APPLE, Product.APPLE, Product.ORANGE, Product.APPLE
        );
        assertEquals(new BigDecimal("2.05"), storeCheckout.calculateTotal(cart));
    }

}