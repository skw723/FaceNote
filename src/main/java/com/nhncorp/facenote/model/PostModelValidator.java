package com.nhncorp.facenote.model;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PostModelValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return PostModel.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		PostModel postModel = (PostModel) target;
		
		if(getStringByte(postModel.getPost_cont()) > 1000) {
			errors.rejectValue("post_cont", "byte");
		}
	}
	
	private int getStringByte(String str) {
		char[] strArr = str.toCharArray();
		int strByte = 0;
		for(char ch : strArr) {
			if(ch > 128) {
				strByte += 3;
			} else {
				strByte++;
			}
		}
		return strByte;
	}
}
