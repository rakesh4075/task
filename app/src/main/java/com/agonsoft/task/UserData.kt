package com.agonsoft.task

data class UserData(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val address: AddressX,
    val phone: String,
    val website: String,
    val company: CompanyX
)