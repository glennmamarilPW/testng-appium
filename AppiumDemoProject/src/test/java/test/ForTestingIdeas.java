package test;

public class ForTestingIdeas {
	public static void main(String args[]){
		String expression = "(1+2)=";
		char character;
		int index=0;
		do {
			character = expression.charAt(index);
			System.out.println(character+" is digit? "+Character.isDigit(character));
			index++;
		}while(character!='=');
	}
}
