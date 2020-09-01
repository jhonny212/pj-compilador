// DO NOT EDIT
// Generated by JFlex 1.8.2 http://jflex.de/
// source: src/main/java/com/mycompany/programming/lan/Gramatica/lexer.Jflex

package com.mycompany.programming.lan.Gramatica;

import java_cup.runtime.Symbol;
import com.mycompany.programming.lan.Gramatica.Errores.ErrorClass;

// See https://github.com/jflex-de/jflex/issues/222
@SuppressWarnings("FallThrough")
public class lexer implements java_cup.runtime.Scanner {

  /** This character denotes the end of file. */
  public static final int YYEOF = -1;

  /** Initial size of the lookahead buffer. */
  private static final int ZZ_BUFFERSIZE = 16384;

  // Lexical states.
  public static final int YYINITIAL = 0;
  public static final int STRING = 2;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = {
     0,  0,  1, 1
  };

  /**
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = {
     0,  0,  0,  0,  0,  0,  0,  0,  0,  1,  2,  3,  1,  4,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  5,  5,  5,  5, 
     1,  5,  6,  5,  7,  8,  9,  5, 10, 11, 12, 13, 14, 15, 16, 17, 
    18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 19, 20,  5, 21,  5, 22, 
     5, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 
    23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 24, 25, 26,  5, 27, 
     5, 28, 29, 30, 31, 32, 33, 33, 33, 34, 33, 33, 35, 36, 37, 38, 
    33, 33, 39, 40, 41, 42, 43, 33, 44, 33, 45, 46, 47, 48,  5,  0, 
     0,  0,  0,  0,  0, 49,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     5,  5,  7,  7,  7,  7,  5,  5,  5,  5,  7,  5,  5,  0,  5,  5, 
     5,  5,  5,  5,  5,  7,  5,  5,  5,  5,  7,  5,  5,  5,  5,  5, 
     7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7, 
     7,  7,  7,  7,  7,  7,  7,  5,  7,  7,  7,  7,  7,  7,  7,  7, 
     7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7,  7, 
     7,  7,  7,  7,  7,  7,  7,  5,  7,  7,  7,  7,  7,  7,  7,  7
  };

  /**
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\2\0\1\1\2\2\1\3\1\4\1\1\1\5\1\6"+
    "\1\7\1\10\1\11\1\12\1\13\1\1\1\14\1\15"+
    "\1\16\1\17\1\20\1\21\1\1\1\22\1\23\10\4"+
    "\1\24\1\25\1\26\1\27\1\30\1\27\1\0\1\31"+
    "\1\0\1\2\1\0\1\32\3\0\1\33\1\34\5\4"+
    "\1\35\3\4\1\36\1\37\2\0\1\40\3\0\11\4"+
    "\1\0\1\2\3\0\6\4\1\41\2\4\1\42\1\43"+
    "\1\44\10\4\2\0\5\4\1\45\2\4\2\0\2\4"+
    "\1\45\2\4\1\46\1\47\2\0\2\4\1\0\1\50"+
    "\2\0\1\4\1\0\1\51\1\52\1\50";

  private static int [] zzUnpackAction() {
    int [] result = new int[133];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /**
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\62\0\144\0\144\0\226\0\144\0\310\0\372"+
    "\0\144\0\144\0\144\0\144\0\144\0\144\0\144\0\u012c"+
    "\0\u015e\0\u0190\0\144\0\144\0\144\0\u01c2\0\u01f4\0\144"+
    "\0\310\0\u0226\0\u0258\0\u028a\0\u02bc\0\u02ee\0\u0320\0\u0352"+
    "\0\u0384\0\144\0\144\0\144\0\u03b6\0\144\0\u03e8\0\u041a"+
    "\0\144\0\u044c\0\u047e\0\u04b0\0\144\0\u04e2\0\u0514\0\u0546"+
    "\0\144\0\144\0\u0578\0\u05aa\0\u05dc\0\u060e\0\u0640\0\u0672"+
    "\0\u06a4\0\u06d6\0\u0708\0\144\0\u073a\0\u076c\0\u079e\0\u04b0"+
    "\0\u07d0\0\u0802\0\u0834\0\u0866\0\u0898\0\u08ca\0\u08fc\0\u092e"+
    "\0\u0960\0\u0992\0\u09c4\0\u09f6\0\u0a28\0\u076c\0\u0a5a\0\u0a8c"+
    "\0\u0abe\0\u0af0\0\u0b22\0\u0b54\0\u0b86\0\u0bb8\0\u0bea\0\310"+
    "\0\u0c1c\0\u0c4e\0\144\0\144\0\144\0\u0c80\0\u0cb2\0\u0ce4"+
    "\0\u0d16\0\u0d48\0\u0d7a\0\u0dac\0\u0dde\0\u0e10\0\u0e42\0\u0e74"+
    "\0\u0ea6\0\u0ed8\0\u0f0a\0\u0f3c\0\u0f6e\0\u0fa0\0\u0fd2\0\u1004"+
    "\0\u1036\0\u1068\0\u109a\0\u10cc\0\u10fe\0\u1130\0\u1162\0\310"+
    "\0\u1194\0\u11c6\0\u11f8\0\u122a\0\u125c\0\u128e\0\u12c0\0\u12f2"+
    "\0\u1324\0\u1356\0\u1388\0\310\0\u13ba";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[133];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /**
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\3\2\4\1\0\1\5\1\3\1\6\1\7\1\10"+
    "\1\11\1\12\1\13\1\14\1\15\1\16\1\3\1\17"+
    "\1\20\1\21\1\22\1\23\1\24\1\25\1\7\1\26"+
    "\1\27\1\30\1\31\1\32\1\7\1\33\1\7\1\34"+
    "\2\7\1\35\1\7\1\36\1\7\1\37\1\7\1\40"+
    "\1\7\1\41\2\7\1\42\1\43\1\44\1\0\6\45"+
    "\1\46\22\45\1\47\30\45\64\0\1\4\57\0\1\7"+
    "\6\0\1\7\12\0\1\7\1\50\3\0\1\7\3\0"+
    "\23\7\3\0\1\7\10\0\1\51\65\0\1\52\4\0"+
    "\1\53\60\0\1\54\1\0\1\21\62\0\1\55\60\0"+
    "\1\56\4\0\1\57\4\0\22\60\41\0\1\61\7\0"+
    "\1\62\3\0\1\62\10\0\1\7\6\0\1\7\12\0"+
    "\1\7\1\50\3\0\1\7\3\0\17\7\1\63\3\7"+
    "\3\0\2\7\6\0\1\7\12\0\1\7\1\50\3\0"+
    "\1\7\3\0\1\7\1\64\21\7\3\0\2\7\6\0"+
    "\1\7\12\0\1\7\1\50\3\0\1\7\3\0\12\7"+
    "\1\65\6\7\1\66\1\7\3\0\2\7\6\0\1\7"+
    "\12\0\1\7\1\50\3\0\1\7\3\0\1\7\1\67"+
    "\21\7\3\0\2\7\6\0\1\7\12\0\1\7\1\50"+
    "\3\0\1\7\3\0\13\7\1\70\7\7\3\0\2\7"+
    "\6\0\1\7\12\0\1\7\1\50\3\0\1\7\3\0"+
    "\5\7\1\71\15\7\3\0\2\7\6\0\1\7\12\0"+
    "\1\7\1\50\3\0\1\7\3\0\5\7\1\72\15\7"+
    "\3\0\2\7\6\0\1\7\12\0\1\7\1\50\3\0"+
    "\1\7\3\0\5\7\1\73\15\7\3\0\1\7\6\45"+
    "\1\0\61\45\1\74\53\45\7\0\1\75\17\0\1\75"+
    "\3\0\23\75\4\0\14\76\1\77\45\76\2\53\1\4"+
    "\1\53\1\5\55\53\22\0\1\100\56\0\1\101\61\0"+
    "\1\102\61\0\1\103\42\0\1\7\6\0\1\7\12\0"+
    "\1\7\1\50\3\0\1\7\3\0\16\7\1\104\4\7"+
    "\3\0\2\7\6\0\1\7\12\0\1\7\1\50\3\0"+
    "\1\7\3\0\4\7\1\105\16\7\3\0\2\7\6\0"+
    "\1\7\12\0\1\7\1\50\3\0\1\7\3\0\16\7"+
    "\1\106\4\7\3\0\2\7\6\0\1\7\12\0\1\7"+
    "\1\50\3\0\1\7\3\0\16\7\1\107\4\7\3\0"+
    "\2\7\6\0\1\7\12\0\1\7\1\50\3\0\1\7"+
    "\3\0\12\7\1\110\10\7\3\0\2\7\6\0\1\7"+
    "\12\0\1\7\1\50\3\0\1\7\3\0\11\7\1\111"+
    "\11\7\3\0\2\7\6\0\1\7\12\0\1\7\1\50"+
    "\3\0\1\7\3\0\1\7\1\112\21\7\3\0\2\7"+
    "\6\0\1\7\12\0\1\7\1\50\3\0\1\7\3\0"+
    "\14\7\1\113\6\7\3\0\2\7\6\0\1\7\12\0"+
    "\1\7\1\50\3\0\1\7\3\0\14\7\1\114\6\7"+
    "\3\0\1\7\1\75\6\0\1\75\12\0\1\75\4\0"+
    "\1\75\3\0\23\75\3\0\1\75\14\76\1\115\61\76"+
    "\1\115\4\76\1\116\40\76\22\0\1\117\66\0\1\120"+
    "\66\0\22\121\4\0\1\7\6\0\1\7\12\0\1\7"+
    "\1\50\3\0\1\7\3\0\13\7\1\122\7\7\3\0"+
    "\2\7\6\0\1\7\12\0\1\7\1\50\3\0\1\7"+
    "\3\0\5\7\1\123\15\7\3\0\2\7\6\0\1\7"+
    "\12\0\1\7\1\50\3\0\1\7\3\0\5\7\1\124"+
    "\15\7\3\0\2\7\6\0\1\7\12\0\1\7\1\50"+
    "\3\0\1\7\3\0\5\7\1\125\15\7\3\0\2\7"+
    "\6\0\1\7\12\0\1\7\1\50\3\0\1\7\3\0"+
    "\22\7\1\126\3\0\2\7\6\0\1\7\12\0\1\7"+
    "\1\50\3\0\1\7\3\0\2\7\1\127\20\7\3\0"+
    "\2\7\6\0\1\7\12\0\1\7\1\50\3\0\1\7"+
    "\3\0\10\7\1\130\12\7\3\0\2\7\6\0\1\7"+
    "\12\0\1\7\1\50\3\0\1\7\3\0\11\7\1\131"+
    "\11\7\3\0\2\7\6\0\1\7\12\0\1\7\1\50"+
    "\3\0\1\7\3\0\15\7\1\132\5\7\3\0\1\7"+
    "\14\76\1\115\4\76\1\4\40\76\32\0\1\133\61\0"+
    "\1\134\61\0\1\135\27\0\1\7\6\0\1\7\12\0"+
    "\1\7\1\50\3\0\1\7\3\0\14\7\1\136\6\7"+
    "\3\0\2\7\6\0\1\7\12\0\1\7\1\50\3\0"+
    "\1\7\3\0\12\7\1\137\10\7\3\0\2\7\6\0"+
    "\1\7\12\0\1\7\1\50\3\0\1\7\3\0\14\7"+
    "\1\140\6\7\3\0\2\7\6\0\1\7\12\0\1\7"+
    "\1\50\3\0\1\7\3\0\12\7\1\141\10\7\3\0"+
    "\2\7\6\0\1\7\12\0\1\7\1\50\3\0\1\7"+
    "\3\0\1\7\1\142\21\7\3\0\2\7\6\0\1\7"+
    "\12\0\1\7\1\50\3\0\1\7\3\0\14\7\1\143"+
    "\6\7\3\0\2\7\6\0\1\7\12\0\1\7\1\50"+
    "\3\0\1\7\3\0\7\7\1\144\13\7\3\0\2\7"+
    "\6\0\1\7\12\0\1\7\1\50\3\0\1\7\3\0"+
    "\7\7\1\145\13\7\3\0\2\7\2\146\1\0\1\146"+
    "\2\0\1\7\12\0\1\7\1\147\3\0\1\7\3\0"+
    "\23\7\3\0\2\7\6\0\1\7\12\0\1\7\1\50"+
    "\3\0\1\7\3\0\1\7\1\130\21\7\3\0\2\7"+
    "\6\0\1\7\12\0\1\7\1\50\3\0\1\7\3\0"+
    "\13\7\1\130\7\7\3\0\2\7\6\0\1\7\12\0"+
    "\1\7\1\50\3\0\1\7\3\0\15\7\1\150\5\7"+
    "\3\0\2\7\6\0\1\7\12\0\1\7\1\50\3\0"+
    "\1\7\3\0\11\7\1\151\11\7\3\0\2\7\6\0"+
    "\1\7\12\0\1\7\1\50\3\0\1\7\3\0\5\7"+
    "\1\152\15\7\3\0\2\7\6\0\1\7\12\0\1\7"+
    "\1\50\3\0\1\7\3\0\12\7\1\153\10\7\3\0"+
    "\2\7\6\0\1\7\12\0\1\7\1\50\3\0\1\7"+
    "\3\0\13\7\1\154\7\7\3\0\1\7\1\0\2\146"+
    "\1\0\1\146\16\0\1\147\37\0\2\147\1\0\1\147"+
    "\2\0\1\155\17\0\1\155\3\0\23\155\4\0\1\7"+
    "\6\0\1\7\12\0\1\7\1\50\3\0\1\7\3\0"+
    "\7\7\1\156\13\7\3\0\2\7\6\0\1\7\12\0"+
    "\1\7\1\50\3\0\1\7\3\0\7\7\1\157\13\7"+
    "\3\0\2\7\2\160\1\0\1\160\2\0\1\7\12\0"+
    "\1\7\1\161\3\0\1\7\3\0\23\7\3\0\2\7"+
    "\6\0\1\7\12\0\1\7\1\50\3\0\1\7\3\0"+
    "\1\7\1\162\21\7\3\0\2\7\6\0\1\7\12\0"+
    "\1\7\1\50\3\0\1\7\3\0\12\7\1\163\10\7"+
    "\3\0\1\7\1\155\2\164\1\0\1\164\2\0\1\155"+
    "\12\0\1\155\4\0\1\155\3\0\23\155\3\0\1\155"+
    "\1\7\6\0\1\7\12\0\1\7\1\50\3\0\1\7"+
    "\3\0\13\7\1\165\7\7\3\0\2\7\6\0\1\7"+
    "\12\0\1\7\1\50\3\0\1\7\3\0\5\7\1\166"+
    "\15\7\3\0\1\7\1\0\2\160\1\0\1\160\16\0"+
    "\1\161\37\0\2\161\1\0\1\161\2\0\1\167\17\0"+
    "\1\167\3\0\23\167\4\0\1\7\6\0\1\7\12\0"+
    "\1\7\1\50\3\0\1\7\3\0\10\7\1\170\12\7"+
    "\3\0\2\7\2\171\1\0\1\171\2\0\1\7\12\0"+
    "\1\7\1\172\3\0\1\7\3\0\23\7\3\0\1\7"+
    "\1\0\2\164\1\0\1\164\2\0\1\155\17\0\1\155"+
    "\3\0\23\155\4\0\1\7\6\0\1\7\12\0\1\7"+
    "\1\50\3\0\1\7\3\0\12\7\1\173\10\7\3\0"+
    "\2\7\6\0\1\7\12\0\1\7\1\50\3\0\1\7"+
    "\3\0\12\7\1\174\10\7\3\0\1\7\1\167\6\0"+
    "\1\167\12\0\1\167\4\0\1\167\3\0\23\167\3\0"+
    "\1\167\1\0\2\171\1\0\1\171\16\0\1\175\37\0"+
    "\2\175\1\0\1\175\2\0\1\75\12\0\1\176\4\0"+
    "\1\75\3\0\23\75\4\0\1\7\2\177\1\0\1\177"+
    "\2\0\1\7\12\0\1\7\1\200\3\0\1\7\3\0"+
    "\23\7\3\0\2\7\6\0\1\7\12\0\1\7\1\50"+
    "\3\0\1\7\3\0\16\7\1\201\4\7\3\0\1\7"+
    "\1\0\2\175\1\0\1\175\15\0\1\176\57\0\1\202"+
    "\1\0\1\176\40\0\2\177\1\0\1\177\16\0\1\200"+
    "\37\0\2\200\1\0\1\200\2\0\1\203\17\0\1\203"+
    "\3\0\23\203\4\0\1\7\6\0\1\7\12\0\1\7"+
    "\1\50\3\0\1\7\3\0\13\7\1\204\7\7\3\0"+
    "\1\7\22\0\1\205\37\0\1\203\6\0\1\203\12\0"+
    "\1\203\4\0\1\203\3\0\23\203\3\0\1\203\20\0"+
    "\1\202\41\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[5100];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** Error code for "Unknown internal scanner error". */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  /** Error code for "could not match input". */
  private static final int ZZ_NO_MATCH = 1;
  /** Error code for "pushback value was too large". */
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /**
   * Error messages for {@link #ZZ_UNKNOWN_ERROR}, {@link #ZZ_NO_MATCH}, and
   * {@link #ZZ_PUSHBACK_2BIG} respectively.
   */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state {@code aState}
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\2\0\2\11\1\1\1\11\2\1\7\11\3\1\3\11"+
    "\2\1\1\11\11\1\3\11\1\1\1\11\1\1\1\0"+
    "\1\11\1\0\1\1\1\0\1\11\3\0\2\11\11\1"+
    "\1\11\1\1\2\0\1\1\3\0\11\1\1\0\1\1"+
    "\3\0\11\1\3\11\10\1\2\0\10\1\2\0\7\1"+
    "\2\0\2\1\1\0\1\1\2\0\1\1\1\0\3\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[133];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** Input device. */
  private java.io.Reader zzReader;

