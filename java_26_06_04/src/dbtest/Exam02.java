package dbtest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

class InsertTest {

    //1.driver확인
    public InsertTest() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("드라이버 등록 성공");
        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 등록 실패");
            e.printStackTrace();
        }
    }

    //2.db에 접속
    public Connection getConnection() {
        Connection conn = null;

        String url = "jdbc:oracle:thin:@//localhost:1521/XE";
        String username = "C##dbexam";
        String password = "m1234";

        try {
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("DB 연결 성공");
        } catch (SQLException e) {
            System.out.println("DB 연결 실패");
            e.printStackTrace();
        }

        return conn;
    }
    //3.db에 요청 4.db 응답처리 5.접속끊기
    public int insertArticle(){
    	Scanner sc = new Scanner(System.in);
    	System.out.println("이름 입력: ");
    	String name = sc.next();
    	System.out.println("나이 입력: ");
    	int age = sc.nextInt();
    	System.out.println("키 입력: ");
    	double height = sc.nextDouble();
    	
    	int su = 0;
    	String sql = "insert into dbtest values (?, ?, ?,sysdate)";
    	
    	Connection conn = getConnection();
    	PreparedStatement pstmt = null;
    	try {
			pstmt = conn.prepareStatement(sql);
			//pstmt 객체 설정
			pstmt.setString(1, name);
			pstmt.setInt(2, age);
			pstmt.setDouble(3, height);
			//db에 요청하고 응답처리
			su = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//접속끊기
			try {
				if(pstmt != null) {pstmt.close();}
				if(conn != null) {conn.close();}
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		}
		return su;
    }
    
}

public class Exam02 {
    public static void main(String[] args) {
    	InsertTest insertTest = new InsertTest();
    	int su = insertTest.insertArticle();
    	if(su>0) {
    		System.out.println(su+"개의 행이 만들어졌습니다.");
    	}
    	else {
    		System.out.println("저장실패");
    	}
    	
    }
}