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



public class doctorStrange {
		public static void main(String[] args){
			String feel[] = { "어드벤처" , "액션" , "판타지", "경이", "모험",
							"로맨스", "스릴러", "SF", "공포","코믹" };
			//영화 장르
			String comments = "<b>닥터 스트레인지</b>,마법계의 어벤져스 <영화리뷰 474번째 이야기> 영제: Doctor Strange(2016) 장르: 어드벤처,경이,SF 런타임: 115분 관람 장소: 일산 롯데시네마 감독: 스콧 데릭슨 출연: 베네딕트 컴버베치, 레이첼..."+
					"ㅎㅎ 그럼 축구얘기는 이만하고 오늘 소개할 노래에 대해서 설명토록 하겠습니다. 모험을 느낀 영화, 오늘 소개할 노래는 영화 <b>닥터 스트레인지</b> ost 노래모음입니다 아직 개봉되지 않은 영화인데요 어젠 가 그젠가 하이라이트 상영회를 했다고 합니다..."+
					"내한공연을 보러 서울에 올라갔을 때 친구와 만나 마블 신작 영화 <b>닥터 스트레인지</b>를 보게되었는데요! 장르: 판타지,대체 타임라인이 쥐불놀이로 대동단결 되어있나 했더?ㅋㅋ <b>닥터 스트레인지</b>에 등장하는 일종의 마법진이 쥐불놀이와 닮았기..."+
					"마블 시네마틱 유니버스 현존 최고, 최강의 마법사 슈퍼히어로인 <b>닥터 스트레인지</b>가 드디어 등장했습니다. 장르: 액션,경이 영화를 보신 분들은 아시겠지만 이번 '<b>닥터 스트레인지</b>'에는 기존 마블 영화에서 단골로 출연하던 아우디가 아닌 람보르..."+
					"돌아왔어요~!! 어느샌가 히어로물 하면 마블영화라는 공식이 생겨나고 있죠~ 이번에 개봉하는 영화 <b>닥터스트레인지</b>~~ 장르: 판타지,경이 영화 <b>닥터스트레인지</b>는 10/26 개봉했습니다. 전세계의 집중을 받는 이번 영화의 주인공은 <b>닥터스트레인지</b>란...";
			
			String replace_String[] = {"[A-Za-z]","[0-9]","[<]|[>]|[(]|[)]","[/] | [?]","[ㅎ]","[:]|[,]|[.]","[ㅋ]"};
			
			int cnt = 0;
			
			/*
			 * Replace 와 ReplaceAll의 차이점
			 * ReplaceAll은 정규식으로 표현해서 인자를 준다.
			 * 따라서 정규식으로 나타내어진 것에 대해서 문자열을 처리한다.
			 * Replace는 정규식이 아닌 단순 문자에 대해서 처리를 한다.
			 * 
			 */
			
			//영문자 , 특수문자 , 숫자 삭제처리
			for(int i = 0 ; i < replace_String.length ; i ++)
				comments = comments.replaceAll(replace_String[i], " ");
			
			
			//System.out.println(comments);
			//영문자, 특수문자 , 숫자 삭제처리
			
			for(int i = 0 ; i < feel.length ; i++){
				Pattern p = Pattern.compile(feel[i]);
				Matcher m = p.matcher(comments);
			
				while(m.find()){
					cnt++;
				}
				System.out.println(feel[i] + " :" + cnt +" 개");
				cnt = 0;
			}
			
		}
		//빅데티어 다음은 예측분석이다 !
}
