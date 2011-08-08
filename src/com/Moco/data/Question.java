package com.Moco.data;

public class Question {
    
    private int mItemId;
        
    private String mItemContent;
    
    private int mItemType;
    
    private int mItemDirty;
    
    private String mSoundId;
    
    private String mPictureId;
    
    public Question(int itemId, String itemContent, int itemType, int itemDirty,
                    String soundId, String pictureId) {
        
        if (itemContent == null) {
            throw new IllegalArgumentException("Spot name must not be null");
        } else {
            this.mItemContent = itemContent;
            this.mItemId = itemId;
            this.mItemType = itemType;
            this.mItemDirty = itemDirty;
            this.mSoundId = soundId;
            this.mPictureId = pictureId;
        }
        
    }
    
    public Question(int itemId, Question question) {
        if (question.getItemContent() == null) {
            throw new IllegalArgumentException("Spot name must not be null");
        } else {
            this.mItemContent = question.getItemContent();
            this.mItemId = itemId;
            this.mItemType = question.getItemType();
            this.mItemDirty = question.getItemDirty();
            this.mSoundId = question.getSoundId();
            this.mPictureId = question.getPictureId();
        }
        
    }
    
    public Question() {
        
    }
//
//    /**
//     * @param mItemContent the mItemContent to set
//     */
//    public void setItemContent(String itemContent) {
//        this.mItemContent = itemContent;
//    }

    /**
     * @return the mItemContent
     */
    public String getItemContent() {
        return mItemContent;
    }

//    /**
//     * @param mItemId the mItemId to set
//     */
//    public void setItemId(int itemId) {
//        this.mItemId = itemId;
//    }

    /**
     * @return the mItemId
     */
    public int getItemId() {
        return mItemId;
    }

//    /**
//     * @param mItemType the mItemType to set
//     */
//    public void setItemType(String itemType) {
//        this.mItemType = itemType;
//    }

    /**
     * @return the mItemType
     */
    public int getItemType() {
        return mItemType;
    }

//    /**
//     * @param mItemDirty the mItemDirty to set
//     */
//    public void setItemDirty(String itemDirty) {
//        this.mItemDirty = itemDirty;
//    }

    /**
     * @return the mItemDirty
     */
    public int getItemDirty() {
        return mItemDirty;
    }

//    /**
//     * @param mSoundId the mSoundId to set
//     */
//    public void setSoundId(String soundId) {
//        this.mSoundId = soundId;
//    }

    /**
     * @return the mSoundId
     */
    public String getSoundId() {
        return mSoundId;
    }

//    /**
//     * @param mPictureId the mPictureId to set
//     */
//    public void setPictureId(String pictureId) {
//        this.mPictureId = pictureId;
//    }

    /**
     * @return the mPictureId
     */
    public String getPictureId() {
        return mPictureId;
    }
    
    
}
