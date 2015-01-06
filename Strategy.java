package net.gusto.util;
import java.io.*;

public interface Strategy {
	void process (File f) throws IOException;		
}