package net.gusto.util;
import java.io.*;
class CheckDirectory {
	public static void main (String[] args) {
		File[] files = new File(".").listFiles();
		System.out.println(files);
	}
}