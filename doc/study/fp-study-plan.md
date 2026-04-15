# Functional Programming Study Plan

Goal: understand the core concepts of functional programming that transcend any specific language, using **Clojure** and **F#** as dual implementation vehicles.

This plan is meant to run **alongside** [`clojure-study-plan.md`](clojure-study-plan.md). For each concept, implement the exercises in both languages. The contrast between a dynamically typed Lisp (Clojure) and a statically typed, functional-first language (F#) makes the general concept visible — you see what is the idea and what is just syntax.

> **Note:** Haskell is a natural next step after F# for going deeper into FP theory (enforced purity, type classes, lazy-by-default). Keep it in mind for later.

---

## How to Use This Plan

Each section identifies:
- The **general FP concept** — language-agnostic
- Its **Clojure expression** — how it looks in this project
- Its **F# expression** — the counterpart
- **Exercises** to implement in both languages

You do not need to master F# before starting. Learn just enough of the syntax to implement each exercise, then move on.

### F# setup

```bash
# Install the .NET SDK (includes F#)
# https://dotnet.microsoft.com/download

# Start F# Interactive (REPL)
dotnet fsi

# Run a script file
dotnet fsi myfile.fsx

# Create a console project
dotnet new console -lang F# -o myproject
cd myproject
dotnet run
```

---

## 1. First-Class and Higher-Order Functions

**The idea:** functions are values. They can be passed as arguments, returned from other functions, and stored in data structures.

| | Clojure | F# |
|---|---|---|
| Pass fn as arg | `(map inc [1 2 3])` | `List.map (fun x -> x + 1) [1; 2; 3]` |
| Return fn | `(defn adder [n] (fn [x] (+ x n)))` | `let adder n = fun x -> x + n` |
| Store fn | `(def ops {:add + :sub -})` | `let ops = [("add", (+)); ("sub", (-))]` |

**Exercises** — implement in both languages:
1. Write `applyTwice f x` — applies `f` to `x` twice: `applyTwice (+1) 3` → `5`.
2. Write `compose f g` — returns a function that applies `g` then `f` (implement `<<` from scratch).
3. Write `flip f` — returns a version of `f` with its first two arguments swapped.
4. Write `myMap f coll` — implement `map` from scratch using recursion only.
5. Write `myFilter pred coll` — implement `filter` from scratch using recursion only.

---

## 2. Pure Functions and Referential Transparency

**The idea:** a pure function always returns the same output for the same input and has no side effects. A referentially transparent expression can be replaced by its value without changing program behavior.

In both Clojure and F#, purity is a **discipline** — neither language enforces it via the type system (unlike Haskell). F# does signal impurity through naming conventions (`printfn`, `Console.ReadLine`) and the `unit` return type, but does not prevent side effects in pure-looking functions.

**Exercises:**
1. Identify which functions in `core.clj` are pure and which are not. List the impure ones and explain why (hint: `println`, `Thread/sleep`, `rand`, `time`).
2. Rewrite `hit` from `core.clj` so the randomness is injected as a parameter, making the core logic pure. Then port the pure version to F#.
3. In F#, write a pure function `collatz : int -> int list` that returns the Collatz sequence for a number. Then write a separate `main` that prints it — observe how isolating IO from logic is a convention, not enforced.

---

## 3. Immutability

**The idea:** values do not change after creation. Instead of mutating, you produce new values.

| | Clojure | F# |
|---|---|---|
| Immutable by default | Yes | Yes (`let` bindings) |
| Mutable escape hatch | `atom`, `ref`, `agent` | `mutable` keyword + `<-` operator |
| "Update" a map | `(assoc m :k v)` → new map | `{ record with Field = v }` → new record |
| "Update" a list | `(conj coll x)` → new list | `x :: list` → new list |

**Exercises:**
1. In both languages, write `updateAll f coll` — returns a new collection with `f` applied to every element. Verify the original is unchanged.
2. In Clojure, write a counter using `atom`. In F#, write the equivalent using a `mutable` variable inside a function. Compare: what does each require syntactically to mutate state?
3. In F#, define a record `type Point = { X: float; Y: float }`. Write `translate dx dy point` that returns a new `Point` without mutating the original.

---

## 4. Recursion and Tail-Call Optimization

**The idea:** recursion is the primary looping mechanism in FP. Tail-call optimization (TCO) prevents stack overflow when the recursive call is the last operation.

| | Clojure | F# |
|---|---|---|
| Basic recursion | `(defn f [n] (if ...))` | `let rec f n = if ...` |
| Tail call | `(recur ...)` explicit | TCO automatic for tail calls |
| Accumulator pattern | `(loop [acc 0] (recur ...))` | helper `let rec loop acc = ...` |

Note: F# requires the `rec` keyword to define recursive functions. Without it, the function name is not in scope inside its own body.

**Exercises** — implement in both:
1. `factorial n` — naive recursion first, then tail-recursive with an accumulator.
2. `sumList coll` — sum a list using recursion and an accumulator.
3. `flattenList nested` — flatten an arbitrarily nested list using recursion.
4. `treeDepth tree` — given a binary tree, return its maximum depth.
5. Implement `myFoldLeft f acc coll` (`reduce`) from scratch using tail recursion.

---

## 5. Function Composition and Pipelines

**The idea:** build complex behavior by composing small, single-purpose functions. Data flows through a pipeline of transformations.

F#'s pipe operator `|>` is its most idiomatic feature and maps directly to Clojure's `->>`.

| | Clojure | F# |
|---|---|---|
| Compose (right-to-left) | `(comp f g h)` | `f << g << h` |
| Compose (left-to-right) | — | `h >> g >> f` |
| Pipeline (left-to-right) | `(->> x f g h)` | `x \|> f \|> g \|> h` |
| Partial application | `(partial + 10)` | `(+) 10` or `fun x -> x + 10` |

**Exercises:**
1. Write `pipeline fns` — compose a list of functions left-to-right and apply to input. Implement in both.
2. Given a list of strings, write a single piped expression (no intermediate `let` bindings) that: filters empty strings, trims whitespace, converts to uppercase, removes duplicates, and sorts.
   - Clojure: use `->>`.
   - F#: use `|>` with `List` functions.
3. Write `repeatedlyApply f n x` — applies `f` to `x` exactly `n` times. Implement using `reduce`/`List.fold`.

---

## 6. Partial Application and Currying

**The idea:** currying transforms a function of N arguments into a chain of N single-argument functions. Partial application fixes some arguments, producing a new function with fewer arguments.

Like Haskell, **F# curries all functions by default**. Clojure does not — you use `partial` explicitly.

| | Clojure | F# |
|---|---|---|
| Partial application | `(partial + 5)` | `(+) 5` — automatic |
| Curried fn | manual: `(fn [x] (fn [y] ...))` | automatic: `let add x y = x + y` |
| Apply to list | `(apply + [1 2 3])` | `List.fold (+) 0 [1; 2; 3]` |

**Exercises:**
1. In Clojure, write a manually curried `add` callable as `((add 3) 5)` → `8`.
2. In F#, define `let add x y = x + y` and observe that `add 3` is already a valid partial function. Use it with `List.map (add 3) [1; 2; 3]`.
3. In both languages, write `zipWith f xs ys` from scratch — takes a binary function and two lists, applies element-wise.
4. Write `curry f` in Clojure — takes a 2-arg function and returns its curried form.
5. Write `uncurry f` in Clojure — takes a curried function and returns a 2-arg function.

---

## 7. Lazy Evaluation

**The idea:** expressions are not evaluated until their value is needed. This allows infinite data structures and deferred computation.

Both Clojure and F# are **eager by default**. Both offer lazy sequences as an opt-in: Clojure via `lazy-seq`/`iterate`, F# via `seq {}` computation expressions and `Seq.initInfinite`.

| | Clojure | F# |
|---|---|---|
| Infinite sequence | `(iterate inc 0)` | `Seq.initInfinite id` |
| Take from infinite | `(take 10 (iterate inc 0))` | `Seq.initInfinite id \|> Seq.take 10` |
| Lazy cons | `(lazy-seq (cons x (f ...)))` | `seq { yield x; yield! f ... }` |

**Exercises:**
1. In both languages, define an infinite sequence of natural numbers and take the first 100 even ones.
2. In both, define an infinite Fibonacci sequence and take the first 20 terms.
3. In both, write `takeWhileLessThan n coll` — takes elements from a (potentially infinite) sequence while they are less than `n`.
4. In both, write a lazy prime number sequence using the Sieve of Eratosthenes. Take the first 50 primes.
5. Compare memory behavior: in Clojure, hold a reference to the head of an infinite seq and observe memory. In F#, use `Seq` (lazy) vs `List` (eager) for the same operation — observe the difference.

---

## 8. Algebraic Data Types and Pattern Matching

**The idea:** a type is defined as a sum of possible shapes (discriminated union / tagged union). You then handle each shape exhaustively via pattern matching.

F# has **native discriminated unions** and **exhaustiveness-checked pattern matching** — a major advantage over Clojure's dynamic approach.

| | Clojure | F# |
|---|---|---|
| Sum type | tagged map: `{:type :circle :r 5}` | `type Shape = Circle of float \| Rectangle of float * float` |
| Pattern match | `(case (:type s) :circle ...)` | `match s with \| Circle r -> ...` |
| Exhaustiveness | not checked | compiler warns on missing cases |
| Nested match | `(cond ...)` chains | nested `match` expressions |

**Exercises:**
1. In F#, define `type Shape = Circle of float | Rectangle of float * float | Triangle of float * float * float`. Write `area : Shape -> float` using pattern matching.
2. In Clojure, represent the same shapes as tagged maps and write `area` using `case` on `:type`.
3. In both, define a binary tree: a node has a value and left/right children; a leaf has no children. Write `treeSum` and `treeDepth`.
4. In F#, define `type Option<'a> = None | Some of 'a` (without using the built-in). Write `safeDiv : int -> int -> Option<int>` returning `None` on division by zero.
5. In Clojure, represent `Option` as `nil` (None) or a value (Some). Write `safeDiv` and chain two safe divisions together.

---

## 9. Option, Result, and Railway-Oriented Programming

**The idea:** instead of throwing exceptions or returning `nil`, model failure explicitly as a value. Chain operations so that the first failure short-circuits the rest. This pattern is called **Railway-Oriented Programming** in F# circles — a practical, concrete approach to what Haskell calls the `Maybe` and `Either` monads.

| | Clojure | F# |
|---|---|---|
| Absent value | `nil` | `None` (type `Option<'a>`) |
| Success/failure | convention or exception | `Ok value` / `Error msg` (type `Result<'a,'e>`) |
| Map over present value | `(some-> val f)` | `Option.map f opt` |
| Chain fallible ops | `(some-> val f g h)` | `result { let! x = f(); let! y = g(x); return y }` |

**Exercises:**
1. In F#, write `safeSqrt : float -> Option<float>` — returns `None` for negative inputs. Chain it with `safeDiv` from section 8 using `Option.bind`.
2. In Clojure, write the equivalent chain using `some->`.
3. In F#, write a function `parseAndDouble : string -> Result<int, string>` — parses a string as int (returning `Error` on failure) then doubles it. Chain two of these with `Result.bind`.
4. In both, implement `maybe-map` (Clojure) / `Option.map` (F#) from scratch — applies a function to the inner value only if it is present.
5. In F#, rewrite the `hit` function (ported from `core.clj`) to return `Result<BodyPart, string>` instead of throwing on an empty list.

---

## 10. Type Systems and Polymorphism

**The idea:** parametric polymorphism (generics) allows functions to work over any type. Type inference deduces types without annotation. Interfaces/protocols allow ad-hoc polymorphism — the same function name behaves differently for different types.

F# has strong type inference and generics. It uses **interfaces** for ad-hoc polymorphism rather than Haskell-style type classes, which feels more familiar if you have OOP background.

| | Clojure | F# |
|---|---|---|
| Generic function | automatic (dynamic typing) | inferred: `let myLength lst = ...` → `'a list -> int` |
| Ad-hoc polymorphism | protocols + `extend-protocol` | interfaces + object expressions |
| Type annotation | rarely needed | optional: `let f (x: int) = ...` |
| Exhaustiveness | none | discriminated union match |

**Exercises:**
1. In F#, write `myLength : 'a list -> int` — works for a list of any type. Observe the inferred type signature in `dotnet fsi`.
2. In F#, write `myZip : 'a list -> 'b list -> ('a * 'b) list` — pairs two lists of potentially different types.
3. In F#, define an interface `IDescribable` with method `Describe : unit -> string`. Implement it for a `Circle` and a `Rectangle` using object expressions.
4. In Clojure, define a protocol `Describable` with `describe`. Implement it for a `Shape` record via `extend-protocol`. Compare the ergonomics to F# interfaces.
5. In F#, write `largest : 'a list -> 'a when 'a : comparison` — works for any comparable type. Test with `int list`, `string list`, and `char list`.

---

## Capstone: Same Project, Two Languages

Implement **Project 1 (Mini Interpreter)** from [`clojure-study-plan.md`](clojure-study-plan.md) in both Clojure and F#.

| Aspect | Clojure approach | F# approach |
|---|---|---|
| AST representation | nested maps/vectors | discriminated union: `type Expr = Num of float \| Add of Expr * Expr \| ...` |
| Pattern matching on AST | `cond`/`case` on `:type` key | native `match`, exhaustiveness-checked |
| Environment | plain map passed as argument | `Map<string, Value>` passed explicitly |
| Error handling | `nil` or exception | `Result<Value, string>` |
| Parsing | string → data with manual recursion | manual recursion or FParsec library |

The F# version will be more explicit about types and failure modes. The Clojure version will be more flexible and data-centric. Both are valid — the comparison reveals which design decisions are driven by the language and which are universal.

---

## Concept Map: General FP vs. Language-Specific

| Concept | General FP | Clojure-specific | F#-specific |
|---|---|---|---|
| Higher-order functions | Yes | `map`, `filter`, `reduce` names | `List.map`, `List.filter`, `List.fold` names |
| Pure functions | Yes | discipline only | discipline only (no IO enforcement) |
| Immutability | Yes | persistent data structures | `let` bindings; records with `with` |
| Recursion + TCO | Yes | `loop/recur` explicit | `rec` keyword required; TCO automatic |
| Lazy evaluation | Yes | opt-in via `lazy-seq` | opt-in via `seq {}` / `Seq` module |
| Function composition | Yes | `comp`, threading macros | `>>`, `<<`, `\|>` pipe operator |
| Partial application | Yes | `partial` fn | automatic (all fns curried) |
| Closures | Yes | standard `fn` | standard `fun` lambda |
| Memoization | Yes | `memoize` fn | manual with `Dictionary` or custom |
| Algebraic data types | FP-common | maps + tags (no enforcement) | discriminated unions, native |
| Pattern matching | FP-common | `cond`/`case`/`core.match` | native `match`, exhaustiveness-checked |
| Ad-hoc polymorphism | FP-common | protocols | interfaces + object expressions |
| Monads / chaining | FP-common | informal (`some->`, `mapcat`) | `Option`/`Result` + computation expressions |
| Macros / homoiconicity | Lisp-specific | `defmacro` | not available |
| Concurrency model | language-specific | `atom`, `ref`, `agent` | `async {}`, `MailboxProcessor` |

---

## Recommended Resources

**F#**
- [*F# for Fun and Profit*](https://fsharpforfunandprofit.com) — free, comprehensive, by Scott Wlaschin; start here
- [*Real-World Functional Programming*](https://www.manning.com/books/real-world-functional-programming) — Petricek & Skeet; practical F# and C# side by side
- [Exercism F# track](https://exercism.org/tracks/fsharp) — small exercises with mentorship
- `dotnet fsi` — F# Interactive REPL, your primary tool

**FP Theory**
- [*Structure and Interpretation of Computer Programs* (SICP)](https://mitp-content-server.mit.edu/books/content/sectbyfn/books_pres_0/6515/sicp.zip/index.html) — foundational, uses Scheme (another Lisp)
- *Category Theory for Programmers* (Milewski) — theory behind functors/monads, free PDF; worth reading after F# clicks

**When you're ready for Haskell**
- [*Learn You a Haskell for Great Good!*](http://learnyouahaskell.com) — free online, friendly intro
- [*Real World Haskell*](http://book.realworldhaskell.org) — free online, practical depth
