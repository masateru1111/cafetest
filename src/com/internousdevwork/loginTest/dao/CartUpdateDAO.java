package com.internousdevwork.loginTest.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.internousdevwork.loginTest.util.DBConnector2;

/**
 * カート内を更新するクラス
 * @author Masateru Iwata
 * @version 1.0
 * @since 1.0
 */
public class CartUpdateDAO {
    
    /**
     * 合計商品数
     */
    private int sumOfItems;
    
    /**
     * itemsデータベースのcart内を更新するメソッド
     * @param id　ユーザーID
     * @param itemId 商品ID
     * @param numberOfItems 商品数
     * @return count 更新数を返します
     */
    public int updateCart(int id,int itemId,int numberOfItems){
        int count = 0;
        Connection con = DBConnector2.getConnection("items");
        try{
            String sql="update cart set number_of_items = ? where id = ? and item_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, numberOfItems);
            ps.setInt(2, id);
            ps.setInt(3, itemId);
            count = ps.executeUpdate();
        }catch(SQLException e){
        e.printStackTrace();
        }
        return count;
    }
    
    /**
     * カート内を削除するメソッド
     * @param id　ユーザーID
     * @param itemId 商品ID
     * @return count 削除件数を返します
     */
    public int deleteCart(int id,int itemId){
        int count = 0;
        Connection con = DBConnector2.getConnection("items");
        try{
            String sql="delete from cart where id = ? and item_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, itemId);
            count = ps.executeUpdate();
        }catch(SQLException e){
        e.printStackTrace();
        }
        return count;
    }
    
    /**
     * ユーザーIDをもとにカート内の商品を検索するメソッド
     * @param id　商品ID
     * @return sumOfItems　合計商品数を返します。
     */
    public int searchCartItems(int id){
        sumOfItems = 0;
        Connection con = DBConnector2.getConnection("items");
        try{
            String sql = "select number_of_items from cart where id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                sumOfItems += rs.getInt("number_of_items");
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
        return sumOfItems;
    } 
 
}
