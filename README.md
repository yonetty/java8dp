

# README

## Summary
This project includes several examples that illustrate how you can improve design patterns 
implementation using Java 8 lambda expression and Stream API.

## Builder pattern
`example.java8dp.creation.builder.java8.MailBuilder` is an example of `Builder Pattern`
 implementation.  
 `Fluent interface` is a well-known method for constructing objects in a fluent way,
 which makes your code easier to read.  
 But it often happens that you want to call a builder method only if come condition is true,
 or you want to call it repeatedly.  
 
 In that case, the code will be like this.

 ```java
Builder builder = new Builder()
    .doA()
    .doB();
if (conditionIsTrue) {
    builder.doC();
}
for (Foo foo in list) {
    builder.doD(foo);
}
builder.doE();
 ```

Not `fluent` at all!  

Using new builder implementation which utilizes lambda expression, you will still be 
able to keep your code fluent and simple.

```java
MailBuilder.send(mailer -> {
    mailer.from("fowler@example.com")
            .to("trump@example.com")
            .doIf(someCondition(), m -> m.cc("clinton@example.com"))
            .subject("Greeting")
            .body("Hello, Mr. President!");
});

final List<String> ccAddresses2 = new ArrayList<>();
MailBuilder.send(mailer -> {
    mailer.from("fowler@example.com")
            .to("trump@example.com")
            .foreach(ccAddresses2, (m, ccAddress) -> m.cc(ccAddress))
            .subject("Greeting")
            .body("Hello, Mr. President!");
});
```

## Template method pattern
`Template method pattern` is very useful when you add customizability to your code, 
but inheritance sometimes is a little bit heavy way for realizing customization.

`example.java8dp.behavior.template.java8.Counter` is an examle of applying lambda 
expression to `Template method pattern`, in which `hook methods` aren't implemented 
in the subclass but are passed as lambda expressions.  

Usage of this template method is below (see test code). 

```
//1,2,Fizz,4,Buzz,Fizz,7,8,Fizz,Buzz,11,Fizz,13,14,FizzBuzz,16,17,Fizz,19,Buzz
Counter.count(c -> c.first(num -> num % 3 == 0, num -> "Fizz")
        .second(num -> num % 5 == 0, num -> "Buzz")
        .onBoth(num -> "FizzBuzz")
        .fromTo(1, 20));
```

## Command pattern
In this example, `command` implementations are replaced by lambda expressions 
(like those hook methods in `Template method pattern`).  
`example.java8dp.behavior.command.java8.PieceCommandFactory` provides those commands 
in the form of static methods.

## Chain fo Responsibility pattern
** This example uses Japanese charactors **

