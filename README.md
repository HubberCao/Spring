<!-- GFM-TOC -->
* [一、异常处理](#一异常处理)
    * [Spring 中异常处理的各种姿势](#Spring 中异常处理的各种姿势)
* [二、String](#二string)
    * [概览](#概览)
* [三、运算](#三运算)
    * [参数传递](#参数传递)
<!-- GFM-TOC -->


# 一、异常处理


## Spring 中异常处理的各种姿势

### 1. @Controller 结合 @ExceptionHandler：

- new Integer(123) 每次都会新建一个对象；
- Integer.valueOf(123) 会使用缓存池中的对象，多次调用会取得同一个对象的引用。

```java
Integer x = new Integer(123);
Integer y = new Integer(123);
System.out.println(x == y);    // false
Integer z = Integer.valueOf(123);
Integer k = Integer.valueOf(123);
System.out.println(z == k);   // true
```

### 2. @ControllerAdvice 结合 @ExceptionHandler

### 3. HandlerExceptionResolver 接口

### 4. Spring 5 的 ResponseStatusException

### 5. Spring Boot 中的异常处理

5.1 实现 ErrorController

5.2 添加 ErrorAttributes

5.3 继承基类 BasicErrorController

参考
- [Spring 中异常处理的各种姿势](https://www.toutiao.com/a6760461717714502148/)
