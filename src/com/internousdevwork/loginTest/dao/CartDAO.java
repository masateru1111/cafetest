package com.internousdevwork.loginTest.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.internousdevwork.loginTest.util.DBConnector2;
import com.internousdevwork.loginTest.dto.ItemsDTO;
import java.util.ArrayList;

/**
 * ユーザーが選択した商品をカート内から取得するクラス
 * @author Masateru Iwata
 * @version 1.0
 * @since 1.0
 */
public class CartDAO {
    /**
     * カートリスト
     */
    private ArrayList<ItemsDTO> cartList = new ArrayList<ItemsDTO>();
    
    /**
     * 合計商品数
     */
    private int sumOfItems;
    
    /**
     * クレジットカード情報が登録されているか確認するメソッド
     * @param id　ユーザーID
     * @return res クレジットカード情報があればtrue、なければfalseを返します
     */
    public boolean serchToken(int id){
        boolean res = false;
        Connection con = DBConnector2.getConnection("openconnect");
        try{
            String sql="select credit_token from user where id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery(); 
            if(rs.next()){
                res = true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return res;
    }

    /**
     * itemsデータベースのcartとitemテーブルからユーザーの選択した商品情報を取得するメソッド
     * @param id　ユーザーID
     * @return res 取得できればtrue、できなければfalseを返します
     */
    public boolean serchItem(int id){
        boolean res = false;
        Connection con = DBConnector2.getConnection("items");
        try{
            String sql="select cart.item_id,item_name,price,img_path,number_of_items,price*number_of_items from cart,item where id = ? and cart.item_id = item.item_id";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery(); 
            while(rs.next()){
                ItemsDTO dto = new ItemsDTO();
                dto.setItemId(rs.getInt("item_id"));
                dto.setItemName(rs.getString("item_name"));
                dto.setPrice(rs.getInt("price"));
                dto.setImgPath(rs.getString("img_path"));
                dto.setTotalPrice((1.08*rs.getInt("price*number_of_items")));
                dto.setNumberOfItems(rs.getInt("number_of_items"));
                cartList.add(dto);
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
     * itemsデータベースのcartテーブルから商品吸うを検索するメソッド
     * @param id　ユーザーID
     * @return sumOfItems　合計商品数
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

    /**
     * @return cartList
     */
    public ArrayList<ItemsDTO> getCartList() {
        return cartList;
    }


    /**
     * @param cartList セットする cartList
     */
    public void setCartList(ArrayList<ItemsDTO> cartList) {
        this.cartList = cartList;
    }

}
