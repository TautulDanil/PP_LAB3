package org.example

class Birth(val year: Int, val month: Int, val day: Int)
{
    override fun toString() : String {
        return "($day.$month.$year)"
    }
}

class Contact(val name: String, val phone: String, val birthDate: Birth){
    fun print(){
        println("Name: $name, Mobile: $phone, Date: $birthDate")
    }
    fun List<Contact>.searchByName(name: String) = this.find { it.name.equals(name, ignoreCase = true) }
    fun List<Contact>.searchByPhone(phone: String) = this.find { it.phone == phone }
    fun MutableList<Contact>.updatePhone(name: String, newPhone: String) {
        val index = this.indexOfFirst { it.name.equals(name, ignoreCase = true) }
        if (index != -1) {
            val oldContact = this[index]
            this[index] = Contact(oldContact.name, newPhone, oldContact.birthDate)
        }
    }
}