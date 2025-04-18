package org.io.io_wrapper;

import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Supplier;

public final class IO<A>{
    private final Effect<A> effect;

    private IO(Effect<A> effect){
        this.effect = effect;
    }

    public static <A> IO<A> of(Supplier<A> effect){
        return new IO<>(Effect.fromSupplier(effect));
    }

    public <B> IO<B> flatMap(Function<? super A, IO<B>> function){
        /*
          a
         */
        return new IO<>(
                () -> function.apply( // applyする
                        this.unsafeRun() // 状態を変化さてから(↑)
                        ) // ここの this で前の状態を引き継いでいる．
                        .unsafeRun() /*
                        apply : in A-> IO<B> なので，
                        unsafeRun : IO<B>->B してBにフラット化する
                        */
        );
    }

    public <B> IO<B> map(Function<? super A, ? extends B> function){

        return this.flatMap(a -> IO.of(// aがインスタンス化されていないといけないがVoid型の場合がある
                () -> function.apply(a)) // apply : @Nonnull in A -> out B の時 a=nullだと困ったことになる．
        );
    }

    public IO<Void> mapToVoid(Consumer<? super A> consumer){
        return this.flatMap(
                (a) -> IO.of(
                        () -> {
                            consumer.accept(a);
                            return null;
                        }
                )
        );
    }

    /**
     * 単純に consumer に作用させるだけで，そのものを返すメソッド
     * <p>{@code void setT(T t'){ this.t = t';}} を
     * <p>{@code A setT(T t'){ this.t=t'; return this; }}
     * <p>にするみたいな．
     * <p>
     *     {@code setT :: (&mut Self, T)->() // 何もしないのではなく}
     * </p>
     * <p>
     *   {@code setT :: (&mut Self, T)-> &mut Self // 変更したオブジェクトを返す}
     * <p/>
     *
     */
    public IO<A> peek(Consumer<? super A> consumer){
        return this.map(
                (a)->{
                    consumer.accept(a);
                    return a;
                }
        );
    }

    public A unsafeRun() {
        return this.effect.runUnsafe();
    }

    @FunctionalInterface
    public interface Effect<A> {
        A runUnsafe();
        static <A> Effect<A> fromSupplier(Supplier<A> supplier){
            return supplier::get;
        }
    }


}

