package com.tamayo.front.controller.implementation;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.tamayo.front.businessdelegate.BusinessDelegate;
import com.tamayo.front.controller.interfaces.WorkorderController;
import com.tamayo.front.model.Workorder;

@Controller
@RequestMapping("workorders")
public class WorkorderControllerImpl implements WorkorderController{
	
	@Autowired
	private BusinessDelegate bd;
	
	private ArrayList<String> logicalOperands;
	
	public WorkorderControllerImpl() {
		logicalOperands = new ArrayList<>();
		logicalOperands.add("AND");
		logicalOperands.add("OR");
		logicalOperands.add("XOR");
		logicalOperands.add("NOT");
		logicalOperands.add("NOR");
		logicalOperands.add("NAND");
	}
	
	@Override
	@GetMapping
	public String index(@RequestParam(required = false, value = "id") Integer id,
			@RequestParam(required = false, value = "product") Integer productid,
			Model model) {
		if(id != null) {
			ArrayList<Workorder> workorders = new ArrayList<>();
			workorders.add(bd.workorder_get(id));
			model.addAttribute("workorders", workorders);
		} else if(productid!=null) {
			model.addAttribute("workorders", bd.workorder_findAllByProduct(productid));
		} else {
			model.addAttribute("workorders", bd.workorder_findAll());
		}
		return "workorders/index";
	}

	@Override
	@GetMapping("/add")
	public String addWorkorderForm(Model model, @ModelAttribute("workorder") Workorder workorder) {
		model.addAttribute("prods", bd.productFindAll());
		return "workorders/add-workorder";
	}

	@Override
	@GetMapping("/add")
	public String saveWorkorder(@ModelAttribute("workorder") @Validated Workorder workorder, BindingResult result, Model model, @RequestParam(value = "action", required = true) String action) {
		if(!action.equals("Cancel")) {
			if(result.hasErrors()) {
				model.addAttribute("workorder", workorder);
				model.addAttribute("prods", bd.productFindAll());
				return "workorders/add-workorder";
			}
			bd.workorder_save(workorder);
		}
		return "redirect:/workorders/";
	}

	@Override
	@GetMapping("/del/{id}")
	public String deleteWorkorder(@PathVariable("id") Integer id, Model model) {
		Workorder workorder = bd.workorder_findById(id);
		bd.workorder_delete(workorder);
		return "redirect:/workorders/";
	}

	@Override
	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		Workorder workorder = bd.workorder_findById(id);
		model.addAttribute("workorder", workorder);
		model.addAttribute("prods", bd.productFindAll());
		
		return "workorders/update-workorder";
	}

	@Override
	@GetMapping("/edit/{id}")
	public String updateWorkorder(@PathVariable("id") Integer id, @RequestParam(value = "action", required = true) String action, @ModelAttribute("workorder") @Validated Workorder workorder, BindingResult bindingResult, Model model) {
		if(action !=null && !action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("prods", bd.productFindAll());
				return "workorders/update-workorder";
			}
			bd.editWorkorder(workorder);
		}
		return "redirect:/workorders/";
	}
	
}
