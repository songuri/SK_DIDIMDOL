package BigData;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * java.util.regex 패키지에 포함되어 있다.
 * 정규 표현식으로 구현되어 있는지에 대해서 검증해야 하는 경우!
 * 예를 들면 이메일 형식, 전화번호 형식, 주민등록번호 형식등 이런것들을 검증하기 위해서 사용 된다.
 * 
 * 
 * 
 * Pattern 클래스 : 정규 표현식으로 문자열을 검증하는 방법!
 * 		1> compile() 메소드
 * 			-static Pattern compile(String regex)
 * 			-기능 : Compiles the given regular expression into a pattern.
 * 
 * 		2> matcher() 메소드
 * 			-public Matcher matcher(CharSequence input)
 * 			-기능: Creates a matcher that will match the given input against this pattern.
 * 
 * 
 * Matcher 클래스
 * 		1> find() 메소드
 * 			-boolean find()
 * 			-기능 : Attempts to find the next subsequence of the input sequence that matches the pattern
 * 
 * 		2> group() 메소드
 * 			-String group()
 * 			-기능 : Return the input subsequence matchered by the previous match.
 */






public class methodForBigdata {
	public static void main(String[] args){
		String str = "사랑은 시가 되고 이별은 별이 되는것."+
					"사랑은 시간을 시처럼 아름답게, 사랑이 떠난 자리를 별처럼"+
					"빛나게....";
		
		/*	위에 문자열을 가지고 어떠한 단어가 몇변 들어가 있는지 확인해보자! */
		
		Pattern p = Pattern.compile("사랑");
		Matcher m = p.matcher(str);
		
		int cnt = 0 ;
		String word = null;
		while(m.find()){
			word = m.group();
			cnt ++;
		}
		System.out.println(true + "=> " + "패턴 일치!!");
		System.out.println("word" + word+" "+ cnt +"개");
		
		
	}

}
