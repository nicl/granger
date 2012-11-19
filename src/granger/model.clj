(ns granger.model)

(defn add-book!
  "Create new book review"
  [data]
  "You have added a new book!")

(defn update-book!
  "Update existing book review"
  [id data]
  "An update action.")

(defn find-book
  "Returns a book review"
  [id]
  "A single book.")

(defn find-books
  "Returns a collection of book reviews"
  []
  "A list of all books.")

(defn is-valid?
  "Whether data is a valid book review representation"
  [data])