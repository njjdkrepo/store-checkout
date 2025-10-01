package model;

import lombok.Value;

import java.math.BigDecimal;
import java.util.List;

/**
 * Represents an immutable product defined by a name and price.
 * This is a value object generated using {@link lombok.Value}.
 * @Value already marks non-static, package-local fields private.
 */
@Value
public class Product {

    String name;
    BigDecimal price;

    // Predefined list of products
    public static final Product APPLE = new Product("Apple", new BigDecimal("0.60"));
    public static final Product ORANGE = new Product("Orange", new BigDecimal("0.25"));

    public static final List<Product> ALL_PRODUCTS = List.of(APPLE, ORANGE);

}
