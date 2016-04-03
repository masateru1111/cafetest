package com.internousdevwork.loginTest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdevwork.loginTest.util.DBConnector2;

/**
 * <p>
 * 入力された値を検証、tempテーブルにinsertし、<br>
 * 更新から1時間が経過した情報を削除するDAOクラス<br>
 * </p>
 * @author Masateru Iwata
 * @version 1.0
 * @since 1.0
 */
public class EditUserDAO  implements SessionAware{

    /**
     * セッション
     */
    private Map<String,Object> session;

    /**
     * データベースに接続するためのインターフェース
     */
    private Connection con;

    /**
     * 結果を格納するインターフェース
     */
    private ResultSet rs;

    /**
     * 更新件数
     */
    public int count;

    /**
     * SQL文
     */
    private String sql;

    /**
     * 一時テーブルのID
     */
    private int tempId = 0;

    /**
     * SQL文を実行するためのインターフェース
     */
    private PreparedStatement ps;

    /**
     * 更新結果の有無
     */
    boolean isResult = false;

    /**
     * <p>
     * 入力されたメールアドレスが既に登録されているものでないかどうか<br>
     * openconnectデータベースのuserテーブルのデータと照合するメソッド<br>
     * </p>
     * @param email メールアドレス
     * @return isResult 合っていればSUCCESS 合っていなければERRORを返します。
     */
    public boolean checkEmail(String email){
        if(email == null){
            isResult = false;
            return isResult;
        }
        try {
            con = DBConnector2.getConnection("openconnect");
            String sql = "select email from user where email = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                isResult = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
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
     * 入力された電話番号が既に登録されているものでないかどうか<br>
     * openconnectデータベースのuserテーブルのデータと照合するメソッド<br>
     * </p>
     * @param cellNumber 電話番号
     * @return isResult 合っていればSUCCESS 合っていなければERRORを返します。
     */
    public boolean checkCellNumber(String cellNumber){
        try {
            con = DBConnector2.getConnection("openconnect");
            String sql = "select cell_number from user where cell_number = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cellNumber);
            rs = ps.executeQuery();
            if (rs.next()) {
                isResult = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
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
     * itemsデータベースのtempテーブルを作成した時刻と現在時刻を比較し、<br>
     * 作成から1時間以上が経過していれば該当IDの情報を全て削除するメソッド<br>
     * </p>
     * @return result 削除件数を返します。
     */
    public int deleteTemp(){
         int count=-1;
        try{
            Connection connection = DBConnector2.getConnection("items");
            String sql = "delete from temp where registration_date < now() - INTERVAL 1 HOUR";
            ps = connection.prepareStatement(sql);
            count= ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return count;
    }

    /**
     * 入力された値を一時テーブルtempへ格納すると同時に、tempIdを取得するメソッド
     * @param userName ユーザー名
     * @param password パスワード
     * @param email メールアドレス
     * @param postalCode 郵便番号
     * @param address 住所
     * @param cellNumber 電話番号
     * @param cardNumber クレジットカード番号
     * @param cardMonth 有効期限(月)
     * @param cardYear 有効期限(年)
     * @param cardHolder クレジットカード名義
     * @param securityCode セキュリティコード
     * @return tempId 格納に成功した場合にtempIdを返します。
     */
    public int insertTemp(String password, String email,
            String postalCode, String address, String cellNumber,
            String cardNumber, String cardMonth, String cardYear, String cardHolder,
            String securityCode) {
        try{
            String expirationDate = cardMonth +" / "+ cardYear;
            con = DBConnector2.getConnection("items");
            sql ="insert into temp (password, email,address ,"
                    + "cell_number,postal_code,card_number, expiration_date,"
                    + "card_holder,security_code,registration_date) "
                    + "values(?,?,?,?,?,?,?,?,?,now())";
            ps = con.prepareStatement(sql);
            ps.setString(1,password);
            ps.setString(2,email);
            ps.setString(3,address);
            ps.setString(4,cellNumber);
            ps.setString(5,postalCode);
            ps.setString(6,cardNumber);
            ps.setString(7,expirationDate);
            ps.setString(8,cardHolder);
            ps.setString(9,securityCode);
            count = ps.executeUpdate();
            sql="SELECT LAST_INSERT_ID() AS LAST";
            ResultSet rs = ps.executeQuery(sql);
            if (rs.next()){
                tempId = rs.getInt("LAST");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return tempId;
    }

    /**
     * セッションを取得するメソッド
     * @return session セッション
     */
    public Map<String, Object>GetSession() {
        return session;
    }
    /**
     * セッションを格納するメソッド
     * @param session セッション
     */
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
    /**
     * 一時テーブルのIDを取得するメソッド
     * @return tempId 一時テーブルのID
     */
    public int getTempId() {
        return tempId;
    }

}
