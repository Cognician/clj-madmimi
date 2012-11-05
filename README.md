# clj-madmimi

A Clojure library to send mail with the [Mad Mimi](https://madmimi.com/) [API](https://madmimi.com/developer)

## Installation

Add `[clj-madmimi "0.1.0"]` to your Leiningen project's dependencies.

## Usage

```clojure
;; grab the namespace
(use 'clj-madmimi.core)

;; grab a ready-to-use sending function
(def mm (clj-madmimi.core/make-mad-mimi "your api key" "your username" "your from address"))

;; prepare your mail
(def message {:promotion "MadMimi promotion"
              :from "Your registered MadMimi originator"
              :to "To address"
              :subject "Subject"
              :html "Your email's html"})

;; send!
(def send-result (mm message))

;; inspect result from MadMimi API
(print send-result)
```

## License

Copyright © 2012 Cognician Software.

With thanks to Steve Losh for [clojure-postmark](https://github.com/sjl/clojure-postmark).

Distributed under the Eclipse Public License, the same as Clojure.
