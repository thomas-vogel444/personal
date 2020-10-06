# Datatypes

- IO
- SyncIO
- Fiber
    - A pure result of a Concurrent data type being started concurrently and that can be either joined or canceled
- Resource
    - `Resource.make` to create a resource and define how it is closed.

- Clock
- ContextShift
    - Pure equivalent of an `ExecutionContext`
- Timer
    - Pure scheduler. Provides the ability to get the current time and delay the execution of a task with a specified time duration.
    - Instance for IO is required by IO.sleep, timeout, etc.