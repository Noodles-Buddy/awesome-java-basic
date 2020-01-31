package interfaceFeature;

/**
 *
 */
public interface MyFun {

    default String getName(){
        return "哈哈哈";
    }

    public static void show(){
        System.out.println("接口中的静态方法！");
    }
}
