Granger
=======

> "Books! And cleverness! There are more important things --
> friendship and bravery." - Hermione Granger

Granger is a Clojure web API which tracks books you have read and what
you thought about them.

Install and run
---------------

To install dependencies run:

    lein deps

Then, start the server, run:

    lein ring server

(You need to install
[leiningen](https://github.com/technomancy/leiningen) first.)

Storage
-------

Granger currently uses [MongoDB](http://www.mongodb.org/) to store
books and reviews.

The JSON schema for a book review is currently:

    {
        created_at: "Thu, 15 Nov 2012 11:10:47 +0000",
        isbn: "978-1-935182-64-1",
        title: "The Joy of Clojure",
        authors: [
            {
                first_name: "Michael",
                last_name: "Fogus"
            },
            {
                first_name: "Chris",
                last_name: "Houser"
            }
        ],
        text: "Here is some review text as a plain text string..."
    }

We don't store much information about a book; the ISBN provides an
easy way for interested parties to find out details such as the
publisher, page count, etc.
