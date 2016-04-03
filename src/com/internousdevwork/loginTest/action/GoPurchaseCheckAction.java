package com.internousdevwork.loginTest.action;
import java.util.ArrayList;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import com.internousdevwork.loginTest.dto.ItemsDTO;
import com.opensymphony.xwork2.ActionSupport;
import com.internousdevwork.loginTest.dao.GoPurchaseCheckDAO;;

/**
 * カートに入力された情報を取得し、処理するクラス
 * @author Masateru Iwata
 * @version 1.0
 * @since 1.0
 */
public class GoPurchaseCheckAction extends ActionSupport implements SessionAware {
    
    /**
     * 生成されたシリアルID
     */
    private static final long serialVersionUID = -1126730886629225448L;

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
      * カートリスト
      */
     ArrayList<ItemsDTO> cartList = new ArrayList<ItemsDTO>();
     
     /**
      * ユーザーIDをもとにカート内の情報を取得するメソッド
      */
     public String execute(){
         if(session.containsKey("id")){
             id = (int) session.get("id");
         }else{
             message = "ログインをしてください";
             return ERROR;
         }
         GoPurchaseCheckDAO dao = new GoPurchaseCheckDAO();
         dao.serchItem(id);
         cartList = dao.getCartList();
         for(int i = 0; i<cartList.size(); i++){
             sumOfPrice += cartList.get(i).getTotalPrice();
         }
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
     * @param sumOfPrice セットする sumOfPrice
     */
    public void setSumOfPrice(int sumOfPrice) {
        this.sumOfPrice = sumOfPrice;
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
