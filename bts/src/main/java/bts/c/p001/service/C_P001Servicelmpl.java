package bts.c.p001.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import bts.b.p001.VO.B_P001VO;
import bts.c.p001.DAO.C_P001DAO;

@Service("c_p001Service")
public class C_P001Servicelmpl implements C_P001Service{
	@Autowired
	C_P001DAO c_p001DAO;
	
	@Override
	public void updateMember(B_P001VO d001vo) throws Exception {
		c_p001DAO.updateMember(d001vo);
	}

	//이미지 업로드
//	@Service
//	public class FileUploadService {
//		 // 윈도우라면 workspace의 드라이브를 파악하여 JVM이 알아서 처리해준다.
//		 // 따라서 workspace가 C드라이브에 있다면 C드라이브에 upload 폴더를 생성해 놓아야 한다.
//		private static final String SAVE_PATH="/upload";  //파일이 저장될 위치
//		private static final String PREFIX_URL="/upload/"; //저장된 파일을 jsp에서 불러오기 위한 경로
//		
//		public String restore(MultipartFile multipartFile) {
//			String url = null;
//			
//			try {
//				//파일 정보
//				String originFilename=multipartFile.getOriginalFilename();
//				String extName
//				=originFilename.substring(originFilename.lastlndexOf("."),originFilename.length());
//				Longsize = multipartFile.getSize();
//				
//				//서버에서 저장 할 파일 이름
//				String saveFileName=genSaveFileName(extName);
//				
//				System.out.println("originFilename:" + originFilename);
//				System.out.println("extensionName:" + extName);
//				System.out.println("size:" + size);
//				System.out.println("saveFileName:" + saveFileName);
//				
//				writeFile(multipartFile, saveFileName);
//				url=PREFIX_URL + saveFileName;  //View에서 이미지파일을 바로보기 위한 경로
//				
//			}
//			catch (IOException e) {
//			     // 원래라면 RuntimeException 을 상속받은 예외가 처리되어야 하지만
//			     // 편의상 RuntimeException을 던진다.
//			     // throw new FileUploadException();
//				throw new RuntimeException(e);
//			}
//			return url;
//		}
//		
//		
//		
//	}
	
	
}
