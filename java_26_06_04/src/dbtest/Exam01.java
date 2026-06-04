package dbtest;

public class Exam01 {
	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 등록 성공");
		} catch(ClassNotFoundException e) {
			System.out.println("드라이버 등록 실패");
			e.printStackTrace();
		}
	}
}
