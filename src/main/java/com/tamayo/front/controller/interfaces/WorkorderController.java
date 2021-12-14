package com.tamayo.front.controller.interfaces;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.tamayo.back.model.Workorder;

public interface WorkorderController {
	public String index(@RequestParam(required = false, value = "id") Integer id,
			@RequestParam(required = false, value = "product") Integer productid,
			Model model);
	public String addWorkorderForm(Model model, @ModelAttribute("workorder") Workorder workorder);
	public String saveWorkorder(@ModelAttribute("workorder") @Validated Workorder workorder, BindingResult result, Model model, @RequestParam(value = "action", required = true) String action);
	public String deleteWorkorder(@PathVariable("id") Integer id, Model model);
	public String showUpdateForm(@PathVariable("id") Integer id, Model model);
	public String updateWorkorder(@PathVariable("id") Integer id, @RequestParam(value = "action", required = true) String action, @ModelAttribute("workorder") @Validated Workorder workorder, BindingResult bindingResult, Model model);
}
