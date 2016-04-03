package com.internousdevwork.loginTest.action;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import com.internousdevwork.loginTest.dao.CartAddItemDAO;
import com.opensymphony.xwork2.ActionSupport;

/**
 * カート内に商品を格納するクラス
 * @author Masateru Iwata
 * @version 1.0
 * @since 1.0
 */
public class CartAddItemAction extends ActionSupport implements SessionAware {
    
    /**
     * 生成されたシリアルID
     */
    private static final long serialVersionUID = 4592817784995331613L;

    /**
     * セッション
     */
     private Map<String,Object>session;
     
     /**
      * 数
      */
     private int numberOfItems;
     
     /**
      * tempユーザーID
      */
     private int itemId;
     
     /**
      * ユーザーID
      */
     private int id;
     
     /**
      * エラーメッセージ
      */
     public String message;
     
     /**
      * 商品の合計
      */
     private int sumOfItems = 0;
     
     /**
      * itemsデータベースのcartテーブルに商品情報を格納するメソッド
      * @return ERROR/SUCCESS 格納できたらSUCCESS、失敗したらERRORを返します
      */
     public String execute(){
         if(!session.containsKey("id")){
             message = "購入はログインしてからにしてください";
             return ERROR;
         }

         id = (int) session.get("id");
         CartAddItemDAO dao = new CartAddItemDAO();
         if(numberOfItems==0){
             message = "数量を選択してください";
             return ERROR;
         }
         if(!dao.searchCard(id)){
             message = "クレジットカード情報がありません";
             return ERROR;
         }
         boolean res = dao.searchCart(id, itemId);
         if(!res){
             int count = dao.insertCart(id,itemId,numberOfItems);
             if(count == 0){
                 return ERROR;
             }
         }else{
             int count = dao.updateCart(id, itemId, numberOfItems);
             if(count == 0){
                 return ERROR;
             }
         }
         sumOfItems = dao.getSumOfItems();
  
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
