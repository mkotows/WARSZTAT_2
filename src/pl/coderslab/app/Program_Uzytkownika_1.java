package pl.coderslab.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import pl.coderslab.model.Solution;
import pl.coderslab.model.User;

public class Program_Uzytkownika_1 {

	public static void main(String[] args) {
		
		try (Connection conn = DriverManager.getConnection(

				"jdbc:mysql://localhost:3306/warsztaty?useSSL=false&characterEncoding=utf8", "root", "coderslab")) {

			
			
			Solution [] solutions= Solution.loadAllByExerciseId(conn, 3);
			if(solutions.length==0)
				System.out.println("tablice pusta");
			else {
				for(int i=0; i<solutions.length;i++) {
					System.out.println(solutions[i].getId() + ": " + solutions[i].getDescription());
				}
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		
	}

}
