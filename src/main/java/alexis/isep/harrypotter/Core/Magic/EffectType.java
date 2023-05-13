package alexis.isep.harrypotter.Core.Magic;

public enum EffectType {
    CONFUSION(EffectCategory.INABILITY, "now confused", "already confused","no longer confused","confused"),
    LAUGH(EffectCategory.INABILITY,"now laughing", "already laughing","no longer laughing","laughing uncontrollably"),
    SLUG_VOMITING(EffectCategory.INABILITY,"now feeling really nauseous","already nauseous","no longer nauseous", "vomiting slugs"),
    DANCING(EffectCategory.INABILITY,"now feeling their legs spasm widly out of control","already dancing","in full control of their legs again","too busy dancing"),
    STUN(EffectCategory.INABILITY,"now stunned", "already stunned","no longer stunned","completely stunned"),

    STRENGTH(EffectCategory.STAT_BOOST,"becoming stronger", "already affected by strength","no longer affected by an effect of strength",""),
    RESISTANCE(EffectCategory.STAT_BOOST,"becoming more resistant", "already affected by resistance","no longer affected by an effect of resistance",""),
    VIVACITY(EffectCategory.STAT_BOOST,"becoming sharper and more alert","already on alert","no longer on alert",""),
    LUCK(EffectCategory.STAT_BOOST,"becoming extremely lucky","already affected by extreme luck","no longer lucky",""),

    EXCRUCIATING_PAIN(EffectCategory.DAMAGE,"now writhing in excruciating pain on the ground","already being tortured","no longer in pain","suffers from torture"),
    BURN(EffectCategory.DAMAGE, "now burning","already burning","no longer burning","suffers from their burns"),

    UNDER_CONTROL(EffectCategory.SELF_HARM,"now under control","already under control","no longer under control","are under their opponent's control"),

    HIDE(EffectCategory.PROTECTION,"now hiding", "already hiding","no longer hiding","hiding"),
    SHIELD(EffectCategory.PROTECTION,"now protected by a shield","already protected by a shield","no longer protected by a shield","protected by a shield"),

    HEAL(EffectCategory.HEAL,"healed by","","",""),
    //INVISIBILITY("now invisible","already invisible","no longer invisible");

    DISARM(EffectCategory.CUSTOM,"now disarmed","already disarmed","armed again","disarmed"),
    ;
    private EffectCategory effectCategory;
    private String startMessage;
    private String alreadyAffectedMessage;
    private String endMessage;
    private String consequenceMessage;

    EffectType(EffectCategory effectCategory, String startMessage, String alreadyAffectedMessage, String endMessage, String consequenceMessage) {
        this.effectCategory = effectCategory;
        this.startMessage = startMessage;
        this.alreadyAffectedMessage = alreadyAffectedMessage;
        this.endMessage = endMessage;
        this.consequenceMessage = consequenceMessage;
    }

    public String getEndMessage() {
        return endMessage;
    }

    public String getAlreadyAffectedMessage() {
        return alreadyAffectedMessage;
    }

    public String getStartMessage() {
        return startMessage;
    }

    public EffectCategory getEffectCategory() {
        return effectCategory;
    }

    public String getConsequenceMessage() {
        return consequenceMessage;
    }
}
