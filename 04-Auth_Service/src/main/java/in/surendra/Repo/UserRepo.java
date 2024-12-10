package in.surendra.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.surendra.Entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{
    
	public User findByEmail(String email);
}
