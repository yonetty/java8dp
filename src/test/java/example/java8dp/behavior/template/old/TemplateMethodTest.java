package example.java8dp.behavior.template.old;

import org.junit.Test;

import example.java8dp.behavior.template.old.FizzBuzzCounter;
import example.java8dp.behavior.template.old.NabeatsuCounter;

public class TemplateMethodTest {

    @Test
    public void test() {
        System.out.println("FizzBuzz : ");
        FizzBuzzCounter counter = new FizzBuzzCounter();
        //1,2,Fizz,4,Buzz,Fizz,7,8,Fizz,Buzz,11,Fizz,13,14,FizzBuzz,16,17,Fizz,19,Buzz,
        counter.count(1, 20);
        System.out.println();

        System.out.println("Nabeatsu : ");
        NabeatsuCounter counter2 = new NabeatsuCounter();
        //21!,22,23!,24!,25,26,27!,28,29,30!!,31!,32!,33!!,34!,35!,36!!,37!,38!,39!!,40,
        counter2.count(21, 40);
    }

}
