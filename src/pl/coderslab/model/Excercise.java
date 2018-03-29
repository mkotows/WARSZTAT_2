package pl.coderslab.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Excercise {
	private int id;
	private String title;
	private String description;
	
	public void saveToDB(Connection conn) throws SQLException {
		if (this.id == 0) {
			String sql = "INSERT INTO Excercises(title, description) VALUES (?, ?)";
			String generatedColumns[] = { "ID" };
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql, generatedColumns);
			preparedStatement.setString(1, this.title);
			preparedStatement.setString(2, this.description);
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
				this.id = rs.getInt(1);
			}
		}else {
			System.out.println("Możesz napisać Update");
		}
	}
	
	static public Excercise loadExcerciseById(Connection conn, int id) throws SQLException {
		String sql = "SELECT * FROM Excercises where id=?";
		PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			Excercise loadedExcercise = new Excercise();
			loadedExcercise.id = resultSet.getInt("id");
			loadedExcercise.title = resultSet.getString("title");
			loadedExcercise.description = resultSet.getString("description");
			return loadedExcercise;
		}
		return null;
	}
	
	static public Excercise[] loadAll(Connection conn) throws SQLException {
		ArrayList<Excercise> excercises = new ArrayList<Excercise>();
		String sql = "SELECT * FROM Excercises";
		PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Excercise loadedExcercise = new Excercise();
			loadedExcercise.id = resultSet.getInt("id");
			loadedExcercise.title = resultSet.getString("title");
			loadedExcercise.description = resultSet.getString("description");
			excercises.add(loadedExcercise);
		}
		Excercise[] eArray = new Excercise[excercises.size()];
		eArray = excercises.toArray(eArray);
		return eArray;
	}
	
	public void delete(Connection conn) throws SQLException {
		if (this.id != 0) {
			String sql = "DELETE FROM Excercises WHERE id= ?";
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, this.id);
			preparedStatement.executeUpdate();
			this.id = 0;
		}
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getId() {
		return id;
	}
}
