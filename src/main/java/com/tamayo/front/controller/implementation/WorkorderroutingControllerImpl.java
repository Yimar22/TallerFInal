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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tamayo.front.businessdelegate.BusinessDelegate;
import com.tamayo.front.controller.interfaces.WorkorderroutingController;
import com.tamayo.front.model.Workorderrouting;

@Controller
@RequestMapping("workorderroutings")
public class WorkorderroutingControllerImpl implements WorkorderroutingController{

	@Autowired
	private BusinessDelegate bd;
	
private ArrayList<String> logicalOperands;
	
	public WorkorderroutingControllerImpl() {
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
	public String index(@RequestParam(required = false, value = "id") Integer id, Integer workorderid, Integer locationid, Model model) {
		model.addAttribute("workorderroutings", bd.workorderrouting_findAll());
		return "/workorderroutings/index";
	}

	@Override
	@GetMapping("/add")
	public String addWorkorderForm(Model model,@ModelAttribute("Workorderrouting") Workorderrouting workorderrouting) {
		model.addAttribute("workorders", bd.workorder_findAll());
		model.addAttribute("locations", bd.location_findAll());
		return null;
	}

	@Override
	@PostMapping("/add")
	public String saveWorkorder(@ModelAttribute("workorderrouting") @Validated Workorderrouting workorderrouting, BindingResult result, Model model, @RequestParam(value = "action", required = true)String action) {
		 if(!action.equals("Cancel")) {
	            if(result.hasErrors()) {
	                model.addAttribute("workorderroutings", bd.workorderrouting_findAll());
	                model.addAttribute("locations", bd.location_findAll());
	                model.addAttribute("workorders",  bd.workorder_findAll());
	                return "prod/add-prod";
	            }else {
	            bd.workorderrouting_save(workorderrouting);
	           }
	        }
		return "redirect:/workorderroutings/";
	}

	@Override
	@GetMapping("/del/{id}")
	public String deleteWorkorder(@PathVariable("id")Integer id, Model model) {
		Workorderrouting wor = bd.workorderrouting_findById(id);
		bd.workorderrouting_delete(wor);
		return "workorderroutings/update-workorderrouting";
	}

	@Override
	@PostMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		Workorderrouting wor = bd.workorderrouting_findById(id);
		model.addAttribute("workorderrouting",wor);
		model.addAttribute("locations", bd.location_findAll());
		model.addAttribute("workorder", bd.workorder_findAll());
		return "workorderroutings/update-workorderrouting";
	}

	@Override
	@PostMapping("/edit/{id}")
	public String updateWorkorder(@PathVariable("id") Integer id,@RequestParam(value = "action", required = true) String action, 
			@Validated Workorderrouting workorderrouting,
			BindingResult bindingResult, Model model) {
		if(action != null && !action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("locations", bd.location_findAll());
				model.addAttribute("workorders", bd.workorder_findAll());
				return "products/update-product";
			}
			bd.editWorkorderrouting(workorderrouting);
		}
		return "redirect:/workorderroutings/";
	}
	
}
