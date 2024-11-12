package com.leon.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.leon.webapp.entity.AuthResponse;
import com.leon.webapp.service.AuthService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

	@Autowired
	private AuthService authService;

	@GetMapping("/")
	public String homePage(Model model) {
		return "home";
	}

	@GetMapping("/login")
	public String loginForm() {
		return "login";
	}

	@PostMapping("/login")
	public ModelAndView handleLogin(@RequestParam String username, @RequestParam String password, Model model,
			HttpSession session) {

		AuthResponse response = authService.login(username, password);

		if (response.getStatusCode() == HttpStatus.OK) {

			session.setAttribute("token", response.getMessage());
			session.setAttribute("username", username);
			return new ModelAndView("redirect:/");
		} else {
			model.addAttribute("error", response.getMessage());
			return new ModelAndView("login");
		}
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		String token = (String) session.getAttribute("token");
		
		if (token != null)
			session.invalidate();
		
		return "redirect:/";
	}

}
