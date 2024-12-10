package in.surendra.Service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import in.surendra.Entity.User;
import in.surendra.Repo.UserRepo;

public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	 
		User user = userRepo.findByEmail(email);
		
		return new org.springframework.security.core.userdetails.User(user.getEmail()
																	  ,user.getPwd()
																	  ,Collections.EMPTY_LIST);
	}
}









