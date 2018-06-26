package com.example.vedantiladda.quiz.dto;

import java.io.Serializable;

public class CategoryDTO implements Serializable{
	private String categoryName;
	private String categoryId;

	public void setCategoryName(String categoryName){
		this.categoryName = categoryName;
	}

	public String getCategoryName(){
		return categoryName;
	}

	public void setCategoryId(String categoryId){
		this.categoryId = categoryId;
	}

	public String getCategoryId(){
		return categoryId;
	}

	@Override
 	public String toString(){
		return 
			"CategoryDTO{" +
			"categoryName = '" + categoryName + '\'' + 
			",categoryId = '" + categoryId + '\'' + 
			"}";
		}
}
