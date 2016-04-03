package com.internousdevwork.loginTest.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.internousdevwork.loginTest.util.DBConnector2;
import java.util.ArrayList;
import com.internousdevwork.loginTest.dto.ItemsDTO;
import com.internousdevwork.loginTest.dto.UserDTO;

/**
 * ユーザー情報、購入履歴情報を取得するクラス
 * @author Masateru Iwata
 * @version 1.0
 * @since 1.0
 */
public class GoMypageDAO {

    /**
     * 購入履歴リスト
     */
    ArrayList<ItemsDTO> history = new ArrayList<ItemsDTO>();

    /**
     * 合計商品数
     */
    private int sumOfItems;
    /**
     * openconnectデータベースのuserテーブルからユーザー情報を取得するメソッド
     * @param dto　UserDTO
     * @param id ユーザーID
     * @return res 取得できればtrue、できなければfalseを返します
     */
    public boolean selectInformation(UserDTO dto,int id){
        boolean res = false;
        Connection con = DBConnector2.getConnection("openconnect");
        try{
            String sql="select * from user where id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery(); 
            if(rs.next()){
                dto.setUserName(rs.getString("user_name"));
                dto.setNameKana(rs.getString("user_kana"));
                dto.setEmail(rs.getString("email"));
                dto.setPassword(rs.getString("password"));
                dto.setPostalCode(rs.getString("postal_code"));
                dto.setAddress(rs.getString("address"));
                dto.setCardNumber(rs.getString("credit_number"));
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
     * itemsデータベースのhistoryテーブルとitemテーブルからユーザーIDをもとに購入情報を取得するクラス
     * @param dto　ItemsDTO
     * @param id　ユーザーID
     * @return res 取得できればtrue、できなければfalseを返します
     */
    public boolean selectItemHistory(ItemsDTO dto,int id){
        boolean res = false;
        Connection con = DBConnector2.getConnection("items");
        try{
            String sql="select history.item_id,history.item_name,number_of_items,price*number_of_items,registration_date from history,item where id=? and history.item_id=item.item_id";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery(); 
            while(rs.next()){
                ItemsDTO dto2 = new ItemsDTO();
                dto2.setItemName(rs.getString("item_name"));
                dto2.setNumberOfItems(rs.getInt("number_of_items"));
                dto2.setTotalPrice(1.08*rs.getInt("price*number_of_items"));
                dto2.setRegistrationDate(rs.getDate("registration_date"));
                history.add(dto2);
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
     * @return history
     */
    public ArrayList<ItemsDTO> getHistory() {
        return history;
    }

    /**
     * @param history セットする history
     */
    public void setHistory(ArrayList<ItemsDTO> history) {
        this.history = history;
    }


}
