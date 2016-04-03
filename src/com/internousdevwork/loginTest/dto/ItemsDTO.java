package com.internousdevwork.loginTest.dto;

import java.util.Date;
import java.util.Set;

public class ItemsDTO {
    private int itemId;
    private String itemName;
    private int price;
    private String explanation;
    private String imgPath;
    private int id;
    private int numberOfItems;
    private int totalPrice;
    private Date registrationDate;
    
    /**
     * @return itemId
     */
    public int getItemId() {
        return itemId;
    }
    /**
     * @param itemId セットする itemId
     */
    public void setItemId(int itemId) {
        this.itemId = itemId;
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
    /**
     * @return numberOfItems
     */
    public int getNumberOfItems() {
        return numberOfItems;
    }
    /**
     * @param numberOfItems セットする numberOfItems
     */
    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }
    /**
     * @return totalPrice
     */
    public int getTotalPrice() {
        return totalPrice;
    }
    /**
     * @param totalPrice セットする totalPrice
     */
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = (int) totalPrice;
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
     * @param totalPrice セットする totalPrice
     */
    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
    /**
     * @return registrationDate
     */
    public Date getRegistrationDate() {
        return registrationDate;
    }
    /**
     * @param registrationDate セットする registrationDate
     */
    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
   

}
