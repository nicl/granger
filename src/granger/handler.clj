(ns granger.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [granger.model :as model]))

(defroutes app-routes
  (GET "/" [] "Welcome to my book reviews site!")
  (POST "/books" [body] (model/create! body))
  (PUT "/books/:id" [id body] (model/update! id body))
  (GET "/books/:id" [id] (model/find-one id))
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
