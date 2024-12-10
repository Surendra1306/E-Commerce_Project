package in.surendra.Service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import in.surendra.Entity.User;

public interface UserService {
	
	public User createUser(User user, MultipartFile file) throws Exception;
	
	public User loginUser(User user);
	
	public User getUserById(Integer uId);
	
	public List<User> getAllUsers();
	
	public User removeUser(Integer uId);

}
