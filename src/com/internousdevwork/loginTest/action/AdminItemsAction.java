package com.internousdevwork.loginTest.action;
import java.util.ArrayList;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import com.internousdevwork.loginTest.dto.ItemsDTO;
import com.opensymphony.xwork2.ActionSupport;
import com.internousdevwork.loginTest.dao.AdminItemsDAO;
/**
 * カートの中身を確認するためのクラス
 * @author Masateru Iwata
 * @version 1.0
 * @since 1.0
 */
public class AdminItemsAction extends ActionSupport implements SessionAware {
    
    /**
     * 生成されたシリアルID
     */
    private static final long serialVersionUID = 1107310380351413280L;

    /**
     * セッション
     */
     private Map<String,Object>session;
 
     /**
      * エラーメッセージ
      */
     public String message;
     
     /**
      * 合計金額
      */
     private int sumOfPrice;
     
     /**
      * 合計数
      */
     private int numberOfItems;
     
     /**
      * カートリスト
      */
     ArrayList<ItemsDTO> itemList = new ArrayList<ItemsDTO>();
     
     /**
      *　カート情報を取得するクラス
      */
     public String execute(){
         if(!session.containsKey("admin")){
             message = "ログインをしてください";
             return ERROR;
         }
         AdminItemsDAO dao = new AdminItemsDAO();
         dao.serchItem();
         itemList = dao.getCartList();
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
    public int getSumOfPrice() {
        return sumOfPrice;
    }

    /**
     * @param totalPrice セットする totalPrice
     */
    public void setTotalPrice(int sumOfPrice) {
        this.sumOfPrice = sumOfPrice;
    }

    

}
