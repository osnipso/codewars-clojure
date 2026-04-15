# Clojure Study Plan

Goal: solve basic/intermediate LeetCode problems fluently in Clojure.

Assumes you've completed chapters 1–4 of *Clojure for the Brave and True* and have working knowledge of `map`, `filter`, `reduce`, `loop/recur`, and basic data structures.

---

## Phase 1 — Core Language Mastery

### 1.1 Destructuring

Clojure destructuring is pervasive. You'll use it in `let`, `fn`, `defn`, `loop`, and `for`.

**Topics**
- Sequential destructuring: `[a b & rest]`
- Map destructuring: `{:keys [x y] :or {y 0}}`
- Nested destructuring
- Destructuring in function parameters

**Exercises**
1. Write `first-and-last [coll]` — returns `[first last]` using only destructuring, no `first`/`last` calls.
2. Write `summarize [{:keys [name age city]}]` — returns `"name is age years old from city"`.
3. Write `swap-pairs [coll]` — given `[1 2 3 4]` returns `[2 1 4 3]`, using destructuring inside `loop`.
4. Write `deep-get [m]` — takes `{:a {:b {:c 42}}}` and returns `42` using nested destructuring in `let`.

---

### 1.2 Sequence Abstraction & Lazy Sequences

Understanding the seq abstraction unlocks nearly all of Clojure's power.

**Topics**
- `seq`, `cons`, `lazy-seq`, `iterate`, `cycle`, `repeat`, `range`
- Infinite sequences and how to consume them safely (`take`, `take-while`)
- `concat`, `interleave`, `interpose`, `partition`, `partition-by`
- `drop`, `drop-while`, `split-at`, `split-with`

**Exercises** *(cumulative — use destructuring from 1.1)*
1. Using `iterate`, generate the Fibonacci sequence. Take the first 20 numbers.
2. Write `running-sum [coll]` — returns a lazy sequence of cumulative sums: `[1 2 3]` → `[1 3 6]`.
3. Write `alternating [a b]` — returns an infinite lazy sequence alternating between `a` and `b`.
4. Write `chunk-when [pred coll]` — groups consecutive elements satisfying `pred` together (like `partition-by` but from scratch using `lazy-seq`).
5. Solve: given a sequence of integers, return all pairs `[i j]` (indices) where `(+ nums[i] nums[j]) = target`. *(Two-sum — use `map-indexed` and `for`)*

---

### 1.3 Higher-Order Functions & Function Composition

**Topics**
- `comp`, `partial`, `juxt`, `apply`, `constantly`, `complement`
- `every?`, `some`, `not-any?`
- `keep`, `mapcat`, `flatten`
- Threading macros: `->`, `->>`, `as->`, `some->`

**Exercises** *(cumulative)*
1. Using only `comp` and `partial`, write a `double-then-inc` function without `defn`.
2. Write `transform-keys [f m]` — applies `f` to every key in a map, using `reduce-kv`.
3. Write `find-first [pred coll]` — returns the first element satisfying `pred`, using `some`.
4. Refactor `newmap` and `newfilter` from `core.clj` to use threading macros (`->>`).
5. Write `pipeline [& fns]` — returns a function that applies each fn in sequence to its input (like `comp` but left-to-right). Verify with `((pipeline inc inc #(* % 3)) 1)` → `9`.

---

### 1.4 Maps, Sets & Frequencies

These are the bread and butter of LeetCode medium problems.

**Topics**
- `assoc`, `dissoc`, `update`, `merge`, `merge-with`
- `group-by`, `frequencies`, `zipmap`, `into`
- `select-keys`, `filter` on maps, `reduce-kv`
- Set operations: `clojure.set/union`, `intersection`, `difference`
- `sorted-map`, `sorted-set`, `priority-map` (via `clojure.data.priority-map`)

**Exercises** *(cumulative)*
1. Write `char-frequency [s]` — returns a frequency map of characters in a string.
2. Write `anagram? [s1 s2]` — returns true if two strings are anagrams. Use `frequencies`.
3. Write `group-by-length [words]` — groups a list of words by their length.
4. Write `invert-map [m]` — swaps keys and values. Handle duplicate values by collecting keys into a set.
5. Given a list of integers, find all pairs that sum to zero. Return them as a set of sorted pairs. *(Uses `frequencies` + `for` + `clojure.set`)*

---

### 1.5 Recursion Patterns

