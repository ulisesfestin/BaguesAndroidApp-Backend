package um.edu.bagues.Product;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    @NotNull
    @Size(max = 100)
    private String name;

    @Column(length = 500)
    @Size(max = 500)
    private String description;

    @Column(nullable = false)
    @NotNull
    @PositiveOrZero
    private Double price;

    @Column(unique = true, nullable = false)
    @NotNull
    private String code;

    @Column()
    private String imageUrl;

}