  /** Current state of the DFA. */
  private int zzState;

  /** Current lexical state. */
  private int zzLexicalState = YYINITIAL;

  /**
   * This buffer contains the current text to be matched and is the source of the {@link #yytext()}
   * string.
   */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** Text position at the last accepting state. */
  private int zzMarkedPos;

  /** Current text position in the buffer. */
  private int zzCurrentPos;

  /** Marks the beginning of the {@link #yytext()} string in the buffer. */
  private int zzStartRead;

  /** Marks the last character in the buffer, that has been read from input. */
  private int zzEndRead;

  /**
   * Whether the scanner is at the end of file.
   * @see #yyatEOF
   */
  private boolean zzAtEOF;

  /**
   * The number of occupied positions in {@link #zzBuffer} beyond {@link #zzEndRead}.
   *
   * <p>When a lead/high surrogate has been read from the input stream into the final
   * {@link #zzBuffer} position, this will have a value of 1; otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /** Number of newlines encountered up to the start of the matched text. */
  private int yyline;

  /** Number of characters from the last newline up to the start of the matched text. */
  private int yycolumn;

  /** Number of characters up to the start of the matched text. */
  private long yychar;

  /** Whether the scanner is currently at the beginning of a line. */
  @SuppressWarnings("unused")
  private boolean zzAtBOL = true;

