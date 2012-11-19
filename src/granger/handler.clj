(ns granger.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [granger.models.books :as books]))

(defroutes app-routes
  (GET "/" [] "Welcome to my book reviews site!")
  (GET "/books" [] (books/find-books))
  (GET ["/books/:isbn", :isbn #"[-0-9]*"] [isbn] (books/find-book isbn))
  (POST "/books" [body] (books/add-book! body))
  (PUT "/books/:isbn" [isbn] (books/update-book! isbn))
  (route/not-found "Not Found"))

(def app (handler/site app-routes))