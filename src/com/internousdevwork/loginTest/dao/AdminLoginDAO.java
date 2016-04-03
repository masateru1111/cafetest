package com.internousdevwork.loginTest.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import com.internousdevwork.loginTest.util.DBConnector2;

/**
 * openconncetデータベースのadminテーブルから管理者情報を検索するクラス
 * @version 1.0
 * @since 1.0
 */
public class AdminLoginDAO {

    /**
     * 管理者情報を検索するクラス
     * @param adminName　管理者名
     * @param password　パスワード
     * @return　res 検索できればtrue、できなければfalseを返します。
     */
    public boolean select(String adminName, String password){ //DAOのselectの戻りはboolean
        boolean res = false;
        Connection con = DBConnector2.getConnection("openconnect");
        try{
            String sql = "select admin_id from admin where admin_id = ? and password = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, adminName);
            ps.setString(2, password);
            ResultSet rs  = ps.executeQuery();
            if(rs.next()){
                res = true;
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

