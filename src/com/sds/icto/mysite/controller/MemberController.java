package com.sds.icto.mysite.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.sds.icto.mysite.dao.MemberDao;
import com.sds.icto.mysite.vo.MemberVo;

@Controller
@EnableWebMvc
public class MemberController {

	@Autowired
	private MemberDao dao;

	@RequestMapping("/getMember.do")
	@ResponseBody
	public Map getMember(@RequestParam("userId") String email) {

		System.out.println("들어왔다");
		Map map = new HashMap<String, Boolean>();
		if (dao.getMember(email, null) != null) {
			map.put("success", true);
		} else {
			map.put("success", false);
		}

		return map;
	}

	@RequestMapping("/goToJoinForm.do")
	public String goToJoinForm() {

		return "/views/user/joinform.jsp";
	}

	@RequestMapping("/loginform.do")
	public String loginForm(Model m,
			@RequestParam(value = "result", required = false) String result) {
		System.out.println(result);
		m.addAttribute("result", result);
		return "/views/user/loginform.jsp";
	}

	@RequestMapping("/login.do")
	public String login(Model m, @RequestParam("password") String password,
			@RequestParam("email") String email, HttpServletRequest request) {

		MemberVo member = dao.getMember(email, password);

		if (member != null) {
			HttpSession session = request.getSession(true);
			session.setAttribute("session", member.getName());
			session.setAttribute("sessionNo", member.getNo());
			session.setAttribute("sessionEmail", member.getEmail());
			return "/main.do";
		} else {
			return "/loginform.do?result=" + false;
		}
	}

	@RequestMapping("/logout.do")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();

		return "/main.do";
	}

	@RequestMapping("/insert.do")
	public String insert(@RequestParam("name") String name,
			@RequestParam("password") String password,
			@RequestParam("email") String email,
			@RequestParam("gender") String gender,
			@RequestParam("agreeProv") String agreeProv) {

		if (agreeProv.equals("y")) {
			dao.insert(new MemberVo(null, name, email, password, gender));
		}
		return "/views/user/joinsuccess.jsp";
	}

	@RequestMapping("/memberUpdate.do")
	public String updateMember(HttpServletRequest request, @RequestParam("newPassword") String password) {

		HttpSession session = request.getSession();
		
		MemberVo vo = new MemberVo();
		vo.setPassword(password);
		vo.setNo((long) session.getAttribute("sessionNo"));
		dao.update(vo);

		return "/main.do";

	}

	@RequestMapping("/updateMemberForm.do")
	public String updateMemberForm(Model m, HttpServletRequest request) {

		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("sessionEmail");

		MemberVo member = dao.getMember(email, null);
		m.addAttribute("member", member);

		return "views/user/updateForm.jsp";

	}

}
