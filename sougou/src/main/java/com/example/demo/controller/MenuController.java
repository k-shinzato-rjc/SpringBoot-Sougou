package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.MemberDto;
import com.example.demo.dto.PlaceDto;
import com.example.demo.dto.PositionDto;
import com.example.demo.form.MemberForm;
import com.example.demo.service.MemberService;
import com.example.demo.service.PlaceService;
import com.example.demo.service.PositionService;

/**
 * Menu画面の管理するコントローラー
 * @author koki_shinzato
 */
@Controller
public class MenuController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private PositionService positionService;
	
	@Autowired
	private PlaceService placeService;
	
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
	public String insert(Model model) {
		
		// 入力欄紐づけ用
		model.addAttribute("form", new MemberForm());
		
		// 役職一覧をDtoで取得し、Formに変換してViewへ渡す
		List<PositionDto> dtoPositionList = positionService.findAll();
		model.addAttribute("positions", positionService.convertToForm(dtoPositionList));
		
		// 事業所一覧をDtoで取得し、Formに変換してViewへ渡す
		List<PlaceDto> dtoPlaceList = placeService.findAll();
		model.addAttribute("places", placeService.convertToForm(dtoPlaceList));
		
		// insert.htmlへ遷移
		return "insert/insert";
		
	}
	
	/**
	 * メンバー一覧画面を表示
	 * @param model
	 * @return
	 */
	@GetMapping("/list")
	public String list(Model model) {
		
		// DBからメンバーデータを取得
		List<MemberDto> memberDto = memberService.findAll();
		
		// Formに変換してViewへ渡す
		model.addAttribute("members", memberService.convertToForm(memberDto));
		
		// list.htmlに遷移
		return "list/list";
	}
	
	/**
	 * 更新画面で戻るボタン押下 → 一覧画面に戻る
	 * @param model
	 * @return
	 */
	@PostMapping("/list")
	public String backlist(Model model) {
		
		// DBからメンバーデータを取得
		List<MemberDto> memberDto = memberService.findAll();
		
		// Formに変換してViewへ渡す
		model.addAttribute("members", memberService.convertToForm(memberDto));
		
		// list.htmlに遷移
		return "list/list";
	}
	
}