**Topics**
- `recur` and `loop` (you've done this — go deeper)
- Mutual recursion with `trampoline`
- `clojure.walk/postwalk`, `prewalk` for tree traversal
- Memoization: `memoize`, `def` + `atom` caches

**Exercises** *(cumulative)*
1. Write `flatten-map [m]` — flattens a nested map into a flat map with path-based keys: `{:a {:b 1}}` → `{[:a :b] 1}`. Use `postwalk` or explicit recursion.
2. Write `tree-sum [tree]` where a tree node is `{:val n :left ... :right ...}`. Sum all values.
3. Write `memoized-fib [n]` using `memoize`. Compare performance with the naive version.
4. Write `count-change [amount coins]` — classic dynamic programming: how many ways to make `amount` using `coins`? Use memoization.
5. Write `permutations [coll]` — returns all permutations of a collection. Use recursion.

---

## Phase 2 — Algorithmic Thinking in Clojure

At this point you have the language. Now practice translating common algorithm patterns into idiomatic Clojure.

### Patterns to learn

| Pattern | Clojure idiom |
|---|---|
| Sliding window | `partition`, manual `loop` with two indices |
| Two pointers | `loop` with index vars |
| Prefix sums | `reductions` |
| BFS/DFS | Queue via `clojure.lang.PersistentQueue`, recursion |
| Memoized DP | `memoize` or `atom`-backed cache |
| Binary search | `loop` with `low`/`high` vars |
| Sorting | `sort`, `sort-by`, custom comparators |

### Exercises *(cumulative — all phases)*

1. **Sliding window maximum** — given a vector and window size `k`, return the max in each window.
2. **Longest substring without repeating characters** — use a `loop` with a set tracking current chars.
3. **Valid parentheses** — use `reduce` with a stack (list). Return true if all brackets close correctly.
4. **Binary search** — implement `binary-search [v target]` on a sorted vector using `loop`.
5. **Merge intervals** — given `[[1 3] [2 6] [8 10]]`, merge overlapping intervals. Use `sort-by` + `reduce`.
6. **Word frequency top-K** — given a string, return the `k` most frequent words. Use `frequencies` + `sort-by`.
7. **Flood fill** — given a 2D grid (vector of vectors), implement flood fill using BFS with a queue.
8. **Coin change (min coins)** — DP using `memoize` or iterative `reduce` over amounts.
9. **LRU Cache** — implement a fixed-size LRU cache using `atom` + `array-map` (preserves insertion order).
10. **Graph BFS** — given an adjacency map `{:a [:b :c] :b [:d]}`, return BFS traversal order from a start node.

---

## Phase 3 — LeetCode Readiness

Work through these problem categories directly on LeetCode (filter by Clojure). Aim for at least 3–5 problems per category before moving on.

| Category | Key Clojure tools |
|---|---|
| Arrays & Hashing | `frequencies`, `group-by`, `set` |
| Two Pointers | `loop` with index bindings |
| Sliding Window | `partition`, manual loop |
| Stack | list as stack (`conj`/`pop`/`peek`) |
| Binary Search | `loop`, `quot` for midpoint |
| Trees | recursive `defn`, `postwalk` |
| Graphs | adjacency maps, BFS queue, `atom` visited set |
| Dynamic Programming | `memoize`, `reduce` over range |
| Strings | `clojure.string` ns, `seq` on strings |

**Suggested first 10 LeetCode problems in Clojure**
1. Two Sum (#1)
2. Valid Parentheses (#20)
3. Merge Two Sorted Lists (#21) — use recursion
4. Maximum Subarray (#53) — Kadane's with `reduce`
5. Climbing Stairs (#70) — memoized recursion
6. Longest Common Prefix (#14) — `apply` + `map` + `take-while`
7. Contains Duplicate (#217) — `frequencies` or `set`
8. Product of Array Except Self (#238) — `reductions`
9. 3Sum (#15) — sorting + two-pointer loop
10. Number of Islands (#200) — BFS/DFS on a grid

---

## Capstone Projects

### Project 1 — Mini Interpreter (~200–300 lines)

Build a calculator that parses and evaluates arithmetic expressions with variables.

**Features**
- Tokenizer: string → tokens
- Parser: tokens → AST (nested maps/vectors)
- Evaluator: AST + env map → result
- Support `+`, `-`, `*`, `/`, `let` bindings, and function calls

**What you'll practice:** recursion, data transformation pipelines, maps as environments, pattern matching via `cond`/`case`.

---

### Project 2 — CSV Data Processor (~250–400 lines)

A CLI tool that reads a CSV file and answers queries about the data.

**Features**
- Parse CSV into a sequence of maps (column name → value)
- Support commands: `filter`, `group-by`, `aggregate` (sum/avg/count), `sort`, `top-k`
- Pretty-print results as an ASCII table
- Compose commands via a pipeline (e.g., `filter age > 30 | group-by city | count`)

**What you'll practice:** sequence processing, higher-order functions, string parsing, `clojure.string`, threading macros, `reduce-kv`.

---

### Project 3 — Graph Pathfinder (~300–450 lines)

A tool that reads a weighted graph and answers shortest-path and connectivity queries.

**Features**
- Load a graph from an EDN file (adjacency list with weights)
- Implement Dijkstra's algorithm (use a priority queue or sorted set)
- Implement BFS for unweighted shortest path
- Detect cycles
- Find all connected components
- REPL-style CLI: `(shortest-path g :a :f)`, `(connected? g :a :b)`

**What you'll practice:** graphs as plain maps, `atom` for mutable state during traversal, `memoize`, priority queues, recursive algorithms, EDN I/O.

---

## Recommended References

- [ClojureDocs](https://clojuredocs.org) — community examples for every core fn
- [4Clojure / Exercism Clojure track](https://exercism.org/tracks/clojure) — small focused problems
- `(doc fn-name)` and `(source fn-name)` in the REPL — your best friends
- *Joy of Clojure* (Fogus & Houser) — deeper idioms, after Brave and True

## Companion Plan

[fp-study-plan.md](fp-study-plan.md) covers the general functional programming concepts behind this plan — implemented in parallel using Clojure and F#. Recommended to follow alongside this one.
