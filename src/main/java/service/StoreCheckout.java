package service;

import model.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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

        Map<Product, Long> productCounts = items.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return productCounts.entrySet().stream()
                .map(entry -> calculateProductTotal(entry.getKey(), entry.getValue().intValue()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calculateProductTotal(Product product, int quantity) {
        // Use if/else if to safely check object equality
        if (product.equals(Product.APPLE)) {
            return applyBuyOneGetOneFree(quantity, product.getPrice());
        } else if (product.equals(Product.ORANGE)) {
            return applyThreeForTwo(quantity, product.getPrice());
        } else {
            return product.getPrice().multiply(new BigDecimal(quantity));
        }
    }

    private BigDecimal applyBuyOneGetOneFree(int quantity, BigDecimal unitPrice) {
        int itemsToPay = (quantity + 1) / 2;  // Round up
        return unitPrice.multiply(BigDecimal.valueOf(itemsToPay));
    }

    private BigDecimal applyThreeForTwo(int quantity, BigDecimal unitPrice) {
        int groupsOfThree = quantity / 3;
        int remainingItems = quantity % 3;
        int itemsToPay = (groupsOfThree * 2) + remainingItems;
        return unitPrice.multiply(BigDecimal.valueOf(itemsToPay));
    }
}
