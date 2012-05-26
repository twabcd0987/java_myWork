
public class test {
	public test(){
		
	}
	public void testA(){
		testB();
	}
	public void testB(){
		System.out.println("B");
	}
	public static void main(String[] args){
		new test();
}
}