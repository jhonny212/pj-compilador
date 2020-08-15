package com.mycompany.programming.lan.Gramatica;

import java_cup.runtime.Symbol;

%%
%class lexer
%cup
%cupdebug
%line
%column
%full
%char
%public
/*Identifiers*/

numero=[0-9]
UpperCase=[A-Z]
LowalCase=[a-z]
symbols=[-$#.]
guionBajo=[_]
LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
WhiteSpace     = {LineTerminator} | [ \t\f]
 /* comments */
Comment = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}
TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment     = "//" {InputCharacter}* {LineTerminator}?
DocumentationComment = "/**" {CommentContent} "*"+ "/"
CommentContent       = ( [^*] | \*+ [^/*] )*
DecIntegerLiteral = 0 | [1-9][0-9]*
Identifier = [:jletter:] [:jletterdigit:]*
Cadena = "\""({Comment}|{CommentContent})*"\""
%{
    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline+1, yycolumn+1, value);
    }
%}
%%
<YYINITIAL>{
"::"                                                                        {return symbol(sym.BOTHPOINT,new String(yytext()));}
"terminal"                                                                  {return symbol(sym.TERM,new String(yytext()));}
"no"                                                                        {return symbol(sym.NO,new String(yytext()));}
"*"                                                                         {return symbol(sym.ASTERIS,new String(yytext()));}
"?"                                                                         {return symbol(sym.QUESTION,new String(yytext()));}
"+"                                                                         {return symbol(sym.PLUS,new String(yytext()));}
"|"                                                                         {return symbol(sym.OR,new String(yytext()));}
"("                                                                         {return symbol(sym.ABREPAREN,new String(yytext()));}  
")"                                                                         {return symbol(sym.CIERRAPAREN,new String(yytext()));} 
"["                                                                         {return symbol(sym.ABRECOR,new String(yytext()));}    
"]"                                                                         {return symbol(sym.CIERRACOR,new String(yytext()));}   
"["({UpperCase}("-")({UpperCase}))"]"                                       {return symbol(sym.UPC,new String(yytext()));}
"["({LowalCase}("-")({LowalCase}))"]"                                       {return symbol(sym.LWC,new String(yytext()));}
"["({numero}("-")({numero}))"]"                                             {return symbol(sym.NUMC,new String(yytext()));}
"="                                                                         {return symbol(sym.IGUAL,new String(yytext()));} 
"%%"                                                                        {return symbol(sym.SEPARATOR,new String(yytext()));} 
"nombre"                                                                    {return symbol(sym.NAME,new String(yytext()));}   
"version"                                                                   {return symbol(sym.VER,new String(yytext()));}
"autor"                                                                     {return symbol(sym.AUTHOR,new String(yytext()));}       
"extension"                                                                 {return symbol(sym.EXTENS,new String(yytext()));}
"{"                                                                         {return symbol(sym.ABREKEY,new String(yytext()));}
"}"                                                                         {return symbol(sym.CIERRAKEY,new String(yytext()));}
":"                                                                         {return symbol(sym.TWOPOINT,new String(yytext()));}
","                                                                         {return symbol(sym.COMA,new String(yytext()));}
";"                                                                         {return symbol(sym.PNTCOMA,new String(yytext()));}

(({numero})+)(".")(({numero})+)                                             {return symbol(sym.FLOAT,new Float(yytext()));}    
(({numero})+)                                                               {return symbol(sym.NUM,new Integer(yytext()));}    
({Identifier})(":")({Identifier})                                           {return symbol(sym.EXT_ID,new String(yytext()));}   

{Identifier}                                                                {return symbol(sym.ID,new String(yytext()));}   
{Cadena}                                                                    {return symbol(sym.CADENA,new String(yytext()));}
{WhiteSpace}                                                                {}
{Comment}                                                                   {}
.                                                                           
{return symbol(sym.ERROR,new String(yytext()));}    

}

