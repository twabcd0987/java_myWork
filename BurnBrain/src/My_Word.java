import java.io.*;
import java.util.*;

public class My_Word {
	
	class Display{
		private String[] word;      
		private int times;	
		int i;
		public void test(){
			System.out.println("haha");
		}
		Scanner in;
		public Display(String[] word){
			this.word=word;
			this.times=1;
		}
		public int getTimes() {
			return times;
		}
		public String getWord(){
			return word[i];
		}
		public void addTimes(){
			times++;
		}
		public String toString(){
			return word+"  "+times+'\n';
		}
		public void compareWord(){
			System.out.println("Begin");
			String input = in.nextLine();
			word = input.split("\\s+");
			System.out.println("sum = " + word.length);
			int i = 0;
		}
	}
	public static void main(String[] args){
		System.out.println("Please input here:");
		String [] word=null;
		My_Word my=new My_Word();
		Display dis=my.new Display(word);
		dis.test();
	}
	

	
}