package com.devcommop.cleanarchitecturenoteapp.feature_note.domain.util

sealed class NoteOrder(val orderType: OrderType){//E! --> Why we put val here and why UseCase is note working without val here ??
    class Title(orderType: OrderType): NoteOrder(orderType = orderType)
    class Date(orderType: OrderType): NoteOrder(orderType = orderType)
    class Color(orderType: OrderType): NoteOrder(orderType = orderType)
}
