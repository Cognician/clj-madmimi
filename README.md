# clj-madmimi

A Clojure library to send mail with the [Mad Mimi](https://madmimi.com/) [API](https://madmimi.com/developer)

## Installation

It's not up on Clojars yet, sorry! You'll have to drop this project into your Leiningen project's checkouts/ folder.

## Usage

```clojure
(use 'clj-madmimi.core)

;; grab a ready-to-use sending function
(def mm clj-madmimi.core/mad-mimi "your api key" "your username")

;; send!
(mm {:promotion "MadMimi promotion"
     :to "To address"
     :subject "Subject
     :from "Your registered MadMimi originator"
     :html "Your email's html"}
```

## License

Copyright © 2012 Cognician Software.

With thanks to Steve Losh for [clojure-postmark](https://github.com/sjl/clojure-postmark).

Distributed under the Eclipse Public License, the same as Clojure.
