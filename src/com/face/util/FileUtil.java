package com.face.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtil {
	public static void saveFile(String base64Str,File storeFile ) throws FileNotFoundException{
		  InputStream instream = new ByteArrayInputStream(base64Str.getBytes());
		FileOutputStream output = new FileOutputStream(storeFile);
		try {
			byte b[] = new byte[1024];
			int j = 0;
			while ((j = instream.read(b)) != -1) {
				output.write(b, 0, j);
			}
			output.flush();
			output.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			// Closing the input stream will trigger connection release
			try {
				instream.close();
			} catch (Exception ignore) {
			}
		}
		
	}

}
