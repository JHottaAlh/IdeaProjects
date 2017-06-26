package jp.smi.spring.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import jp.smi.spring.dto.UserDto;
import jp.smi.spring.form.UserForm;
import jp.smi.spring.service.LoginService;

@SessionAttributes("User")
@Controller
public class MainControll {
	@Autowired
    private LoginService loginService;
	
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
		first.setMailaddress("");
		model.addAttribute("signupForm", first);
		
		return "signup";
	}
	
	@RequestMapping(value = "signup", method = RequestMethod.POST)
	public String createUser(@ModelAttribute UserForm userInfo, Model model){
		//ここでインタフェースを使ってうまいこと登録をば
		
		return "index";
	}
	
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(Model model){
		UserForm first = new UserForm();
		first.setLogin_id("");
		first.setPassword("");
		model.addAttribute("loginForm", first);
		
		return "login";
	}
	
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String userCheck(@ModelAttribute UserForm userInfo, Model model){
		String login_id = userInfo.getLogin_id();		//値は取得できてる
		String password = userInfo.getPassword();
		UserDto dto = loginService.getUser(login_id, password);
		model.addAttribute("User", dto);
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(SessionStatus sessionStatus){
		//セッションを削除したい場合メソッドの引数にSessionStatus型を宣言し、setCompleteメソッドを呼ぶだけ
		sessionStatus.setComplete();
		return "redirect:/";
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
