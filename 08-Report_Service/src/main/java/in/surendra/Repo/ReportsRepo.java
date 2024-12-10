package in.surendra.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import in.surendra.Entity.OrderReports;
import in.surendra.Entity.OrderStatus;

import java.time.LocalDate;
import java.util.List;


public interface ReportsRepo extends JpaRepository<OrderReports, Integer> {
	
	
	public List<OrderReports> findByOrderStatus(OrderStatus orderStatus);
	
	public List<OrderReports> findByOrderDate(LocalDate orderDate);

}
