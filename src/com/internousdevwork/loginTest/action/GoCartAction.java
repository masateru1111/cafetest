package com.internousdevwork.loginTest.action;
import java.util.ArrayList;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import com.internousdevwork.loginTest.dto.ItemsDTO;
import com.opensymphony.xwork2.ActionSupport;
import com.internousdevwork.loginTest.dao.CartDAO;

/**
 * カートの中身を確認するためのクラス
 * @author Masateru Iwata
 * @version 1.0
 * @since 1.0
 */
public class GoCartAction extends ActionSupport implements SessionAware {
    
    /**
     * 生成されたシリアルID
     */
    private static final long serialVersionUID = -2158794847764680136L;

    /**
     * セッション
     */
     private Map<String,Object>session;
     
     /**
      * ユーザーID
      */
     private int id;
     
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
      * 合計商品数
      */
     private int sumOfItems;
     
     /**
      * カートリスト
      */
     ArrayList<ItemsDTO> cartList = new ArrayList<ItemsDTO>();
     
     /**
      *　カート情報を取得するクラス
      */
     public String execute(){
         if(session.containsKey("id")){
             id = (int) session.get("id");
         }else{
             message = "ログインをしてください";
             return ERROR;
         }
         CartDAO dao = new CartDAO();
         if(!dao.serchToken(id)){
             message ="クレジットカード情報を登録してください";
             return ERROR;
         }
         if(!dao.serchItem(id)){
             message ="カートの中に商品がありません";
             return ERROR;
         }
         cartList = dao.getCartList();
         for(int i = 0; i<cartList.size(); i++){
             sumOfPrice += cartList.get(i).getTotalPrice();
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
     * @return message
     */
    public String getmessage() {
        return message;
    }

    /**
     * @param message セットする message
     */
    public void setmessage(String message) {
        this.message = message;
    }

    /**
     * @return cartList
     */
    public ArrayList<ItemsDTO> getCartList() {
        return cartList;
    }

    /**
     * @param cartList セットする cartList
     */
    public void setCartList(ArrayList<ItemsDTO> cartList) {
        this.cartList = cartList;
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
