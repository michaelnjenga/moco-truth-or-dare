package com.Moco.data;

// TODO: Auto-generated Javadoc
/**
 * The Class Question.
 */
public class Question {
    
    /** The m item id. */
    private int mItemId;
        
    /** The m item content. */
    private String mItemContent;
    
    /** The m item type. */
    private int mItemType;
    
    /** The m item dirty. */
    private int mItemDirty;
    
    /** The m sound id. */
    private String mSoundId;
    
    /** The m picture id. */
    private String mPictureId;
    
    /**
     * Instantiates a new question.
     *
     * @param itemId the item id
     * @param itemContent the item content
     * @param itemType the item type
     * @param itemDirty the item dirty
     * @param soundId the sound id
     * @param pictureId the picture id
     */
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
    
    /**
     * Instantiates a new question.
     *
     * @param itemId the item id
     * @param question the question
     */
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
    
    /**
     * Instantiates a new question.
     */
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
 * Gets the item content.
 *
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
 * Gets the item id.
 *
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
 * Gets the item type.
 *
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
 * Gets the item dirty.
 *
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
 * Gets the sound id.
 *
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
 * Gets the picture id.
 *
 * @return the mPictureId
 */
    public String getPictureId() {
        return mPictureId;
    }
    
    
}
