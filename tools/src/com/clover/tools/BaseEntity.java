package com.clover.tools;


import java.io.*;

/**
 *
 * @author clover
 *
 */
public abstract class BaseEntity implements Serializable{
    /**
     * 子类可直接调用实现深度克隆<br/>
     * @return BaseEntity 返回后强制格转
     */
    public BaseEntity clone(){
        try {
            ByteArrayOutputStream dataStream = new ByteArrayOutputStream();
            ObjectOutputStream oout = new ObjectOutputStream(dataStream);
            oout.writeObject(this);

            ObjectInputStream oin = new ObjectInputStream(new ByteArrayInputStream(dataStream.toByteArray()));
            return (BaseEntity) oin.readObject();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

}