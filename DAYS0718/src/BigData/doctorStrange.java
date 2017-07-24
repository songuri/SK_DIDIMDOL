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



public class doctorStrange {
		public static void main(String[] args){
			String feel[] = { "��庥ó" , "�׼�" , "��Ÿ��", "����", "����",
							"�θǽ�", "������", "SF", "����","�ڹ�" };
			//��ȭ �帣
			String comments = "<b>���� ��Ʈ������</b>,�������� ����� <��ȭ���� 474��° �̾߱�> ����: Doctor Strange(2016) �帣: ��庥ó,����,SF ��Ÿ��: 115�� ���� ���: �ϻ� �Ե��ó׸� ����: ���� ������ �⿬: ���׵�Ʈ �Ĺ���ġ, ����ÿ..."+
					"���� �׷� �౸���� �̸��ϰ� ���� �Ұ��� �뷡�� ���ؼ� ������� �ϰڽ��ϴ�. ������ ���� ��ȭ, ���� �Ұ��� �뷡�� ��ȭ <b>���� ��Ʈ������</b> ost �뷡�����Դϴ� ���� �������� ���� ��ȭ�ε��� ���� �� ������ ���̶���Ʈ ��ȸ�� �ߴٰ� �մϴ�..."+
					"���Ѱ����� ���� ���￡ �ö��� �� ģ���� ���� ���� ���� ��ȭ <b>���� ��Ʈ������</b>�� ���ԵǾ��µ���! �帣: ��Ÿ��,��ü Ÿ�Ӷ����� ��ҳ��̷� �뵿�ܰ� �Ǿ��ֳ� �ߴ�?���� <b>���� ��Ʈ������</b>�� �����ϴ� ������ �������� ��ҳ��̿� ��ұ�..."+
					"���� �ó׸�ƽ ���Ϲ��� ���� �ְ�, �ְ��� ������ ����������� <b>���� ��Ʈ������</b>�� ���� �����߽��ϴ�. �帣: �׼�,���� ��ȭ�� ���� �е��� �ƽð����� �̹� '<b>���� ��Ʈ������</b>'���� ���� ���� ��ȭ���� �ܰ�� �⿬�ϴ� �ƿ�� �ƴ� ������..."+
					"���ƿԾ��~!! ������� ����ι� �ϸ� ����ȭ��� ������ ���ܳ��� ����~ �̹��� �����ϴ� ��ȭ <b>���ͽ�Ʈ������</b>~~ �帣: ��Ÿ��,���� ��ȭ <b>���ͽ�Ʈ������</b>�� 10/26 �����߽��ϴ�. �������� ������ �޴� �̹� ��ȭ�� ���ΰ��� <b>���ͽ�Ʈ������</b>��...";
			
			String replace_String[] = {"[A-Za-z]","[0-9]","[<]|[>]|[(]|[)]","[/] | [?]","[��]","[:]|[,]|[.]","[��]"};
			
			int cnt = 0;
			
			/*
			 * Replace �� ReplaceAll�� ������
			 * ReplaceAll�� ���Խ����� ǥ���ؼ� ���ڸ� �ش�.
			 * ���� ���Խ����� ��Ÿ������ �Ϳ� ���ؼ� ���ڿ��� ó���Ѵ�.
			 * Replace�� ���Խ��� �ƴ� �ܼ� ���ڿ� ���ؼ� ó���� �Ѵ�.
			 * 
			 */
			
			//������ , Ư������ , ���� ����ó��
			for(int i = 0 ; i < replace_String.length ; i ++)
				comments = comments.replaceAll(replace_String[i], " ");
			
			
			//System.out.println(comments);
			//������, Ư������ , ���� ����ó��
			
			for(int i = 0 ; i < feel.length ; i++){
				Pattern p = Pattern.compile(feel[i]);
				Matcher m = p.matcher(comments);
			
				while(m.find()){
					cnt++;
				}
				System.out.println(feel[i] + " :" + cnt +" ��");
				cnt = 0;
			}
			
		}
		//��Ƽ�� ������ �����м��̴� !
}
