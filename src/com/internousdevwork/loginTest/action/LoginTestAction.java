package com.internousdevwork.loginTest.action;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import com.internousdevwork.loginTest.dao.LoginDAO;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Masateru Iwata
 * @version 1.0
 * @since 1.0
 */

/**
 * ログインするクラス
 * @author Masateru Iwata
 * @version 1.0
 * @since 1.0
 */
public class LoginTestAction extends ActionSupport implements SessionAware{
    
    /**
     * 生成されたシリアルID
     */
    private static final long serialVersionUID = 174508153072958431L;
    
    /**
     * 氏名
     */
    private String user_name;
    
    /**
     * パスワード
     */
    private String password;
    
    /**
     * セッション
     */
    private Map<String,Object>session;

    public String execute(){
        LoginDAO dao = new LoginDAO();
        boolean isResult = dao.select(user_name, password);
        if(!isResult){
            return ERROR;
        }
        session.put("user", user_name);
        session.put("id", dao.getId());
        return SUCCESS;
    }


    /**
     * @return user_name
     */
    public String getUser_name() {
        return user_name;
    }


    /**
     * @param user_name セットする user_name
     */
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }


    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }


    /**
     * @param password セットする password
     */
    public void setPassword(String password) {
        this.password = password;
    }


    /**
     * @return session
     */
    public Map<String, Object> getSession() {
        return session;
    }

    /**
     * @param session セットする session
     */
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

}