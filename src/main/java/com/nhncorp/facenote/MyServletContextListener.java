package com.nhncorp.facenote;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;

import javax.servlet.ServletContextEvent;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.ContextLoaderListener;

import com.nhncorp.facenote.utils.CustomFileUtils;

public class MyServletContextListener extends ContextLoaderListener {
	@Value("#{common['image.filepath']}")
	private String imageFilePath;

	@Override
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		
		Properties prop = new Properties();
		FileInputStream propFis = null;
		ClassLoader cl;
		
        cl = Thread.currentThread().getContextClassLoader();
        if( cl == null ) {
        	cl = ClassLoader.getSystemClassLoader();        	
        }
                
        //properties 세팅 전이라 직접 읽어옴
        URL url = cl.getResource("properties/common.properties");
		try {
			propFis = new FileInputStream(url.getFile());
			prop.load(propFis);
			File directory = new File(prop.getProperty("image.filepath"));
			CustomFileUtils.createDirectory(directory);
		} catch (Exception e) {
			
		}
	}
}
