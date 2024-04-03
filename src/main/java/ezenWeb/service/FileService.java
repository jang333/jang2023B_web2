package ezenWeb.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class FileService {

    private String uploadPath = "C:\\Users\\504\\Desktop\\jang2023B_web2\\build\\resources\\main\\static\\uploadimg\\";

    public String fileUpload(MultipartFile multipartFile){

        //파일이름을 식별가능한 uuid와 조합
        String uuid = UUID.randomUUID().toString(); //uuid:고유한 id 난수성으로 생성
        System.out.println("uuid = " + uuid);
        String fileName = uuid+"_"+multipartFile.getOriginalFilename().replaceAll("_","-");

        //어디에
        File file = new File(uploadPath+fileName);
        System.out.println("file = " + file);
        System.out.println("file.exists() = " + file.exists());
        //무엇을
        try{
            multipartFile.transferTo(file);
        }catch (Exception e){
            System.out.println("e = " + e);
            return null;
        }
        return fileName;
    }

}
