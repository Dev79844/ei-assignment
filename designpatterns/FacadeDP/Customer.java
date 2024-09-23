package FacadeDP;

public class Customer {
    public static void main(String args[]){
        System.out.println(Waiter.deliverFood(FoodType.PASTA));
        System.out.println(Waiter.deliverFood(FoodType.PIZZA));
    }
}
