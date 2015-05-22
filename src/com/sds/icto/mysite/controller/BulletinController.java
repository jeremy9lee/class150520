package com.sds.icto.mysite.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sds.icto.mysite.dao.BulletinDaoImpl;
import com.sds.icto.mysite.vo.BulletinBoard;

@Controller
public class BulletinController {

	@Autowired
	private BulletinDaoImpl dao;

	@RequestMapping("/bulletinWriteForm.do")
	public String bulletinWriteForm() {
		return "/WEB-INF/views/board/bulletinWriteForm.jsp";
	}

	@RequestMapping("/bulletinWrite.do")
	public String bulletinWrite(Model m,
			@RequestParam("sessionNo") String memberNo,
			@RequestParam("title") String title,
			@RequestParam("content") String content,
			@RequestParam("name") String name) {

		try {
			dao.insert(new BulletinBoard(0, Integer.parseInt(memberNo), title,
					name, content, null, 0));
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "/bulletinMain.do";
	}

	@RequestMapping("/bulletinUpdateForm.do")
	public String bulletinUpdateForm(Model m, @RequestParam("no") String no) {

		BulletinBoard b = null;
		try {
			b = dao.searchBybNo(Integer.parseInt(no));
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		m.addAttribute("bulletin", b);
		return "/WEB-INF/views/board/bulletinUpdateForm.jsp";
	}

	@RequestMapping("/bulletinUpdate.do")
	public String guestupdate(Model m, @RequestParam("title") String title,
			@RequestParam("content") String content,
			@RequestParam("b_no") String b_no) {

		BulletinBoard b = new BulletinBoard();
		b.setB_no(Integer.parseInt(b_no));
		b.setContent(content);
		b.setB_title(title);

		try {
			dao.updateBoard(b);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "/bulletinDetail.do?no=" + Integer.parseInt(b_no);
	}

	@RequestMapping("/bulletinDelete.do")
	public String bulletinDelete(Model m, @RequestParam("b_no") String no, HttpServletRequest request)
			throws NumberFormatException, SQLException {

		HttpSession session = request.getSession();
		long memberno = (long) session.getAttribute("sessionNo");
		dao.delete(0,(Integer.parseInt(no)));
		return "/bulletinMain.do";
	}
	
	@RequestMapping("/bulletinMain.do")
	public String bulletinMain(Model m)
			throws NumberFormatException, SQLException {

		ArrayList<BulletinBoard> list = (ArrayList<BulletinBoard>) dao.selectAllList(true);
		ArrayList<BulletinBoard> listByAdmin = (ArrayList<BulletinBoard>) dao.selectAllList(false);
		
		m.addAttribute("list", list);
		m.addAttribute("listByAdmin", listByAdmin);

		return "/WEB-INF//views/board/bulletinBoard.jsp";
	}
	
	
	@RequestMapping("/bulletinDetail.do")
	public String bulletinDetail(Model m, @RequestParam("no") String no)
			throws NumberFormatException, SQLException {

		BulletinBoard b = dao.searchBybNo(Integer.parseInt(no));
		dao.updateHit(b);
		List<Map<String, Object>> prevAndNextText = dao.getPrevAndNextText(no);

		for (int i = 0; i < prevAndNextText.size(); i++) {
			System.out.println(prevAndNextText.get(i));
		}
		m.addAttribute("bulletin", b);
		m.addAttribute("prevAndNext", prevAndNextText.get(0));
		return "/WEB-INF/views/board/bulletinDetail.jsp";
	}

	

	
	
}
