package com.danielm.redditskin.data

data class Data(
    val after: String,
    val before: Any,
    val children: List<PostItem>,
    val dist: Int,
    val modhash: String
)

