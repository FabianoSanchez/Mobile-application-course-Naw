package com.example.swipequiz

data class Quiz (
    var quiz : String,
    var answer : Boolean
){
    companion object {
     val quiz_questions = arrayOf(
         "A 'val' and 'var' are the same thing",
         "Mobile Application Development grants 12 ECTS",
         "A Unit in Kotlin corresponds to void in Java",
         "In Kotlin 'when' replaces the 'switch' operator in Java")

     val quiz_answers = arrayOf(
        false,
        false,
        true,
        true
    )


    }


}