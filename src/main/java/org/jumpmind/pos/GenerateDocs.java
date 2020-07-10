package org.jumpmind.pos;

public class GenerateDocs {
    public static void main(String...args) throws Exception {
        ApplicationYamlDocumenter yaml = new ApplicationYamlDocumenter();
        yaml.render("/application.yml", "build/docs/JumpMind-Commerce-Parameters.html");
    }
}
