package com.internousdevwork.loginTest.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.internousdevwork.loginTest.dto.UserInformationDTO;
import com.internousdevwork.loginTest.util.DBConnector2;
import java.util.ArrayList;

/**
 * openconnectデータベースのuserテーブルからユーザー情報を取得するメソッド
 * @author Masateru Iwata
 * @version 1.0
 * @since 1.0
 */
public class AdminUserDAO {

    /**
     * ユーザーリスト
     */
    ArrayList<UserInformationDTO> userList = new ArrayList<UserInformationDTO>();

    /**
     * ユーザー情報を取得するメソッド
     * @return res　リストに取得できたらtrue、できなければfalseを返します。
     */
    public boolean selectUserList(){
        boolean res = false;
        Connection con = DBConnector2.getConnection("openconnect");
        try{
            String sql="select * from user";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(); 
            while(rs.next()){
                UserInformationDTO dto = new UserInformationDTO();
                dto.setId(rs.getInt("id"));
                dto.setUserName(rs.getString("user_name"));
                dto.setPassword(rs.getString("password"));
                dto.setNameKana(rs.getString("user_kana"));
                dto.setEmail(rs.getString("email"));
                dto.setCardNumber(rs.getString("credit_number"));
                dto.setToken(rs.getString("credit_token"));
                dto.setPostalCode(rs.getString("postal_code"));
                dto.setAddress(rs.getString("address"));
                dto.setRegistrationDate(rs.getString("registration_date"));
                userList.add(dto);
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
     * @return userList
     */
    public ArrayList<UserInformationDTO> getUserList() {
        return userList;
    }

    /**
     * @param userList セットする userList
     */
    public void setUserList(ArrayList<UserInformationDTO> userList) {
        this.userList = userList;
    }


}
