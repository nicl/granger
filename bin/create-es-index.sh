#!/bin/bash

curl -XPOST localhost:9200/books -d '
{
    "mappings" : {
        "book" : {
            "properties" : {
                "authors": { "type": "string", "index": "analyzed" },
                "categories": { "type": "string", "index": "analyzed" },
                "created_at" : { "type" : "date", "format": "date_time"  },
                "isbn": { "type": "long" },
                "description": { "type": "string", "index": "analyzed" },
                "title": { "type": "string", "index": "analyzed" },
                "review": { "type": "string", "index": "analyzed" }
            }
        }
    }
}
'
