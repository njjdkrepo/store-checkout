package service;

import model.Product;

import java.math.BigDecimal;
import java.util.List;

/**
 * Service to handle shopping cart calculations.
 */
public class StoreCheckout {

    /**
     * Calculates the total cost of a list of products by summing their prices.
     *
     * @param items the list of products to be totaled. Can be null or empty.
     * @return the total cost as a {@link BigDecimal}, or {@link BigDecimal#ZERO} if the list is null or empty.
     *
     */
    public BigDecimal calculateTotal(List<Product> items){
        if (items == null || items.isEmpty()) {
            return BigDecimal.ZERO;
        }

        return items.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
