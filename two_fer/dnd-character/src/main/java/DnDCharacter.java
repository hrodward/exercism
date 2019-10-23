class DnDCharacter {

    private static final Integer THREE_DICE_MIN_SUM = 3;
    private static final Integer OFFSET = THREE_DICE_MIN_SUM;
    private static final Integer THREE_DICE_MAX_SUM = 18;

    private Integer strength;
    private Integer dexterity;
    private Integer constitution;
    private Integer intelligence;
    private Integer wisdom;
    private Integer charisma;

    int ability() {
        return (int) Math.round(Math.random() * (THREE_DICE_MAX_SUM - OFFSET) + THREE_DICE_MIN_SUM);
    }

    int modifier(int input) {
        return Math.floorDiv(input - 10, 2);
    }

    int getStrength() {
        if (strength == null) {
            strength = ability();
        }
        return strength;
    }

    int getDexterity() {
        if (dexterity == null) {
            dexterity = ability();
        }
        return dexterity;
    }

    int getConstitution() {
        if (constitution == null) {
            constitution = ability();
        }
        return constitution;
    }

    int getIntelligence() {
        if (intelligence == null) {
            intelligence = ability();
        }
        return intelligence;
    }

    int getWisdom() {
        if (wisdom == null) {
            wisdom = ability();
        }
        return wisdom;
    }

    int getCharisma() {
        if (charisma == null) {
            charisma = ability();
        }
        return charisma;
    }

    int getHitpoints() {
        return 10 + modifier(getConstitution());
    }

}
