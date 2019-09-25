package db;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/sample1")
public class Servlet extends HttpServlet {

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
		response.setContentType("text/html;charset = utf-8");
		PrintWriter out = response.getWriter();
		MemberDAO dao = new MemberDAO();
		List<MemberVO> list = dao.listMembers();

		out.print("<html><body>");
		out.print("<table border = 1><tr align = 'center' bgcolor='lightgreen'>");
		out.print("<td>Number</td><td>Sample<</td></tr>");

		for (int i = 0; i < list.size(); i++) {
			MemberVO vo = (MemberVO) list.get(i);
			int num = vo.getNum();
			String sample = vo.getTitle();
			out.print("<tr><td>" + num + "</td><td>" + sample + "</td><td>");
		}
		out.print("</table></body></html>");
	}
}
