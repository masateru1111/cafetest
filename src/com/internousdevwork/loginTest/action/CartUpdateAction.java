    package com.internousdevwork.loginTest.action;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;
import com.internousdevwork.loginTest.dao.CartUpdateDAO;
import com.internousdevwork.loginTest.dto.ItemsDTO;

import java.util.ArrayList;

/**
 * カート内の商品数を更新するクラス
 * @author Masateru Iwata
 * @version 1.0
 * @since 1.0
 */
public class CartUpdateAction extends ActionSupport implements SessionAware {
    
    /**
     * 生成されたシリアルID
     */
    private static final long serialVersionUID = -8026872417552519151L;

    /**
     * セッション
     */
     private Map<String,Object>session;
     
     /**
      * 商品ID
      */
     private int itemId;
     
     /**
      * ユーザーID
      */
     private int id;
     
     /**
      * 商品数
      */
     private String numberOfItems;
     
     /**
      * エラーメッセージ
      */
     private String message;
     /**
      * 商品数
      */
     private int intNumberOfItems;
     
     /**
      * 合計商品数
      */
     private int sumOfItems;
     
     /**
      *　カートリスト
      */
     ArrayList<ItemsDTO> cartList = new ArrayList<ItemsDTO>();
     
     /**
      * カート内の商品数を更新するメソッド
      */
     public String execute(){
         if(!numberOfItems.matches("^[0-9]*")){
             message="半角数字で入力してください";
             return ERROR;
         }
         intNumberOfItems = Integer.parseInt(numberOfItems);
         id = (int) session.get("id");
         CartUpdateDAO dao = new CartUpdateDAO();
         if(intNumberOfItems==0){
             dao.deleteCart(id,itemId);
             return SUCCESS;
         }
         int count = dao.updateCart(id,itemId,intNumberOfItems);
         if(count == 0){
             message = "更新できませんでした";
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
     * @return itemId
     */
    public int getItemId() {
        return itemId;
    }

    /**
     * @param itemId セットする itemId
     */
    public void setItemId(int itemId) {
        this.itemId = itemId;
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
     * @return numberOfItems
     */
    public String getNumberOfItems() {
        return numberOfItems;
    }

    /**
     * @param numberOfItems セットする numberOfItems
     */
    public void setNumberOfItems(String numberOfItems) {
        this.numberOfItems = numberOfItems;
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
