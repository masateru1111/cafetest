package com.internousdevwork.loginTest.action;
import java.util.ArrayList;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import com.internousdevwork.loginTest.dao.AdminUserDAO;
import com.internousdevwork.loginTest.dto.UserInformationDTO;
import com.opensymphony.xwork2.ActionSupport;

/**
 * ユーザー情報をopenconnectデータベースから取得して表示するクラス
 * @author Masateru Iwata
 * @version 1.0
 * @since 1.0
 */
public class AdminUserAction extends ActionSupport implements SessionAware {
    
    /**
     * 生成されたシリアルID
     */
    private static final long serialVersionUID = 7643930559575985795L;

    /**
     * セッション
     */
     private Map<String,Object>session;
     
     /**
      * ユーザーリスト
      */
     ArrayList<UserInformationDTO> userList = new ArrayList<UserInformationDTO>();
     
     /**
      * ユーザーリストを取得するメソッド
      * return SUCCESS/ERROR リストを取得できればSUCCESS、できなければERRORを返します。
      */
     public String execute(){
         AdminUserDAO dao = new AdminUserDAO();
         boolean isResult = false;
         isResult = dao.selectUserList();
         if(!isResult){
             return ERROR;
         }
         userList = dao.getUserList();
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
     * @return userList
     */
    public ArrayList<UserInformationDTO> getUserList() {
        return userList;
    }

    /**
     * @param userList セットする userList
     */
    public void setUserList(ArrayList<UserInformationDTO> userList) {
        this.userList = userList;
    }
    
}
