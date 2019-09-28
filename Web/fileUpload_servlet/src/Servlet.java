
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/upload/*")
public class Servlet extends HttpServlet {

	private static String IMAGE_REF0 = "C:\\Users\\MongEe\\Desktop\\working";
	SampleService service;
	SampleVO vo;

	@Override
	public void init(ServletConfig config) throws ServletException {
		service = new SampleService();
		vo = new SampleVO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandler(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandler(request, response);
	}

	protected void doHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nextPage = "";
		int extra_int = 1;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action = request.getPathInfo();
		try {
			List<SampleVO> list = new ArrayList<>();
			if(action == null || action.equals("/listsample.do")){
				list = service.listSample();
				request.setAttribute("listSample", list);
				nextPage = "/upload/listSample.jsp";
			}
			else if (action.equals("/formsample.do")) {
				list = service.listSample();
				extra_int = service.extraction_int();
				request.setAttribute("listSampe",list);
				request.setAttribute("extranum",extra_int);
				nextPage = "/upload/formSample.jsp";
			}else if(action.equals("/addsample.do")) {
				Map<String, String> SampleMap = upload(request, response);
				int num = (int)request.getAttribute("extranum");
				String title = SampleMap.get("title");
				String imageFileName = SampleMap.get("imageFileName");

				vo.setNum(num);
				vo.setSample(title);
				vo.setImageFileName(imageFileName);
				service.addSample(vo);
				nextPage = "/upload/listsample.do";
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	protected Map<String, String> upload(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> SampleMap = new HashMap<String, String>();
		File currentDirPath = new File(IMAGE_REF0);
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(currentDirPath);
		factory.setSizeThreshold(1024 * 1024);
		ServletFileUpload upload = new ServletFileUpload(factory);

		try {
			List<FileItem> items = upload.parseRequest(request);
			for (int i = 0; i < items.size(); i++) {
				FileItem fileItem = (FileItem) items.get(i);
				if (fileItem.isFormField()) {
					SampleMap.put(fileItem.getFieldName(), fileItem.getString("utf-8"));
				} else {
					System.out.println("FileName :" + fileItem.getFieldName());
					System.out.println("Size :" + fileItem.getSize() + "bytes");
					if (fileItem.getSize() > 0) {
						int idx = fileItem.getName().lastIndexOf("\\");
						if (idx == -1) {
							idx = fileItem.getName().lastIndexOf("/");
						}
						String fileName = fileItem.getName().substring(idx + 1);
						System.out.println("FileName :" + fileName);
						SampleMap.put(fileItem.getFieldName(), fileName);
						File uploadFile = new File(currentDirPath + "\\" + fileName);
						fileItem.write(uploadFile);

					} // end if
				} // end if
			} // end for
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}

}
