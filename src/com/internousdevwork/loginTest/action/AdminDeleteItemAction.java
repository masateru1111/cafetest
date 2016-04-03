package com.internousdevwork.loginTest.action;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;
import com.internousdevwork.loginTest.dao.AdminDeleteItemsDAO;

/**
 * 商品を削除するクラス
 * @author Masateru Iwata
 * @version 1.0
 * @since 1.0
 */
public class AdminDeleteItemAction extends ActionSupport implements SessionAware {
    
    /**
     * 生成されたシリアルID
     */
    private static final long serialVersionUID = -9093342098106367397L;
    
    /**
     * セッション
     */
     private Map<String,Object>session;
     
     /**
      * メッセージ
      */
     private String message;
     
     /**
      * 商品ID
      */
     private int itemId;
     
     /**
      * 商品IDを元に
      */
     public String execute(){
         AdminDeleteItemsDAO dao = new AdminDeleteItemsDAO();
         int count = 0;
         count = dao.delete(itemId);
         if(count==0){
             message = "商品を削除できませんでした";
             return ERROR;
         }
         message = "商品を削除しました"; 
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
    
    
}
