package de.chrisward.theyworkforyou.helper

import de.chrisward.theyworkforyou.R

object ResourceHelper {
    fun getPartyColor(party: String): Int {
        when (party) {
            "Conservative" -> return R.color.conservative
            "DUP" -> return R.color.dup
            "Green" -> return R.color.green
            "Labour", "Labour/Co-operative" -> return R.color.labour
            "Liberal Democrat" -> return R.color.libdem
            "Plaid Cymru" -> return R.color.plaid_cymru
            "Scottish National Party" -> return R.color.snp
            "Sinn Féin" -> return R.color.sinn_fein
            "Speaker" -> return R.color.speaker
            else -> return R.color.independent
        }
    }
}
