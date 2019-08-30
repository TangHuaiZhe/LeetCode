package leetcode;

/**
 * author: tang created on: 2019-08-17 21:20 description:
 */
class PrintFooBarAlternately {

  public static void main(String[] args) {
    FooBar fooBar = new FooBar(5);
    new Thread(() -> {
      try {
        fooBar.foo(() -> System.out.print("foo"));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();

    new Thread(() -> {
      try {
        fooBar.bar(() -> System.out.println("bar"));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();
  }
}

class FooBar {

  private int n;
  //private Semaphore semaphoreFoo=new Semaphore(0);
  //private Semaphore semaphoreBar=new Semaphore(0);
  private volatile int semaphore1 = 0;
  private volatile int semaphore2 = 0;

  FooBar(int n) {
    this.n = n;
  }

  public void foo(Runnable printFoo) throws InterruptedException {
    for (int i = 0; i < n; i++) {
      if (semaphore1 == 0) {
        printFoo.run();
        //由于下面阻塞了，所以这里变为0，下面的方法就能继续执行
        //semaphoreBar.release();
        semaphore1--;
        //这里让他等一会，等到bar()执行完
        //semaphoreFoo.acquire();
        semaphore2++;
      }
    }
  }

  public void bar(Runnable printBar) throws InterruptedException {
    for (int i = 0; i < n; i++) {
      if (semaphore2 == 1) {
        printBar.run();
        // 进来先变为1，就会等上面的release()使他变为0，才进行，所以肯定在foo之后。
        //semaphoreBar.acquire();
        semaphore1++;
        //bar()执行完了，就让foo()继续。
        //semaphoreFoo.release();
      }
    }
  }
}
