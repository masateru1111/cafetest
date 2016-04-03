package com.internousdevwork.loginTest.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.internousdevwork.loginTest.util.DBConnector2;
import com.internousdevwork.loginTest.dto.ItemsDTO;
import java.util.ArrayList;

/**
 * itemsデータベースに接続して処理するクラス
 * @author Masateru Iwata
 * @version 1.0
 * @since 1.0
 */
public class GoPurchaseCheckDAO {
    
    /**
     * カートリスト
     */
    private ArrayList<ItemsDTO> cartList = new ArrayList<ItemsDTO>();
    
    /**
     * カート内の商品数を更新するメソッド
     * @param id　ユーザーID
     * @param itemId 商品ID
     * @param numberOfItems カート内のすべての商品
     * @return　count　更新件数を返します。
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
     * itemsデータベースのcartテーブルとitemテーブルから各種データを取得するメソッド
     * @param id　ユーザーID
     * @return res 取得できたらtrue、できなければfalseを返します。
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
