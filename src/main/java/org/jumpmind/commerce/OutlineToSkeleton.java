package org.jumpmind.commerce;

import org.apache.commons.lang3.StringUtils;

import java.io.*;

public class OutlineToSkeleton {

    private FileOutputStream out;

    public static void main(String[] args) throws Exception {

        OutlineToSkeleton outliner = new OutlineToSkeleton();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    "src/main/resources/outline.txt"));
            String line = reader.readLine();
            while (line != null) {
//                System.out.println(line);
                outliner.processLine(line);
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void processLine(String line) throws Exception {
        int level = getLevel(line);
        line = line.trim();
        line = line.substring(line.indexOf('.')+1).trim();
        if (level == 1) {
            if (out != null) {
                out.close();
            }

            String dir = "src/main/markdown/" + line;
            new File("src/main/markdown/" + line).mkdirs();

            out = new FileOutputStream(new File(dir + "/" +line + ".md"));
        }

        String header = StringUtils.leftPad("",  level, "#");



        String formattedLine = header + " " + line;

        formattedLine += "\n\n";

//        if (level <=2 ) {
//            formattedLine += "\n";
//        }

        out.write(formattedLine.getBytes());

        System.out.println(formattedLine);

    }

    private int getLevel(String line) {
        int leadingSpaces = line.indexOf(line.trim());
        return (leadingSpaces / 3)+1;
    }


}
