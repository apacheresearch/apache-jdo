package apache.research.demojdo.repository;

import apache.research.demojdo.entity.Product;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import java.util.List;

public class QueryRepository {

    public static List<Product> getProductByPrecioMinimo(PersistenceManager pm , Double precioMinimo){
        Query<Product> query = pm.newQuery(Product.class);
        query.setFilter("this.precio >= :precioMinimo");
        return (List<Product>) query.execute(precioMinimo);
    }
}
