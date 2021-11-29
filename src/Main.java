public class Main {
    public static void main(String[] args) {
        //------Thread   MyThread-----------
//        MyThread thread1 = new MyThread("t1");
//        MyThread thread2 = new MyThread("t2");
//
//        thread1.start();
//        thread2.start();
        //------Thread   MyThread-----------

        //------Runnable   MyRunnable-----------

//        Thread thread1 = new Thread(new MyRunnable());
//        Thread thread2 = new Thread(new MyRunnable());
//
//        thread1.start();
//        thread2.start();
        //------Runnable   MyRunnable-----------

        //------Runnable   MyRunnable реализация анонимного класса-----------
//        Thread thread1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                  for (int i = 0; i < 5; i++) {
//                      System.out.println(i + " " + Thread.currentThread().getName());
//                  }
//            }
//        });
//        Thread thread2 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                  for (int i = 0; i < 5; i++) {
//                      System.out.println(i + " " + Thread.currentThread().getName());
//                  }
//            }
//        });
//
//        thread1.start();
//        thread2.start();
        //------Runnable   MyRunnable реализация анонимного класса-----------

//            Thread thread1 = new Thread(() -> {
//                  for (int i = 0; i < 5; i++) {
//                      System.out.println(i + " " + Thread.currentThread().getName());
//                  }
//            });
//            Thread thread2 = new Thread(() -> {
//              for (int i = 0; i < 5; i++) {
//                  System.out.println(i + " " + Thread.currentThread().getName());
//              }
//            });
//
//            thread1.start();
//            thread2.start();
        //------Runnable   MyRunnable реализация анонимного класса-----------
        //------Ожидание завершения потока------------------------
//        Thread thread1 = new Thread(() -> {
//            for (int i = 0; i < 10; i++) {
//                System.out.println(i + " " + Thread.currentThread().getName());
//            }
//        });
//        thread1.start();
//        try {
//            thread1.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("end" + " " + Thread.currentThread().getName());
        //------Ожидание завершения потока------------------------

//        Counter counter = new Counter();
//        final Object mon = new Object(); // внешняя синхронизация, второй варик ее
//
//        Thread t1 = new Thread(() -> {
//            for (int i = 0; i < 1000000; i++) {
//                synchronized (mon){
//                    counter.inc();
//                }
//
//            }
//        });
//
//        Thread t2 = new Thread(() -> {
//            for (int i = 0; i < 1000000; i++) {
//                synchronized (mon){
//                    counter.dec();
//                }
//
//            }
//        });
//
//        t1.start();
//        t2.start();
//
//        try {
//            t1.join();
//            t2.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(counter.getC()); //-90  будет любое значение т.к. чтение и запись пересекаются
//        // нужен монитор synchronized им может быть любой обьект (Counter)
//        //его следует использовать когда одну область памяти могут менять несколько потоков


//        CounterWithMon counter = new CounterWithMon();
//
//        Thread t1 = new Thread(() -> {
//            for (int i = 0; i < 1000000; i++) {
//                    counter.inc();
//            }
//        });
//
//        Thread t2 = new Thread(() -> {
//            for (int i = 0; i < 1000000; i++) {
//                    counter.dec();
//            }
//        });
//
//        t1.start();
//        t2.start();
//
//        try {
//            t1.join();
//            t2.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(counter.getC()); //внутренняя синхронизация в классе CounterWithMon с двумя обьектами и двумя синхронизациями для двух разных пар методов
//        //влияющих на одну переменную

        //-------------Остановка потока------------
        //1 спобоб можем предоставить возможность потока самой JVM, чтобы она имела право остановить поток он должен быть статусом demon поток

//        Thread timer = new Thread(new Runnable() {
//            int seconds = 0;
//
//            @Override
//            public void run() {
//                while (true) {
//                    try {
//                        Thread.sleep(1000); //метод заставляет ничего не делать поток сколько то милисекунд
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    seconds++;
//                    System.out.println("Time: " + seconds + "s");
//                }
//            }
//        });
//        timer.setDaemon(true); //указываем,что timer это демон поток СТАВИТСЯ ПЕРЕД start. Как только поток закончит свою работу он отключится, некий фоновый поток
//        //!!!!!! Блок final демон может не остановить
//        timer.start();
//
//        try {
//            Thread.sleep(5050); //метод заставляет ничего не делать поток сколько то милисекунд
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("end");

        //2 способ сами указываем ОСТАНОВИСЬ флагом

//            Thread timer = new Thread(new Runnable() {
//            int seconds = 0;
//
//            @Override
//            public void run() {
//                while (!Thread.currentThread().isInterrupted()) { //был ли поднят флаг
//                    seconds++;
//                    System.out.println("Time: " + seconds + "s");
//                }
//            }
//        });
//
//        timer.start();
//
//        try {
//            Thread.sleep(300); //метод заставляет ничего не делать поток сколько то милисекунд
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        timer.interrupt(); // через 300мс сработает метод interrupt(), тогда Thread.currentThread().isInterrupted() станет true, в while была инверсия, стало false
//        //и поток остановился
//        System.out.println("end");

        //------пример срабатывания InterruptedException-----

        Thread timer = new Thread(new Runnable() {
            int seconds = 0;
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) { //был ли поднят флаг
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        System.out.println("InterruptedException");
//                        Thread.currentThread().interrupt();
                        System.out.println("ffff");
                        break;
                    }
                    seconds++;
                    System.out.println("Time: " + seconds + "s");
                }
            }
        });

        timer.start();

        try {
            Thread.sleep(3000); //метод заставляет ничего не делать поток сколько то милисекунд
        } catch (InterruptedException e) { // если поток на время остановки interrupt(ом) был в состоянии sleep то есть уже был остановлин то бросится InterruptedException
            //но когда он бросается isInterrupted сбрасывается на false, в нашем случае поток с инверсией опять продолжит работу
            e.printStackTrace();
        }

        timer.interrupt(); // через 300мс сработает метод interrupt(), тогда Thread.currentThread().isInterrupted() станет true, в while была инверсия, стало false
        //и поток остановился
        System.out.println("end");
    }
}
