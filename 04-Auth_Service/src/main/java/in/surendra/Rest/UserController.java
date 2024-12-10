package in.surendra.Rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.surendra.Constants.AppConstants;
import in.surendra.Entity.User;
import in.surendra.PropMsg.PropertiesMessages;
import in.surendra.Response.ApiResponse;
import in.surendra.Service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	PropertiesMessages messages;
	 
	@PostMapping("/register")	
	public ResponseEntity<ApiResponse<User>> registerUser(@RequestParam("user") String userJson
										,@RequestParam("file") MultipartFile file) throws Exception{
		
		ObjectMapper mapper = new ObjectMapper();
		User userObj = mapper.readValue(userJson, User.class);
		
		User user = userService.createUser(userObj, file);
		
		ApiResponse<User> response = new ApiResponse<>();
		Map<String, String> propMessages = messages.getMessages();
		
		if(user != null) {
			response.setStatusCode(200);
			//response.setMessage("user registered successfully"); --configure these hard coded messages in app.prop
			//response.setMessage(propMessages.get("regSucc")); --set these hardcoded strings in appconstants
			response.setMessage(propMessages.get(AppConstants.reg_Succ));
			response.setData(user);
		}
		else {
			response.setStatusCode(500);
			response.setMessage(AppConstants.reg_Err);
		}
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/login")
	public ResponseEntity<ApiResponse<User>> loginUser(User user){
		
		User loginUser = userService.loginUser(user);
		
		ApiResponse<User> response = new ApiResponse<>();
		Map<String, String> propMessages = messages.getMessages();
		
		if(loginUser != null) {
			response.setStatusCode(201);
			response.setMessage(propMessages.get(AppConstants.login_Succ));
			response.setData(loginUser);
		}
		else {
			response.setStatusCode(501);
			response.setMessage(propMessages.get(AppConstants.login_Err));
		}
		
		return new ResponseEntity<>(response,HttpStatus.OK);
		
	}
	
		
}
