package in.surendra.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.surendra.Entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
