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

Granger currently uses [Elasticsearch](http://www.elasticsearch.org/)
to store books and reviews.

Formats
-------

Book resources are represented as follows:

    {
        "authors": [ "Michael Fogus", "Chris Houser" ],
        "created_at": "2014-11-15T11:10:47.000Z",
        "categories": [ "Computers" ],
        "isbn": "9781935182641",
        "description": "About Clojure...",
        "title": "The Joy of Clojure",
        "review": "Here is some review text as a plain text string..."
    }

All fields are mandatory apart from 'review'.

We don't store much information about a book; the ISBN provides an
easy way for interested parties to find out details such as the
publisher, page count, etc. A good source of information (used here)
is the Google Books API.

The structure of the API itself is that of the
[JSON API format](http://jsonapi.org/).
