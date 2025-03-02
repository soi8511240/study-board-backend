package kr.co.test.week2board.board.util;

import kr.co.test.week2board.board.model.AttachDTO;
import org.springframework.stereotype.Service;

@Service
public class BinaryAttach {

    public String saveFile(AttachDTO attachDTO){
//        byte fileByte[] = Base64.getDecoder().decode(fileBinary.getbytes("UTF-8"));
//
//        Map<String,Object> map = commonService.selectFileInfo(commandMap.getMap());
//
//        String fileName = attachDTO.getOriginalFileName();
//        int fileSize = 디비에서 가져온 파일 사이즈;
//        String fileBinary= 디비에서 가져온 파일 바이너리;
//
//        response.setContentType("application/octet-stream");
//        response.setContentLength(fileSize);
//        response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(fileName,"UTF-8")+"\";");
//        response.setHeader("Content-Transfer-Encoding", "binary");
//        response.getOutputStream().write(fileByte);
//
//        response.getOutputStream().flush();
//        response.getOutputStream().close();

        return "String";
    }
}
