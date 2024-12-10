package in.surendra.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.surendra.Entity.Product;


public interface ProductRepo extends JpaRepository<Product, Integer> {

}
