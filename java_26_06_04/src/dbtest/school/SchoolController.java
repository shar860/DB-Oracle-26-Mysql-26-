package dbtest.school;

import java.util.List;
import java.util.Scanner;

public class SchoolController {
	Scanner sc = new Scanner(System.in);
	SchoolDAO dao = new SchoolDAO();
	SchoolDTO dto = new SchoolDTO();
	int key;
	public void main_menu() {
		System.out.println("**************");
        System.out.println("  관리   ");
        System.out.println("**************");
        System.out.println("   1. 입력   ");
        System.out.println("   2. 검색   ");
        System.out.println("   3. 삭제   ");
        System.out.println("   4. 종료   ");
        System.out.println("**************");
        System.out.print("번호선택: ");
        key = sc.nextInt();
        switch(key) {
        case 1:
        	insert_menu();
        	break;
        case 2:
        	select_menu();
        	break;
        case 3:
        	delete_menu();
        	break;
        case 4:
        	System.out.println("프로그램을 종료합니다.");
        	System.exit(0);
        	break;
        default:
        	System.out.println("번호를 잘못 입력했습니다.");
        	main_menu();
        	break;
        }
	}
	public void insert_menu() {
		System.out.println("**************");
        System.out.println("  입력   ");
        System.out.println("**************");
        System.out.println("   1. 학생   ");
        System.out.println("   2. 교수   ");
        System.out.println("   3. 관리자   ");
        System.out.println("   4. 이전메뉴   ");
        System.out.println("**************");
        System.out.print("번호선택: ");
        key = sc.nextInt();
        switch(key) {
        case 1:
        	System.out.println("이름 : ");
        	dto.setName(sc.next());
        	System.out.println("학번 : ");
        	dto.setValue(sc.next());
        	dto.setCode(key);
        	int num = dao.insertArticle(dto);
    		if (num > 0)
    			System.out.println("저장 성공");
    		else
    			System.out.println("저장 실패");
    		System.out.println("=================");
    		insert_menu();
        	break;
        case 2:
        	System.out.println("이름 : ");
        	dto.setName(sc.next());
        	System.out.println("과목 : ");
        	dto.setValue(sc.next());
        	dto.setCode(key);
        	num = dao.insertArticle(dto);
    		if (num > 0)
    			System.out.println("저장 성공");
    		else
    			System.out.println("저장 실패");
    		System.out.println("=================");
    		insert_menu();
        	break;
        case 3:
        	System.out.println("이름 : ");
        	dto.setName(sc.next());
        	System.out.println("부서 : ");
        	dto.setValue(sc.next());
        	dto.setCode(key);
        	num = dao.insertArticle(dto);
    		if (num > 0)
    			System.out.println("저장 성공");
    		else
    			System.out.println("저장 실패");
    		System.out.println("=================");
    		insert_menu();
        	break;
        	
        case 4:
        	main_menu();
        	break;
        default:
        	System.out.println("번호를 잘못 입력했습니다.");
        	insert_menu();
        	break;
        }
	}
	public void select_menu() {
		System.out.println("*********************");
        System.out.println("         검색         ");
        System.out.println("*********************");
        System.out.println("    1.  이름검색        ");
        System.out.println("    2.  전체검색        ");
        System.out.println("    3.  이전메뉴        ");
        System.out.println("*********************");
        System.out.print("번호선택 : ");
        key = sc.nextInt();
        switch(key) {
        case 1: {
            System.out.println("검색할 이름 : ");
            dto.setName(sc.next());
            List<SchoolDTO> list = dao.manualSelectArticle(dto); 
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
                System.out.println("-------------");
            }
            select_menu();
            break;
        }
        case 2: {
        	List<SchoolDTO> list = dao.selectArticle();
    		for (int i = 0; i < list.size(); i++) {
    			System.out.println(list.get(i));
    			System.out.println("-------------");
    		}
    		select_menu();
        	break;
        }
        case 3:
        	main_menu();
        	break;
        default:
        	System.out.println("번호를 잘못 입력했습니다.");
        	select_menu();
        	break;
        }
	}
	
	public void delete_menu() {
		System.out.print("삭제를 원하는 이름 입력 : ");
		int num = dao.deleteArticle(sc.next());
		if (num > 0) {
			System.out.println(num + "개 행이 삭제되었습니다.");
		} else {
			System.out.println("삭제 실패");
		}
		main_menu();
	}
}


