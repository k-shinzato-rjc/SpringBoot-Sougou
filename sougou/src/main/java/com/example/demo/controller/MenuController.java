package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.dto.MemberDto;
import com.example.demo.service.MemberService;

/**
 * Menu画面の管理するコントローラー
 * @author koki_shinzato
 */
@Controller
public class MenuController {
	
	@Autowired
	private MemberService memberService;
	
	/**
	 * 初期メニュー画面の表示
	 * @author koki_shinzato
	 * @return 初期メニュー画面
	 */
	@GetMapping("/")
	public String index() {
		return "menu/index";
	}
	
	/**
	 * 新規登録画面に遷移
	 * @author koki_shinzato
	 * @return 新規登録画面
	 */
	@GetMapping("/insert")
	public String insert() {
		
		// DBから役職と事業所のリストを取得
		
		return "insert/insert";
		
	}
	
	/**
	 * メンバー一覧画面を表示
	 * @param model
	 * @return
	 */
	@GetMapping("/list")
	public String list(Model model) {
		
		// DBからデータを取得
		List<MemberDto> memberDto = memberService.findAll();
		
		// Formに変換してViewへ渡す
		model.addAttribute("members", memberService.convertToForm(memberDto));
		
		// list.htmlに遷移
		return "list/list";
	}
}
