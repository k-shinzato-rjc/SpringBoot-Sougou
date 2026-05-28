package com.example.demo.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		model.addAttribute("places", placeSercice.convertToForm(placeSercice.findAll()));
		
		model.addAttribute("positions", positionService.convertToForm(positionService.findAll()));
		
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
	public String insertConf(@ModelAttribute("member") MemberForm form, Model model) {
		return "insert/insertConf";
	}
	
	/**
	 * 登録確認画面から登録ボタンを押下 → Get通信のinsertCompへリダイレクト
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
						BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		
		// バリデーション結果にエラーがあった場合
		if(result.hasErrors()) {
			
			// エラーメッセージを転送
			model.addAttribute("error", "入力内容に不備がありました。");
			
			// 登録画面へ戻る
			return "menu/error";
		}
		
		// FormをDtoに変換し、DB登録
		memberService.save(form.toDto());
		
		// リダイレクト先へ登録データを渡す
		redirectAttributes.addFlashAttribute("member", form);
		
		// 完了画面へ遷移
		return "redirect:/insertCompRedir";
	}
	
	/**
	 * 登録完了画面に遷移（リダイレクト)
	 * @author koki_shinzato
	 * 
	 * @param model
	 * @return 登録完了画面
	 */
	@GetMapping("/insertCompRedir")
	public String insertComp(Model model) {
		
		// リダイレクトされたデータが存在しない場合（画面更新時など）
		if(!model.containsAttribute("member")) {
			
			// Viewへエラーメッセージを渡し、エラー画面へ遷移
			model.addAttribute("error", "登録完了データは一覧画面よりご確認ください");
			return "menu/error";
		}
		
		// 登録完了画面へ遷移
		return "insert/insertComp";
	}
}
