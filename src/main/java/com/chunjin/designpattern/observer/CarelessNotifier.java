package com.chunjin.designpattern.observer;

public class CarelessNotifier extends AbsNotifier {


  @Override
  public void addListener(Object object, String methodName, Object... args) {
    System.out.println("有新的同学委托粗心的放哨人!");
    this.getEventHandler().addEvent(object, methodName, args);
  }

  @Override
  public void notifyX() {
    System.out.println("粗心的放哨人告诉所有需要帮忙的同学：老师来了");
    try {
      this.getEventHandler().notifyX();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
