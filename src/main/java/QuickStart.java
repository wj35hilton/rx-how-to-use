import rx.Observable;
import rx.functions.Action1;
import java.util.stream.IntStream;

public class QuickStart {
  public static void main(String [] args) {
    System.out.println("HELLO");
    hello("Paul", "Cecilia");

    synchObservable(); // https://github.com/ReactiveX/RxJava/wiki/How-To-Use-RxJava
  }

  public static void hello(String... names) {
    Observable.from(names).subscribe(new Action1<String>() {
      @Override
      public void call(String s) {
        System.out.println("Hello " + s + "!");
      }
    });
  }

  public static void synchObservable() {
      Observable obs = Observable.create( subscriber -> {
              IntStream
              .rangeClosed(0, 50)
              .forEach(x -> {
                      if (!subscriber.isUnsubscribed()) {
                          subscriber.onNext("value_#" + x);
                      }
                  });

              if (!subscriber.isUnsubscribed()) {
                  subscriber.onCompleted();
              }
          });

      obs.subscribe(s -> System.out.println(s));
  }

}
