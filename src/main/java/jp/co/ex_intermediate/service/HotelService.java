package jp.co.ex_intermediate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.ex_intermediate.domain.Hotel;
import jp.co.ex_intermediate.repository.HotelRepository;


/**
 * ホテル情報を返すサービスクラス.
 * 
 * @author daiki.takayama
 *
 */
@Service
@Transactional
public class HotelService {
	
	@Autowired
	private HotelRepository repository;
	
	
	
	/**
	 * パラメータから受け取った値以下の宿泊費のホテル情報を返す.
	 * 検索欄が空欄だった場合、すべてのホテル情報を宿泊費の降順で返す.
	 * 
	 * @param price 検索するホテルの料金
	 * @return 該当するホテル情報を返す
	 */
	public List<Hotel> searchLessThanPrice(String price) {
		if(price.equals("")) {
			return repository.findAll();
		}else{
			return repository.findByLessThanPrice(Integer.parseInt(price));
		}

	}
	

}
