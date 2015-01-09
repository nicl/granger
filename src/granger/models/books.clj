(ns granger.models.books
  (:require [clojurewerkz.elastisch.native :as es]
            [clojurewerkz.elastisch.native.document :as esd]
            [clojurewerkz.elastisch.native.response :as esr]
            [ring.util.response :refer :all]))

(def conn (es/connect [["127.0.0.1" 9300]] {"cluster.name" "elasticsearch"}))

(def index "books")

(def doc-type "book")

(def book-keys #{:authors
                 :categories
                 :isbn
                 :description
                 :title})

(defn keys-exist [whitelist vals]
  (every? (partial contains? whitelist) vals))

(defn valid-book?
  "Check that map is a valid representation of a book"
  [m]
  (and (keys-exist book-keys (keys m))
       (keys-exist m book-keys)))

(defn upsert-book!
  "Create book review, or update if already exists"
  [isbn book]
  {:pre [(valid-book? book)]}
  (let [resource (esd/put conn index doc-type isbn book)]
    (created isbn resource))) ;; needs error handling like everything else here

(defn fetch-book
  "Fetch book review for a given ISBN"
  [isbn]
  (let [res (esd/get conn index doc-type isbn)]
    (response (res :_source))))

(defn search-books
  "Retrieves a collection of books matching a given search"
  []
  (let [hits (esr/hits-from (esd/search conn index doc-type
                                        :query {:match_all {}}))]
    (response (map :_source hits))))

;; example book

(def example-book {:authors ["john" "mark"]
                   :categories ["Fiction"]
                   :isbn 12355
                   :description "all you wanted was my heart"
                   :title "living free but from a prison cell"})
