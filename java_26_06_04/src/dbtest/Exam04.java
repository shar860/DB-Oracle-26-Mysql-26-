package dbtest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class SelectTest{
	//1.driver확인
	public SelectTest() {
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
    public List<DbtestDTO> selectArticle() {
    	List<DbtestDTO> list = new ArrayList<DbtestDTO>();
    	String sql = "select * from dbtest";
    	//db에 접속
    	Connection conn = getConnection();
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	try {
    		// 요청, 응답 객체 생성
			pstmt = conn.prepareStatement(sql);
			//db에 요청하고 응답처리
			rs = pstmt.executeQuery(); // read일 때만 executeQuery()
			//응답결과 처리
			while(rs.next()) {
				String name = rs.getString("name");
				int age = rs.getInt("age");
				double height = rs.getDouble("height");
				String logtime = rs.getString("logtime");
				DbtestDTO dto = new DbtestDTO(name,age,height,logtime);
				list.add(dto);
			}
			
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
		return list;
    	
    }
    
}

class UpdateTest{
	public UpdateTest(){
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
    public int updateArticle() {
    	Scanner sc = new Scanner(System.in);
    	System.out.println("이름 입력: ");
    	String name = sc.next();
    	
    	int su = 0;
    	String sql = "update dbtest set age=age+1 where name=?";
    	
    	Connection conn = getConnection();
    	PreparedStatement pstmt = null;
    	try {
			pstmt = conn.prepareStatement(sql);
			//pstmt 객체 설정
			pstmt.setString(1, name);
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

public class Exam04 {
	public static void main(String[] args) {
		UpdateTest updateTest = new UpdateTest();
		int su = updateTest.updateArticle();
		
		SelectTest selectTest = new SelectTest();
		List<DbtestDTO> list = selectTest.selectArticle();
		for(int i=0; i<list.size(); i++) {
			DbtestDTO dto = list.get(i);
			System.out.println(dto);
		}
	}
}
