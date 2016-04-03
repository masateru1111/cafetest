package com.internousdevwork.loginTest.action;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import com.internousdevwork.loginTest.dao.ContactDAO;
import com.opensymphony.xwork2.ActionSupport;

/**
 * お問い合わせ情報を取得するクラス
 * @author Masateru Iwata
 * @version 1.0
 * @since 1.0
 */
public class ContactAction extends ActionSupport implements SessionAware {

    /**
     * 生成されたシリアルID
     */
    private static final long serialVersionUID = -5127982268444105278L;

    /**
     * セッション
     */
    private Map<String,Object>session;

    /**
     * お問い合わせ氏名
     */
    private String contactName;

    /**
     * お問い合わせメール
     */
    private String contactMailAddress;

    /**
     * お問い合わせメッセージ
     */
    private String contactMessage;

    /**
     * エラーメッセージ
     */
    public String message;

    /**
     * 処理件数
     */
    public int count;

    public String execute(){
        if(contactName.equals("")
                || contactMailAddress.equals("")
                || contactMessage.equals("")){
            message = "入力されていない項目があります";
            return ERROR;
        }else{
            ContactDAO dao = new ContactDAO();
            count = dao.insert(contactName,contactMailAddress,contactMessage);
        }
        if(count == 0){
            message ="送信できませんでした";
            return ERROR;
        }else{
            message ="お問い合わせの送信完了しました";
            return SUCCESS;
        }    
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
     * @return contactName
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * @param contactName セットする contactName
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * @return contactMailAddress
     */
    public String getContactMailAddress() {
        return contactMailAddress;
    }

    /**
     * @param contactMailAddress セットする contactMailAddress
     */
    public void setContactMailAddress(String contactMailAddress) {
        this.contactMailAddress = contactMailAddress;
    }

    /**
     * @return contactMessage
     */
    public String getContactMessage() {
        return contactMessage;
    }

    /**
     * @param contactMessage セットする contactMessage
     */
    public void setContactMessage(String contactMessage) {
        this.contactMessage = contactMessage;
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
     * @return count
     */
    public int getCount() {
        return count;
    }

    /**
     * @param count セットする count
     */
    public void setCount(int count) {
        this.count = count;
    }


}
