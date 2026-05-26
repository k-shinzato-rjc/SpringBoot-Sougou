package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.MemberDto;
import com.example.demo.service.MemberService;


/**
 * メンバー情報を操作するコントローラー
 * @author koki_shinzato
 */
@Controller
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
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

	/**
	 * 実装予定
	 * @param model
	 * @return
	 */
	@PostMapping("/detail")
	public String detail(Model model) {
		
		// メンバー指定
		
		return "detail/detail";
	}
	
	/**
	 * 実装予定
	 * @param model
	 * @return
	 */
	@PostMapping("/update")
	public String update(Model model) {
		
		// メンバー指定
		
		return "update/update";
	}
	
	/**
	 * 実装予定
	 * @param model
	 * @return
	 */
	@PostMapping("/delete")
	public String delete(Model model) {
		
		// メンバー指定
		
		return "delete/delete";
	}
}
