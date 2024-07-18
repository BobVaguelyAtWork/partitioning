# partitionBy

A common exercise in programming is run-length encoding, where something like "aaabbccc" becomes "a3b2c3".
We're going to generalize that a little bit. 

`partitionBy` is a method that takes a List and a Closure (which defaults to the identity closure). The closure is applied to each element in the list, and the list is partitioned into sublists where each sublist is of consecutive items where the Closure returns an equal value.

For example, partitioning on identity returns lists of consecutive duplicates:
```groovy
partitionBy([4, 1, 1, 4, 3, 3]) == [[4], [1, 1], [4], [3, 3]]
```

We can group numbers by their magnitude/absolute value:
```groovy
partitionBy([8, -4, 4, 2, -3, 2, 1, -1, -1]) { Math.abs(it) } == [[8], [-4, 4], [2], [-3], [2], [1, -1, -1]]
```

And more generally, anything that we can do with each element in the list, we can use as a determinant for how
to partition the list.

