public class NullPointerExample {
    public static void main(String[] args) {

        int numberToCheck = 0;
        Result result = isNumberBiggerThanZero(numberToCheck);
        if (result.isRight()){
            System.out.println("The number was bigger than zero");
        }
    }

    public static Result isNumberBiggerThanZero(int number){
        Result result = new Result();;
        if (number > 0) {
            result.setRight(true);
        } else if (number < 0){
            result.setRight(false);
        }
        return result;
    }
}
