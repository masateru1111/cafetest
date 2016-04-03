package com.internousdevwork.loginTest.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.internousdevwork.loginTest.dto.ItemsDTO;
import com.internousdevwork.loginTest.util.DBConnector2;

/**
 * 商品をカートから取り出し、ユーザーIDをもとにデータを格納するクラス
 * @author Masateru Iwata
 * @version 1.0
 * @since 1.0
 */
public class GoPurchaseCompletionDAO {
    /**
     * クレジットカードトークン
     */
    private String token;
    
    /**
     *  購入履歴を格納するリスト
     */
    ArrayList<ItemsDTO> history = new ArrayList<ItemsDTO>();
    
    /**
     * openconnectデータベースのuserテーブルから、クレジットカード情報を検索するメソッド
     * @param id　ユーザーID
     * @return res　格納されていればtrue、なければfalseを返します。
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
                token = rs.getString("credit_token");
                res = true;
                }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return res;
    }
    
    /**
     * itemsデータベースのitemとcartテーブルから各種データを取得するメソッド
     * @param dto ItemsDTO
     * @param id ユーザーID
     * @return res historyテーブルに格納できたらtrue、できなければfalseを返します。
     */
    public boolean selectItemHistory(int id){
        boolean res = false;
        Connection con = DBConnector2.getConnection("items");
        try{
            String sql="select cart.item_id,item_name,number_of_items,price*number_of_items from cart,item where id=? and cart.item_id=item.item_id";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ItemsDTO dto = new ItemsDTO();
                dto.setItemId(rs.getInt("item_id"));
                dto.setItemName(rs.getString("item_name"));
                dto.setNumberOfItems(rs.getInt("number_of_items"));    
                sql = "insert into history(id,item_id,item_name,number_of_items,registration_date)value(?,?,?,?,now())";
                ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                ps.setInt(2, dto.getItemId());
                ps.setString(3, dto.getItemName());
                ps.setInt(4, dto.getNumberOfItems());
                ps.executeUpdate();
                history.add(dto);
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
     * カード内の商品を削除するメソッド
     * @param id　ユーザーID
     * @return count　削除できた件数を返します。
     */
    public int deleteCart(int id){
        int count = 0;
        Connection con = DBConnector2.getConnection("items");
        try{
            String sql="delete from cart where id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            count = ps.executeUpdate();
        }catch(SQLException e){
        e.printStackTrace();
        }
        return count;
    }
    
    /**
     * @return token
     */
    public String getToken() {
        return token;
    }
    /**
     * @param token セットする token
     */
    public void setToken(String token) {
        this.token = token;
    }
 
 
    
}
