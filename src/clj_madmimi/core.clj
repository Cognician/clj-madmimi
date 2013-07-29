(ns clj-madmimi.core
  (:require [clj-http.client :as client]))

(def mad-mimi-api "https://api.madmimi.com/mailer")

(defn send-mail
  [mail]
  (client/post mad-mimi-api {:form-params mail}))

(defn mad-mimi
  [api-key username from mail]
  {:pre [(and (:promotion mail)
              (:to mail)
              (:subject mail)
              (:html mail))]}
  (let [to-send {:api_key api-key
                 :username username
                 :from from
                 :promotion_name (:promotion mail)
                 :recipient (:to mail)
                 :subject (:subject mail)
                 :raw_html (:html mail)}
        to-send (if-let [reply-to (:reply-to mail)]
                  (assoc to-send :reply_to reply-to)
                  to-send)]
    (send-mail to-send)))

(defn make-mad-mimi
  [api-key username from]
  (partial mad-mimi api-key username from))
