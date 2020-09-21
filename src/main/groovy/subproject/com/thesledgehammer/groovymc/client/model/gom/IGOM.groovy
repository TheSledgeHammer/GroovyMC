package subproject.com.thesledgehammer.groovymc.client.model.gom

/* GOM (Groovy Object Model Interface) */
interface IGOM {

    /* GOM format version */
    String getGOMVersion();

    String getName();

    /* retrieve GOM object */
    def getGOM();

    def getParent();

    def getTextures();

    def getTexturesByName(String name);

    def getItemTextureLayer(int index);

    def getElements();

    def getElementPart(int index);

    boolean getShade();

    def getVariables();

    def getVariableByName(String variableName);

    def getRules();

    def getRulesByName(String ruleName);

    def getValues();

    def getValueByName(def valueName);

    boolean AmbientOcclusion();

    /* Translation Rotation Scale Rotation(TRSR) */
    def Display();

    def DisplayName(String name);

    ArrayList<Float> Translation(String name);

    ArrayList<Float> Rotation(String name);

    ArrayList<Float> Scale(String name);

    /* Item Models Only */
    def Overrides();

    def OverridesPredicate();

    def OverridesModel();
}