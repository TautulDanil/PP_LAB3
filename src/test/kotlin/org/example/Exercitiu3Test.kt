package org.example

import junit.framework.TestCase.assertTrue
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

@RunWith(Parameterized::class)
class Lab3Ex3AgendaTest(
    private val agenda: MutableList<Contact>
) {
    companion object {
        @JvmStatic
        @Parameters
        fun data() : Collection<MutableList<Contact>> {
            val agenda = mutableListOf<Contact>()
            agenda.add(Contact("Mihai", "0744321987", Birth(1900, 11, 25)))
            agenda += Contact("George", "0761332100", Birth(2002, 3, 14))
            agenda += Contact("Liviu" , "0231450211", Birth(1999, 7, 30))
            agenda += Contact("Popescu", "0211342787", Birth(1955, 5, 12))

            return listOf(agenda)
        }
    }

    @Test
    fun testAgendaCreate() {
        // create a new contact
        agenda.add(Contact("Ana", "0744321687", Birth(2666, 4, 14)))
        assertTrue(agenda.any { contact: Contact -> contact.name == "Ana" })
    }

    @Test
    fun testReadContact() {
        val contact = agenda.find{ contact: Contact -> contact.name == "Mihai" && contact.phone == "0744321987" }
        assertNotNull(contact)
    }

    @Test
    fun testUpdateContact() {
        val nameToUpdate = "Mihai"
        val newPhone = "0744000000"
        val index = agenda.indexOfFirst { it.name == nameToUpdate }
        assertFalse(index == -1)
        agenda[index] = Contact(agenda[index].name, newPhone, agenda[index].birthDate)

        assertTrue(agenda.any { it.name == nameToUpdate && it.phone == newPhone })
    }

    @Test
    fun testDeleteContact() {
        val initialSize = agenda.size
        val contactToDelete = "George"
        agenda.removeIf { it.name == contactToDelete }

        assertTrue(agenda.size < initialSize)
        assertFalse(agenda.any { it.name == contactToDelete })
    }

    @Test
    fun testSearchContact() {
        val byName = agenda.find { it.name == "Liviu" }
        assertNotNull(byName)
        val byPhone = agenda.find { it.phone == "0211342787" }
        assertNotNull(byPhone)
        assertTrue(byPhone?.name == "Popescu")
    }
}