package com.internousdevwork.loginTest.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.internousdevwork.loginTest.util.DBConnector2;

/**
 * 商品をデータベースに追加するクラス
 * @author user
 * @author Masateru Iwata
 * @version 1.0
 * @since 1.0
 */
public class AdminAddItemsDAO {

    /**
     * itemsデータベースのitemテーブルに下記の項目を格納するメソッド
     * @param price　価格
     * @param itemName　商品名
     * @param explanation　商品説明
     * @param imgPath　画像パス
     * @return　count　テーブル格納件数を返します。
     */
    public int insert(int price,String itemName,String explanation, String imgPath){
        int count = 0;
        Connection con = DBConnector2.getConnection("items");
        try{
            String sql="insert into item(item_name,price,explanation,img_path)value(?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, itemName);
            ps.setInt(2, price);
            ps.setString(3, explanation);
            ps.setString(4, imgPath);
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
