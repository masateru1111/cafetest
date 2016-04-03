package com.internousdevwork.loginTest.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.internousdevwork.loginTest.util.DBConnector2;

/**
 * itemsデータベースのitemテーブル内にある商品を削除するクラス
 * @author Masateru Iwata
 * @version 1.0
 * @since 1.0
 */
public class AdminDeleteItemsDAO {

    /**
     * itemsデータベースのitemテーブル内にある商品を削除するメソッド
     * @param itemId　商品ID
     * @return count 削除件数を返します。
     */
    public int delete(int itemId){
        int count = 0;
        Connection con = DBConnector2.getConnection("items");
        try{
            String sql="delete from item where item_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, itemId);
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
