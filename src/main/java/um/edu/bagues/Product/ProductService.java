package um.edu.bagues.Product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> getProductById(Integer id) {
        return productRepository.findById(id);
    }

    public Optional<Product> getProductByCode(String code) {
        return productRepository.findByCode(code);
    }

    public List<Product> getProductsByName(String name) {
        return productRepository.findByName(name);
    }

    public List<Product> getProductsByPriceGreaterThanEqual(Double price) {
        return productRepository.findByPriceGreaterThanEqual(price);
    }

    public void deleteProductById(Integer id) {
        productRepository.deleteById(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product updateProduct(Integer id, Product newProductData) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setName(newProductData.getName());
                    existingProduct.setDescription(newProductData.getDescription());
                    existingProduct.setPrice(newProductData.getPrice());
                    existingProduct.setCode(newProductData.getCode());
                    existingProduct.setImageUrl(newProductData.getImageUrl());
                    return productRepository.save(existingProduct);
                })
                .orElseThrow(() -> new RuntimeException("Product not found with id " + id));
    }
}

