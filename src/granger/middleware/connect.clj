(ns granger.middleware.connect
  (:require somnium.congomongo :as mongo))

(def conn
  (mongo/make-connection "test"
                         :host "127.0.0.1"
                         :port 27017))

(defn wrap-connection
  "Middleware that connects to a database and provides the connection
  to the response."
  [handler]
  (fn [request]
      ((mongo/set-connection! conn)
       (handler request))))