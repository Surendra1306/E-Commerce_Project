package in.surendra.Utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

public class FileUtils {

	
	
	public static String uploadFile(String fileName,MultipartFile file) throws Exception{
		
		Path uploadPath = Paths.get("uploads/userImages");
		
		if(!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		
		//String fileCode = RandomStringUtils.randomAlphabetic(8);
		//Path path = uploadPath.resolve(fileCode+"_"+fileName);
		
		Path filePath = uploadPath.resolve(file.getOriginalFilename());
		
		Files.copy(file.getInputStream(), filePath,StandardCopyOption.REPLACE_EXISTING);
		
		return filePath.getFileName().toString();
		
		//return fileCode;
	}
	
}
