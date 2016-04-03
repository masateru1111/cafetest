package com.internousdevwork.loginTest.action;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;
import com.internousdevwork.loginTest.dao.AdminDeleteUserDAO;

/**
 * ユーザーを削除するクラス
 * @author Masateru Iwata
 * @version 1.0
 * @since 1.0
 */
public class AdminDeleteUserAction extends ActionSupport implements SessionAware {
    
    /**
     * 生成されたシリアルID
     */
    private static final long serialVersionUID = -3680842871171531588L;
    
    /**
     * セッション
     */
     private Map<String,Object>session;
     
     /**
      * メッセージ
      */
     private String message;
     
     /**
      * ユーザーID
      */
     private int id;
     
     /**
      * ユーザーを削除するクラス
      * return ERROR/SUCCESS 削除できたらSUCCESS、できなければERRORを返します。
      */
     public String execute(){
         AdminDeleteUserDAO dao = new AdminDeleteUserDAO();
         int count = 0;
         count = dao.delete(id);
         if(count==0){
             message = "ユーザーを削除できませんでした";
             return ERROR;
         }
         message = "ユーザーを削除しました"; 
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

    
    
}
