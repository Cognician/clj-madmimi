(ns clj-madmimi.core
  (:require [clj-http.client :as client]))

(def mad-mimi-api "https://api.madmimi.com/mailer")

(defn send-mail
  [mail]
  (client/post mad-mimi-api {:form-params mail}))

(defn mad-mimi
  [api-key username from mail]
  {:pre [(every? #{:promotion :to :subject :html} (keys mail))]}
  (send-mail {:api_key api-key
              :username username
              :from from
              :promotion_name (:promotion mail)
              :recipient (:to mail)
              :subject (:subject mail)
              :raw_html (:html mail)}))

(defn make-mad-mimi
  [api-key username from]
  (partial mad-mimi api-key username from))
