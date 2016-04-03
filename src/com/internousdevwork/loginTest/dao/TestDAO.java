package com.internousdevwork.loginTest.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.internousdevwork.loginTest.util.DBConnector2;

public class TestDAO {

    public boolean serchCellNumber(String cellNumber){
        boolean res = false;
        Connection con = DBConnector2.getConnection("datebase");
        try{
            String sql="select cell_number from user where cell_number = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cellNumber);
            ResultSet rs = ps.executeQuery(); 
            if(rs.next()){
                String errorMessage = "すでに番号が登録されています。";
            }else{
                res = true;
                return res;
            }
        }catch(SQLException e){
        e.printStackTrace();
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return res;
    }
 
    
}
