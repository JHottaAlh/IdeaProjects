package jp.co.POS.alh.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.POS.alh.bean.GoodsBean;
import jp.co.POS.alh.dto.GoodsDto;
import jp.co.POS.alh.service.GoodsService;

@Controller
public class MainController {
	@Autowired
	private GoodsService goodsService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model){
		GoodsDto dto = new GoodsDto();
		dto.setCode("");
		dto.setGoods_name("");
		dto.setCategory("");
		dto.setPrice(0);
		model.addAttribute("goods", dto);
		
		return "index";
	}
	
	@RequestMapping(value = "goodsScan", method = RequestMethod.POST)
	@ResponseBody		//これでレスポンスをjson形式で送る
	public String[] goods(Model model, @ModelAttribute GoodsBean goods ){
		System.out.println("到達したでぇ！");
		//商品データなどを配列で渡す
		String code = goods.getCode();
		GoodsDto dto = goodsService.getGoods(code);
		String price = Integer.toString(dto.getPrice());
		String[] goodsInfo = {dto.getGoods_name(), dto.getMaker(), price};
		
		return goodsInfo;
	}
}
