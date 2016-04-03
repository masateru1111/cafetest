package com.internousdevwork.loginTest.action;

import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import com.internousdev.util.CreditCard;
import com.internousdevwork.loginTest.dao.EditUserCompletionDAO;
import com.internousdevwork.loginTest.dto.UserDTO;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 情報変更で入力された値をopenconnectデータベースのuserテーブルに登録するアクションクラス
 * @author Masateru Iwata
 * @version 1.0
 * @since 1.0
 */
public class EditUserCompletionAction extends ActionSupport implements SessionAware {

    /**
     * 生成されたシリアルナンバー
     */
    private static final long serialVersionUID = -6481579646769152722L;

    /**
     * パスワード
     */
    private String password;

    /**
     * ユーザー名
     */
    private String userName;

    /**
     * メールアドレス
     */
    private String email;

    /**
     * 住所
     */
    private String address;

    /**
     * 電話番号
     */
    private String cellNumber;

    /**
     * 郵便番号
     */
    private String postalCode;

    /**
     * クレジットカードの種別
     */
    private String card;

    /**
     * クレジットカードの下4ケタ
     */
    public String card4;

    /**
     * クレジットカードトークン
     */
    public String token;

    /**
     * クレジットカード番号
     */
    private String cardNumber;

    /**
     * クレジットカード名義
     */
    private String cardHolder;

    /**
     * 有効期限(月)
     */
    public String cardMonth;

    /**
     * 有効期限(年)
     */
    public String cardYear;

    /**
     * 有効期限(月/年)
     */
    private String expirationDate = cardMonth+"/"+cardYear;

    /**
     * セキュリティコード
     */
    private String securityCode;

    /**
     * セッション
     */
    private Map<String, Object> session;

    /**
     * 一時テーブルのID
     */
    private int tempId;

    /**
     * ユーザーID
     */
    private int id;
     
    /**
     * メッセージ
     */
    private String message;

    /**
     * openconnectデータベースに接続しuserテーブルに値を格納するメソッド
     * @return SUCESS/ERROR 成功した場合にSUCESS、失敗した場合にERRORを返します
     */
    public String execute(){
        if(!session.containsKey("tempId")){
            return ERROR;
        }
        id = (int) session.get("id");
        tempId = (int)session.get("tempId");
        UserDTO dto = new UserDTO();
        EditUserCompletionDAO dao = new EditUserCompletionDAO();
        if(!dao.select(tempId,dto)){
            return ERROR;
        }
        userName = dto.getUserName();
        password = dto.getPassword();
        email = dto.getEmail();
        postalCode = dto.getPostalCode();
        address = dto.getAddress();
        cellNumber = dto.getCellNumber();
        if(dao.update(password,email,postalCode,address,cellNumber,id)<1){
            message="情報変更できませんでした";
            return ERROR;
        }
        cardNumber = dto.getCardNumber();
        cardHolder = dto.getCardHolder();
        expirationDate = dto.getExpirationDate();
        securityCode = dto.getSecurityCode();
        if(cardNumber!=null){
            CreditCard creditCard = new CreditCard();
            if(creditCard.isExists(cardNumber)){
                creditCard.add(cardNumber, cardHolder, expirationDate, securityCode,id);
                token = creditCard.getToken();
                card4 =creditCard.getCreditCardNumber();
                if(token != null){
                    if(dao.updatecard(token, card4, id) < 1){
                        message = "クレジットカード情報を登録できませんでした";
                        return ERROR;
                    }
                }else{
                    message="クレジットカード情報に誤りがあります";
                    return ERROR;
                }
            }
        }
        
        if(dao.delete(tempId) < 0){
            return ERROR;
        }
        session.remove("tempId");
        message="変更完了しました。";
        return SUCCESS;
    }

    /**
     * パスワードを取得するメソッド
     * @return password パスワード
     */
    public String getPassword() {
        return password;
    }

    /**
     * ユーザー名を取得するメソッド
     * @return userName ユーザー名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * メールアドレスを取得するメソッド
     * @return email メールアドレス
     */
    public String getEmail() {
        return email;
    }

    /**
     * 住所を取得するメソッド
     * @return address 住所
     */
    public String getAddress() {
        return address;
    }

    /**
     * 電話番号を取得するメソッド
     * @return cellNumber 電話番号
     */
    public String getCellNumber() {
        return cellNumber;
    }

    /**
     * 郵便番号を取得するメソッド
     * @return postalCode 郵便番号
     */
    public String getPostalCode() {
        return postalCode;
    }
    /**
     * クレジットカードの種類を取得するメソッド
     * @return card クレジットカードの種類
     */
    public String getCard() {
        return card;
    }

    /**
     * クレジットカードの下4ケタの種類を取得するメソッド
     * @return card4 クレジットカードの下4ケタ
     */
    public String getCard4() {
        return card4;
    }

    /**
     * クレジットカードトークンの種類を取得するメソッド
     * @return token クレジットカードトークン
     */
    public String getToken() {
        return token;
    }

    /**
     * クレジットカード番号を取得するメソッド
     * @return cardNumber クレジットカード番号
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * クレジットカード名義を取得するメソッド
     * @return cardHolder クレジットカード名義
     */
    public String getCardHolder() {
        return cardHolder;
    }

    /**
     * クレジットカードの有効期限(月)の種類を取得するメソッド
     * @return cardMonth クレジットカードの有効期限(月)
     */
    public String getCardMonth() {
        return cardMonth;
    }

    /**
     * クレジットカードの有効期限(年)の種類を取得するメソッド
     * @return cardYear クレジットカードの有効期限(年)
     */
    public String getCardYear() {
        return cardYear;
    }

    /**
     * セッションを取得するメソッド
     * @return session セッション
     */
    public Map<String, Object> getSession() {
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
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message セットする message
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
