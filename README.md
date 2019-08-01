# build-me-a-sheep



Java library that builds a valued object from any class.

**Useful for tests** : it easily feeds an object with default values, enjoy :)

In the library, a valued object is called a "**Sheep**"



## Usage

Just call **build** method on **SheepFactory** :

*Example :*

```java
Foo foo = (Foo) sheepFactory.build(Foo.class).get();
```

> *Notes :*
>
> - *The build method returns an **Optional***
> - *SheepFactory is a Spring component*



## Overload default values

Default values are defined with **@Value** annotation in **TypeProperties**.

To overload them, just add these entries in a Spring properties file (**application*.yml**, ...) :

```yml
sheep:
  types:
    boolean: true
    integer: 4
    long: 789
```

