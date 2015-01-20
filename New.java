// TIJ generics ex11 p451
package net.gusto.util;
import java.util.*;
class New {
	 static <T> List<T> list() {
	 	 return new ArrayList<T>();
	 }
	 static <T,U> HashMap<T,U> hashMap() {
	 	 return new HashMap<T,U>();
	 }
	 
	 public static void main (String[] args) {
	 	 HashMap<Integer,String> hmis = New.hashMap();
	 	 hmis.put(2,"asdf");
	 	 //hmis.put(2,3);
	 	 HashMap hmss = New.<String,String>hashMap();
	 	 hmss.put("asdf","qwert");
	 }
}


