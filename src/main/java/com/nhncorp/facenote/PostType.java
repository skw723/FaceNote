package com.nhncorp.facenote;

public enum PostType {
	TEXT(1, "TEXT"),IMAGE(2,"IMAGE");
	
	private final int typeCode;
	private final String typeStr;
	
	PostType(int postCode, String postStr) {
		this.typeCode = postCode;
		this.typeStr = postStr;
	}

	@Override
	public String toString() {
		return String.valueOf(typeCode);
	}
	
	public int getTypeCode() {
		return typeCode;
	}
	
	public static PostType getPostType(int typeCode) {
		PostType[] types = PostType.values();
		
		for(PostType type : types) { 
			if(type.getTypeCode() == typeCode) {
				return type;
			}
		}
		
		return null;
	}
	
	public String getTypeStr() {
		return typeStr;
	}
}
