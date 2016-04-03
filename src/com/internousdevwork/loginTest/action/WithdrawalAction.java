package com.internousdevwork.loginTest.action;
import java.util.Map;
import com.internousdevwork.loginTest.dao.WithdrawDAO;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 退会するクラス
 * @author Masateru Iwata
 * @version 1.0
 * @since 1.0
 */
public class WithdrawalAction extends ActionSupport implements SessionAware {
    
    /**
     * 生成されたシリアルID
     */
    private static final long serialVersionUID = -3291407348854365342L;
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
      * 退会件数
      */
     private int count = 0;
     
     public String execute(){
         if(session.containsKey("id")){
             id = (int) session.get("id");
         }else{
             message = "ログインしてください";
         }
         WithdrawDAO dao = new WithdrawDAO();
         count = dao.remove(id);
         if(count == 0){
             message ="ログインしてください";
         }
         count = 0;
         count = dao.removeCart(id);
         session.remove("id");
         session.remove("user");
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
