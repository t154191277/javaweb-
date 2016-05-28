package dao;

import java.util.List;

import domain.Shoe;

public interface IShoeDao {
	
	Shoe find(String productID);
	
	List<Shoe> findTeamShoe();
	
	List<String> findDetailImg(String productID);
}
