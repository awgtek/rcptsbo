package tests;

import java.util.List;

import com.awgtek.rctsbo.bll.Product;
import com.awgtek.rctsbo.dal.ProductManagerDao;

public class MockProductManagerDaoImpl implements ProductManagerDao {

    private List products;

    public void setProducts(List p) {
        products = p;
    }

    public List getProductList() {
        return products;
    }

    public void increasePrice(Product prod, int pct) {
        double newPrice = prod.getPrice().doubleValue() * (100 + pct)/100;
        prod.setPrice(new Double(newPrice));
    }

}