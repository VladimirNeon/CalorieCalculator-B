package ua.opnu.springlab2.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    @Pointcut("execution(* ua.opnu.springlab2.service.*.add*(..))")
    public void allAddMethods() {}

    @Pointcut("execution(* ua.opnu.springlab2.service.*.get*(..))")
    public void allGetMethods() {}

    @Pointcut("execution(* ua.opnu.springlab2.service.*.delete*(..))")
    public void allDeleteMethods() {}
}
