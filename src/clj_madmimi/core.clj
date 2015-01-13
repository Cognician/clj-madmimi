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
    (send-mail payload)))

(defn make-mad-mimi
  [api-key username from]
  (partial mad-mimi api-key username from))
