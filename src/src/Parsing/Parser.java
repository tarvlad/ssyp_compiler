package Parsing;

import java.util.ArrayList;
import java.util.HashMap;

public class Parser {
    final private ArrayList<String> tokens;
    final private String[] Operations = {"ADD", ""};

    public Parser(ArrayList<String> tokens) {
        this.tokens = tokens;
    }

    int getSize(String type) {
        switch (type) {
            case "Int":
                return 8;
            default:
                System.out.println("Invalid typeName: " + type + "!");
                return 0;
        }
    }

    public HashMap<String, Integer[]> createVarsTable() {
        boolean InVars = false;
        HashMap<String, Integer[]> table = new HashMap<>();
        VarsTypes type = VarsTypes.VAR;
        int VP = 0;
        for (int k = 0; k < this.tokens.size(); k++) {
            String thisToken = this.tokens.get(k), nextToken = this.tokens.get(k + 1);
            if (thisToken.equals("#") && (nextToken.contains("ARGS_BEGIN") || nextToken.contains("VARS_BEGIN")
                    || nextToken.contains("CONSTANTS_BEGIN")))
            {
                InVars = true;
                type = VarsTypes.valueOf(nextToken.split("_")[1]);
            } else if (thisToken.equals("#") && (nextToken.contains("ARGS_END") || nextToken.contains("VARS_END")
                    || nextToken.contains("CONSTANTS_END"))) {
                InVars = false;
                type = VarsTypes.valueOf(nextToken.split("_")[1]);
            } else if (nextToken.equals("@") && InVars) {
                if (getSize(tokens.get(k + 2)) != 0) {
                    table.put(thisToken, new Integer[] {VP, type.ordinal()});
                    VP += getSize(tokens.get(k + 2));
                }
            }
        }
        return table;
    }
}
