package com.dao;
/*
Dao is responsible for database opration like insert ,update ,
delete,select.
*/
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bean.Student;
import com.util.StudentUtil;

public class StudentDao {

	public static void insertStudent(Student s)
	{
		try {
			Connection conn=StudentUtil.createConnection();
			String sql="insert into student(fname,lname,email,mobile,password) values(?,?,?,?,?)";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, s.getFname());
			pst.setString(2, s.getLname());
			pst.setString(3, s.getEmail());
			pst.setString(4, s.getMobile());
			pst.setString(5, s.getPassword());
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Student checkLogin(Student s)
	{
		Student student=null;
		try {
			Connection conn=StudentUtil.createConnection();
			String sql="select * from student where email=? and password=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, s.getEmail());
			pst.setString(2, s.getPassword());
			ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				student=new Student();
				student.setId(rs.getInt("id"));
				student.setFname(rs.getString("fname"));
				student.setLname(rs.getString("lname"));
				student.setEmail(rs.getString("email"));
				student.setMobile(rs.getString("mobile"));
				student.setPassword(rs.getString("password"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return student;
	}
	
}
