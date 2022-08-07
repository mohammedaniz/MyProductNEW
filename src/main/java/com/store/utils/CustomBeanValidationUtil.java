package com.store.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.store.models.Response;

@Component
public class CustomBeanValidationUtil {
	
	public Response beanValidation(BindingResult errors, String message) {
		Map<String,String> mapErrors = new HashMap<>();
		if(errors.hasErrors()) {
			List<FieldError> errz = errors.getFieldErrors();
			errz.forEach(error -> {
				mapErrors.put(error.getField(), error.getDefaultMessage());
			});
			return new Response(mapErrors, 404, message);
		}else {
			return new Response(null, 200, null);
		}
		
	}

}
