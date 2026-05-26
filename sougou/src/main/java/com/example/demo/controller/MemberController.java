package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;


/**
 * メンバー情報を操作するコントローラー
 * @author koki_shinzato
 */
@Controller
public class MemberController {

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
