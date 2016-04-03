package com.internousdevwork.loginTest.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.internousdevwork.loginTest.util.DBConnector2;

/**
 * itemsデータベースのcartテーブル内を処理するクラス
 * @author Masateru Iwata
 * @version 1.0
 * @since 1.0
 */
public class CartAddItemDAO {
    
    /**
     * 商品の合計
     */
    private int sumOfItems = 0;

    /**
     * カートの中身を確認するメソッド
     * @param id　ユーザーID
     * @param itemId　商品ID
     * @return res 商品があればtrue、なければfalseを返します
     */
    public boolean searchCart(int id,int itemId){
        boolean res = false;
        Connection con = DBConnector2.getConnection("items");
        try{
            String sql="select * from cart where id = ? and item_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, itemId);
            ResultSet rs = ps.executeQuery();
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

    /**
     * 商品をカートに格納するメソッド
     * @param id　ユーザーID
     * @param itemId tempユーザーID
     * @param numberOfItems 商品数
     * @return　count　追加できた件数を返します
     */
    public int insertCart(int id,int itemId,int numberOfItems){
        int count = 0;
        Connection con = DBConnector2.getConnection("items");
        try{
            String sql="insert into cart(id,item_id,number_of_items)value(?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, itemId);
            ps.setInt(3, numberOfItems);
            count = ps.executeUpdate(); 
            sql = "select number_of_items from cart where id = ?";
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
        return count;
    }

    /**
     * カート内の商品を更新します
     * @param id　ユーザーID
     * @param itemId tempユーザーID
     * @param numberOfItems 商品数
     * @return　count 更新した件数を返します
     */
    public int updateCart(int id,int itemId,int numberOfItems){
        int count = 0;
        Connection con = DBConnector2.getConnection("items");
        try{
            String sql="update cart set number_of_items = ? where id= ? and item_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, numberOfItems);
            ps.setInt(2, id);
            ps.setInt(3, itemId);
            count = ps.executeUpdate(); 
            sql = "select number_of_items from cart where id = ?";
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
        return count;
    }

    /**
     * クレジットカード情報が登録されているか確認するメソッド
     * @param id　ユーザーID
     * @return res トークンがあればtrue、なければfalseを返します
     */
    public boolean searchCard(int id){
        boolean res = false;
        Connection con = DBConnector2.getConnection("openconnect");
        try{
            String sql="select credit_token from user where id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
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
     * @return sumOfItems
     */
    public int getSumOfItems() {
        return sumOfItems;
    }

    /**
     * @param sumOfItems セットする sumOfItems
     */
    public void setSumOfItems(int sumOfItems) {
        this.sumOfItems = sumOfItems;
    }
}
