package jp.co.POS.alh.bean;

public class GoodsBean {
	private String code;
	private String goods_name;
	private String maker;
	private int price;
	private String category;
	
	public String getCode() {return code;}
	public void setCode(String code) {this.code = code;}
	
	public String getGoods_name() {return goods_name;}
	public void setGoods_name(String goods_name) {this.goods_name = goods_name;}
	
	public String getMaker() {return maker;}
	public void setMaker(String maker) {this.maker = maker;}
	
	public int getPrice() {return price;}
	public void setPrice(int price) {this.price = price;}
	
	public String getCategory() {return category;}
	public void setCategory(String category) {this.category = category;}
}
