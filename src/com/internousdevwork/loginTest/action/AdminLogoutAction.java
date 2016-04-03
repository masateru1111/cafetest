package com.internousdevwork.loginTest.action;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 管理者ログアウトするクラス
 * @version 1.0
 * @since 1.0
 */
public class AdminLogoutAction extends ActionSupport implements SessionAware {

    /**
     * 生成されたシリアルID
     */
    private static final long serialVersionUID = -3688323332490611385L;

    /**
     * セッション
     */
    private Map<String,Object>session;

    /**
     * セッションを削除
     */
    public String execute(){
        if(session.containsKey("admin")){
            session.remove("admin");
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
}
