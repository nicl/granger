(ns granger.utils.upload
  (:require [clj-http.client :as client]))

;; parse a google books api url and add to local ES

(def google-books-api-url "https://www.googleapis.com/books/v1/")

(def granger-api-url "http://localhost:3000/books/")

(defn fetch-book-by-google-id
  "Fetch a book using the Google Books API"
  [id]
  (let [url (str google-books-api-url "volumes/" id)]
    (:body (client/get url {:as :json}))))

(defn fetch-book-by-isbn
  "Fetch a book using the Google Books API"
  [isbn]
  (let [url (str google-books-api-url "volumes?q=isbn:" isbn)]
    (-> (client/get url {:as :json}) :body :items first)))

(defn get-isbn
  [google-book]
  (->> google-book
       :volumeInfo
       :industryIdentifiers
       (filter #(= "ISBN_13" (:type %)))
       first
       :identifier))

(defn get-volume-info
  [google-book field]
  (-> google-book :volumeInfo field))

(defn convert-format
  "Takes a Google Book resource and converts it to the Granger format"
  [google-book]
  (let [bk google-book]
    {:authors (get-volume-info bk :authors)
     :categories (get-volume-info bk :categories)
     :isbn (get-isbn bk)
     :description (get-volume-info bk :description)
     :title (get-volume-info bk :title)}))

(defn add-to-api
  [book]
  (let [isbn (:isbn book)
        url (str granger-api-url isbn)]
    (client/put url {:form-params book :content-type :json})))

(defn add-book
  [isbn]
  (-> isbn
      fetch-book-by-isbn
      convert-format
      add-to-api))
