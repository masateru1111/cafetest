package com.internousdevwork.loginTest.action;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import com.internousdevwork.loginTest.dao.GoPurchaseCompletionDAO;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 購入を完了させるクラス
 * @author Masateru Iwata
 * @version 1.0
 * @since 1.0
 */
public class GoPurchaseCompletionAction extends ActionSupport implements SessionAware {
    
    /**
     * 生成されたシリアルID
     */
    private static final long serialVersionUID = -4084154547172129244L;
   
    /**
     * セッション
     */
     private Map<String,Object>session;
     
     /**
      * ユーザーID
      */
     private int id;
     
     /**
      * メッセージ
      */
     private String message;
     
     /**
      * クレジットカードトークン
      */
     private String token;
     
     public String execute(){
         id = (int) session.get("id");
         GoPurchaseCompletionDAO dao = new GoPurchaseCompletionDAO();

         if(!dao.serchToken(id)){
             message ="クレジットカード情報を登録してください";
             return ERROR;
         }
         token = dao.getToken();
         if(token==null){
             message ="クレジットカード情報を登録してください";
             return ERROR;
         }
         boolean res = dao.selectItemHistory(id);
         if(!res){
             return ERROR;
         }
         if(dao.deleteCart(id)==0){
             return ERROR;
         }
         message = "ご購入いただき、誠にありがとうございました";
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

    
}
