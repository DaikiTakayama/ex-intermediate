package jp.co.ex_intermediate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.ex_intermediate.domain.Team;
import jp.co.ex_intermediate.repository.TeamRepository;


/**
 * 球団情報を呼び出すサービスクラス.
 * 
 * @author daiki.takayama
 *
 */
@Service
@Transactional
public class TeamService {
	
	@Autowired
	private TeamRepository repository;
	/**
	 * 球団のリストを取得するメソッド.
	 * 
	 * @return findAll()処理の実行結果を返す
	 */
	public List<Team> showTeamList() {
		
		return 	repository.findAll();
		
	}
	
	
	/**
	 * 該当する球団IDの詳細情報を返すメソッド.
	 * 
	 * @param id 詳細情報を取得したい球団のID
	 * @return findById()処理の実行結果を返す
	 */
	public Team showTeamDetail(Integer id) {
		return repository.findById(id);
	}
	
}
