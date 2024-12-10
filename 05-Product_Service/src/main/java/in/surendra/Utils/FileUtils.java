package in.surendra.Utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;



public class FileUtils {
	
	 public static String uploadProductImg(String imgName, MultipartFile file) throws Exception {
		 
		 Path savingPath = Paths.get("/uplaoads/product-Images");
		 
		 if(!Files.exists(savingPath)) {
			 Files.createDirectories(savingPath);		
		 }
		 
		 Path getPath = savingPath.resolve(file.getOriginalFilename());
		 
		 Files.copy(file.getInputStream(), getPath, StandardCopyOption.REPLACE_EXISTING);
		 
		 return getPath.getFileName().toString();
	 }

}
