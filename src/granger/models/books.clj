(ns granger.models.books
  (:use ring.util.response)
  (:require [somnium.congomongo :as mongo]))

(def conn
  (mongo/make-connection "test"
                         :host "127.0.0.1"
                         :port 27017))

(def book-keys #{:created_at
                 :isbn
                 :title
                 :authors
                 :text})

(defn keys-exist [whitelist vals]
  (every? (partial contains? whitelist) vals))

(defn valid-book?
  "Check that map is a valid representation of a book"
  [m]
  (and (keys-exist book-keys (keys m))
       (keys-exist m book-keys)))

(defn add-book!
  "Create new book review"
  [book]
  {:pre [(valid-book? book)]}
  (mongo/with-mongo conn (mongo/insert! :test book)))

(defn update-book!
  "Update existing book review"
  [old updates]
  {:pre [(valid-book? (merge old updates))]}
  (mongo/with-mongo conn
    (mongo/update! :test old (merge old updates))))

(defn find-book
  "Returns book review for a given ISBN"
  [isbn]
  (mongo/with-mongo conn
    (mongo/fetch-one :test
                     :as :json
                     :where {:isbn isbn})))

(defn find-books
  "Returns a collection of book reviews"
  []
  (mongo/with-mongo conn
    (mongo/fetch :test :as :json)))