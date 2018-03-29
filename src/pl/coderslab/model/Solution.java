package pl.coderslab.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Solution {
	private int id;
	private Date created;
	private Date updated;
	private String description;
	private int excercise_id;
	private int user_id;
	
	public Solution() {
		this.created = new Date(System.currentTimeMillis());
	}
	
	public void saveToDB(Connection conn) throws SQLException {
		if (this.id == 0) {
			String sql = "INSERT INTO Solutions(created, updated, description, excercise_id, user_id) VALUES (?, ?, ?, ?, ?)";
			String generatedColumns[] = { "ID" };
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql, generatedColumns);
			preparedStatement.setDate(1, this.created);
			preparedStatement.setDate(2, this.updated);
			preparedStatement.setString(3, this.description);
			preparedStatement.setInt(4, this.excercise_id);
			preparedStatement.setInt(5, this.user_id);
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
				this.id = rs.getInt(1);
			}
		}else {
			System.out.println("Możesz napisać Update");
		}
	}
	
	static public Solution loadSolutionById(Connection conn, int id) throws SQLException {
		String sql = "SELECT * FROM Solutions where id=?";
		PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			Solution loadedSolution = new Solution();
			loadedSolution.id = resultSet.getInt("id");
			loadedSolution.created = resultSet.getDate("created");
			loadedSolution.updated = resultSet.getDate("updated");
			loadedSolution.description = resultSet.getString("description");
			loadedSolution.excercise_id = resultSet.getInt("excercise_id");
			loadedSolution.user_id = resultSet.getInt("user_id");
			return loadedSolution;
		}
		return null;
	}
	
	static public Solution[] loadAll(Connection conn) throws SQLException {
		String sql = "SELECT * FROM Solutions";
		Solution[] uArray = loadSolutions(conn, sql);
		return uArray;
	}

	private static Solution[] loadSolutions(Connection conn, String sql) throws SQLException {
		ArrayList<Solution> solutions = new ArrayList<Solution>();
		PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Solution loadedSolution = new Solution();
			loadedSolution.id = resultSet.getInt("id");
			loadedSolution.created = resultSet.getDate("created");
			loadedSolution.updated = resultSet.getDate("updated");
			loadedSolution.description = resultSet.getString("description");
			loadedSolution.excercise_id = resultSet.getInt("excercise_id");
			loadedSolution.user_id = resultSet.getInt("user_id");
			solutions.add(loadedSolution);
		}
		Solution[] uArray = new Solution[solutions.size()];
		uArray = solutions.toArray(uArray);
		return uArray;
	}
	
	public void delete(Connection conn) throws SQLException {
		if (this.id != 0) {
			String sql = "DELETE FROM Solutions WHERE id= ?";
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, this.id);
			preparedStatement.executeUpdate();
			this.id = 0;
		}
	}
	
	public static Solution[] loadSolutionsByUserId(Connection conn, Integer id) throws SQLException{
	
		String sql = "SELECT * FROM Solutions WHERE user_id="+id;
		Solution[] uArray = loadSolutions(conn, sql);
		return uArray;
		
//		Solution[] allSolutions = loadAll(conn);
//		ArrayList<Solution> idSolutions = new ArrayList<>();
//		for(Solution sol: allSolutions) {
//			if(sol.user_id==id) {
//				idSolutions.add(sol);
//			}
//		}
//		
//		Solution[] returnArray = new Solution [idSolutions.size()];
//		returnArray = idSolutions.toArray(returnArray);
//		return returnArray;
		

	}
	
	public static Solution[] loadAllByExerciseId(Connection conn, Integer excerciseId) throws SQLException {
		String sql = "SELECT * FROM Solutions WHERE excercise_id=" + excerciseId + " ORDER BY created ASC";
		Solution[] uArray = loadSolutions(conn, sql);
		return uArray;
	}
	
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getExcercise_id() {
		return excercise_id;
	}
	public void setExcercise_id(int excercise_id) {
		this.excercise_id = excercise_id;
	}
	public int getUsers_id() {
		return user_id;
	}
	public void setUsers_id(int users_id) {
		this.user_id = users_id;
	}
	public int getId() {
		return id;
	}

}
