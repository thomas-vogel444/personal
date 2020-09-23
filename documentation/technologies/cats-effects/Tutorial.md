# Tutorial

From https://typelevel.org/cats-effect/tutorial/tutorial.html

## bracket

`Resource` is based on `bracket`

3 stages:
- resource acquisition
- usage
- release
    - Always run regardless of usage
    - Doesn't run if aquisition fails

Each stage defined by an `IO` instance

## Dealing with cancellation

Some `IO` instances are cancellable.
e.g. `IO` from `Resource.use`