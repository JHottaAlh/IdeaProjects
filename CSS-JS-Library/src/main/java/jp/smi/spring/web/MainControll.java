package jp.smi.spring.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.smi.spring.form.UserForm;

@Controller
public class MainControll {
	
	//RequestMappingアノテーションでどのURLに対してどう送信されたか(GET,POSTなど)を指定
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model){
		
		//遷移させたいページの.jspを除いた部分
		//(この場合index.jspに遷移)
		return "index";
	}
	
	@RequestMapping(value = "signup", method = RequestMethod.GET)
	public String signup(Model model){
		UserForm first = new UserForm();
		first.setLogin_id("");
		first.setPassword("");
		first.setName("");
		model.addAttribute("signupForm", first);
		
		return "signup";
	}
	
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(Model model){
		
		return "login";
	}
	
	@RequestMapping(value = "/css", method = RequestMethod.GET)
	public String css(Model model){
		
		return "css";
	}
	
	@RequestMapping(value = "/js", method = RequestMethod.GET)
	public String js(Model model){
		
		return "js";
	}
}
