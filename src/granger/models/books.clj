(ns granger.models.books
  (:use ring.util.response)
  (:require [somnium.congomongo :as mongo]))

(def conn
  (mongo/make-connection "test"
                         :host "127.0.0.1"
                         :port 27017))

(defn add-book!
  "Create new book review"
  [data]
  "You have added a new book!")

(defn update-book!
  "Update existing book review"
  [id data]
  "An update action.")

(defn find-book
  "Returns book review for a given ISBN"
  [isbn]
  (mongo/with-mongo conn
    (response (mongo/fetch-one :test :as :json))))

(defn find-books
  "Returns a collection of book reviews"
  []
  (mongo/with-mongo conn
    (response (mongo/fetch :test :as :json))))

(defn is-valid?
  "Whether data is a valid book review representation"
  [data])