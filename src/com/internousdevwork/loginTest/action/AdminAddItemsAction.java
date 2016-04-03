package com.internousdevwork.loginTest.action;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;
import com.internousdevwork.loginTest.dao.AdminAddItemsDAO;

/**
 * 商品を追加するクラス
 * @author Masateru Iwata
 * @version 1.0
 * @since 1.0
 */
public class AdminAddItemsAction extends ActionSupport implements SessionAware {

    /**
     * 生成されたシリアルID
     */
    private static final long serialVersionUID = 3490736752467065774L;

    /**
     * セッション
     */
    private Map<String,Object>session;
    
    /**
     * 商品名
     */
    private String itemName;
    
    /**
     * 商品説明
     */
    private String explanation;
    
    /**
     * 画像パス
     */
    private String imgPath;
    
    /**
     * 価格
     */
    private int price;
    
    /**
     * メッセージ
     */
    private String message;
    
    /**
     * 商品名、商品説明、価格、画像パスを追加するメソッド
     * return ERROR/SUCCESS　更新できたらSUCCESS、できなければERRORを返します。
     */
    public String execute(){
        AdminAddItemsDAO dao = new AdminAddItemsDAO();
        int count = 0;
        count = dao.insert(price,itemName,explanation,imgPath);
        if(count==0){
            return ERROR;
        }
        message = "商品を追加しました"; 
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
     * @return itemName
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * @param itemName セットする itemName
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * @return explanation
     */
    public String getExplanation() {
        return explanation;
    }

    /**
     * @param explanation セットする explanation
     */
    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    /**
     * @return imgPath
     */
    public String getImgPath() {
        return imgPath;
    }

    /**
     * @param imgPath セットする imgPath
     */
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    /**
     * @return price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price セットする price
     */
    public void setPrice(int price) {
        this.price = price;
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
