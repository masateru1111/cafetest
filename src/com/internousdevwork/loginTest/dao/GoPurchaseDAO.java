package com.internousdevwork.loginTest.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.internousdevwork.loginTest.dto.ItemsDTO;
import com.internousdevwork.loginTest.util.DBConnector2;
import java.util.ArrayList;

/**
 * 商品リストをitemsデータベースのitemテーブルから取得するクラス
 * @author Masateru Iwata
 * @version 1.0
 * @since 1.0
 */
public class GoPurchaseDAO {
    
    /**
     * 商品リスト
     */
    ArrayList<ItemsDTO> itemList = new ArrayList<ItemsDTO>();
    
    /**
     * 合計数
     */
    private int sumOfItems;
    
    /**
     * itemテーブルからすべての商品情報を取得する
     * @return　res　取得できればtrue、できなければfalseを返します。
     */
    public boolean select(){
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
                itemList.add(dto);
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
     * カート内の商品数を検索するメソッド
     * @param id　ユーザーID
     * @return sumOfItems 商品数を返します。
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
     * @return itemList
     */
    public ArrayList<ItemsDTO> getItemList() {
        return itemList;
    }

    /**
     * @param itemList セットする itemList
     */
    public void setItemList(ArrayList<ItemsDTO> itemList) {
        this.itemList = itemList;
    } 
    
}
