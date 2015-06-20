package com.nhncorp.facenote.utils;

import java.io.File;
import java.io.IOException;

public class CustomFileUtils {
	public static boolean createFile(File file) throws IOException  {
		File dir = file.getParentFile();
		if(!dir.exists()) {
			dir.mkdirs();
		}

		if(!file.exists()) {
			file.createNewFile();		
			return true;
		}
		return false;
	}
}
