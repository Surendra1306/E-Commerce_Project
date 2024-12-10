package in.surendra.Entity;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "catgory_table")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoryId;
	private String categoryName;
	
	
	@OneToMany(mappedBy = "category"
			  ,cascade = CascadeType.ALL
			  ,fetch = FetchType.EAGER)
	private List<Product> products;
	
	@CreationTimestamp
	@Column(name = "create_tm", updatable = false)
	private LocalDate createdTime;
	
	@UpdateTimestamp
	@Column(name = "update_tm", insertable = false)
	private LocalDate updatedTime;
	
	
	
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public LocalDate getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(LocalDate createdTime) {
		this.createdTime = createdTime;
	}
	public LocalDate getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(LocalDate updatedTime) {
		this.updatedTime = updatedTime;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	

	
	
}
