package in.surendra.Service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface RoleService {
	
	public RoleService createRole(RoleService role, MultipartFile file);
	
	public RoleService loginRole(RoleService role);
	
	public RoleService getRoleById(Integer rid);
	
	public List<RoleService> getAllRole();
	
	public RoleService removeRole(Integer rid);

}
