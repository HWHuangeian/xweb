import com.huangweihan.xweb.entity.Dish;
import com.huangweihan.xweb.entity.Type;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Description
 *
 * @author: Administrator
 * @date: 2018/11/4 0004
 */
public class LambdaTest {

    private static List<Dish> list = new ArrayList<>();

    static {
        list.add(new Dish("白菜", true, 100, 100, Type.OTHER));
        list.add(new Dish("红薯叶", true, 200, 200, Type.OTHER));
        list.add(new Dish("芥菜", true, 300, 300, Type.OTHER));
        list.add(new Dish("罗非鱼", false, 400,400, Type.FISH));
        list.add(new Dish("章鱼", false, 500,500, Type.FISH));
        list.add(new Dish("鲨鱼", false, 600,600, Type.FISH));
        list.add(new Dish("鲸鱼", false, 700,800, Type.FISH));
        list.add(new Dish("鸡肉", false, 700,700, Type.MEAT));
        list.add(new Dish("猪肉", false, 900,900, Type.MEAT));
        list.add(new Dish("牛肉", false, 1000,1000, Type.MEAT));
    }

    /* Predicate：<T> -> Boolean */
    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for(T s : list) {
            if (p.test(s)) {
                result.add(s);
            }
        }
        return result;
    }

    /* Consumer：<T> -> void */
    public static <T> void forEach(List<T> list, Consumer<T> c) {
        for (T i : list) {
            c.accept(i);
        }
    }
    /* Function：<T, R> -> R */
    public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for (T s : list) {
            result.add(f.apply(s));
        }
        return result;
    }


    public static void main(String[] args) {
        /* Predicate：<T> -> Boolean */
        Predicate<Dish> isVegetarianDishPredicate = (Dish d) -> d.isVegetarian();
        Predicate<Dish> isNotVegetarianDishPredicate = isVegetarianDishPredicate.negate();//非
        Predicate<Dish> isVegetarianAndPriceHighDishPredicate = isVegetarianDishPredicate.and(a -> a.getCalories() > 200);//与
        Predicate<Dish> isVegetarianOrPriceHighDishPredicate = isVegetarianDishPredicate.or(a -> a.getCalories() < 500);//或
        List<String> dishList1 = filter(list, isVegetarianDishPredicate).stream().map(Dish::getName).collect(Collectors.toList());
        List<String> dishList2 = filter(list, isNotVegetarianDishPredicate).stream().map(Dish::getName).collect(Collectors.toList());
        List<String> dishList3 = filter(list, isVegetarianAndPriceHighDishPredicate).stream().map(Dish::getName).collect(Collectors.toList());
        List<String> dishList4 = filter(list, isVegetarianOrPriceHighDishPredicate).stream().map(Dish::getName).collect(Collectors.toList());
        System.out.println("演示Predicate<T>的使用，找到食物中的所有蔬菜：");
        System.out.println(dishList1.toString());
        System.out.println(dishList2.toString());
        System.out.println(dishList3.toString());
        System.out.println(dishList4.toString());

        /* Consumer：<T> -> void */
        Consumer<Dish> dishConsumer = (Dish d) -> System.out.print(d.getName() + " ");
        System.out.println("演示Consumer<T>的使用，打印所有食物名称：");
        forEach(list, dishConsumer);
        System.out.println();

        /* Function：<T, R> -> R */
        List<Integer> dishFunction = map(list, (Dish d) -> d.getCalories());
        System.out.println("演示Function<T, R>的使用，打印所有食物的热量：");
        System.out.print(dishFunction.toString());
        System.out.println();

        /*食物按照热量排序（直接排序）*/
        list.sort(Comparator.comparing(Dish::getCalories).reversed());
        List<String> directNames = list.stream().map(Dish::getName).collect(Collectors.toList());
        System.out.println(directNames.toString());
        /*食物按照热量排序（比较器链）*/
        list.sort(Comparator.comparing(Dish::getCalories).thenComparing(Dish::getPrice));
        List<String> compareNames = list.stream().map(Dish::getName).collect(Collectors.toList());
        System.out.println(compareNames.toString());

        /*Filter筛选出所有素菜*/
        List<String> vegetable = list.stream().filter(Dish::isVegetarian).distinct().skip(2).limit(2).map(Dish::getName).collect(Collectors.toList());
        System.out.println("Filter筛选出所有素菜：");
        System.out.print(vegetable.toString());
        System.out.println();

        /*Optional*/
        Optional<Dish> op = list.stream().filter(d -> d.getCalories() > 200).findFirst();
        if (op.isPresent())
            System.out.println(op.get().getName());

        /*reduce有初始值*/
        int sum = list.stream().mapToInt(Dish::getCalories).reduce(0, (a, b) -> a + b);
        System.out.println(sum);
        /*reduce无初始值*/
        OptionalInt sumOp = list.stream().mapToInt(Dish::getCalories).reduce((a, b) -> a + b);
        if (sumOp.isPresent())
            System.out.println(sumOp.getAsInt());
        /*Optional计算最大最小值，暗含拆箱装箱成本*/
        Optional<Integer> max = list.stream().map(Dish::getCalories).reduce(Integer::max);
        if (max.isPresent())
            System.out.println(max.get());
        Optional<Integer> min = list.stream().map(Dish::getCalories).reduce(Integer::min);
        if (max.isPresent())
            System.out.println(min.get());

        /*直接调用sum方法求和*/
        int nSum = list.stream().mapToInt(Dish::getCalories).sum();
        System.out.println(nSum);

        /*计算流中有几种菜*/
        int vegetableCount = list.stream().filter(Dish::isVegetarian).map(d -> 1).reduce(0, (a, b) -> a + b);
        System.out.println(vegetableCount);

        /*forEach打印*/
        list.stream().map(Dish::getName).forEach(System.out::print);

        /*获得最大最小值平均值总和*/
        DoubleSummaryStatistics summaryStatistics = list.stream().collect(Collectors.summarizingDouble(Dish::getCalories));
        System.out.println("max: " + summaryStatistics.getMax());
        System.out.println("min: " + summaryStatistics.getMin());
        System.out.println("avg: " + summaryStatistics.getAverage());
        System.out.println("sum: " + summaryStatistics.getSum());

    }
}
