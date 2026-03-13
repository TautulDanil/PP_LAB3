package org.example

data class Node(
    val url: String,
    val children: MutableList<Node> = mutableListOf()
)