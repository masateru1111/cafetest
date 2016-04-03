package com.internousdevwork.loginTest.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.internousdevwork.loginTest.util.DBConnector2;

/**
 * openconnectデータベースのuserテーブルからユーザーIDを元に削除するクラス
 * @author Masateru Iwata
 * @version 1.0
 * @since 1.0
 */
public class AdminDeleteUserDAO {

    public int delete(int id){
        int count = 0;
        Connection con = DBConnector2.getConnection("openconnect");
        try{
            String sql="delete from user where id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            count = ps.executeUpdate(); 
        }catch(SQLException e){
        e.printStackTrace();
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count;
    }   
}
