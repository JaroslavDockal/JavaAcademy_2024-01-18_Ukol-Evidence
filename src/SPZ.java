import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SPZ {
    private String spz;
    private boolean isValid;

    public SPZ(String spz) {
        this.spz = spz.replaceAll("\\s", "");
        this.isValid = isValid() && containsLetterAndDigit();
    }

    /*public boolean isValid() {
        return isValid;
    }*/

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
        if (!isElectric() &&  spz.matches("[0-9A-Z]{1}[ABCEHJKLMPSTUZ]{1}[0-9A-Z]{5}")){
            return true;
        } else {
            return false;
        }
    }

    /*První je na značce trojčíslí identifikující ambasádu,
    pak vždy dvě písmena CD/XX/XS/HC/. Následuje pořadové dvojčíslí.*/
    private boolean isDiplomatic() {
        return spz.matches("[0-9]{3}[CD]{2}[0-9]{2}") || //diplomatická imunita
                spz.matches("[0-9]{3}[XX]{2}[0-9]{2}") || //omezená diplomatická imunita
                spz.matches("[0-9]{3}[XS]{2}[0-9]{2}") || //diplomatický personál
                spz.matches("[0-9]{3}[HC]{2}[0-9]{2}");   //honorární konsul
    }

    private boolean isElectric() {
        return spz.startsWith("EL") && spz.matches("EL[0-9A-Z]{3,5}");
    }

    //Veteráni mají číselný kód kraje (01-14) před písmenem V na třetí pozici, následuje čtyřmístné pořadové číslo.
    private boolean isHistorical() {
        return spz.matches("[0-1]{1}[0-9]{1}V[0-9A-Z]{2,4}");
    }

    /* Zvláštní motorová vozidla - na značce je nejprve písmeno kraje,
    následuje dvojčíslím série a čtyřmístným pořadovým číslem.*/
    private boolean isHandling() {
        return spz.matches("[ABCEHJKLMPSTUZ]{1}[A-Z0-9]{6}");
    }

    /*Jednorázové použití s omezenou platností (převoz vozidla z prodejny domů apod.)
    Na značce je vyznačen kraj následovaný 6 pořadovými číslicemi.*/
    private boolean isSaleToRegistration() {
        return spz.matches("[ABCEHJKLMPSTUZ]{1}[A-Z][0-9A-Z]{6}");
    }

    //Auta pro zkušební účely mají písmeno F, následované čtyřmístným pořadovým číslem.
    private boolean isTest() {
        return spz.startsWith("F") && spz.matches("F[0-9A-Z]{4}");
    }

    //Závodní auta mají písmeno R. Kód kraje je číselný 01 až 14 před prvním písmenem R. Následuje čtyřmístné pořadové číslo.
    private boolean isSports() {
        return spz.matches("[0-1]{1}[0-9]{1}R[0-9A-Z]{2,4}");
    }
    private boolean isMilitary() {
        return spz.matches("[0-9]{1,7}");
    }
}
