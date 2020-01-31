package optional;

import java.util.Optional;

public class OptionalUtilUtility {

    /**
     * 延迟执行
     * @param s
     * @return
     */
    public static Optional<Integer> stringToInt(String s){
        try{
            return Optional.of(Integer.parseInt(s));
        }catch (NumberFormatException e){
            return Optional.empty();
        }
    }
}
