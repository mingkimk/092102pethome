package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

//오라클 데이터 베이스에 연결하고 select, insert, update, delete 작업을 실행해주는 클래스입니다.
public class BoardDAO {
	private Statement stmt;
	private ResultSet rs;
	private Connection conn = null;
	Context initContext;
	Context envContext;
	DataSource ds;

	private static BoardDAO bDAO;

	private BoardDAO() {

	}

	public void insert(BoardDTO o) {
		BoardDTO b = o;
		Date today = new Date();

		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");

		if (connect()) {
			try {
				String sql = "insert into board values(board_no.NEXTVAL,?,?,?,?,?,?)";
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setString(1, b.getInterest());
				psmt.setString(2, b.getTitle());
				psmt.setString(3, b.getId());
				psmt.setString(4, b.getContents());
				psmt.setString(5, date.format(today));
				psmt.setInt(6, 0);
				psmt.executeUpdate();
			} catch (SQLException e) {
			}
		} else {
		}

	}

	public void update(BoardDTO o) {
		BoardDTO b = o;
		Date today = new Date();

		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");

		if (connect()) {
			try {
				String sql = "update board set interest =?, title =?, contents = ?, dt =?, cnt = 0 where no = ?";
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setString(1, b.getInterest());
				psmt.setString(2, b.getTitle());
				psmt.setString(3, b.getContents());
				psmt.setString(4, date.format(today));
				psmt.setInt(5, b.getNo());
				psmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
		}

	}

	public void delete(int no) {
		String sql = "delete FROM board where no = ?";
		if (connect()) {
			try {
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setInt(1, no);
				psmt.executeUpdate();
			} catch (SQLException e) {

			}
		} else {
		}
	}

	public ArrayList<BoardDTO> getAll() {
		ArrayList<BoardDTO> list = new ArrayList<>();
		String sql = "SELECT * FROM board";
		if (connect()) {
			try {
				stmt = conn.createStatement();
				if (stmt != null) {
					rs = stmt.executeQuery(sql);
					while (rs.next()) {
						BoardDTO board = new BoardDTO();
						board.setNo(rs.getInt("no"));
						board.setInterest(rs.getString("interest"));
						board.setTitle(rs.getString("title"));
						board.setId(rs.getString("id"));
						board.setContents(rs.getString("contents"));
						board.setDt(rs.getString("dt"));
						board.setCnt(rs.getInt("cnt"));

						list.add(board);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else {
			System.out.println("DB연결 실패");
			System.exit(0);
		}

		return list;
	}

	public BoardDTO getOne(int no) {
		BoardDTO b = new BoardDTO();

		String sql = "SELECT * FROM board where no = ?";
		if (connect()) {
			try {
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setInt(1, no);
				rs = psmt.executeQuery();
				while (rs.next()) {
					b.setNo(rs.getInt("no"));
					b.setInterest(rs.getString("interest"));
					b.setTitle(rs.getString("title"));
					b.setId(rs.getString("id"));
					b.setContents(rs.getString("contents"));
					b.setDt(rs.getString("dt"));
					b.setCnt(rs.getInt("cnt"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else {
			System.out.println("DB연결 실패");
			System.exit(0);
		}

		return b;
	}

	public static BoardDAO getInstance() {
		if (bDAO == null) {
			bDAO = new BoardDAO();
		}
		return bDAO;
	}

	private boolean connect() {
		boolean result = true;
		try {
			if (conn == null) {
				initContext = new InitialContext();
				envContext = (Context) initContext.lookup("java:/comp/env");
				ds = (DataSource) envContext.lookup("jdbc/myoracle");
				conn = ds.getConnection();
			}

			return result;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
}