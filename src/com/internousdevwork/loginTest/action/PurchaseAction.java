package com.internousdevwork.loginTest.action;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdevwork.loginTest.dao.GoPurchaseDAO;
import com.internousdevwork.loginTest.dto.ItemsDTO;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;

/**
 * 商品情報を取得するクラス
 * @author Masateru iwata
 *
 */
public class PurchaseAction extends ActionSupport implements SessionAware {
    
    /**
     * 生成されたシリアルID
     */
    private static final long serialVersionUID = -3541642205493762569L;
   
    /**
     * セッション
     */
     private Map<String,Object>session;
     
     /**
      * ユーザーID
      */
     private int id;
     
     /**
      * 合計数
      */
     private int sumOfItems;
     
     /**
      * 商品リスト
      */
     ArrayList<ItemsDTO> itemList = new ArrayList<ItemsDTO>();
     
     public String execute(){
         GoPurchaseDAO dao  = new GoPurchaseDAO();
         if(!dao.select()){
             return ERROR;
         };
         itemList = dao.getItemList();
         if(session.containsKey("id")){
             id = (int) session.get("id");
            sumOfItems = dao.searchCartItems(id);
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

    /**
     * @return itemList
     */
    public ArrayList<ItemsDTO> getItemList() {
        return itemList;
    }

    /**
     * @param itemList セットする itemList
     */
    public void setItemList(ArrayList<ItemsDTO> itemList) {
        this.itemList = itemList;
    }

    /**
     * @return sumOfItems
     */
    public int getSumOfItems() {
        return sumOfItems;
    }

    /**
     * @param sumOfItems セットする sumOfItems
     */
    public void setSumOfItems(int sumOfItems) {
        this.sumOfItems = sumOfItems;
    }


    
}
