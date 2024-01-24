/*
Akceptuje jen značky definované ve Vyhlášce č. 343/2014 Sb., (Část pátá)
Neakceptuje značky k umístění na nosné zařízení připojitelné k silničnímu vozidlu (§23/e)
Ověřování splnění §24/2 je do určité úrovně redundantní...
Klasické staré značky by to teoreticky mělo označit za značky na přání
*/

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SPZ {
    private String spz;

    public SPZ(String spz) {
        this.spz = spz.replaceAll("\\s", "");
    }

    public String getSPZ() {
        if (isValid() && (spz.length() >= 5 && spz.length() <= 8 && containsLetterAndDigit()) || isMilitary()) {
            return spz.substring(0, Math.min(spz.length(), 7));
        } else {
            return "Neplatná SPZ";
        }
    }

    public String getRegion() {
        if (isValid() && ((isStandard() && !isElectric())  || isHandling() || isSaleToRegistration())) {
            char regionLetter;
            if (isStandard()){
                regionLetter = spz.charAt(1);
            } else if ((isHandling() || isSaleToRegistration()) && !isElectric()) {
                regionLetter = spz.charAt(0);
            } else {
                regionLetter = 'X';
            }

            switch (regionLetter) {
                case 'A':
                    return "Hlavní město Praha";
                case 'S':
                    return "Středočeský kraj";
                case 'U':
                    return "Ústecký kraj";
                case 'L':
                    return "Liberecký kraj";
                case 'K':
                    return "Karlovarský kraj";
                case 'H':
                    return "Královéhradecký kraj";
                case 'E':
                    return "Pardubický kraj";
                case 'P':
                    return "Plzeňský kraj";
                case 'C':
                    return "Jihočeský kraj";
                case 'J':
                    return "Vysočina";
                case 'B':
                    return "Jihomoravský kraj";
                case 'M':
                    return "Olomoucký kraj";
                case 'T':
                    return "Moravskoslezský kraj";
                case 'Z':
                    return "Zlínský kraj";
                default:
                    return "Neznámý kraj";
            }
        } else if (isValid() && (isHistorical()  || isSports())) {
            int regionCode = Integer.parseInt(spz.substring(0, 2));

            switch (regionCode) {
                case 1:
                    return "Hlavní město Praha";
                case 2:
                    return "Jihomoravský kraj";
                case 3:
                    return "Jihočeský kraj";
                case 4:
                    return "Pardubický kraj";
                case 5:
                    return "Královéhradecký kraj";
                case 6:
                    return "Vysočina";
                case 7:
                    return "Karlovarský kraj";
                case 8:
                    return "Liberecký kraj";
                case 9:
                    return "Olomoucký kraj";
                case 10:
                    return "Plzeňský kraj";
                case 11:
                    return "Středočeský kraj";
                case 12:
                    return "Moravskoslezský kraj";
                case 13:
                    return "Ústecký kraj";
                case 14:
                    return "Zlínský kraj";
                default:
                    return "Neznámý kraj";
            }
        } else {
            return "Neznámý kraj";
        }
    }

    public String getType() {
        if (!isValid()) {
            return "Neplatná SPZ";
        }else if (isElectric()) {
            return "Elektrická vozidla";
        } else if (isDiplomatic()) {
            return "Diplomatická";
        } else if (isHistorical()) {
            return "Historická vozidla";
        } else if (isHandling()) {
            return "Manipulační provoz";
        } else if (isSaleToRegistration()) {
            return "Jízda z místa prodeje do místa registrace";
        } else if (isTest()) {
            return "Zkušební provoz";
        } else if (isSports()) {
            return "Sportovní vozidla";
        } else if (isStandard()) {
            return "Standardní";
        } else if (isMilitary()) {
            return "Vojenská";
        } else {
            return "Na přání";
        }
    }

    public String getSummary() {
        if (isValid()) {
            if ((isStandard() || isHistorical() || isHandling() || isSaleToRegistration() || isSports()) && !isElectric()) {
                return getSPZ() + " (" + getRegion() + ")";
            } else {
                return getSPZ() + " (" + getType() + ")";
            }
        } else {
            return "Neplatná SPZ";
        }
    }

    private boolean isValid() {
        if (isMilitary() && spz.length() == 7) {
            return true;
        }

        Pattern pattern = Pattern.compile("^(?=.*[A-Z])(?=.*[0-9])[A-Z0-9]{5,8}$");
        Matcher matcher = pattern.matcher(spz);
        return matcher.matches() && spz.length() >= 5 && spz.length() <= 8 && containsLetterAndDigit();
    }

    private boolean containsLetterAndDigit() {
        boolean containsLetter = false;
        boolean containsDigit = false;

        for (char c : spz.toCharArray()) {
            if (Character.isLetter(c)) {
                containsLetter = true;
            } else if (Character.isDigit(c)) {
                containsDigit = true;
            }

            if (containsLetter && containsDigit) {
                return true;
            }
        }

        return false;
    }

    private boolean isStandard() {
        if (spz.matches("[0-9A-Z]{1}[ABCEHJKLMPSTUZ]{1}[0-9A-Z]{5}") && !isDiplomatic() && !isElectric()){ //tyhle 2 mají na druhé pozici znaky které by mohli projít
            return true;
        } else {
            return false;
        }
    }

    /*Registrační značka diplomatická nebo cizinecká je složena z velkých písmen latinské
    abecedy „CD“, „XX“, „XS“ nebo „HC“ a u automobilů z 5, u motocyklů ze 4 a u mopedů
    se šlapadly ze 3 arabských číslic.
    "Postaru" to bylo jinak - trojčíslí identifikující ambasádu,
    dvě písmena CD/XX/XS/HC a pořadové dvojčíslí.
    Zahrnuty obě varianty*/
    private boolean isDiplomatic() {
        return  spz.matches("[CD]{2}[0-9]{3,5}") || //diplomatická imunita
                spz.matches("[XX]{2}[0-9]{3,5}") || //omezená diplomatická imunita
                spz.matches("[XS]{2}[0-9]{3,5}") || //diplomatický personál
                spz.matches("[HC]{2}[0-9]{3,5}") || //honorární konsul
                spz.matches("[0-9]{3}[CD]{2}[0-9]{2}") ||
                spz.matches("[0-9]{3}[XX]{2}[0-9]{2}") ||
                spz.matches("[0-9]{3}[XS]{2}[0-9]{2}") ||
                spz.matches("[0-9]{3}[HC]{2}[0-9]{2}");
    }

    /*Registrační značka elektrického vozidla je složena z velkých písmen „EL“ a z
    dalších arabských číslic nebo velkých písmen latinské abecedy, kterých je u
    automobilů 5, u motocyklů 4 a u mopedů se šlapadly 3.*/
    private boolean isElectric() {
        return spz.startsWith("EL") && spz.matches("EL[0-9A-Z]{3,5}");
    }

    /*Registrační značka pro historická vozidla začíná vždy dvoumístným číselným znakem
    arabských číslic registračního místa, které zajišťuje registraci historických vozidel
    na správním území kraje, a velkým písmenem „V“, za nímž se u historických automobilů
    umísťují 4, u historických motocyklů 3 a u historických mopedů se šlapadly 2 arabské
    číslice nebo písmena latinské abecedy.*/
    private boolean isHistorical() {
        return spz.matches("[0-1]{1}[0-9]{1}V[0-9A-Z]{2,4}");
    }

    /* Zvláštní registrační značka pro manipulační provoz se skládá nejméně z 5 znaků
    a nejvíce z 6 znaků a začíná vždy písmenem kódu kraje, ostatní znaky jsou složeny
    z velkých písmen latinské abecedy a arabských číslic..*/
    private boolean isHandling() {
        return spz.matches("[ABCEHJKLMPSTUZ]{1}[A-Z0-9]{4,5}");
    }

    /*Zvláštní registrační značka pro jízdu z místa prodeje do místa registrace silničního
    vozidla se skládá ze 7 znaků a začíná vždy písmenem kódu kraje, ostatní znaky jsou
    vyjádřeny arabskými číslicemi nebo písmeny latinské abecedy.*/
    private boolean isSaleToRegistration() {
        return spz.matches("[ABCEHJKLMPSTUZ]{1}[A-Z][0-9A-Z]{6}");
    }

    /*Zvláštní registrační značka pro zkušební provoz se skládá z 5 znaků
    a začíná vždy písmenem „F“, ostatní znaky jsou vyjádřeny arabskými číslicemi
    nebo písmeny latinské abecedy. */
    private boolean isTest() {
        return spz.startsWith("F") && spz.matches("F[0-9A-Z]{4}");
    }

    /*Registrační značka pro sportovní vozidla začíná vždy dvoumístným číselným znakem
    arabských číslic registračního místa, které zajišťuje registraci sportovních vozidel
    na správním území kraje, a velkým písmenem „R“, za nímž se u sportovních automobilů
    umísťují 4 a u sportovních motocyklů 3 arabské číslice. */
    private boolean isSports() {
        return spz.matches("[0-1]{1}[0-9]{1}R[0-9A-Z]{2,4}");
    }

    //Tohle jsem ve vyhlášce nenašel - je možné vydat novou, nebo to je historie?
    private boolean isMilitary() {
        return spz.matches("[0-9]{1,7}");
    }
}
