

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
*This example uses Japanese charactors*  
When you use `Chain of Responsibility pattern`, you would define several concrete 
classes which implement the specified interface, then you would construct a chain 
connecting instances of those classes in order.  
`example.java8dp.behavior.cor.java8.Approvable` is an example of 
`Chain of Responsibility` pattern, in which responsibility is fulfilled 
not by subclasses, but by lambda expressions.  
This interface also provides a static utility method called `chain`, which 
connects those lamba expression one after another, creating yet another 
lambda expression which acts as a head element of tha chain.  

Usege of this `Chain of Responsibility pattern` implementation is like below.

```java
Approvable kacho = Approvable.approver("課長", "鈴木", amount -> amount <= 10000);
Approvable bucho = Approvable.approver("部長", "田中", amount -> amount <= 100000);
Approvable jigyoBucho = Approvable.approver("事業部長", "山田", amount -> amount <= 1000000);

Approvable cor = Approvable.chain(kacho, bucho, jigyoBucho);
Approval approval = cor.approve(10000);
```

`example.java8dp.behavior.cor.advanced.ChainOfResponsibility<Q, R>` is a more 
abstracted form of `Chain of Responsiblity`, which accepts any kind of 
`Function<Q, R>` lambda expressions and build a chain of them.  
Usege is like below.

```java
Function<Integer, Approval> kacho = approver("課長", "鈴木", amount -> amount <= 10000);
Function<Integer, Approval> bucho = approver("部長", "田中", amount -> amount <= 100000);
Function<Integer, Approval> jigyoBucho = approver("事業部長", "山田", amount -> amount <= 1000000);

Function<Integer, Approval> head = new ChainOfResponsibility<Integer, Approval>()
        .add(kacho)
        .add(bucho)
        .add(jigyoBucho)
        .tail(amount -> System.out.println(String.format("決裁できません。 %,3d円", amount)))
        .build();

Approval approval = head.apply(10000);
```
