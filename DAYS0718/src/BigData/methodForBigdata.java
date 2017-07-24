package BigData;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * java.util.regex ��Ű���� ���ԵǾ� �ִ�.
 * ���� ǥ�������� �����Ǿ� �ִ����� ���ؼ� �����ؾ� �ϴ� ���!
 * ���� ��� �̸��� ����, ��ȭ��ȣ ����, �ֹε�Ϲ�ȣ ���ĵ� �̷��͵��� �����ϱ� ���ؼ� ��� �ȴ�.
 * 
 * 
 * 
 * Pattern Ŭ���� : ���� ǥ�������� ���ڿ��� �����ϴ� ���!
 * 		1> compile() �޼ҵ�
 * 			-static Pattern compile(String regex)
 * 			-��� : Compiles the given regular expression into a pattern.
 * 
 * 		2> matcher() �޼ҵ�
 * 			-public Matcher matcher(CharSequence input)
 * 			-���: Creates a matcher that will match the given input against this pattern.
 * 
 * 
 * Matcher Ŭ����
 * 		1> find() �޼ҵ�
 * 			-boolean find()
 * 			-��� : Attempts to find the next subsequence of the input sequence that matches the pattern
 * 
 * 		2> group() �޼ҵ�
 * 			-String group()
 * 			-��� : Return the input subsequence matchered by the previous match.
 */






public class methodForBigdata {
	public static void main(String[] args){
		String str = "����� �ð� �ǰ� �̺��� ���� �Ǵ°�."+
					"����� �ð��� ��ó�� �Ƹ����, ����� ���� �ڸ��� ��ó��"+
					"������....";
		
		/*	���� ���ڿ��� ������ ��� �ܾ � �� �ִ��� Ȯ���غ���! */
		
		Pattern p = Pattern.compile("���");
		Matcher m = p.matcher(str);
		
		int cnt = 0 ;
		String word = null;
		while(m.find()){
			word = m.group();
			cnt ++;
		}
		System.out.println(true + "=> " + "���� ��ġ!!");
		System.out.println("word" + word+" "+ cnt +"��");
		
		
	}

}
