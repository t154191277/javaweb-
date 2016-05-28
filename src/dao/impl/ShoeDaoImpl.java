package dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

import javax.servlet.jsp.jstl.sql.Result;

import dao.DBDao;
import dao.IShoeDao;
import domain.Shoe;

public class ShoeDaoImpl implements IShoeDao {
	
	DBDao dbDao = new DBDao();
	List<String> valuesList = new ArrayList<String>();

	@Override
	public Shoe find(String productID) {
		String sql = "select * from shoe where productID = (?)";
		valuesList.add(productID);
		dbDao.setSqlStr(sql);
		dbDao.setValuesList(valuesList);
		Result rs = dbDao.executeQuery();
		if(!isEmpty(rs)){
			Shoe shoe = new Shoe();
			for (SortedMap sm : rs.getRows()){
				String name = sm.get("name").toString();
				String brand = sm.get("brand").toString();
				String img = sm.get("img").toString();
				Double price = (Double) sm.get("price");
				Double discount_price = (Double) sm.get("discount_price");
				String status = sm.get("rstatus").toString();
				shoe.setName(name);
				shoe.setBrand(brand);
				shoe.setShoeID(productID);
				shoe.setImgPath(img);
				shoe.setPrice(price);
				shoe.setDiscount_price(discount_price);
				shoe.setStatus(status);
			}
			return shoe;
		}
		return null;
	}

	@Override
	public List<Shoe> findTeamShoe() {
		String sql = "select * from shoe";
		dbDao.setSqlStr(sql);
		Result rs = dbDao.executeQuery();
		List<Shoe> shoeList = new ArrayList<Shoe>();
		if(!isEmpty(rs)){
			for (SortedMap sm : rs.getRows()){
				Shoe shoe = new Shoe();
				String productID = sm.get("productID").toString();
				String name = sm.get("name").toString();
				String brand = sm.get("brand").toString();
				String img = sm.get("img").toString();
				double price = (Double) sm.get("price");
				double discount_price = (Double) sm.get("discount_price");
				String status = sm.get("rstatus").toString();
				shoe.setName(name);
				shoe.setBrand(brand);
				shoe.setShoeID(productID);
				shoe.setImgPath(img);
				shoe.setPrice(price);
				shoe.setDiscount_price(discount_price);
				shoe.setStatus(status);
				shoeList.add(shoe);
			}
			return shoeList;
		}
		return null;
	}

	@Override
	public List<String> findDetailImg(String productID) {
		System.out.print("productID???"+productID+"\n");
		List<String> listImg = new ArrayList<String>();
		String sql = "select img_path from img where productID = (?)";
		valuesList.clear();
		valuesList.add(productID);
		dbDao.setSqlStr(sql);
		dbDao.setValuesList(valuesList);
		Result rs = dbDao.executeQuery();
		if (!isEmpty(rs)) {
			for (SortedMap sm : rs.getRows()){
				String path = sm.get("img_path").toString();
				listImg.add(path);
			}
			return listImg;
		}
		return null;
	}
	
	private boolean isEmpty(Result rs){
		Object[][] x = rs.getRowsByIndex();
		if(rs.getRowCount()==0) return true;
		return (x[0][0].equals("") || x[0][0]==null);
	}

}
