(ns clj-madmimi.core
  (:require [clj-http.client :as client]))

(defn send-mimi-mail [api-key username mail]
  (let [mail-map {:username username
                  :api_key api-key
                  :promotion_name (:promotion mail)
                  :recipient (:to mail)
                  :subject (:subject mail)
                  :from (:from mail)
                  :raw_html (:html mail)}
        request {:form-params mail-map
                 :throw-exceptions false}
        response (client/post "https://api.madmimi.com/mailer" request)]
    response))

(defn mad-mimi [api-key username]
  (partial send-mimi-mail api-key username))