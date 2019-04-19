package de.chrisward.theyworkforyou.helper;

import de.chrisward.theyworkforyou.R;

public class ResourceHelper {
    public static int getPartyColor(String party) {
        switch(party) {
            case "Conservative":
                return R.color.conservative;
            case "DUP":
                return R.color.dup;
            case "Green":
                return R.color.green;
            case "Labour":
            case "Labour/Co-operative":
                return R.color.labour;
            case "Liberal Democrat":
                return R.color.libdem;
            case "Plaid Cymru":
                return R.color.plaid_cymru;
            case "Scottish National Party":
                return R.color.snp;
            case "Sinn Féin":
                return R.color.sinn_fein;
            case "Speaker":
                return R.color.speaker;
            default:
                return R.color.independent;
        }
    }
}
