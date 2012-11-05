(ns clj-madmimi.test.core
  (:use midje.sweet)
  (:require [clj-madmimi.core :as clj-madmimi]))

(def mm (clj-madmimi/mad-mimi "NOTAVALIDAPIKEY"
                                         "test@mail.com"))

(def test-html "<html><body><b>Test Body</b>[[tracking_beacon]]</body></html>")

(def test-email {:to "test@mail.com"
                 :from "test@mail.com"
                 :subject "Test Subject"
                 :html test-html
                 :promotion "Test Promotion"})

(fact "Test MadMimi Mail with wrong credentials"
  (mm test-email)
  => (contains
      {:body "Authentication failed"
       :status 401}))