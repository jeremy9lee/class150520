package com.sds.icto.mysite.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sds.icto.mysite.dao.BulletinDao;
import com.sds.icto.mysite.vo.BulletinBoard;

@Controller
public class BulletinController {

	@Autowired
	private BulletinDao dao;

	@RequestMapping("/bulletinWriteForm.do")
	public String bulletinWriteForm() {
		return "views/board/bulletinWriteForm.jsp";
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
		return "/views/board/bulletinUpdateForm.jsp";
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
	public String bulletinDelete(Model m, @RequestParam("b_no") String no)
			throws NumberFormatException, SQLException {

		dao.deleteById(Integer.parseInt(no));
		return "/bulletinMain.do";
	}
	
	@RequestMapping("/bulletinMain.do")
	public String bulletinMain(Model m)
			throws NumberFormatException, SQLException {

		ArrayList<BulletinBoard> list = (ArrayList<BulletinBoard>) dao.selectAllList(0);
		ArrayList<BulletinBoard> listByAdmin = (ArrayList<BulletinBoard>) dao.selectAllList(1);
		
		m.addAttribute("list", list);
		m.addAttribute("listByAdmin", listByAdmin);

		return "/views/board/bulletinBoard.jsp";
	}
	
	
	@RequestMapping("/bulletinDetail.do")
	public String bulletinDetail(Model m, @RequestParam("no") String no)
			throws NumberFormatException, SQLException {

		BulletinBoard b = dao.searchBybNo(Integer.parseInt(no));
		dao.updateHit(b);
		HashMap<String, Object> prevAndNextText = (HashMap<String, Object>) dao.getPrevAndNextText(Integer.parseInt(no));

		m.addAttribute("bulletin", b);
		m.addAttribute("prevAndNext", prevAndNextText);
		return "/views/board/bulletinDetail.jsp";
	}

	

	
	
}
