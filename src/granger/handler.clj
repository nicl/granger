(ns granger.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [granger.model :as model]
            [granger.middleware.connect :as connect]))

(defroutes app-routes
  (GET "/" [] "Welcome to my book reviews site!")
  (GET "/books" [] (model/find-books))
  (GET "/books/:id" [id] (model/find-book id))
  (POST "/books" [body] (model/add-book! body))
  (PUT "/books/:id" [id body] (model/update-book! id body))
  (route/not-found "Not Found"))

(def app
(-> (handler/site app-routes)
    (connect/wrap-connection)))
