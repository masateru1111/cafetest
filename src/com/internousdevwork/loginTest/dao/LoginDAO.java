package com.internousdevwork.loginTest.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import com.internousdevwork.loginTest.util.DBConnector2;

/**
 * openconnectデータベースのuserテーブル内にユーザー情報があるか確認するクラス
 * @author Masateru Iwata
 * @version 1.0
 * @since 1.0
 */
public class LoginDAO {
    
    /**
     * ユーザーID
     */
    private int id;
    
    /**
     * openconnectデータベースのuserテーブルから名前とパスワードをもとに検索するメソッド
     * @param user_name　氏名
     * @param password　パスワード
     * @return res 検索できたらtrue、できなければfalseを返します。
     */
    public boolean select(String user_name, String password){ //DAOのselectの戻りはboolean
        boolean res = false;
        Connection con = DBConnector2.getConnection("openconnect");
        try{
            String sql = "select id from user where user_name = ? and password = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user_name);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                id = rs.getInt("id"); 
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

    /**
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id セットする id
     */
    public void setId(int id) {
        this.id = id;
    }    
    
}

