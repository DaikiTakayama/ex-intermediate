package jp.co.ex_intermediate.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.ex_intermediate.domain.Team;
import jp.co.ex_intermediate.repository.TeamRepository;


/**
 * 演習1 球団情報を表示するクラス.
 * @author daiki.takayama
 *
 */
@Controller
@RequestMapping("/exam01")
public class Ex01Controller {
	
	
	@Autowired
	private TeamRepository repository;
	
	/**
	 * 球団情報のリストを出力する.
	 * @param model 球団情報のリストを格納するリクエストスコープ
	 * @return 球団リストを出力
	 */
	@RequestMapping("")
	public String showTeamList(Model model) {
		List<Team> teamList=repository.showList();
		model.addAttribute("teamList",teamList);
		
		return "team/team-list";
	}
	
	/**
	 * 球団の詳細情報を出力するクラス.
	 * @param id 詳細表示する球団のID
	 * @param model　球団情報を格納するリクエストスコープ
	 * @return 詳細画面へフォワード
	 */
	@RequestMapping("/showTeamDetail")
	public String showTeamDetail(Integer id,Model model) {
		Team team = repository.showTeamDetail(id);
		model.addAttribute("team",team);
		return "team/team-detail";
	}
}
