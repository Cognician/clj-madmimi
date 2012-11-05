(ns clj-madmimi.test.core
  (:use midje.sweet)
  (:require [clj-madmimi.core :as clj-madmimi]))

(background (around :facts 
                    (with-redefs [clj-madmimi.core/send-mail identity]
                      ?form)))

(def mm (clj-madmimi/make-mad-mimi "NOT-A-VALID-API-KEY" "test@mail.com" "test@mail.com"))

(def test-html "<html><body><b>Test Body</b>[[tracking_beacon]]</body></html>")

(def test-email {:to "test@mail.com"
                 :subject "Test Subject"
                 :html test-html
                 :promotion "Test Promotion"})

(fact "Test MadMimi Mail uses the right structure."
  (mm test-email)
  => {:username "test@mail.com"
      :api_key "NOT-A-VALID-API-KEY"
      :promotion_name "Test Promotion"
      :from "test@mail.com"
      :recipient "test@mail.com"
      :subject "Test Subject"
      :raw_html test-html})