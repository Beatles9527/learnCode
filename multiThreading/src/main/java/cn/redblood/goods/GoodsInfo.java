package cn.redblood.goods;

/**
 * @author The_Beatles
 * @date 2021/12/18 16:08
 */

public class GoodsInfo {

    private String name;
    private int price;
    private int num;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "GoodsInfo{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", num=" + num +
                '}';
    }

    public GoodsInfo(String name, int price, int num) {
        this.name = name;
        this.price = price;
        this.num = num;
    }

    public GoodsInfo() {
    }

    public GoodsInfo(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void changeNumber(int number){
        this.num = number;
    }

}
