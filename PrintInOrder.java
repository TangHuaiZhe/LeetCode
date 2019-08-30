package leetcode;

import java.util.concurrent.CountDownLatch;

/**
 * author: tang created on: 2019-08-17 21:07 description:
 */

//三个不同的线程将会共用一个 Foo 实例。
//
//    线程 A 将会调用 one() 方法
//    线程 B 将会调用 two() 方法
//    线程 C 将会调用 three() 方法
//    请设计修改程序，以确保 two() 方法在 one() 方法之后被执行，three() 方法在 two() 方法之后被执行。

class Foo {

  private boolean firstFinished;
  private boolean secondFinished;
  private final Object lock = new Object();

  public Foo() {

  }

  public void first(Runnable printFirst) throws InterruptedException {

    synchronized (lock) {
      // printFirst.run() outputs "first". Do not change or remove this line.
      printFirst.run();
      firstFinished = true;
      lock.notifyAll();
    }
  }

  public void second(Runnable printSecond) throws InterruptedException {

    synchronized (lock) {
      while (!firstFinished) {
        lock.wait();
      }

      // printSecond.run() outputs "second". Do not change or remove this line.
      printSecond.run();
      secondFinished = true;
      lock.notifyAll();
    }
  }

  public void third(Runnable printThird) throws InterruptedException {

    synchronized (lock) {
      while (!secondFinished) {
        lock.wait();
      }

      // printThird.run() outputs "third". Do not change or remove this line.
      printThird.run();
    }
  }
}


class Foo2 {
  private CountDownLatch countDownLatchA;
  private CountDownLatch countDownLatchB;

  public Foo2() {
    countDownLatchA = new CountDownLatch(1);
    countDownLatchB = new CountDownLatch(1);
  }

  public void first(Runnable printFirst) throws InterruptedException {
    // printFirst.run() outputs "first". Do not change or remove this line.
    printFirst.run();
    countDownLatchA.countDown();
  }

  public void second(Runnable printSecond) throws InterruptedException {
    countDownLatchA.await();
    // printSecond.run() outputs "second". Do not change or remove this line.
    printSecond.run();
    countDownLatchB.countDown();
  }

  public void third(Runnable printThird) throws InterruptedException {
    countDownLatchB.await();
    // printThird.run() outputs "third". Do not change or remove this line.
    printThird.run();
  }
}

