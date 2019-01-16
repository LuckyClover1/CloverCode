package com.clover.enumEntity;

/**
 * @author Clover
 */
public enum MediaType {
    Image("image"),Video("video"),Word("word"),Multi("multi");
    private String type;
    private MediaType(String type) {
        this.type = type;
    }
    public String getValue(){
        return type;
    }

    public MediaType getType(String typeName){
        for(MediaType type : MediaType.values()){
            if(typeName.equalsIgnoreCase(type.type)){
                return type;
            }
        }
        return Word;
    }
}
