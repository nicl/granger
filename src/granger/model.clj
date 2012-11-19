(ns granger.model)

(defn create!
  "Create new book review"
  [data])

(defn update!
  "Update existing book review"
  [id data])

(defn find-one
  "Returns a book review"
  [id]
  (str "You are looking for the following ID:" id))

(defn find-all
  "Returns a collection of book reviews")

(defn is-valid?
  "Whether data is a valid book review representation"
  [data])