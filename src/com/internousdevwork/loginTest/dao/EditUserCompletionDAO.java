package com.internousdevwork.loginTest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.internousdevwork.loginTest.dto.UserDTO;
import com.internousdevwork.loginTest.util.DBConnector2;

/**
 * 新規登録で入力された値をDBopenconnectのuserに登録するDAOクラス
 * @author Masateru Iwata
 * @version 1.0
 * @since 1.0
 */
public class EditUserCompletionDAO {

    /**
     * データベースに接続するためのインターフェース
     */
    private Connection con;

    /**
     * SQL文を実行するためのインターフェース
     */
    private PreparedStatement ps;

    /**
     * 結果を格納するインターフェース
     */
    private ResultSet rs;

    /**
     * SQL文
     */
    private String sql;

    /**
     * ユーザーID
     */
    private int id;

    /**
     * 更新件数
     */
    private int count = 0;

    /**
     * itemsデータベースのtempテーブルに格納されているユーザー情報を検索し、UserDTOに格納するメソッド
     * @param tempId 一時テーブルのID
     * @param dto ユーザー情報を格納するDTOクラス
     * @return result 取得できたらdtoに格納してtrue、できなかったらfalseを返します。
     */
    public boolean select(int tempId, UserDTO dto){
        boolean isResult = false;
        try{
            con = DBConnector2.getConnection("items");
            sql = "select user_name,password,email,postal_code,address,cell_number,card_number,"
                    + "card_holder,expiration_date,security_code from temp WHERE temp_id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, tempId);
            rs = ps.executeQuery();
            while(rs.next()){
                dto.setUserName(rs.getString("user_name"));
                dto.setPassword(rs.getString("password"));
                dto.setEmail(rs.getString("email"));
                dto.setPostalCode(rs.getString("postal_code"));
                dto.setAddress(rs.getString("address"));
                dto.setCellNumber(rs.getString("cell_number"));
                dto.setCardNumber(rs.getString("card_number"));
                dto.setCardHolder(rs.getString("card_holder"));
                dto.setExpirationDate(rs.getString("expiration_date"));
                dto.setSecurityCode(rs.getString("security_code"));
                isResult = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if( con!= null){
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return isResult;
    }

    /**
     * <p>
     * openconnectデータベースのuserテーブルに対して、itemsデータベースtempテーブル内に<br>
     * 格納されているユーザー情報を登録するメソッド<br>
     * </p>
     * @param userName ユーザー名
     * @param password パスワード
     * @param email メールアドレス
     * @param postalCode 郵便番号
     * @param address 住所
     * @param cellNumber 電話番号
     * @return res データベースに値を格納することに成功したらresにtrue、失敗したらfalseを返します。
     */
    public int update(String password,String email,String postalCode,String address,String cellNumber,int id){
        try{
            Connection con = DBConnector2.getConnection("openconnect");
            sql ="update user set password=?,email=?,postal_code=?,address=?,cell_number=?,updated_date=now() where id =?";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1, password);
            ps.setString(2, email);
            ps.setString(3, postalCode);
            ps.setString(4, address);
            ps.setString(5, cellNumber);
            ps.setInt(6, id);
            count= ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * openconnectデータベースのuserテーブルに格納されているユーザー情報を更新するメソッド
     * @param token クレジットカードトークン
     * @param card4 クレジットカードの下４ケタ
     * @param userId ユーザーID
     * @return result 更新に成功したらsuccess、失敗したらfalseを返します。
     */
    public int updatecard(String token, String card4, int userId){
        con = DBConnector2.getConnection("openconnect");
        sql = "update user set credit_number= ?,credit_token= ? where id = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, card4);
            ps.setString(2, token);
            ps.setInt(3, userId);
            count = ps.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    /**
     *<p>
     * updateに成功したramenデータベースのtempテーブルに<br>
     * 格納されているユーザー情報を削除するメソッド
     * </p>
     * @param tempId 一時テーブルのID
     * @return count 更新件数を返します。
     */
    public int delete(int tempId) {
        Connection con = DBConnector2.getConnection("items");
        try{
            String sql ="delete from temp  where temp_id =?";
            PreparedStatement ps;
            ps = con.prepareStatement(sql);
            ps.setInt(1,tempId);
            count = ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                con.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return count;
    }

    /**
     * ユーザーIDを取得するメソッド
     * @return id ユーザーID
     */
    public int getId() {
        return id;
    }

}
