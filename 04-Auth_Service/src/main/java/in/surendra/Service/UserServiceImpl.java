package in.surendra.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import in.surendra.Entity.User;
import in.surendra.Repo.UserRepo;
import in.surendra.Utils.FileUtils;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	PasswordEncoder pwdEncoder;
	
	@Autowired
	private AuthenticationManager authManager;

	@Override
	public User createUser(User user, MultipartFile file) throws Exception {
	
		User email = userRepo.findByEmail(user.getEmail());
		
		if(email==null) {
			String uploadedFile = FileUtils.uploadFile(file.getName(), file);
			user.setPwd(pwdEncoder.encode(user.getPwd()));
			user.setUserImg(uploadedFile);
			return userRepo.save(user);
		}
		return email;
	}

	@Override
	public User loginUser(User user) {
		 
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken
															(user.getEmail()
															,user.getPwd());
		try {
			Authentication authenticate = authManager.authenticate(token);
			if(authenticate.isAuthenticated()) {
				return userRepo.findByEmail(user.getEmail());
			}
		} catch (Exception e) {
			 e.printStackTrace();
		}
		return null;
	}

	@Override
	public User getUserById(Integer uId) {
		
	return userRepo.findById(uId).orElseThrow();
	 
	}

	@Override
	public List<User> getAllUsers() {
		List<User> all = userRepo.findAll();
		return all;
	}

	@Override
	public User removeUser(Integer uId) {
		boolean existsById = userRepo.existsById(uId);
		if(existsById) {
			userRepo.deleteById(uId);
		}
		return null;
	}

}
