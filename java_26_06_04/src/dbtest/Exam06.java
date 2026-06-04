package dbtest;

import java.util.List;
import java.util.Scanner;

public class Exam06 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		DbtestDAO dao = new DbtestDAO();
		
		
		DbtestDTO dto = new DbtestDTO();
		System.out.println("이름 입력: ");
    	dto.setName(sc.next());
    	System.out.println("나이 입력: ");
    	dto.setAge(sc.nextInt());
    	System.out.println("키 입력: ");
    	dto.setHeight(sc.nextDouble());
    	
    	
    	int su = dao.insertArticle(dto);
    	if(su>0) {
    		System.out.println("저장 성공");
    	}
    	else {
    		System.out.println("저장 실패");
    	}
    	System.out.println("================");
    	
    	
    	//select
    	List<DbtestDTO> list = dao.selectArticle();
    	for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
			System.out.println("-----------------------------");
		}
    	
    	//update
    	System.out.println("나이를 한살 올리기 위해 수정할 이름 입력: ");
    	String name = sc.next();
    	dao.updateArticle(name);
    	if(su>0) {
    		System.out.println(su+"개의 행이 수정되었습니다.");
    	}
    	else {
    		System.out.println("수정 실패");
    	}
    	System.out.println("-----------------------------");
    	
    	//delete
    	System.out.println("삭제할 이름 입력: ");
    	name = sc.next();
    	su = dao.deleteArticle(name);
    	if(su>0) {
    		System.out.println(su+"개의 행이 삭제되었습니다.");
    	}
    	else {
    		System.out.println("삭제 실패");
    	}
    	
    	//select
    	list = dao.selectArticle();
    	for(int i = 0; i<list.size();i++) {
    		System.out.println(list.get(i));
    		System.out.println("-----------------------------");
    	}
	}
}
