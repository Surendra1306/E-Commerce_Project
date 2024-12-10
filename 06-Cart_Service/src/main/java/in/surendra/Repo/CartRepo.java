package in.surendra.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.surendra.Dto.CartDto;
import in.surendra.Entity.Cart;

public interface CartRepo extends JpaRepository<Cart, Integer> {

	Optional<Cart> findByUserId(Integer uid);
	
}