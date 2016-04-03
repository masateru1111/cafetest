package com.internousdevwork.loginTest.action;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import com.internousdevwork.loginTest.dao.EditUserDAO;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 情報変更画面から値を取得し、一時テーブルに格納するクラス
 * @author Masateru Iwata
 * @version 1.0
 * @since 1.0
 */
public class EditUserAction extends ActionSupport implements SessionAware {
    
    /**
     * 生成されたシリアルID
     */
    private static final long serialVersionUID = 6749502415120894455L;

    /**
     * セッション
     */
     private Map<String,Object>session;
     
     /**
      * 氏名
      */
     private String userName;
     
     /**
      * フリガナ
      */
     private String nameKana;
     
     /**
      * パスワード
      */
     private String password;
     
     /**
      * 確認用パスワード
      */
     private String confirmPass;
     
     /**
      * メールアドレス
      */
     private String mailAddress;
     
     /**
      * 確認用メールアドレス
      */
     private String confirmMail;
     
     /**
      * 郵便番号
      */
     private String postalCode;
     
     /**
      * 住所
      */
     private String address;
     
     /**
      * クレジットカード種類
      */
     private String card;
     
     /**
      * 電話番号
      */
     private String cellNumber;
     
     /**
      *　クレジットカード番号
      */
     private String creditNumber;
     
     /**
      * クレジットカード期限（月）
      */
     private String cardMonth;
     
     /**
      * クレジットカード期限（年）
      */
     private String cardYear;
     
     /**
      * クレジットカード名義
      */
     private String cardHolder;
     
     /**
      * セキュリティコード
      */
     private String securityCode;
     
     /**
      * メッセージ
      */
     public String message;
     
     /**
      * 変更情報を一時テーブルに格納するクラス
      * @return SUCCESS/ERROR　格納に成功したらSUCCESS、失敗ならERRORを返します
      */
     public String execute(){
         if(creditNumber != null){
             if(card==null
                     || cardYear == null
                     || cardMonth == null){
                 message = "クレジットカード情報に未入力の項目があります。";
                 return ERROR;
             }
         }
         
         EditUserDAO dao = new EditUserDAO();
         if( dao.checkEmail(mailAddress)){
             message = "このメールアドレスは既に使用されています";
             return ERROR;
         }
         if(dao.checkCellNumber(cellNumber)){
             message = "この電話番号は既に使用されています";
             return ERROR;
         }
   
         if(dao.deleteTemp()<0){
             return ERROR;
         }
      
         if(dao.insertTemp(password,mailAddress,
                 postalCode,address,cellNumber,
                 creditNumber,cardMonth,cardYear,cardHolder,
                 securityCode)<1){
             return ERROR;
         }
         int tempId  = dao.getTempId() ;
         session.put("tempId", tempId);
         return SUCCESS;
     }
    
    /**
     * セッションを取得するメソッド
     * @return session
     */
    public Map<String, Object> getSession() {
        return session;
    }

    /**
     * セッションを格納するメソッド
     * @param session セットする session
     */
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    /**
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName セットする userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return nameKana
     */
    public String getNameKana() {
        return nameKana;
    }

    /**
     * @param nameKana セットする nameKana
     */
    public void setNameKana(String nameKana) {
        this.nameKana = nameKana;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password セットする password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return confirmPass
     */
    public String getConfirmPass() {
        return confirmPass;
    }

    /**
     * @param confirmPass セットする confirmPass
     */
    public void setConfirmPass(String confirmPass) {
        this.confirmPass = confirmPass;
    }

    /**
     * @return mailAddress
     */
    public String getMailAddress() {
        return mailAddress;
    }

    /**
     * @param mailAddress セットする mailAddress
     */
    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }


    /**
     * @return confirmMail
     */
    public String getConfirmMail() {
        return confirmMail;
    }

    /**
     * @param confirmMail セットする confirmMail
     */
    public void setConfirmMail(String confirmMail) {
        this.confirmMail = confirmMail;
    }

    /**
     * @return postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * @param postalCode セットする postalCode
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address セットする address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return cellNumber
     */
    public String getCellNumber() {
        return cellNumber;
    }

    /**
     * @param cellNumber セットする cellNumber
     */
    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    /**
     * @return creditNumber
     */
    public String getCreditNumber() {
        return creditNumber;
    }

    /**
     * @param creditNumber セットする creditNumber
     */
    public void setCreditNumber(String creditNumber) {
        this.creditNumber = creditNumber;
    }

    /**
     * @return cardMonth
     */
    public String getCardMonth() {
        return cardMonth;
    }

    /**
     * @param cardMonth セットする cardMonth
     */
    public void setCardMonth(String cardMonth) {
        this.cardMonth = cardMonth;
    }

    /**
     * @return cardYear
     */
    public String getCardYear() {
        return cardYear;
    }

    /**
     * @param cardYear セットする cardYear
     */
    public void setCardYear(String cardYear) {
        this.cardYear = cardYear;
    }

    /**
     * @return cardHolder
     */
    public String getCardHolder() {
        return cardHolder;
    }

    /**
     * @param cardHolder セットする cardHolder
     */
    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    /**
     * @return securityCode
     */
    public String getSecurityCode() {
        return securityCode;
    }

    /**
     * @param securityCode セットする securityCode
     */
    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    /**
     * @return card
     */
    public String getCard() {
        return card;
    }

    /**
     * @param card セットする card
     */
    public void setCard(String card) {
        this.card = card;
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
