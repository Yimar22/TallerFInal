package com.tamayo.front.controller.interfaces;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.tamayo.front.model.Workorderrouting;


public interface WorkorderroutingController {
	public String index(@RequestParam(required = false, value = "id") Integer id,
			@RequestParam(required = false, value = "product") Integer workorderid,
			@RequestParam(required = false, value = "product") Integer locationid,
			Model model);
	public String addWorkorderForm(Model model, @ModelAttribute("workorderrouting") Workorderrouting workorderrouting);
	public String saveWorkorder(@ModelAttribute("workorderrouting") @Validated Workorderrouting workorderrouting, BindingResult result, Model model, @RequestParam(value = "action", required = true) String action);
	public String deleteWorkorder(@PathVariable("id") Integer id, Model model);
	public String showUpdateForm(@PathVariable("id") Integer id, Model model);
	public String updateWorkorder(@PathVariable("id") Integer id, @RequestParam(value = "action", required = true) String action, @ModelAttribute("workorderrouting") @Validated Workorderrouting workorderrouting, BindingResult bindingResult, Model model);
}
