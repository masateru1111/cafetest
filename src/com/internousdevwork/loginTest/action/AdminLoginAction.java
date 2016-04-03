package com.internousdevwork.loginTest.action;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import com.internousdevwork.loginTest.dao.AdminLoginDAO;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 管理者画面へ遷移するためのクラス
 * @author Masateru Iwata
 * @version 1.0
 * @since 1.0
 */
public class AdminLoginAction extends ActionSupport implements SessionAware{
 
    /**
     * 生成されたシリアルID
     */
    private static final long serialVersionUID = 8377499468719023645L;
   
    /**
     * 管理者名
     */
    private String adminName;
    
    /**
     * パスワード
     */
    private String password;
    
    /**
     * セッション
     */
    private Map<String,Object>session;

    public String execute(){
        AdminLoginDAO dao = new AdminLoginDAO();
        boolean isResult = dao.select(adminName, password);
        if(!isResult){
            return ERROR;
        }
        session.put("admin", adminName);
        session.put("id",25);
        session.put("user", "オーナー");
        return SUCCESS;
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


    /**
     * @return adminName
     */
    public String getAdminName() {
        return adminName;
    }

    /**
     * @param adminName セットする adminName
     */
    public void setAdminName(String adminName) {
        this.adminName = adminName;
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
    

}