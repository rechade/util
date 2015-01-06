package net.gusto.util;
import java.io.*;
import java.nio.file.*;
// import static java.nio.file.StandardCopyOption.*;

public class ProcessFiles {
	
	private String ext="*";
	private Strategy strategy;
	private File file;
	public ProcessFiles (Strategy s, File f) {
		strategy = s;
		file =f;
	}
	public ProcessFiles (Strategy s, File f, String extension) {
		strategy = s;
		file =f;
		ext = extension;
	}
	public ProcessFiles (Strategy s, String extension) {
		strategy = s;
		ext = extension;
	}	
	public void start() {
		if (file.isDirectory()) {			
			processDirectory(file);
		} else {
			process();
		}
	}
	void processDirectory (File root) {		
		try {
			String regex;
			if (ext == "*") {
				regex = ".*";				
			} else {				
				regex = ".*\\." + ext;
			}
			for (File file : Directory.local(root.getAbsolutePath(), regex)) {
				strategy.process(file.getCanonicalFile());
			}			
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public void process () {
		try {
			strategy.process(file);
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
	}
	
}