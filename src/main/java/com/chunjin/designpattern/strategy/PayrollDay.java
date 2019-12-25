package com.chunjin.designpattern.strategy;


//策略枚举
public enum PayrollDay {

  MONDAY(PayType.WEEKDAY), TUESDAY(PayType.WEEKDAY), WENESDAY(PayType.WEEKDAY),
  THURSDAY(PayType.WEEKDAY), FRIDAY(PayType.WEEKDAY),
  STATURDAY(PayType.WEEKEND), SUNDAY(PayType.WEEKEND);

  private final PayType payType;

  PayrollDay(PayType payType) {
    this.payType = payType;
  }

  int pay(int minutesWorked, int payRate) {
    return payType.pay(minutesWorked, payRate);
  }

  private enum PayType {

    WEEKDAY {
      int overtimePay(int minsWorked, int payRate) {
        return 1;
      }
    },
    WEEKEND {
      int overtimePay(int minsWorked, int payRate) {
        return 1;
      }
    };

    abstract int overtimePay(int mins, int payRate);

    private static final int MINS_PER_SHIFT = 8 * 60;

    int pay(int minsWorked, int payRate) {
      int basePay = minsWorked * payRate;
      return basePay + overtimePay(minsWorked, payRate);
    }
  }


}
