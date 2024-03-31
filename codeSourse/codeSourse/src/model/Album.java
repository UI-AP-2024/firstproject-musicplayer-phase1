package model;

public class Album {
    private int uniqeId;
    private String name;

    //*********************************************
    public Album(int uniqeId,String name){
        this.uniqeId = uniqeId;
        this.name = name;
    }
    //*********************************************
    public int getUniqeId(){
        return uniqeId;
    }
    public String getName(){
        return name;
    }
    //*********************************************
    public void setUniqeId(int uniqeId){
        this.uniqeId = uniqeId;
    }
    public void setName(String name){
        this.name = name;
    }
    //*********************************************
   @Override
    public String toString(){
        return "Album{" +
                "uniqeId=" + uniqeId +
                ", name='" + name + '\'' +
                '}' ;
    }

}
