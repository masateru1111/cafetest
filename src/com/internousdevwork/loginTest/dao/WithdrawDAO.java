package com.internousdevwork.loginTest.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.internousdevwork.loginTest.util.DBConnector2;

/**
 * openconnectデータベースのuserテーブルからユーザー情報を削除するクラス
 * @author Masateru Iwata
 * @version 1.0
 * @since 1.0
 */
public class WithdrawDAO {

    public int remove(int id){
        int count = 0;
        Connection con = DBConnector2.getConnection("openconnect");
        try{
            String sql="delete from user where id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            count = ps.executeUpdate(); 
        }catch(SQLException e){
            e.printStackTrace();
        }
        return count;
    }
    public int removeCart(int id){
        int count = 0;
        Connection con = DBConnector2.getConnection("items");
        try{
            String sql="delete from cart where id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            count = ps.executeUpdate(); 
        }catch(SQLException e){
            e.printStackTrace();
        }
        return count;
    }

}
