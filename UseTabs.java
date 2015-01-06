package net.gusto.util;
import java.io.*;
import java.nio.file.*;

public class UseTabs implements Strategy {
	static int countInitialSpaces(String s) {
		int index=0;
		int result=0;
		while (s.charAt(index++)==' ') {
			result++;
		}
		return result;
	}
	public void process (File f) throws IOException {
		BufferedReader in=null;
		try {
			in = new BufferedReader(
				new StringReader(BufferedInputFile.read(f.getAbsolutePath())));
		} catch (FileNotFoundException e) {
			System.out.println ("Could not open " + f.getAbsolutePath());
			throw e;
		} catch (Exception e) {
			try {
				in.close();
			} catch (IOException e2) {
				System.out.println ("in.close() unsuccessful");
				throw e2;
			}
			throw e;				
		} 
		PrintWriter out = null;
		try {
			out = new PrintWriter(
				new BufferedWriter(new FileWriter("out.txt")));
		} catch (IOException e) {
			System.out.println("out.txt:" + e.toString());
			throw e;
		}
		int numTabs, numSpaces;
		String s;
		while((s = in.readLine()) != null ) {
			StringWriter sw = new StringWriter(); 
			if (s.length()>0) {
				numSpaces = countInitialSpaces(s);			
				numTabs = numSpaces/2;
				for (int i=0; i<numTabs; i++) {
					sw.append('\t');
				}
				sw.append(s.substring(numSpaces, s.length()));
			}
			out.println(sw.toString());
		}
		try {
			out.close();
		} catch (Exception e) {
			System.out.println("close output file failed: out.txt" );
			throw e;
		}
		try {
			in.close();
		} catch (Exception e) {
			System.out.println("close input file failed: " + f.getAbsolutePath() );
			throw e;
		}
		java.nio.file.Path sourcePath = FileSystems.getDefault().getPath(".", "out.txt");
		java.nio.file.Path destPath = FileSystems.getDefault().getPath(".", f.getName());
		try {
			Files.move(sourcePath, destPath, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			System.out.println(e + "move file failed : out.txt to " + f.getName());	
			throw e;
		}
	}
}