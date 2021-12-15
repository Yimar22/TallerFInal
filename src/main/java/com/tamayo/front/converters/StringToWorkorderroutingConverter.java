package com.tamayo.front.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

import com.tamayo.front.businessdelegate.BusinessDelegate;
import com.tamayo.front.model.Workorderrouting;

@WritingConverter
@Component
public class StringToWorkorderroutingConverter implements Converter<String, Workorderrouting>{
	
	@Autowired
	private BusinessDelegate bd;
	
	@Override
	public Workorderrouting convert(String source) {
		return null;
	}
	
}
