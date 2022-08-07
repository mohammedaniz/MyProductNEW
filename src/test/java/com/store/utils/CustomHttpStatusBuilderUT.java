package com.store.utils;



import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.store.models.Response;

@RunWith(SpringRunner.class)
public class CustomHttpStatusBuilderUT {
	
	
	@Test
	public void shouldTestbuildResponseEntity_whenStatus100IsPassed_receiveContinue() {
		ResponseEntity<Object> response = CustomHttpStatusBuilder.buildResponseEntity(new Response(null, 100, null), null);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONTINUE);
	}

}
