package com.internousdevwork.loginTest.action;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;

/**
 * ユーザーログアウトするクラス
 * @author Masateru Iwata
 * @version 1.0
 * @since 1.0
 */
public class LogoutAction extends ActionSupport implements SessionAware {
    
    /**
     * 生成されたシリアルID
     */
    private static final long serialVersionUID = 2973134637250481711L;

    /**
     * セッション
     */
     private Map<String,Object>session;
     
     /**
      * ユーザーID
      */
     private int id;
     
     /**
      * セッションのユーザーIDと氏名を削除する
      */
     public String execute(){
         id = (int) session.get("id");
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
}
