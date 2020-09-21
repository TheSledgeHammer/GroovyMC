package subproject.com.thesledgehammer.groovymc.client.model.gom

abstract class GOM implements IGOM {

    private def obj;
    private String name;

    GOM(String mPath, String mVersion, String mType) {
        this.obj = SuperGOM.readSuperGOM(mPath, mVersion, mType);
        this.name = String.valueOf(SuperGOM.readSuperGOM(mPath, mVersion, mType));
    }

    @Override
    String getGOMVersion() {
        return obj.modelversion;
    }

    @Override
    String getName() {
        return name;
    }

    @Override
    def getGOM() {
        return obj;
    }

    @Override
    def getParent() {
        if(obj.parent == null) {
            return new Exception("Parent isn't defined in ${getName()}")
        }
        return obj.parent;
    }

    @Override
    def getTextures() {
        return obj.textures;
    }

    @Override
    def getTexturesByName(String name) {
        return obj.textures.get(name);
    }

    @Override
    def getItemTextureLayer(int index) {
        return obj.textures.getAt("layer${index}");
    }

    @Override
    def getElements() {
        return obj.elements;
    }

    @Override
    def getElementPart(int index) {
        return obj.elements.get(index);
    }

    @Override
    boolean getShade() {
        return obj.shade
    }

    @Override
    def getVariables() {
        return obj.variables;
    }

    @Override
    def getVariableByName(String variableName) {
        return obj.variables.get(variableName);
    }

    @Override
    def getRules() {
        return obj.rules;
    }

    @Override
    def getRulesByName(String ruleName) {
        return getRules()[ruleName];
    }

    @Override
    def getValues() {
        return  obj.values;
    }

    @Override
    def getValueByName(Object valueName) {
        return obj.values.get(valueName);
    }

    @Override
    boolean AmbientOcclusion() {
        return obj.ambientocclusion;
    }

    //Translation Rotation Scale Rotation(TRSR)
    @Override
    def Display() {
        return obj.display;
    }

    @Override
    def DisplayName(String name) {
        if(obj.display.get(name) == null) {
            return new Exception("${name} is incorrect...!");
        }
        return obj.display.get(name);
    }

    @Override
    ArrayList<Float> Translation(String name) {
        ArrayList<Float> arrObj = new ArrayList<>();
        if(obj.display.get(name) == null) {
            throw new Exception("${name} is incorrect...!")
        }
        if(obj.display.get(name).translation == null) {
            throw new Exception("${name} does not contain Translation...!");
        }
        for(int i = 0; i < obj.display.get(name).translation.size; i++) {
            arrObj.add(i, obj.display.get(name).translation.get(i));
        }
        return arrObj;
    }

    @Override
    ArrayList<Float> Rotation(String name) {
        ArrayList<Float> arrObj = new ArrayList<>();
        if(obj.display.get(name) == null) {
            throw new Exception("${name} is incorrect...!");
        }
        if(obj.display.get(name).rotation == null) {
            throw new Exception("${name} does not contain Rotation...!");
        }
        for(int i = 0; i < obj.display.get(name).rotation.size; i++) {
            arrObj.add(i, obj.display.get(name).rotation.get(i));
        }
        return arrObj;
    }

    @Override
    ArrayList<Float> Scale(String name) {
        ArrayList<Float> arrObj = new ArrayList<>();
        if(obj.display.get(name) == null) {
            throw new Exception("${name} is incorrect...!");
        }
        if(obj.display.get(name).scale == null) {
            throw new Exception("${name} does not contain Scale...!");
        }
        for(int i = 0; i < obj.display.get(name).scale.size; i++) {
            arrObj.add(i, obj.display.get(name).scale.get(i));
        }
        return arrObj;
    }

    @Override
    def Overrides() {
        return obj.overrides;
    }

    @Override
    def OverridesPredicate() {
        return obj.overrides.predicate;
    }

    @Override
    def OverridesModel() {
        return obj.overrides.model;
    }
}
