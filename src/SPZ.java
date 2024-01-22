import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SPZ {
    private String spz;
    private boolean isValid;

    public SPZ(String spz) {
        this.spz = spz.replaceAll("\\s", "");
        this.isValid = isValidPlate() && containsLetterAndDigit();
    }

    public String getPlateNumber() {
        if (isValid()) {
            return spz.substring(0, 7);
        } else {
            return "Neplatná SPZ";
        }
    }

    public boolean isValid() {
        return isValid;
    }

    public String getRegion() {
        if (isValid() && (isStandard() || isHistorical() || isHandling() || isSaleToRegistration() || isSports())) {
            char secondLetter = spz.charAt(1);
            switch (secondLetter) {
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
        } else {
            return "Neznámý kraj";
        }
    }

    public String getSPZ() {
        return spz;
    }

    public String getType() {
        if (!isValid()) {
            return "Neplatná SPZ";
        } else if (isStandard()) {
            return "Standardní";
        } else if (isDiplomatic()) {
            return "Diplomatická";
        } else if (isForeign()) {
            return "Cizinecká";
        }else if (isElectric()) {
            return "Elektrická vozidla";
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
        } else {
            return "Na přání";
        }
    }

    public String getSummary() {
        if (isValid()) {
            if (isStandard() || isHistorical() || isHandling() || isSaleToRegistration() || isSports()) {
                return getSPZ() + " (" + getRegion() + ")";
            } else {
                return getSPZ() + " (" + getType() + ")";
            }
        } else {
            return "Neplatná SPZ";
        }
    }

    private boolean isValidPlate() {
        if (isStandard()) {
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
        if (spz.length() == 8 && Character.isDigit(spz.charAt(7))) {
            return true;
        } else if (spz.length() == 7 && Character.isDigit(spz.charAt(6))) {
            return true;
        }
        return false;
    }

    private boolean isDiplomatic() {
        return spz.matches("[CDXXS]{2}[0-9]{3,5}");
    }

    private boolean isForeign() {
        return spz.matches("[HC]{2}[0-9]{3,5}");
    }

    private boolean isElectric() {
        return spz.startsWith("EL") && spz.matches("EL[0-9A-Z]{3,5}");
    }

    private boolean isHistorical() {
        return spz.matches("[0-9]{2}V[0-9A-Z]{2,4}");
    }

    private boolean isHandling() {
        return spz.matches("[A-Z0-9]{5,6}");
    }

    private boolean isSaleToRegistration() {
        return spz.matches("[A-Z][0-9A-Z]{6}");
    }

    private boolean isTest() {
        return spz.startsWith("F") && spz.matches("F[0-9A-Z]{4}");
    }

    private boolean isSports() {
        return spz.matches("[0-9]{2}R[0-9A-Z]{2,3}");
    }
}
