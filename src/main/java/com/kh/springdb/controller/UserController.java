package com.kh.springdb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.springdb.model.UserCreateForm;
import com.kh.springdb.model.UserRole;
import com.kh.springdb.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {
	private final UserService userService;
	
	//회원가입 창
	@GetMapping("/signup")
	public String signUp(UserCreateForm userCreateForm) {
		return "signup_form";
	}
	
	@PostMapping("/signup")
	public String signup(@Valid UserCreateForm userCreateForm,BindingResult bindingResult) {
		if(!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
			bindingResult.rejectValue("password2", "passwordInCorrect","비밀번호와 비밀번호 확인이 일치하지 않습니다.");
		}
		//html 폼에서 선택한 역할을 가지고 오기 위해
		
		UserRole role = userCreateForm.getIsRole();
		userService.createUser(userCreateForm.getUsername(),userCreateForm.getPassword1() , userCreateForm.getEmail(),userCreateForm.getIsRole());
		//userService.createUser(userCreateForm.getUsername(),userCreateForm.getPassword1() , userCreateForm.getEmail());
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login_form";
	}
}
