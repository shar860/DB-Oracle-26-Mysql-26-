package dbtest.school;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SchoolDAO {
	// 1.드라이버 확인
	public SchoolDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 등록 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 등록 실패");
			e.printStackTrace();
		}
	}
	
	// 2. DB 접속
	public Connection getConnection() {
		Connection conn = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "C##dbexam";
		String password = "m1234";
		try {
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("==DB 접속 성공==");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("==DB 접속 실패==");
		}
		return conn;
	}
	
	// 3. DB 요청 4. DB 응답처리 5. DB 접속종료
	public int insertArticle(SchoolDTO dto) {
		int num = 0;
		String sql = "insert into school values (?,?, ?, sysdate)";
		// DB 접속
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		try {
			// 요청, 응답 객체 생성
			pstmt = conn.prepareStatement(sql);
			// pstmt 객체 설정
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getValue());
			pstmt.setInt(3, dto.getCode());
			// DB에 요청하고 응답처리
			num = pstmt.executeUpdate(); // sql문을 실행하는 코드
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 접속 종료
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return num;
	}
	// 3. DB 요청 4. DB 응답처리 5. DB 접속종료
	public List<SchoolDTO> selectArticle() {
		List<SchoolDTO> list = new ArrayList<SchoolDTO>();
		String sql = "select * from school";
		// DB 접속
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 요청, 응답 객체 생성
			pstmt = conn.prepareStatement(sql);
			// DB에 요청하고 응답처리
			rs = pstmt.executeQuery(); // sql문을 실행하는 코드 // read일 떄만 executeQuery()
			// 응답결과 처리
			while (rs.next()) {
				String name = rs.getString("name");
	            String value = rs.getString("value");
	            int code = rs.getInt("code");
	            SchoolDTO dto = new SchoolDTO(name, value, code);
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 접속 종료
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	// 3. DB 요청 4. DB 응답처리 5. DB 접속종료
	public List<SchoolDTO> manualSelectArticle(SchoolDTO dto) {
	    List<SchoolDTO> list = new ArrayList<SchoolDTO>();
	    String sql = "select * from school where name like ?";
	    
	    Connection conn = getConnection();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try {
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, "%" + dto.getName() + "%");
	        rs = pstmt.executeQuery(); 
	        
	        while (rs.next()) {
	            String name = rs.getString("name");
	            String value = rs.getString("value");
	            int code = rs.getInt("code");
	            SchoolDTO rowDto = new SchoolDTO(name, value, code);
	            list.add(rowDto);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (pstmt != null) pstmt.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return list;
	}
		// 3. DB 요청 4. DB 응답처리 5. DB 접속종료
		public int deleteArticle(String name) {
			String sql = "delete school where name=?";
			// DB 접속
			Connection conn = getConnection();
			PreparedStatement pstmt = null;
			int num = 0;
			try {
				// 요청, 응답 객체 생성
				pstmt = conn.prepareStatement(sql);
				// pstmt 객체 설정
				pstmt.setString(1, name);
				// DB에 요청하고 응답처리
				num = pstmt.executeUpdate(); // sql문을 실행하는 코드
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				// 접속 종료
				try {
					if (pstmt != null) {
						pstmt.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return num;
		
		}
}
