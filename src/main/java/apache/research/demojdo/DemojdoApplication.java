package apache.research.demojdo;

import apache.research.demojdo.entity.Product;
import apache.research.demojdo.repository.QueryRepository;
import apache.research.demojdo.util.JDOUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;
import java.util.List;


@SpringBootApplication
public class DemojdoApplication {

	static  Logger logger = LoggerFactory.getLogger(DemojdoApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(DemojdoApplication.class, args);

		//testSimpleJDO();

		//testTypeQuery();

		testJDOExcel();
	}

	private static void testJDOExcel() {
		PersistenceManager pm = JDOUtil.getPMFExcel().getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try{
			tx.begin();
			Product product = new Product("Producto para Excel", 300.0);
			pm.makePersistent(product);
			tx.commit();
		}catch (Exception e){
			if (tx.isActive())
				tx.rollback();
			logger.error(e.getMessage());
		}finally {
			pm.close();
		}
	}

	private static void testTypeQuery() {
		PersistenceManager pm = JDOUtil.getPMFMysql().getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try{
			List<Product> products = QueryRepository.getProductByPrecioMinimo(pm,50.0);
			for(Product p:products)
				logger.info("Product name:"+ p.getNombre()+" price:"+p.getPrecio());
		}catch (Exception e){
			if (tx.isActive())
				tx.rollback();
			logger.error(e.getMessage());
		}finally {
			pm.close();
		}
	}

	private static void testSimpleJDO() {
		PersistenceManager pm = JDOUtil.getPMFMysql().getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try{
			tx.begin();
			Product product = new Product();
			product.setNombre("Producto A");
			product.setPrecio(4.3);
			pm.makePersistent(product);
			tx.commit();
		}catch (Exception e){
			if (tx.isActive())
				tx.rollback();
			logger.error(e.getMessage());
		}finally {
			pm.close();
		}
	}

}
