package um.edu.bagues.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Optional<Product> findByCode(String code);

    List<Product> findByName(String name);

    List<Product> findByPriceGreaterThanEqual(Double price);

}
