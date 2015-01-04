(ns granger.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [granger.models.books :as books]
            [ring.middleware.json :as json]))

(defroutes app-routes
  (GET "/" [] "Welcome to my book reviews site!")
  (GET "/books" [] (books/search-books))
  (GET ["/books/:isbn", :isbn #"[0-9]*"] [isbn] (books/fetch-book isbn))
  (PUT "/books/:isbn" [isbn :as req] (books/upsert-book! isbn (:body req)))
  (route/not-found "Not Found"))

(def app
  (-> (handler/site app-routes)
      (json/wrap-json-body {:keywords? true})
      (json/wrap-json-response)))
