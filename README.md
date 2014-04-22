# clj-lqs

A Clojure client for Loganis Query Script API.

## Usage with Leiningen

``` clojure
[clj-lqs 0.1.0]
```

## Query functions

* You need a token string and a query map
* `(lqs->map token query)` gives result as a map with :colnames and :rows as keys
* `(lqs->csv token query)` gives result as csv string

## Documentation

* Visit http://docs.loganis.com

## License

Copyright © 2014 Loganis - iWebMa Ltd.

Distributed under the Eclipse Public License, the same as Clojure.
