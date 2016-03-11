(ns clj-madmimi.core
  (:require [clj-http.client :as client]))

(def default-api-url   "https://api.madmimi.com/mailer")
(def alternate-api-url "https://madmimi.com/mailer")

(defn mad-mimi [api-url api-key username from mail]
  {:pre [(and (:promotion mail)
              (:to mail)
              (:subject mail)
              (:html mail))]}
  (let [payload (cond-> {:api_key        api-key
                         :username       username
                         :from           from
                         :promotion_name (:promotion mail)
                         :recipient      (:to mail)
                         :subject        (:subject mail)
                         :raw_html       (:html mail)}

                  (:reply-to mail)
                  (assoc :reply_to (:reply-to mail))

                  (not (nil? (:track-links? mail)))
                  (assoc :track_links (boolean (:track-links? mail))))]
    (client/post api-url {:form-params payload})))

(defn make-mad-mimi
  ([api-key username from]
   (make-mad-mimi default-api-url api-key username from))
  ([api-url api-key username from]
   (partial mad-mimi api-url api-key username from)))
