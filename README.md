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

```
优点：

优先级最高。
@ExceptionHandler 标记的方法返回值类型支持多种。可以是视图，也可以是 json 等。

缺点：

一个 Controller 中的 @ExceptionHandler 注解上的异常类型不能出现相同的，否则运行时抛异常。
需要显式的声明处理的异常类型。
作用域仅仅是该 Controller 并不是真正意义上的全局异常。如果要想作用于全局需要将其放入所有控制器的父类中。
```

### 2. @ControllerAdvice 结合 @ExceptionHandler

这是 1. 的改进型，通过定义 @ControllerAdvice 类并在方法上标记 @ExceptionHandler ，达到了全局异常处理的目的：

```
优点：

全局的异常处理。
完全控制响应的主体以及状态码
将多个异常映射到同一方法，以一起处理，并且它充分利用了更新的 Restful ResponseEntity 响应

缺点：

一个 Controller 中的 @ExceptionHandler 注解上的异常类型不能出现相同的，否则运行时抛异常。
需要显式的声明处理的异常类型。

一般情况下也建议使用该方式进行异常处理。大多数情况下都是兼容的。
```

### 3. HandlerExceptionResolver 接口

```
优点：

这是一个全局的异常处理器。
这种方式全局异常处理返回JSP、velocity 等模板视图比较方便。
支持多种格式的响应，虽然覆写的方法返回的是 ModelAndView 但是因为参数中有 HttpServletResponse， 我们可以利用它来进行定制响应结果。
例如，如果客户端要求输入application / json，那么在出现错误情况时，我们要确保我们返回一个以application / json编码的响应。

缺点：

我们需要与低级的 HttpServletResponse 交互才能实现各种形式的响应体。
优先级比较低
```
### 4. Spring 5 的 ResponseStatusException

在最新的 Spring 5 中你还可以通过 抛出 ResponseStatusException 异常来进行处理。

```
优点：

使用比较方便
一种类型，多种状态代码：一种异常类型可以导致多种不同的响应。与@ExceptionHandler相比，这减少了紧密耦合
我们将不必创建那么多的自定义异常类
由于可以通过编程方式创建异常，因此可以更好地控制异常处理

缺点：

没有统一的异常处理方式，强制执行某些应用程序范围的约定更加困难
可能会有大量的重复代码。
```

### 5. Spring Boot 中的异常处理

5.1 实现 ErrorController
```
Spring Boot 在默认情况下，提供了 /error 映射来处理所有错误，在 Servlet 容器里注册了全局的错误页面(Whitelabel Error Page)并返回客户端。
通过实现 ErrorController 接口并注册为 Bean。这里不再举例。可参考 BasicErrorController 。
```
5.2 添加 ErrorAttributes

5.3 继承基类 BasicErrorController
```
Spring Boot 自动配置还提供了实现 ErrorController 接口异常处理的基类 BasicErrorController，默认是处理 text/html类型请求的错误，
可以继承该基类自定义处理更多的请求类型，添加公共方法并使用 @RequestMapping 注解的 produce属性指定处理类型。
```
参考
- [Spring 中异常处理的各种姿势](https://www.toutiao.com/a6760461717714502148/)
