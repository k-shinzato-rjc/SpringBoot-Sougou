package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Menu画面の管理するコントローラー
 * @author koki_shinzato
 */
@Controller
public class MenuController {
	
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
}
