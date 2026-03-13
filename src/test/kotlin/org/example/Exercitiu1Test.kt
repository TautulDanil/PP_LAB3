package org.example

import junit.framework.TestCase.assertTrue
import junit.framework.TestCase.assertFalse
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

@RunWith(Parameterized::class)
class Lab3Exemplu1Test(
    private val regex : Regex,
    private val testInput : String,
    private val shouldMatch : Boolean) {

    companion object {
        @JvmStatic
        @Parameters
        fun data() : Collection<Array<Any>> {
            val stringIpv6 = "link/ether a0:b1:c2:d3:e4:f5 brd ff:ff:ff:ff:ff:ff"
            val stringIpv4 = "inet 192.168.0.2/24 brd 192.168.0.255 scope global eno1"
            val stringEmail = "Hi,\n You can contact me at john.smith@gmail.com"
            val stringUrl = "You should use a search engine like www.duckduckgo.com"
            val stringTime = "I'll meet you at 08:00 AM tomorrow"
            val stringWithDuplicates = "one two two three three three four four four four"
            val stringWithoutDuplicates = "abc def"

//            val ipRegex = Regex("((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)")
            var ipv4Regex = Regex("(\\b25[0-5]|\\b2[0-4][0-9]|\\b[01]?[0-9][0-9]?)(\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)){3}")
            val ipv6Regex = Regex("(([0-9a-fA-F]{1,4}:){7,7}[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,7}:|([0-9a-fA-F]{1,4}:){1,6}:[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,5}(:[0-9a-fA-F]{1,4}){1,2}|([0-9a-fA-F]{1,4}:){1,4}(:[0-9a-fA-F]{1,4}){1,3}|([0-9a-fA-F]{1,4}:){1,3}(:[0-9a-fA-F]{1,4}){1,4}|([0-9a-fA-F]{1,4}:){1,2}(:[0-9a-fA-F]{1,4}){1,5}|[0-9a-fA-F]{1,4}:((:[0-9a-fA-F]{1,4}){1,6})|:((:[0-9a-fA-F]{1,4}){1,7}|:)|fe80:(:[0-9a-fA-F]{0,4}){0,4}%[0-9a-zA-Z]{1,}|::(ffff(:0{1,4}){0,1}:){0,1}((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])|([0-9a-fA-F]{1,4}:){1,4}:((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9]))")
            val emailRegex = Regex("\\w+[+.\\w-]*@([\\w-]+.)*\\w+[\\w-]*.([a-z]{2,4}|\\d+)")
            val urlRegex = Regex("(https?:\\/\\/)?www\\.[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)")
            val timeRegex = Regex("(0?[1-9]|1[0-2]):[0-5][0-9]")
            val duplicatesRegex = Regex("(\\b\\w+\\b)(?=.*\\b\\1\\b)")
            val splitRegex = Regex("\\d+")

            return listOf(
                arrayOf(ipv4Regex, stringIpv4, true),
                arrayOf(emailRegex, stringEmail, true),
                arrayOf(urlRegex, stringUrl, true),
                arrayOf(timeRegex, stringTime, true),
                arrayOf(timeRegex, "08:00", true),
                arrayOf(timeRegex, "Tomorrow at 09:15", true),
                arrayOf(duplicatesRegex, stringWithDuplicates, true),
                arrayOf(duplicatesRegex, stringWithoutDuplicates, false),
                arrayOf(splitRegex, "This10text20is30splitted40by50regex", true),
                arrayOf(ipv4Regex, stringWithDuplicates, false)
            )
        }
    }

    @Test
    fun testRegex() {
        if (shouldMatch) {
            assertTrue("Expected /${regex.pattern}/ to match '${testInput}'", regex.findAll(testInput).any())
        } else {
            assertFalse("Expected /${regex.pattern}/ to NOT match '${testInput}'", regex.findAll(testInput).any())
        }
    }
}