  /** Whether the user-EOF-code has already been executed. */
  private boolean zzEOFDone;

  /* user code: */
  public ErrorClass error=new ErrorClass(); 
 StringBuffer string = new StringBuffer();
    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline+1, yycolumn+1, value);
    }


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public lexer(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Translates raw input code points to DFA table row
   */
  private static int zzCMap(int input) {
    return ZZ_CMAP[input];
  }

  /**
   * Refills the input buffer.
   *
   * @return {@code false} iff there was new input.
   * @exception java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead - zzStartRead);

      /* translate stored positions */
      zzEndRead -= zzStartRead;
      zzCurrentPos -= zzStartRead;
      zzMarkedPos -= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length * 2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      throw new java.io.IOException(
          "Reader returned 0 characters. See JFlex examples/zero-reader for a workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
        if (numRead == requested) { // We requested too few chars to encode a full Unicode character
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        } else {                    // There is room in the buffer for at least one more char
          int c = zzReader.read();  // Expecting to read a paired low surrogate char
          if (c == -1) {
            return true;
          } else {
            zzBuffer[zzEndRead++] = (char)c;
          }
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }


  /**
   * Closes the input reader.
   *
   * @throws java.io.IOException if the reader could not be closed.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true; // indicate end of file
    zzEndRead = zzStartRead; // invalidate buffer

    if (zzReader != null) {
      zzReader.close();
    }
  }


  /**
   * Resets the scanner to read from a new input stream.
   *
   * <p>Does not close the old reader.
   *
   * <p>All internal variables are reset, the old input stream <b>cannot</b> be reused (internal
   * buffer is discarded and lost). Lexical state is set to {@code ZZ_INITIAL}.
   *
   * <p>Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader The new input stream.
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzEOFDone = false;
    yyResetPosition();
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE) {
      zzBuffer = new char[ZZ_BUFFERSIZE];
    }
  }

  /**
   * Resets the input position.
   */
  private final void yyResetPosition() {
      zzAtBOL  = true;
      zzAtEOF  = false;
      zzCurrentPos = 0;
      zzMarkedPos = 0;
      zzStartRead = 0;
      zzEndRead = 0;
      zzFinalHighSurrogate = 0;
      yyline = 0;
      yycolumn = 0;
      yychar = 0L;
  }


  /**
   * Returns whether the scanner has reached the end of the reader it reads from.
   *
   * @return whether the scanner has reached EOF.
   */
  public final boolean yyatEOF() {
    return zzAtEOF;
  }


  /**
   * Returns the current lexical state.
   *
   * @return the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state.
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   *
   * @return the matched text.
   */
  public final String yytext() {
    return new String(zzBuffer, zzStartRead, zzMarkedPos-zzStartRead);
  }


  /**
   * Returns the character at the given position from the matched text.
   *
   * <p>It is equivalent to {@code yytext().charAt(pos)}, but faster.
   *
   * @param position the position of the character to fetch. A value from 0 to {@code yylength()-1}.
   *
   * @return the character at {@code position}.
   */
  public final char yycharat(int position) {
    return zzBuffer[zzStartRead + position];
  }


  /**
   * How many characters were matched.
   *
   * @return the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occurred while scanning.
   *
   * <p>In a well-formed scanner (no or only correct usage of {@code yypushback(int)} and a
   * match-all fallback rule) this method will only be called with things that
   * "Can't Possibly Happen".
   *
   * <p>If this method is called, something is seriously wrong (e.g. a JFlex bug producing a faulty
   * scanner etc.).
   *
   * <p>Usual syntax/scanner level error handling should be done in error fallback rules.
   *
   * @param errorCode the code of the error message to display.
   */
  private static void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    } catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  }


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * <p>They will be read again by then next call of the scanning method.
   *
   * @param number the number of characters to be read again. This number must not be greater than
   *     {@link #yylength()}.
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
    
  yyclose();    }
  }




  /**
   * Resumes scanning until the next regular expression is matched, the end of input is encountered
   * or an I/O-Error occurs.
   *
   * @return the next token.
   * @exception java.io.IOException if any I/O-Error occurs.
   */
  @Override  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char[] zzBufferL = zzBuffer;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      yychar+= zzMarkedPosL-zzStartRead;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':  // fall through
        case '\u000C':  // fall through
        case '\u0085':  // fall through
        case '\u2028':  // fall through
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn += zzCharCount;
        }
      }

      if (zzR) {
        // peek one character ahead if it is
        // (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof)
            zzPeek = false;
          else
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {

          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMap(zzInput) ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
            zzDoEOF();
          { return new java_cup.runtime.Symbol(sym.EOF); }
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1:
            { this.error.AddError(0,yyline+1,yycolumn+1,yytext(),"Es lexema no esta definido");
  return symbol(sym.ERROR,new String(yytext()));
            }
            // fall through
          case 43: break;
          case 2:
            { 
            }
            // fall through
          case 44: break;
          case 3:
            { string = new StringBuffer();
yybegin(STRING);
            }
            // fall through
          case 45: break;
          case 4:
            { return symbol(sym.ID,new String(yytext()));
            }
            // fall through
          case 46: break;
          case 5:
            { return symbol(sym.AND,new String(yytext()));
            }
            // fall through
          case 47: break;
          case 6:
            { return symbol(sym.ABREPAREN,new String(yytext()));
            }
            // fall through
          case 48: break;
          case 7:
            { return symbol(sym.CIERRAPAREN,new String(yytext()));
            }
            // fall through
          case 49: break;
          case 8:
            { return symbol(sym.ASTERIS,new String(yytext()));
            }
            // fall through
          case 50: break;
          case 9:
            { return symbol(sym.PLUS,new String(yytext()));
            }
            // fall through
          case 51: break;
          case 10:
            { return symbol(sym.COMA,new String(yytext()));
            }
            // fall through
          case 52: break;
          case 11:
            { return symbol(sym.POINT,new String(yytext()));
            }
            // fall through
          case 53: break;
          case 12:
            { return symbol(sym.NUM,new Integer(yytext()));
            }
            // fall through
          case 54: break;
          case 13:
            { return symbol(sym.TWOPOINT,new String(yytext()));
            }
            // fall through
          case 55: break;
          case 14:
            { return symbol(sym.PNTCOMA,new String(yytext()));
            }
            // fall through
          case 56: break;
          case 15:
            { return symbol(sym.IGUAL,new String(yytext()));
            }
            // fall through
          case 57: break;
          case 16:
            { return symbol(sym.QUESTION,new String(yytext()));
            }
            // fall through
          case 58: break;
          case 17:
            { return symbol(sym.ABRECOR,new String(yytext()));
            }
            // fall through
          case 59: break;
          case 18:
            { return symbol(sym.CIERRACOR,new String(yytext()));
            }
            // fall through
          case 60: break;
          case 19:
            { return symbol(sym.GUIONBAJO,new String(yytext()));
            }
            // fall through
          case 61: break;
          case 20:
            { return symbol(sym.ABREKEY,new String(yytext()));
            }
            // fall through
          case 62: break;
          case 21:
            { return symbol(sym.OR,new String(yytext()));
            }
            // fall through
          case 63: break;
          case 22:
            { return symbol(sym.CIERRAKEY,new String(yytext()));
            }
            // fall through
          case 64: break;
          case 23:
            { string.append( yytext() );
            }
            // fall through
          case 65: break;
          case 24:
            { yybegin(YYINITIAL);
               return symbol(sym.CADENA,new String(string.toString()));
            }
            // fall through
          case 66: break;
          case 25:
            { return symbol(sym.SEPARATOR,new String(yytext()));
            }
            // fall through
          case 67: break;
          case 26:
            { return symbol(sym.BOTHPOINT,new String(yytext()));
            }
            // fall through
          case 68: break;
          case 27:
            { return symbol(sym.SPACE,new String(" "));
            }
            // fall through
          case 69: break;
          case 28:
            { return symbol(sym.SAL_TABL,new String(yytext()));
            }
            // fall through
          case 70: break;
          case 29:
            { return symbol(sym.NO,new String(yytext()));
            }
            // fall through
          case 71: break;
          case 30:
            { string.append('\"');
            }
            // fall through
          case 72: break;
          case 31:
            { return symbol(sym.EXT_ID,new String(yytext()));
            }
            // fall through
          case 73: break;
          case 32:
            { return symbol(sym.FLOAT,new Float(yytext()));
            }
            // fall through
          case 74: break;
          case 33:
            { return symbol(sym.TIPO_VAR,new String(yytext()));
            }
            // fall through
          case 75: break;
          case 34:
            { return symbol(sym.NUMC,new String(yytext()));
            }
            // fall through
          case 76: break;
          case 35:
            { return symbol(sym.UPC,new String(yytext()));
            }
            // fall through
          case 77: break;
          case 36:
            { return symbol(sym.LWC,new String(yytext()));
            }
            // fall through
          case 78: break;
          case 37:
            { return symbol(sym.AUTHOR,new String(yytext()));
            }
            // fall through
          case 79: break;
          case 38:
            { return symbol(sym.NAME,new String(yytext()));
            }
            // fall through
          case 80: break;
          case 39:
            { return symbol(sym.TERM,new String(yytext()));
            }
            // fall through
          case 81: break;
          case 40:
            { return symbol(sym.VER,new String(yytext()));
            }
            // fall through
          case 82: break;
          case 41:
            { return symbol(sym.EXTENS,new String(yytext()));
            }
            // fall through
          case 83: break;
          case 42:
            { return symbol(sym.LANZAMIENTO,new String(yytext()));
            }
            // fall through
          case 84: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }

  /**
   * Converts an int token code into the name of the
   * token by reflection on the cup symbol class/interface sym
   */
  private static String getTokenName(int token) {
    try {
      java.lang.reflect.Field [] classFields = sym.class.getFields();
      for (int i = 0; i < classFields.length; i++) {
        if (classFields[i].getInt(null) == token) {
          return classFields[i].getName();
        }
      }
    } catch (Exception e) {
      e.printStackTrace(System.err);
    }

    return "UNKNOWN TOKEN";
  }

  /**
   * Same as next_token but also prints the token to standard out
   * for debugging.
   */
  public java_cup.runtime.Symbol debug_next_token() throws java.io.IOException {
    java_cup.runtime.Symbol s = next_token();
    System.out.println( "line:" + (yyline+1) + " col:" + (yycolumn+1) + " char:" + yychar + " --"+ yytext() + "--" + getTokenName(s.sym) + "--");
    return s;
  }

  /**
   * Runs the scanner on input files.
   *
   * This main method is the debugging routine for the scanner.
   * It prints debugging information about each returned token to
   * System.out until the end of file is reached, or an error occured.
   *
   * @param argv   the command line, contains the filenames to run
   *               the scanner on.
   */
  public static void main(String[] argv) {
    if (argv.length == 0) {
      System.out.println("Usage : java lexer [ --encoding <name> ] <inputfile(s)>");
    }
    else {
      int firstFilePos = 0;
      String encodingName = "UTF-8";
      if (argv[0].equals("--encoding")) {
        firstFilePos = 2;
        encodingName = argv[1];
        try {
          // Side-effect: is encodingName valid?
          java.nio.charset.Charset.forName(encodingName);
        } catch (Exception e) {
          System.out.println("Invalid encoding '" + encodingName + "'");
          return;
        }
      }
      for (int i = firstFilePos; i < argv.length; i++) {
        lexer scanner = null;
        try {
          java.io.FileInputStream stream = new java.io.FileInputStream(argv[i]);
          java.io.Reader reader = new java.io.InputStreamReader(stream, encodingName);
          scanner = new lexer(reader);
          while ( !scanner.zzAtEOF ) scanner.debug_next_token();
        }
        catch (java.io.FileNotFoundException e) {
          System.out.println("File not found : \""+argv[i]+"\"");
        }
        catch (java.io.IOException e) {
          System.out.println("IO error scanning file \""+argv[i]+"\"");
          System.out.println(e);
        }
        catch (Exception e) {
          System.out.println("Unexpected exception:");
          e.printStackTrace();
        }
      }
    }
  }


}
