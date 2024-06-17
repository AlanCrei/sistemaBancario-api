package com.princeton.sistemaBancario.api.exceptionhandler;



import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Problem {

	private Integer status;
	private String title;
	private String detail;
	private String userMessage;
	private List<Object> objects;
	
	@Getter
	@Builder
	public static class Object {
		private String name;
		private String userMessage;	
	}	
}
