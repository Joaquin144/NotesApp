package com.devcommop.cleanarchitecturenoteapp.feature_note.domain.util

sealed class NoteOrder(val orderType: OrderType){//E! --> Why we put val here and why UseCase is note working without val here ??
    class Title(orderType: OrderType): NoteOrder(orderType = orderType)
    class Date(orderType: OrderType): NoteOrder(orderType = orderType)
    class Color(orderType: OrderType): NoteOrder(orderType = orderType)

    /**
     * We will call this function when we need to change from Ascending to Descending or from Descending to Ascending.
     *
     * copy() function is by default provided in data class by Kotlin but in case of normal classes we need to implement our own copy() function
     */
    fun copy(orderType: OrderType): NoteOrder{
        return when(this){
            is Title -> Title(orderType)
            is Date -> Date(orderType)
            is Color -> Color(orderType)
        }
    }
}
