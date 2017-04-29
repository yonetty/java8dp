package example.java8dp.behavior.template.java8;

import org.junit.Test;

public class TemplateMethodTest {

    @Test
    public void test() {
        //1,2,Fizz,4,Buzz,Fizz,7,8,Fizz,Buzz,11,Fizz,13,14,FizzBuzz,16,17,Fizz,19,Buzz
        Counter.count(c -> c.first(num -> num % 3 == 0, num -> "Fizz")
                .second(num -> num % 5 == 0, num -> "Buzz")
                .onBoth(num -> "FizzBuzz")
                .fromTo(1, 20));
        //21!,22,23!,24!,25,26,27!,28,29,30!!,31!,32!,33!!,34!,35!,36!!,37!,38!,39!!,40
        Counter.count(c -> c.first(num -> num % 3 == 0, num -> String.valueOf(num) + "!")
                .second(num -> String.valueOf(num).contains("3"), num -> String.valueOf(num) + "!")
                .onBoth(num -> String.valueOf(num) + "!!")
                .fromTo(21, 40));

    }
}
