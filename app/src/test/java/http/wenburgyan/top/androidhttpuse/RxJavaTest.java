package http.wenburgyan.top.androidhttpuse;

import org.junit.Test;

import java.util.concurrent.Callable;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RxJavaTest {

    @Test
    public void testHelloWorld(){
        System.out.println("Hello world");

        Flowable.just("1", "2").subscribe(System.out::println);

        //method reference
        Flowable.just("method reference")
                .subscribe(System.out::println);

        //lambda expression
        Flowable.just("lambda expression")
                .subscribe(s -> System.out.println(s));

        //Anonymous inner class
        Flowable.just("Anonymous inner class")
                .subscribe(new Consumer<String>() {
                    @Override public void accept(String s) {
                        System.out.println(s);
                    }
                });
    }

    @Test
    public void testObservable(){
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                while (!emitter.isDisposed()) {
                    long time = System.currentTimeMillis();
                    emitter.onNext(time);
                    Thread.sleep(1000);
                    if (time % 2 != 0) {
                        emitter.onError(new IllegalStateException("Odd millisecond!"));
                        break;
                    }
                }
            }
        })
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object x) throws Exception {
                        System.out.println(x);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });

        // written in simple form with lambda and method reference
        Observable.create(emitter -> {
            while (!emitter.isDisposed()) {
                long time = System.currentTimeMillis();
                emitter.onNext(time);
                Thread.sleep(1000);
                if (time % 2 != 0) {
                    emitter.onError(new IllegalStateException("Odd millisecond!"));
                    break;
                }
            }
        })
                .subscribe(System.out::println, Throwable::printStackTrace);
    }

    @Test
    public void testBackGroundWork(){
        System.out.println(Thread.currentThread().getName()+"----start");
        Flowable.fromCallable(() -> {
            System.out.println(Thread.currentThread().getName()+"---- in call()");
            Thread.sleep(1000); //  imitate expensive computation
            return "Done";
        })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .subscribe((String s) -> System.out.println(Thread.currentThread().getName()+"---- observer:"+s), Throwable::printStackTrace);

        System.out.println(Thread.currentThread().getName()+"----read to sleep");
        try {
            Thread.sleep(2000); // <--- wait for the flow to finish
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"----end");
    }


}
