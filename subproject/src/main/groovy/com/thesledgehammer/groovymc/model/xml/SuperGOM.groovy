package com.thesledgehammer.groovymc.model.xml

import groovy.xml.MarkupBuilder

class SuperGOM {

    static String CURRENT_VERSION = "1.0.1";

    static void writeSuperGOM(String modelVersion) {
        def writer = new FileWriter("gom-${modelVersion}.xml");
        def xml = new MarkupBuilder(writer);

        xml.gom {
            ModelVersion(modelVersion)
            Parent()
            Textures {

            }
            Shade()
            Rules {
                When()
                Type()
                From()
                To()
                Origin()
                Angle()
                Scale()
            }
            Elements {
                Element() {
                    From()
                    To()
                    Light()
                    Render()
                    Faces {
                        Up {
                            UV()
                            Texture()
                            CullFace()
                            Rotation()
                            Tint()
                        }
                        Down {
                            UV()
                            Texture()
                            CullFace()
                            Rotation()
                            Tint()
                        }
                        North {
                            UV()
                            Texture()
                            CullFace()
                            Rotation()
                            Tint()
                        }
                        East {
                            UV()
                            Texture()
                            CullFace()
                            Rotation()
                            Tint()
                        }
                        West {
                            UV()
                            Texture()
                            CullFace()
                            Rotation()
                            Tint()
                        }
                        South {
                            UV()
                            Texture()
                            CullFace()
                            Rotation()
                            Tint()
                        }
                    }
                    Rotation {
                        Origin()
                        Axis()
                        Angle()
                        Rescale()
                    }
                    Shade()
                    Colour()
                    Visible()
                    Invert()
                    BothSides()
                }
            }
            Display {
                Name()
                Translation()
                Rotation()
                Scale()
            }
            Values()
            AmbientOcculusion()
        }
        writer.close();
    }

    static def readSuperGOM(String modelVersion) {
        def xml = new XmlSlurper();
        def superGom = xml.parse(new FileReader("gom-${modelVersion}.xml"))
        return superGom;
    }
}
