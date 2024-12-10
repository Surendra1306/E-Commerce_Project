package in.surendra.Repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.surendra.Entity.Order;

public interface OrderRepo extends JpaRepository<Order, Integer>{
	
	public List<Order> findByUserId(Integer userId);
	
	public List<Order> findByOrderStatusAndOrderDate(String orderStatus, LocalDate orderDate);
	
}
