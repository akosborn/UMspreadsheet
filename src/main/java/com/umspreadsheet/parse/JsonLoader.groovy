package com.umspreadsheet.parse

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import com.umspreadsheet.show.Show

@Grapes(
        @Grab(group='com.google.code.gson', module='gson', version='2.8.5')
)

Gson gson = new Gson()

JsonArray showArray  = new JsonParser().parse(new FileReader("C:/Users/andre/Desktop/shows/2018.json"))
        .getAsJsonArray()
for (JsonElement showEl : showArray) {
    Show show = gson.fromJson(showEl, Show)
}