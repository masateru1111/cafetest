package com.internousdevwork.loginTest.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.internousdevwork.loginTest.util.DBConnector2;

/**
 * itemsデータベースのcontactテーブルにお問い合わせ情報を格納するクラス
 * @author Masateru Iwata
 * @version 1.0
 * @since 1.0
 */
public class ContactDAO {

    public int insert(String name,String mail, String message){
        int count = 0;
        Connection con = DBConnector2.getConnection("items");
        try{
            String sql="insert into contact(contact_name,contact_mail,contact_message,registration_date )value(?,?,?,now())";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, mail);
            ps.setString(3, message);
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
