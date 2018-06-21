package com.example.vedantiladda.quiz.dto;

public class Category{
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
			"Category{" + 
			"categoryName = '" + categoryName + '\'' + 
			",categoryId = '" + categoryId + '\'' + 
			"}";
		}
}
