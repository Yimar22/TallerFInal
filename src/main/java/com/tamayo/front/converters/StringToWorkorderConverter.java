package com.tamayo.front.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

import com.tamayo.front.businessdelegate.BusinessDelegate;
import com.tamayo.front.model.Workorder;

@WritingConverter
@Component
public class StringToWorkorderConverter implements Converter<String, Workorder>{
	
	@Autowired
	private BusinessDelegate bd;

	@Override
	public Workorder convert(String source) {
		return bd.workorder_get(Integer.parseInt(source));
	}
	
	
}
