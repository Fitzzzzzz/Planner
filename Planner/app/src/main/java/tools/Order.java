package tools;

/**
 * Created by fitzz on 16-8-15.
 */
public class Order {
    public int _id;
    public String title;
    public String detail;
    public int isDone;
    public int classify;
    public int importance;

    public Order(){

    }

    public Order(int _id,String title,String detail,int isDone,int classify,int importance){
        this._id = _id;
        this.detail = detail;
        this.title = title;
        this.isDone = isDone;
        this.classify = classify;
        this.importance = importance;
    }
}
