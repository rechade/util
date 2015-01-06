package net.gusto.util;
import java.io.*;

public class FixTabs {	
	
	public static void main(String[] args) {	
		ProcessFiles pf = null;
		if (args.length == 0) {				
			System.out.println("Processing all .java files in current directory");
			pf = new ProcessFiles(new UseTabs(),new File("."),"java");
		} else if (args.length == 1) {
			System.out.println("Processing " + args[0]);
			pf = new ProcessFiles(new UseTabs(),new File(args[0]),"java");		
		}
		try {
			pf.start();			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}