public class Producer implements Runnable {
    Store store;

    public Producer(Store store) {
        this.store = store;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {

            store.put(); //добавить в магазин товар
        }
    }
}

class Consumer implements Runnable{
    Store store;

    public Consumer(Store store) {
        this.store = store;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            store.get(); //купить товар
        }
    }
}

class Store{
    private int product;
    private final Object mon = new Object();

    public void get(){
        synchronized (mon){
            while(product <= 0){ //вложенное пробуждение
                try {
                    mon.wait(); // освободи монитор а сам уходи в ожидание
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            product--;
            System.out.println("Покупатель купил 1 товар");
            System.out.println("Товаров осталось " + product); //теперь надо сообщить put что что то изменилось, проверь
            mon.notify();//разбудит ПРОИЗВОЛЬНЫЙ поток, который в состоянии wait, если есть несколько то надо использовать mon.notifyAll();
        }
    }

    public void put(){ //должен добавлять товар
        synchronized (mon){
            while(product >= 3){ //вложенное пробуждение
                try {
                    mon.wait(); // освободи монитор а сам уходи в ожидание
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            product++;
            System.out.println("Производитель добавил 1 товар");
            System.out.println("Товаров осталось " + product); //теперь надо сообщить put что что то изменилось, проверь
            mon.notify();//разбудит ПРОИЗВОЛЬНЫЙ поток, который в состоянии wait, если есть несколько то надо использовать mon.notifyAll();
        }
    }
}

class MainApp{
    public static void main(String[] args) {
        Store store = new Store();
        Producer producer = new Producer(store);
        Consumer consumer = new Consumer(store);

        new Thread(producer).start(); //можно делать в таком сокращенном варианте если ссылки на трэды не нужны
        new Thread(consumer).start(); //если делаем ссылку на трэд то так не получится
    }
}
