package com.dao;
/*
Dao is responsible for database opration like insert ,update ,
delete,select.
*/
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.bean.StudentBean;
import com.util.StudentUtil;

public class StudentDao {
	
	public int  doinsert(StudentBean s) {
		System.out.println();
		int result=0;
		try {
			StudentUtil sutil = new StudentUtil();
			Connection conn = sutil.createConnection();
			String sql = "insert into student(fname,lname,email)values(?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, s.getFname());
			pst.setString(2, s.getLname());
			pst.setString(3, s.getEmail());
			result=pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
