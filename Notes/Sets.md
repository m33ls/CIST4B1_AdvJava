## The Basis of Sets
* Definition: A Set is a collection that cannot contain duplicate elements
* Purpose: Sets are often used in mathematical implementations and in databasing
* Hash Table Framework: Sets are based on hash tables and obtain much of the benefits of their efficiency

* Uniqueness: Cannot have duplicate elements
* Unordered: No specific order; hash table
* Null Elements: Allows null elements to be inserted (Some implementations lack this)

* Hash Set: Uses a hash table, best performance, no order
* LinkedHashSet: Maintains insertion order, slightly slower than hashSet
* TreeSet: Elements are sorted according to their natural ordering or by a specified comparator

* Two fundamental types, dynamic and static sets
* Dynamic sets are mutable, meaning they allow for the addition, removal, and modification of elements after the set has been created. (ex: Hash Tables)
* Static sets are immutable, meaning one they are created, their elements cannot be changed, added, or removed. They have a fixed size and composition. (ex: unmodifiableSet)

## Multisets (Bags)
* Bags, or multisets are a data stucture that is very similar to sets in nature with one key difference
* Bags can contain duplicate items
* Still maintain unordered nature, still can be based on a hash table for efficiency

## Counter class
* Counter class belongs to our collections library ( Same as deque)
* Dictionary (hash table) based without focusing on key value pairs, and allows duplicate values to exist
* Servers as our built in bag / multiset class

