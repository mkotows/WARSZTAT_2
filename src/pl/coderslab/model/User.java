package pl.coderslab.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class User {
	private static final String SHOW_USERS = "SELECT Users.id, Users.username FROM Users";

	private int id;
	private String username;
	private String email;
	private String password;
	private int userGroupId;

	public void saveToDB(Connection conn) throws SQLException {
		if (this.id == 0) {
			String sql = "INSERT INTO Users(username, email, password, user_group_id) VALUES (?, ?, ?, ?)";
			String generatedColumns[] = { "ID" };
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql, generatedColumns);
			preparedStatement.setString(1, this.username);
			preparedStatement.setString(2, this.email);
			preparedStatement.setString(3, this.password);
			preparedStatement.setInt(4, this.userGroupId);
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
				this.id = rs.getInt(1);
			}
		} else {
			String sql ="UPDATE Users SET username=?, email=?, password=?, user_group_id=? WHERE id= ?";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, this.username);
			preparedStatement.setString(2, this.email);
			preparedStatement.setString(3, this.password);
			preparedStatement.setInt(4, this.userGroupId);
			preparedStatement.setInt(5, this.id);
			preparedStatement.executeUpdate();
		}
	}

	static public User loadUserById(Connection conn, int id) throws SQLException {
		String sql = "SELECT * FROM Users where id=?";
		PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			User loadedUser = new User();
			loadedUser.id = resultSet.getInt("id");
			loadedUser.username = resultSet.getString("username");
			loadedUser.password = resultSet.getString("password");
			loadedUser.email = resultSet.getString("email");
			loadedUser.userGroupId = resultSet.getInt("user_group_id");
			return loadedUser;
		}
		return null;
	}

	static public User[] loadAll(Connection conn) throws SQLException {
		String sql = "SELECT * FROM Users";
		User[] uArray = loadAllFromSQL(conn, sql);
		return uArray;
	}

	private static User[] loadAllFromSQL(Connection conn, String sql) throws SQLException {
		ArrayList<User> users = new ArrayList<User>();
		PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			User loadedUser = new User();
			loadedUser.id = resultSet.getInt("id");
			loadedUser.username = resultSet.getString("username");
			loadedUser.password = resultSet.getString("password");
			loadedUser.email = resultSet.getString("email");
			loadedUser.userGroupId = resultSet.getInt("user_group_id");
			users.add(loadedUser);
		}
		User[] uArray = new User[users.size()];
		uArray = users.toArray(uArray);
		return uArray;
	}

	public void delete(Connection conn) throws SQLException {
		if (this.id != 0) {
			String sql = "DELETE FROM Users WHERE id= ?";
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, this.id);
			preparedStatement.executeUpdate();
			this.id = 0;
		}
	}

	public static User[] loadAllByGrupId(Connection conn, Integer groupId) throws SQLException{
		String sql = "SELECT * FROM Users WHERE user_group_id="+groupId;
		User[] uArray = loadAllFromSQL(conn, sql);
		return uArray;
	}
	
	public static ArrayList<Integer> showAllUsersAndGetIds(Connection conn) throws SQLException {
		ArrayList<Integer> usersIdArr =  new ArrayList<>();
		Statement statement = conn.createStatement();
		ResultSet rSetUsers = statement.executeQuery(SHOW_USERS);
		
		while(rSetUsers.next()) {
			Integer userId = rSetUsers.getInt("id");
			String name = rSetUsers.getString("username");
			System.out.println(userId + ": " + name);
			usersIdArr.add(userId);
		}
		
		return usersIdArr;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserGroupId() {
		return userGroupId;
	}

	public void setUserGroupId(int userGroupId) {
		this.userGroupId = userGroupId;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", userGroupId=" + userGroupId + "]";
	}

	public void editUser(Scanner scan, User user) {
		System.out.println("Podaj imię: ");
		user.setUsername(scan.nextLine());
		System.out.println("Podaj email: ");
		user.setEmail(scan.nextLine());
		System.out.println("Podaj hasło: ");
		user.setPassword(scan.nextLine());
		System.out.println("Podaj nr grupy: ");
		user.setUserGroupId(scan.nextInt());
		scan.nextLine();
	}

	
}
