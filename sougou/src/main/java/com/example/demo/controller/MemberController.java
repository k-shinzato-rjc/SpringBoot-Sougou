package com.example.demo.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.MemberForm;
import com.example.demo.service.MemberService;
import com.example.demo.service.PlaceService;
import com.example.demo.service.PositionService;


/**
 * メンバー情報を操作するコントローラー
 * @author koki_shinzato
 */
@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private PlaceService placeSercice;
	
	@Autowired
	private PositionService positionService;
	
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
	
	/**
	 * 確認画面で戻るボタン押下 内容を維持しつつ登録画面に遷移
	 * @author koki_shinzato
	 * 
	 * @param form
	 * @return 登録画面
	 */
	@PostMapping("/insert")
	public String insertBack(@ModelAttribute("form") MemberForm form, Model model) {
		
		// 事業所一覧を取得し、Viewへ渡す
		model.addAttribute("places", placeSercice.convertForm(placeSercice.getAll()));
		
		model.addAttribute("positions", positionService.convertForm(positionService.getAll()));
		
		return "insert/insert";
	}
	
	/**
	 * 登録画面から登録ボタンを押下 → 確認画面へ遷移
	 * @author koki_shinzato
	 * 
	 * @param form
	 * @return 登録確認画面
	 */
	@PostMapping("/insertConf")
	public String insertConf(@ModelAttribute("member") MemberForm form) {
		return "insert/insertConf";
	}
	
	/**
	 * 登録確認画面から登録ボタンを押下 → 完了画面へ遷移
	 * @author koki_shinzato
	 * 
	 * @param form
	 * @param result
	 * @param model
	 * @param redirect
	 * @return
	 */
	@PostMapping("/insertComp")
	public String insertComp(@Valid @ModelAttribute("member") MemberForm form,
						BindingResult result, Model model) {
		
		// バリデーション結果にエラーがあった場合
		if(result.hasErrors()) {
			
			// エラーメッセージを転送
			model.addAttribute("error", "入力内容に不備がありました。");
			
			// 登録画面へ戻る
			return "menu/error";
		}
		
		// FormをDtoに変換し、DB登録
		memberService.regist(form.toDto());
		
		// 完了画面へ遷移
		return "insert/insertComp";
	}
}
