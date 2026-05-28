package com.example.demo.controller;

import java.util.Objects;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.MemberDto;
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
			
			// エラー画面へ遷移
			return "menu/error";
		}
		
		// FormをDtoに変換し、DB登録
		memberService.save(form.toDto());
		
		// リダイレクトURLにメンバーIDのパラメーターを付与
		redirectAttributes.addAttribute("memberId", form.getMemberId());
		
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
	public String insertComp(@RequestParam("memberId") String memberId, Model model) {
		
		// メンバーIDに該当するメンバーデータを取得し、Viewへ渡す
		MemberDto memberDto = memberService.findById(memberId);
		
		// 取得データが存在しない場合
		if(Objects.isNull(memberDto)) {
			model.addAttribute("error", "該当するメンバーデータが存在しませんでした");
			return "menu/error";
		}
		
		// Dto → Form 変換、Viewへ渡す
		model.addAttribute("member",memberDto.fromDtoToForm());
		
		// 登録完了画面へ遷移
		return "insert/insertComp";
	}
	
	/**
	 * メンバー一覧画面 → 詳細画面へ遷移
	 * @author koki_shinzato
	 * 
	 * @param memberId
	 * @param model
	 * @return 詳細画面
	 */
	@GetMapping("/detail")
	public String detail(@RequestParam("memberId") String memberId, Model model) {
		
		// リクエストパラメーターのmemberIdを用いて、メンバー情報を取得
		MemberDto memberDto = memberService.findById(memberId);
		
		// メンバー情報が存在しない場合
		if(Objects.isNull(memberDto)) {
			model.addAttribute("error", "IDに該当するメンバーが存在しませんでした");
			
			// エラー画面に遷移
			return "menu/error";
		}
		
		// Form型データをViewへ渡す
		model.addAttribute("member", memberDto.fromDtoToForm());
		
		// 詳細画面へ遷移
		return "detail/detail";
		
	}
	
}
