package ru.otus.otuskotlin.oop

data class Product(
    val id: ProductId,
    val name: String,
) {

    @JvmInline
    value class ProductId(
        val value: Long
    )
}
