package com.internousdevwork.loginTest.action;
import java.util.Map;
import java.util.ArrayList;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;
import com.internousdevwork.loginTest.dao.GoMypageDAO;
import com.internousdevwork.loginTest.dto.ItemsDTO;
import com.internousdevwork.loginTest.dto.UserDTO;

/**
 * ユーザー情報と購入履歴を取得するクラス
 * @author Masateru Iwata
 * @version 1.0
 * @since 1.0
 */
public class GoMypageAction extends ActionSupport implements SessionAware {
    
    /**
     * 生成されたシリアルID
     */
    private static final long serialVersionUID = 3911153713203129276L;

    /**
     * セッション
     */
     private Map<String,Object>session;
     
     /**
      * ユーザーID
      */
     private int id;
     
     /**
      * 氏名
      */
     private String userName;
     
     /**
      * フリガナ
      */
     private String nameKana;
     
     /**
      * メールアドレス
      */
     private String mailAddress;
     
     /**
      * パスワード
      */
     private String password;
     
     /**
      * 郵便番号
      */
     private String postalCode;
     
     /**
      * 住所
      */
     private String address;
     
     /**
      * クレジットカード下4桁
      */
     private String card4;
     
     /**
      * 商品名
      */
     private String itemName;
     
     /**
      * 商品数
      */
     private int numberOfItems;
     
     /**
      * 合計金額
      */
     private int totalPrice;
     
     /**
      * メッセージ
      */
     public String message;
     
     /**
      * 商品合計個数
      */
     private int sumOfNumberOfItems;
     
     /**
      * 購入リスト
      */
     ArrayList<ItemsDTO> history = new ArrayList<ItemsDTO>();
     
     /**
      * 合計商品数
      */
     private int sumOfItems;
     
     
     /**
      * ユーザー情報と購入履歴の情報を取得するメソッド
      */
     public String execute(){
         if(!session.containsKey("id")){
             message = "ログインしてください";
             return ERROR;
         }
                 
         id = (int) session.get("id");
         GoMypageDAO dao = new GoMypageDAO();
         UserDTO dto = new UserDTO();
         boolean isResult = dao.selectInformation(dto,id);
         if(!isResult){
             return ERROR;
         }else{
             userName = dto.getUserName();
             nameKana = dto.getNameKana();
             mailAddress = dto.getEmail();
             password = dto.getPassword();
             postalCode = dto.getPostalCode();
             address = dto.getAddress();
             card4 = dto.getCardNumber();
         }
         ItemsDTO idto = new ItemsDTO();
         isResult = dao.selectItemHistory(idto,id);
         
         history = dao.getHistory();
         for(int i=0; i < history.size(); i++){
             totalPrice += history.get(i).getTotalPrice();
             sumOfNumberOfItems += history.get(i).getNumberOfItems();
         }  
         
         sumOfItems = dao.searchCartItems(id);
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
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id セットする id
     */
    public void setId(int id) {
        this.id = id;
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
     * @param addres セットする addres
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return card4
     */
    public String getCard4() {
        return card4;
    }

    /**
     * @param card4 セットする card4
     */
    public void setCard4(String card4) {
        this.card4 = card4;
    }

    /**
     * @return itemName
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * @param itemName セットする itemName
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * @return numberOfItems
     */
    public int getNumberOfItems() {
        return numberOfItems;
    }

    /**
     * @param numberOfItems セットする numberOfItems
     */
    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    /**
     * @return totalPrice
     */
    public int getTotalPrice() {
        return totalPrice;
    }

    /**
     * @param totalPrice セットする totalPrice
     */
    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
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

    /**
     * @return sumOfNumberOfItems
     */
    public int getSumOfNumberOfItems() {
        return sumOfNumberOfItems;
    }

    /**
     * @param sumOfNumberOfItems セットする sumOfNumberOfItems
     */
    public void setSumOfNumberOfItems(int sumOfNumberOfItems) {
        this.sumOfNumberOfItems = sumOfNumberOfItems;
    }

    /**
     * @return sumOfItems
     */
    public int getSumOfItems() {
        return sumOfItems;
    }

    /**
     * @param sumOfItems セットする sumOfItems
     */
    public void setSumOfItems(int sumOfItems) {
        this.sumOfItems = sumOfItems;
    }
    
}
