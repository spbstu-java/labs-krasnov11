package ru.spbstu.edu.krasnov2.lab2;

public class TestCallClass {

    public void pubicCall(){
        System.out.println("pubicCall");
    }

    @CallCount(3)
    public void pubicCall(int someValue){
        System.out.printf("pubicCall(%d)%n", someValue);
    }

    @CallCount(2)
    protected void protectedCall(){
        System.out.println("protectedCall");
    }

    @CallCount(3)
    public void protectedCall(int someValue, String s){
        System.out.printf("pubicCall(%d, %s)%n", someValue, s);
    }

    @CallCount(2)
    private void privateCall(){
        System.out.println("privateCall");
    }

    @CallCount(3)
    private void privateCall(int someValue, String s){
        System.out.printf("privateCall(%d, %s)%n", someValue, s);
    }
}
