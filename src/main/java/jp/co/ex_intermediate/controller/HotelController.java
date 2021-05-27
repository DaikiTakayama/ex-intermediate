package jp.co.ex_intermediate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.ex_intermediate.domain.Hotel;
import jp.co.ex_intermediate.service.HotelService;


/**
 * ホテル情報を画面に出力するクラス
 * 
 * @author daiki.takayama
 *
 */
@Controller
@RequestMapping("/exam02")
public class HotelController {
	
	@Autowired
	private HotelService service;
	
	
	/**
	 * ホテルの価格検索画面を出力する.
	 * 
	 * 
	 * @return ホテル検索画面へフォワード
	 */
	@RequestMapping("")
	public String input() {
		return "hotel/hotel-input";
	}
	
	
	/**
	 * ホテル検索画面で取得したパラメータに対して、検索結果を出力する.
	 * 返ってきた値がnullだった場合、"検索結果はゼロ件でした"と表示.
	 * 
	 * @param price 検索画面で受け取ったパラメータ
	 * @param model 結果を送るリクエストスコープ
	 * @return 検索結果画面を表示
	 */
	@RequestMapping("/result")
	public String searchResult(String price,Model model) {
		//受け取った文字列か数値かどうか確認
		try {
			//負数だった場合、検索できないのでエラー
			if(Integer.parseInt(price) <0) {
				model.addAttribute("fail","検索結果はゼロ件でした");
				return "hotel/hotel-input";
			}
		}
		//文字列だった場合、検索結果はゼロ
		catch(NumberFormatException ignored) {
			if(price.equals("")) {
				//findAll処理を実行する（例外を無視）
				List<Hotel> hotelList= service.searchLessThanPrice(price);
				model.addAttribute("price",price);
				model.addAttribute("hotelList",hotelList);
				
				return "hotel/hotel-input";
			}
			//検索できない文字列だった場合
			model.addAttribute("fail","検索結果はゼロ件でした");
			return "hotel/hotel-input";
		}
		
		List<Hotel> hotelList= service.searchLessThanPrice(price);
		//hotelListが空だった場合検索結果はゼロ
		if(hotelList.size()==0) {
			model.addAttribute("fail","検索結果はゼロ件でした");
		}
		
		model.addAttribute("price",price);
		model.addAttribute("hotelList",hotelList);
		
		return "hotel/hotel-input";
	}
}
