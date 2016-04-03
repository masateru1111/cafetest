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
public class AdminItemsDAO {
    
    /**
     * カートリスト
     */
    private ArrayList<ItemsDTO> cartList = new ArrayList<ItemsDTO>();
    
    /**
     * itemsデータベースのcartとitemテーブルからユーザーの選択した商品情報を取得するメソッド
     * @param id　ユーザーID
     * @return res 取得できればtrue、できなければfalseを返します
     */
    public boolean serchItem(){
        boolean res = false;
        Connection con = DBConnector2.getConnection("items");
        try{
            String sql="select * from item";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(); 
            while(rs.next()){
                ItemsDTO dto = new ItemsDTO();
                dto.setItemId(rs.getInt("item_id"));
                dto.setItemName(rs.getString("item_name"));
                dto.setPrice(rs.getInt("price"));
                dto.setExplanation(rs.getString("explanation"));
                dto.setImgPath(rs.getString("img_path"));
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
