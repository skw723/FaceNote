package com.nhncorp.facenote.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class CustomFileUtils {
	public static boolean createDirectory(File directory) throws IOException  {
		if(directory.exists()) {
			return false;
		}
		/*if(!dir.exists()) {
			dir.mkdirs();
		}
		
		if(!file.exists()) {
			file.createNewFile();		
			return true;
		}
		return false;*/
		FileUtils.forceMkdir(directory);
		return true;
	}
}
