package org.jumpmind.pos;

import com.google.common.io.Resources;
import com.vladsch.flexmark.ext.gfm.strikethrough.StrikethroughExtension;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.options.MutableDataSet;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.util.StringUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ApplicationYamlDocumenter {

    private Map<String, String> comments = new HashMap<>();

    private Set<String> headersPrinted = new HashSet<>();

    public void render(String inputFile, String outputFileName) {
        Yaml yaml = new Yaml();

        try {
            Map<String, Object> yamlNode = yaml.load(new InputStreamReader(getClass().getResourceAsStream(inputFile)));

            StringBuilder buffer = new StringBuilder(1024);
            buffer.append(tableHeader());
            renderNode("", yamlNode, buffer);
            buffer.append(tableFooter());

            String html = wrapWithHtml(buffer.toString());

            System.out.println(buffer.toString());

            File outputFile = new File(outputFileName);
            FileUtils.write(outputFile, html, "UTF8");
            System.out.println(outputFile.getCanonicalPath());
        } catch (Exception ex) {
            throw new RuntimeException("Failed to render document", ex);
        }
    }


    private void renderNode(String key, Object yamlNode, StringBuilder buffer) {
        if (yamlNode instanceof Map) {
            renderMapNode(key, (Map<String, Object>) yamlNode, buffer);
        } else if (yamlNode instanceof List) {
            renderListNode(key, (List<Object>) yamlNode, buffer);
        } else {
            renderValueNode(key, yamlNode, buffer);
        }
    }

    private void renderMapNode(String key, Map<String, Object> yamlNode, StringBuilder buffer) {
        writeComment(key, buffer, yamlNode);

        for (String subkey : yamlNode.keySet()) {
            Object node = yamlNode.get(subkey);

            renderNode(combine(key, subkey), node, buffer);
        }
    }

    private String combine(String key, String subkey) {
        if (StringUtils.hasLength(key)) {
            return key + "." + subkey;
        } else {
            return subkey;
        }
    }

    private void renderListNode(String key, List<Object> yamlNode, StringBuilder buffer) {
        writeComment(key, buffer, buffer);
        for (Object node : yamlNode) {
            buffer.append("\t");
            renderNode(key, node, buffer);
        }
    }

    private void writeComment(String key, StringBuilder buffer, Object yamlNode) {
        String comment = comments.get(key);

        boolean hasPrimitiveChildren = hasPrimitiveChildren(yamlNode);

        if ((StringUtils.isEmpty(comment) && !hasPrimitiveChildren)
                || (StringUtils.isEmpty(comment) && StringUtils.isEmpty(key))) {
            return;
        }

        if (headersPrinted.contains(key)) {
            return;
        }

        int level = key.split("\\.").length;

        // section header here.

        headersPrinted.add(key);

        if (level <= 2) {
            buffer.append("<tr><td colspan=\"3\"><div class=\"sectionHeader1 sectionHeader\">").append(key).append("</div>");
        } else {
            buffer.append("<tr><td colspan=\"3\"><div class=\"sectionHeader\">").append(key).append("</div>");
        }


        if (StringUtils.hasLength(comment)) {
            buffer.append("<br/><div class=\"comment\">").append(comment).append("</div>");
        }

        buffer.append("</td></tr>\n");
    }

    private boolean hasPrimitiveChildren(Object yamlNode) {
        if (yamlNode instanceof Map) {
            Map<String, Object> yamlMapNode = (Map<String, Object>) yamlNode;
            for (Object value : yamlMapNode.values()) {
                if (!(value instanceof Map)
                        && !(value instanceof List)) {
                    return true;
                }
            }
        } else if (yamlNode instanceof List) {
            List<Object> yamlListNode = (List<Object>) yamlNode;
            for (Object value : yamlListNode) {
                if (!(value instanceof Map)
                        && !(value instanceof List)) {
                    return true;
                }
            }
        }

        return false;
    }

    private void renderValueNode(String key, Object yamlNode, StringBuilder buffer) {

        if (isComment(key)) {
            comments.put(key.replace("__", ""), (String) yamlNode);
        } else {
            String comment = comments.get(key);
            buffer.append(tableRow(key, yamlNode, comment));
        }
    }

    private String tableHeader() {
        StringBuilder buff = new StringBuilder();
        buff.append("\n");
        buff.append("<table class=\"table\">\n");
        return buff.toString();
    }

    private String tableFooter() {
        return "</table>\n";
    }

    private String tableRow(String key, Object yamlNode, String comment) {
        StringBuilder buff = new StringBuilder();
        if (comment == null) {
            comment = "";
        }
        buff.append("<tr>");
        buff.append("<td class=\"keyColumn\">").append(key).append("</td><td class=\"valueColumn\">").append(yamlNode).append("</td><td><div class=\"comment\">").append(comment).append("</div></td>");
        buff.append("</tr>\n");

        return buff.toString();
    }

    private boolean isComment(String key) {
        if (key == null) {
            return false;
        }
        return key.contains("__");
    }

    private String wrapWithHtml(String htmlBody) {
        try {
            String templateText =
                    IOUtils.toString(getClass().getResourceAsStream("/parameters-template.html"), Charset.defaultCharset());
            String html = templateText.replace("BODY_HERE", htmlBody);
            html = html.replace("<table>", "<table class=\"table\"");
            return html;
        } catch (IOException e) {
            throw new RuntimeException("Failed to generate HTML document ", e);
        }
    }

    private void convertToHtml(String markdownFilename) {
        try {
            MutableDataSet options = new MutableDataSet();
            options.set(Parser.EXTENSIONS, Arrays.asList(StrikethroughExtension.create(), TablesExtension.create()));
            Parser parser = Parser.builder(options).build();
            HtmlRenderer renderer = HtmlRenderer.builder(options).build();
            Node document = null;

            document = parser.parseReader(new FileReader(new File(markdownFilename)));
            String html = renderer.render(document);

            java.net.URL url = Resources.getResource("header.html");
            String templateText = Resources.toString(url, StandardCharsets.UTF_8);

            String htmlFilename = markdownFilename.replace(".md", ".html");

            html = templateText.replace("BODY_HERE", html);
            html = html.replace("<table>", "<table class=\"table\"");

            File htmlFile = new File(htmlFilename);

            System.out.println(htmlFile.getCanonicalPath());

            FileUtils.write(htmlFile, html, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert markdown file", e);
        }
    }

}
