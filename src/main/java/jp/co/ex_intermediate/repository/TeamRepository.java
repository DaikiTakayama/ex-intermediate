package jp.co.ex_intermediate.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.ex_intermediate.domain.Team;

/**
 * 球団テーブルを操作するリポジトリ.
 * 
 * @author daiki.takayama
 *
 */
@Repository
public class TeamRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<Team> TEAM_ROW_MAPPER = new BeanPropertyRowMapper<>(Team.class);

	/**
	 * 球団情報のリストを呼び出す.
	 * 
	 * @return 球団情報の一覧を返す
	 */
	public List<Team> findAll() {
		String sql = "select id,league_name,team_name,headquarters,inauguration,history from teams"
				+ " order by inauguration";

		List<Team> teamList = template.query(sql, TEAM_ROW_MAPPER);

		return teamList;
	}

	/**
	 * 与えられた球団IDの情報を出力.
	 * 
	 * @param id 検索する球団のID
	 * @return 検索する球団情報
	 */
	public Team findById(Integer id) {
		String sql = "select id,league_name,team_name,headquarters,inauguration,history from teams" + " where id=:id";

		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

		Team team = template.queryForObject(sql, param, TEAM_ROW_MAPPER);
		return team;
	}
}
