package jp.co.ex_intermediate.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.ex_intermediate.domain.Hotel;

/**
 * Hotelsテーブルを操作するリポジトリクラス.
 * 
 * @author daiki.takayama
 *
 */
@Repository
public class HotelRepository {

	private static final RowMapper<Hotel> HOTEL_ROW_MAPPER = new BeanPropertyRowMapper<>(Hotel.class);
	
	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 渡された宿泊費以下のホテルを降順で出力する. 渡された値がnullならば全権検索を行う.
	 * 
	 * @param price 基準となる宿泊費
	 * @return 該当するホテルのリストを送る
	 */
	public List<Hotel> findByLessThanPrice(Integer price) {
		System.out.println("--- findByLessThanPrice() ---");
		
		List<Hotel> hotelList =new ArrayList<>();
		String sql = "select id,area_name,hotel_name,address,nearest_station,price,parking from hotels where price <=:price order by price desc";
		SqlParameterSource param = new MapSqlParameterSource().addValue("price", price);
		hotelList= template.query(sql, param, HOTEL_ROW_MAPPER);
		

		return hotelList;
	}
	/**
	 * Hotels テーブルに格納された情報を宿泊費の降順で返す.
	 * @return ホテル情報を宿泊費の降順で返す.
	 */
	public List<Hotel> findAll() {
		System.out.println("--- findAll()---");

		String sql = "select id,area_name,hotel_name,address,nearest_station,price,parking from hotels order by price desc";
		
		List<Hotel> hotelList = template.query(sql, HOTEL_ROW_MAPPER);
		return hotelList;
	}

}
