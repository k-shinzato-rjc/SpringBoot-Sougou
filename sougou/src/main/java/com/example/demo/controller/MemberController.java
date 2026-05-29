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
	@PostMapping("/detail")
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
	
	/**
	 * 一覧画面で削除ボタン押下 → 削除画面に遷移
	 * @author koki_shinzato
	 * 
	 * @param memberId
	 * @param model
	 * @return 削除画面
	 */
	@GetMapping("/delete")
	public String delete(@RequestParam("memberId") String memberId, Model model) {
		
		// メンバーデータ取得
		MemberDto memberDto = memberService.findById(memberId);
		
		// データ無し
		if(Objects.isNull(memberDto)) {
			model.addAttribute("error", "該当するメンバーデータが見つかりませんでした");
			
			// エラー画面遷移
			return "menu/error";
		}
		
		// 取得したデータをViewへ渡す
		model.addAttribute("member", memberDto.fromDtoToForm());
		
		// 削除画面へ遷移
		return "delete/delete";
	}
	
	/**
	 * 削除画面の削除ボタンを押下 → ユーザーIDをパラメーターに付与してリダイレクト
	 * @author koki_shinzato
	 * 
	 * @param memberForm
	 * @param redirectAttributes
	 * @return リダイレクト先メソッド
	 */
	@PostMapping("/deleteComp")
	public String deleteComp(@ModelAttribute("member") MemberForm memberForm, RedirectAttributes redirectAttributes) {
		
		redirectAttributes.addAttribute("memberId", memberForm.getMemberId());
		
		return "redirect:/deleteCompRedir";
	}
	
	/**
	 * DB削除処理 → 削除完了画面が表示される
	 * @author koki_shinzato
	 * 
	 * @param memberId
	 * @param model
	 * @return
	 */
	@GetMapping("/deleteCompRedir")
	public String deleteCompRedir(@RequestParam("memberId") String memberId, Model model) {
		
		// 削除対象データを取得
		MemberDto memberDto = memberService.findById(memberId);
		
		if(Objects.isNull(memberDto)) {
			model.addAttribute("error", "既にメンバーデータが存在しません");
			
			return "menu/error";
		}
		
		// DB削除処理
		memberService.deleteById(memberId);
		
		// 削除完了画面 表示用
		model.addAttribute("member", memberDto.fromDtoToForm());
		
		return "delete/deleteComp";
	}
	
	/**
	 * 一覧画面から更新ボタンを押下 → 更新画面を表示
	 * @author koki_shinzato
	 * 
	 * @param memberId
	 * @param model
	 * @return 更新画面
	 */
	@PostMapping("/update")
	public String update(@RequestParam("memberId") String memberId, Model model) {
		
		// メンバーデータ取得
		MemberDto memberDto = memberService.findById(memberId);
		
		// データが存在しない場合
		if(Objects.isNull(memberDto)) {
			model.addAttribute("error", "該当するデータが存在しません");
			
			// エラー画面遷移
			return "menu/error";
		}
		
		// 該当するメンバーデータをFormに変換してViewへ渡す
		model.addAttribute("member", memberDto.fromDtoToForm());
		
		// 権限情報リスト
		model.addAttribute("positions", positionService.convertToForm(positionService.findAll()));
		
		// 事業所情報リスト
		model.addAttribute("places", placeSercice.convertToForm(placeSercice.findAll()));
		
		// 更新画面へ遷移
		return "update/update";
	}
	
	/**
	 * 更新確認画面で戻るボタンを押下 → 入力内容を維持して更新画面に戻る
	 * @author koki_shinzato
	 * 
	 * @param memberId
	 * @param model
	 * @return 更新画面
	 */
	@PostMapping("/backUpdate")
	public String backUpdate(@ModelAttribute("member") MemberForm memberForm, Model model) {
		
		// 該当するメンバーデータをFormに変換してViewへ渡す
		model.addAttribute("member", memberForm);
		
		// 権限情報リスト
		model.addAttribute("positions", positionService.convertToForm(positionService.findAll()));
		
		// 事業所情報リスト
		model.addAttribute("places", placeSercice.convertToForm(placeSercice.findAll()));
		
		// 更新画面へ遷移
		return "update/update";
	}
	
	/**
	 * 更新画面から更新ボタン押下 → 確認画面へ遷移
	 * @author koki_shinzato
	 * 
	 * @param memberForm
	 * @param model
	 * @return 更新確認画面
	 */
	@PostMapping("/updateConf")
	public String updateConf(@Valid @ModelAttribute("member") MemberForm memberForm, BindingResult result, Model model) {
		
		// 入力チェック
		if(result.hasErrors()) {
			model.addAttribute("error", "入力内容に不備があります。");
			
			return "menu/error";
		}
		
		// 入力内容をViewへ再び渡す
		model.addAttribute("member", memberForm);
		
		// 更新確認画面へ遷移
		return "update/updateConf";
	}
	
	/**
	 * 更新確認画面で更新ボタン押下 → メンバーIDをリクエストパラメーターに付与してリダイレクト
	 * @author koki_shinzato
	 * 
	 * @param memberForm
	 * @param redirectAttributes
	 * @return リダイレクト先
	 */
	@PostMapping("/updateComp")
	public String updateComp(@ModelAttribute("member") MemberForm memberForm, RedirectAttributes redirectAttributes, Model model) {
		
		// DB更新
		memberService.save(memberForm.toDto());
		
		// 登録データのIDをリダイレクトパラメーターに付与して転送
		redirectAttributes.addAttribute("memberId", memberForm.getMemberId());
		
		// リダイレクト
		return "redirect:/updateCompRedir";
	}
	
	/**
	 * DB更新実施、更新完了画面へ遷移
	 * @author koki_shinzato
	 * 
	 * @param model
	 * @return 更新完了画面
	 */
	@GetMapping("/updateCompRedir")
	public String updateCompRedir(@RequestParam("memberId") String memberId, Model model) {
		
		// リダイレクトされたIDを用いて、DBから登録されたデータを取得
		MemberDto memberDto = memberService.findById(memberId);
		
		// 完了画面更新時などの対応
		if(Objects.isNull(memberDto)) {
			model.addAttribute("error", "DB更新が正常に完了しませんでした");
			
			return "menu/error";
		}
		
		// 登録したデータをFormに変換してViewへ渡す
		model.addAttribute("member", memberDto.fromDtoToForm());
		
		// 更新完了画面へ遷移
		return "update/updateComp";
	}
}
