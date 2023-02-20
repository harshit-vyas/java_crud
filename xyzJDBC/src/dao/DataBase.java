package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import bean.Teacher;
import logic.Data1;


public class DataBase {
	String url="jdbc:mysql://localhost:3306/info";
	 String id= "root";
	 String pass="ROOT";
	 Connection con ;
	public  Connection getConnection() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			 con = DriverManager.getConnection(url, id, pass);
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}
	
	public boolean insert(Teacher t) {
		boolean b = true;
		try {
		Connection con = getConnection();
		
		PreparedStatement ps = con.prepareStatement("insert into teacher value(?,?,?,?,?)");
		ps.setInt(1, t.getId());
		ps.setString(2, t.getName());
		ps.setString(3, t.getAddress());
		ps.setString(4, t.getContact());
		ps.setInt(5, t.getSalary());
		b=ps.execute();
		con.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return !b;
	}
	public boolean delete(int id) {
		boolean b= true;
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("delete from teacher where id = ?");
			ps.setInt(1, id);
			b=ps.execute();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return !b;
	}
	public boolean updateSalary(int salary, int id) {
		 boolean b=true;
		try {
			Connection con = getConnection();
			PreparedStatement ps  = con.prepareStatement("update teacher set salary=? where id = ?");
			ps.setInt(1, salary);
			ps.setInt(2, id);
			b=ps.execute();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return !b;
		
	}
	 public boolean getById(int id) {
		 boolean b= true;
		 try {
			Connection con= getConnection();
			PreparedStatement ps = con.prepareStatement("select *from teacher where id = ?");
			ps.setInt(1, id);
			b = ps.execute();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return b;
	 }
	 public void getAll() {
		 try {
			Connection con = getConnection() ;
			PreparedStatement ps = con.prepareStatement("select *from teacher");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getInt(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	 }
	 
	 public static void main(String[] args) {
		 DataBase d= new DataBase();
		 d.getConnection();
		 Scanner sc=new Scanner(System.in);
		 int n=7;
		 while(n!=0) {
			 System.err.println("enter 1 to insert \nenter 2 to delete \nenter 3 to update salary \nenter 4 to get data by id \nenter 5 to get all data");
			 n=sc.nextInt();
		switch (n) {
			case 1 :Teacher t = Data1.dataTeacher();
				d.insert(t);
				break;
			case 2 :
				System.out.println("enter id");
				int id=sc.nextInt();
				d.delete(id);
				break;
			case 3 :
				System.out.println("enter salary");
				int salary = sc.nextInt();
				System.out.println("enter id");
				int id1= sc.nextInt();
				d.updateSalary(salary, id1);
				break;
			case 4 :System.out.println("enter id");
				int id2= sc.nextInt();
				d.getById(id2);
				break;
			case 5 :
				d.getAll();
				break;
		}
	}
  }
}
