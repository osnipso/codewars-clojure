# REPL, Calva Jack-in, and Loading Source Files

## What Happens at REPL Startup

The behavior is the same whether you use **Calva Jack-in** or **`lein repl`** in the terminal. The key driver is the `:main` entry in `project.clj`.

### Effect of `:main` in `project.clj`

```clojure
:main ^:skip-aot clojure-noob.core
```

Leiningen reads this when starting any REPL (headless or interactive) and **loads that namespace automatically**. As a result:

- All `defn`s in `core.clj` are defined and available immediately — no manual `require` needed.
- All **top-level expressions** (bare `(time ...)`, `(def ...)`, etc.) are also executed at startup — which is why you see a pause: the `Thread/sleep 1000` calls inside `vampire-related-details` run during load.
- The REPL starts in the `clojure-noob.core` namespace, not `user`.

### What Calva Jack-in Does

When you run **Calva: Start Project REPL and Connect (Jack-in)** in VS Code:

1. Calva reads `project.clj` to determine the build tool and dependencies.
2. It starts a Leiningen subprocess running `lein repl :headless`, which launches an nREPL server.
3. Leiningen loads the `:main` namespace as part of startup (same as plain `lein repl`).
4. Calva connects its nREPL client to that server.

Calva does not do anything extra regarding namespace loading — the `:main` key does all the work.

### Verifying the active namespace

```clojure
*ns*
;; => #object[clojure.lang.Namespace 0x... "clojure-noob.core"]
```

---

## Loading a Separate Source File / Namespace

If you create a second file, e.g. `src/clojure_noob/utils.clj` with `(ns clojure-noob.utils ...)`, it is **on the classpath** but not loaded until you explicitly ask for it.

### Option 1 — `require` (recommended)

```clojure
;; Load the namespace, refer to it with an alias
(require '[clojure-noob.utils :as u])
(u/some-fn 42)

;; Load and pull specific vars into current namespace
(require '[clojure-noob.utils :refer [some-fn other-fn]])
(some-fn 42)

;; Pull everything in (convenient for exploration, avoid in production code)
(require '[clojure-noob.utils :refer :all])
```

To **reload after editing**:
```clojure
(require '[clojure-noob.utils :refer :all] :reload)
```

### Option 2 — `use` (deprecated, avoid)

```clojure
(use 'clojure-noob.utils) ; same as require + :refer :all, but discouraged
```

### Option 3 — `load-file` (by path, not namespace)

```clojure
(load-file "src/clojure_noob/utils.clj")
```

Useful when you want to load a file that isn't on the classpath, but normally `require` is preferred.

### Option 4 — Calva: Load/Evaluate Current File

Open the file in VS Code and press `Ctrl+Alt+C Enter` (same on macOS — Calva uses `Ctrl`, not `Cmd`, for all its chord shortcuts on every platform).
Calva evaluates the entire file in its declared namespace and makes it available to the REPL.

### Option 5 — Declare it in `ns` of another file

In `core.clj` (or any file), add to its `ns` form:
```clojure
(ns clojure-noob.core
  (:require [clojure-noob.utils :as u]))
```
When `core.clj` is loaded, `utils.clj` is automatically loaded too.

---

## Using `lein repl` in the Terminal

Run this from the project root (where `project.clj` lives):

```bash
$ cd ~/dev/learning/clojure-noob
$ lein repl
```

```
nREPL server started on port ...
REPL-y 0.5.1, nREPL 0.9.0
Clojure 1.11.1
clojure-noob.core=>
```

Because `:main clojure-noob.core` is set in `project.clj`, all functions from `core.clj` are immediately available.

### Loading other source files / namespaces

Given a second file at `src/clojure_noob/utils.clj` declaring `(ns clojure-noob.utils ...)`:

```clojure
;; Load with an alias
clojure-noob.core=> (require '[clojure-noob.utils :as u])

;; Load multiple namespaces in one form
clojure-noob.core=> (require '[clojure-noob.utils :as u]
                             '[clojure-noob.other :refer [some-fn]])

;; Load a file by path (when not on the classpath, or for quick scripts)
clojure-noob.core=> (load-file "src/clojure_noob/utils.clj")
```

### Reloading after edits

When you edit a source file and want the REPL to pick up the changes:

```clojure
clojure-noob.core=> (require '[clojure-noob.utils :refer :all] :reload)
```

`:reload` forces re-evaluation of the file even if the namespace was already loaded.

### Smarter reloading with `tools.namespace`

For larger projects with many interdependent namespaces, `:reload` only reloads the one file. `tools.namespace` reloads all changed namespaces in dependency order:

```clojure
(require '[clojure.tools.namespace.repl :refer [refresh]])
(refresh)
```

Requires adding `[org.clojure/tools.namespace "1.4.0"]` to `:dependencies` in `project.clj`.

### If `:main` is not set

Without `:main` in `project.clj`, `lein repl` starts in the bare `user` namespace with nothing loaded. You would then require namespaces manually:

```clojure
user=> (require '[clojure-noob.core :refer :all])
user=> (add-100 5)
;; => 105
```

Alternatively, set `:repl-options {:init-ns clojure-noob.core}` in `project.clj` to control the startup namespace explicitly, or create a `dev/user.clj` file that requires whatever you need at startup.

---

## Summary Table

| Scenario | Namespace loaded? | How |
|---|---|---|
| `lein repl` or Calva Jack-in + `:main` in `project.clj` | Yes | Leiningen loads `:main` ns on startup |
| `lein repl` or Calva Jack-in, no `:main` | No | Must `require` manually |
| `lein repl` + `:repl-options {:init-ns ...}` | Yes | Alternative to `:main` for REPL-only config |
| `lein repl` + `dev/user.clj` | Yes | Auto via `user` ns init |
| Calva Load File (`Ctrl+Alt+C Enter`) | Yes | Evaluates open file (same shortcut on macOS) |
