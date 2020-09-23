# IO

From https://typelevel.org/cats-effect/datatypes/io.html

A data type for encoding side effects as pure values, capable of expressing both synchronous and asynchronous computations.

IO is trampolined in its flatMap evaluation. This means that you can safely call flatMap in a recursive function of arbitrary depth, without fear of blowing the stack.

# Describing Effects

- Pure values
    - `IO.pure`
    - `IO.unit`
- Synchronous Effects
    - `IO.apply`
        ```scala
        def apply[A](body: => A): IO[A] = ???
        ```
- Asynchronous Effects
    - `IO.async`
        - Describes simple asynchronous processes that cannot be canceled
            ```scala
            def async[A](k: (Either[Throwable, A] => Unit) => Unit): IO[A] = ???
            ```
    - `IO.cancelable`
        - Describes cancelable `IO`
            ```scala
            def cancelable[A](k: (Either[Throwable, A] => Unit) => IO[Unit]): IO[A] = ???
            ```
    - `IO.never`
        - represents a non-terminating `IO` defined in terms of `async`

# Deferred Execution

- `IO.suspend`

# Concurrency and Cancellation

Cancellation status is only checked after asynchronous boundaries.
It can be acheived in the following way:
- Building it with `IO.cancelable`, `IO.async`, `IO.asyncF`, `IO.bracket`
- Using `IO.cancelBoundary`, `IO.shift`

`IO` tasks that are cancelable usually become non-terminating on `cancel`

# Shifting 

Use cases
- Shifting blocking actions off of the main compute pool.
- Defensively re-shifting asynchronous continuations back to the main compute pool.
- Yielding control to some underlying pool for fairness reasons.
- Preventing an overflow of the call stack in the case of improperly constructed async actions.

