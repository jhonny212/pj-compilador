/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.programming.lan.programming.language;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenizer {

    private class ExpresionRegular {

        private final Pattern regex;
        private final int token;
        private final String tipo;

        public ExpresionRegular(Pattern regex, int token, String tipo) {
            //super();
            this.regex = regex;
            this.token = token;
            this.tipo = tipo;
        }
    }

    public class Token {

        private final int token;
        private final String sequence;
        private final String tipo;

        public Token(int token, String sequence, String tipo) {
            //super();
            this.token = token;
            this.sequence = sequence;
            this.tipo = tipo;
        }

        public int getToken() {
            return token;
        }

        public String getSequence() {
            return sequence;
        }

        public String getTipo() {
            return tipo;
        }

    }

    private final ArrayList<ExpresionRegular> tokenInfos;
    private final ArrayList<Token> tokens;

    public Tokenizer() {
        tokenInfos = new ArrayList<>();
        tokens = new ArrayList<>();
    }

    public void add(String regex, int token, String tipo) {
        tokenInfos.add(new ExpresionRegular(Pattern.compile("^(" + regex + ")"), token, tipo));
    }

    public void tokenize(String str, boolean ignoreSpace) throws Exception {
        String s = "";
        if (ignoreSpace) {
            s = str.trim();
        } else {
            s = str;
        }
        tokens.clear();
        while (!s.equals("")) {
            boolean match = false;
            for (ExpresionRegular info : tokenInfos) {
                Matcher m = info.regex.matcher(s);
                if (m.find()) {
                    match = true;
                    String tok = m.group().trim();
                    s = m.replaceFirst("").trim();
                    tokens.add(new Token(info.token, tok, info.tipo));
                }
            }

            if (!match) {
                throw new Exception("Error inesperado en: " + s);
            }
        }
    }

    public ArrayList<Token> getTokens() {
        return tokens;
    }

}
