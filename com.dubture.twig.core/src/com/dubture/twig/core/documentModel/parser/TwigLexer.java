/* The following code was generated by JFlex 1.4.1 on 7/17/11 2:17 PM */

/******************************************************************************** 
 * Copyright (c) 2006 Zend Corporation and IBM Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Zend and IBM - Initial implementation
 ********************************************************************************/

package com.dubture.twig.core.documentModel.parser;

import org.eclipse.php.internal.core.util.collections.IntHashtable;
import org.eclipse.wst.sse.core.utils.StringUtils;

import com.dubture.twig.core.util.Debug;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.1
 * on 7/17/11 2:17 PM from the specification file
 * <tt>highlighting_scanner.jflex</tt>
 */
public class TwigLexer extends com.dubture.twig.core.documentModel.parser.AbstractTwigLexer {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int ST_TWIG_DOUBLE_QUOTES = 4;
  public static final int ST_TWIG_IN_STATEMENT = 2;
  public static final int ST_TWIG_SINGLE_QUOTES = 5;
  public static final int YYINITIAL = 0;
  public static final int ST_TWIG_COMMENT = 7;
  public static final int ST_TWIG_HIGHLIGHTING_ERROR = 6;
  public static final int ST_TWIG_IN_STATEMENT_BODY = 3;
  public static final int ST_TWIG_IN_PRINT = 1;

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\14\1\25\2\0\1\14\22\0\1\14\1\15\1\26\1\17"+
    "\1\15\1\21\1\15\1\23\5\15\1\16\2\15\12\2\1\15\1\0"+
    "\5\15\1\7\2\1\1\11\1\12\1\13\2\1\1\6\4\1\1\3"+
    "\1\4\3\1\1\10\1\5\6\1\1\15\1\24\2\15\1\1\1\0"+
    "\1\7\2\1\1\11\1\12\1\13\2\1\1\6\4\1\1\3\1\4"+
    "\3\1\1\10\1\5\6\1\1\22\1\15\1\20\1\15\201\1\uff00\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\10\0\1\1\1\2\1\3\4\2\1\4\1\5\1\6"+
    "\1\7\1\10\1\11\5\12\1\13\1\1\1\6\1\1"+
    "\1\14\1\15\1\16\1\17\1\20\1\21\1\22\1\1"+
    "\1\2\1\23\2\2\1\24\1\0\1\25\3\0\1\12"+
    "\1\23\2\12\1\26\1\27\1\30\1\2\1\12\1\2"+
    "\1\12\1\2\1\12";

  private static int [] zzUnpackAction() {
    int [] result = new int[61];
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
    "\0\0\0\27\0\56\0\105\0\134\0\163\0\212\0\241"+
    "\0\0\0\270\0\317\0\346\0\375\0\u0114\0\u012b\0\u0142"+
    "\0\0\0\u0159\0\0\0\u0170\0\u0187\0\u019e\0\u01b5\0\u01cc"+
    "\0\u01e3\0\u01fa\0\0\0\u0211\0\0\0\u0228\0\u023f\0\0"+
    "\0\u0256\0\0\0\0\0\u026d\0\u0284\0\u029b\0\u02b2\0\270"+
    "\0\u02c9\0\u02e0\0\u02f7\0\u0170\0\0\0\u030e\0\u0187\0\u0325"+
    "\0\u033c\0\u019e\0\u0353\0\u036a\0\u0381\0\u0398\0\u03af\0\u03c6"+
    "\0\u03dd\0\u03f4\0\u040b\0\u0422\0\u0439";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[61];
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
    "\27\0\1\11\1\12\1\13\1\14\2\12\1\15\1\16"+
    "\1\12\1\17\2\12\1\20\2\21\1\11\1\22\1\11"+
    "\1\23\1\24\1\11\1\20\1\25\1\11\1\26\1\11"+
    "\1\27\2\26\1\30\1\31\1\26\1\32\2\26\2\11"+
    "\1\33\2\11\1\34\3\11\1\0\2\11\1\12\1\13"+
    "\1\14\2\12\1\15\1\16\1\12\1\17\2\12\1\20"+
    "\2\21\1\11\1\35\1\36\1\23\1\24\1\11\1\20"+
    "\1\25\26\37\1\40\23\41\1\42\3\41\14\43\1\44"+
    "\10\43\1\44\1\43\17\45\1\46\7\45\1\0\13\12"+
    "\15\0\1\13\25\0\3\12\1\47\7\12\14\0\2\12"+
    "\1\50\4\12\1\50\3\12\14\0\2\12\1\51\4\12"+
    "\1\50\3\12\14\0\11\12\1\52\1\12\27\0\1\20"+
    "\10\0\1\20\21\0\1\53\6\0\23\54\1\55\1\56"+
    "\2\54\24\57\1\60\1\57\1\55\1\0\13\26\14\0"+
    "\3\26\1\61\7\26\14\0\2\26\1\62\4\26\1\62"+
    "\3\26\14\0\2\26\1\63\4\26\1\62\3\26\14\0"+
    "\11\26\1\64\1\26\33\0\1\65\26\0\1\66\6\0"+
    "\26\37\1\0\23\41\1\0\3\41\14\0\1\44\10\0"+
    "\1\44\1\0\17\45\1\0\7\45\20\0\1\67\7\0"+
    "\4\12\1\50\6\12\14\0\10\12\1\50\2\12\14\0"+
    "\12\12\1\70\27\0\1\53\10\0\1\53\1\0\25\54"+
    "\1\0\1\54\25\57\1\0\1\57\1\0\4\26\1\62"+
    "\6\26\14\0\10\26\1\62\2\26\14\0\12\26\1\71"+
    "\27\0\1\65\10\0\1\65\15\0\1\66\10\0\1\66"+
    "\15\0\1\67\10\0\1\67\2\0\5\12\1\72\5\12"+
    "\14\0\5\26\1\73\5\26\14\0\2\12\1\74\10\12"+
    "\14\0\2\26\1\75\10\26\14\0\11\12\1\51\1\12"+
    "\14\0\11\26\1\63\1\26\13\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[1104];
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


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\10\7\0\1\11\7\1\1\11\1\1\1\11\7\1"+
    "\1\11\1\1\1\11\2\1\1\11\1\1\2\11\10\1"+
    "\1\0\1\11\3\0\15\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[61];
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

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the textposition at the last state to be included in yytext */
  private int zzPushbackPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /* user code: */
    public TwigLexer(int state){
        initialize(state);
    }
    public void reset(char array[], int offset, int length) {
        this.zzBuffer = array;
        this.zzCurrentPos = offset;
        this.zzMarkedPos = offset;
        this.zzPushbackPos = offset;
        this.yychar = offset;
        this.zzEndRead = offset + length;
        this.zzStartRead = offset;
        this.zzAtEOF = zzCurrentPos >= zzEndRead;
        this.firstPos = offset;
    }

    public void reset(java.io.Reader  reader, char[] buffer, int[] parameters){
    	this.zzReader = reader;
    	this.zzBuffer = buffer;
    	this.zzMarkedPos = parameters[0];
    	this.zzPushbackPos = parameters[1];
    	this.zzCurrentPos = parameters[2];
    	this.zzStartRead = parameters[3];
    	this.zzEndRead = parameters[4];
    	this.yyline = parameters[5];  
    	initialize(parameters[6]);
    }

    
    public int[] getParamenters(){
    	return new int[]{zzMarkedPos, zzPushbackPos, zzCurrentPos, zzStartRead, zzEndRead, yyline, zzLexicalState};
    }

    protected int getZZLexicalState() {
        return zzLexicalState;
    }

    protected int getZZMarkedPos() {
        return zzMarkedPos;
    }

    protected int getZZEndRead() {
        return zzEndRead;
    }

    public char[] getZZBuffer() {
        return zzBuffer;
    }
    
    protected int getZZStartRead() {
    	return this.zzStartRead;
    }

    protected int getZZPushBackPosition() {
    	return this.zzPushbackPos;
    }

	protected void pushBack(int i) {
		yypushback(i);
	}

	// A pool of states. To avoid creation of a new state on each createMemento.
	private static final IntHashtable lexerStates = new IntHashtable(100);
	
	protected IntHashtable getLexerStates() {
		return lexerStates;
	}
	
	private final void dump(String s) {
		if (Debug.debugTokenizer) {
			System.out.println("lexer: " + s + " (" + yychar + "-" + //$NON-NLS-2$//$NON-NLS-1$
				(yylength() + yychar) + "):\'" +//$NON-NLS-1$
					StringUtils.escape(yytext()) + "\'");//$NON-NLS-1$
		}
	}
	
	

 // End user code


  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public TwigLexer(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  public TwigLexer(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 120) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzPushbackPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead < 0) {
      return true;
    }
    else {
      zzEndRead+= numRead;
      return false;
    }
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = zzPushbackPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public String yylex() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = zzLexicalState;


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
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
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 24: 
          { if(Debug.debugTokenizer)
		dump("TWIG COMMENT CLOSE");
		

	return TWIG_COMMENT_CLOSE;
          }
        case 25: break;
        case 11: 
          { if(Debug.debugTokenizer)
		dump("TWIG MINUS");
    

	return TWIG_MINUS;
          }
        case 26: break;
        case 13: 
          { if(Debug.debugTokenizer)
		dump("TWIG DOUBLE QUOTES END");

	popState();
    return TWIG_DOUBLE_QUOTES_END;
          }
        case 27: break;
        case 23: 
          { if(Debug.debugTokenizer)
		dump("TWIG_STMT_CLOSE");

	popState();
	return TWIG_STMT_CLOSE;
          }
        case 28: break;
        case 17: 
          { popState();return TWIG_WHITESPACE;
          }
        case 29: break;
        case 1: 
          { if(Debug.debugTokenizer)
		dump("TWIG HIGHLIGHT ERROR");

	reportError();
    yypushback(1);
    pushState(ST_TWIG_HIGHLIGHTING_ERROR);
          }
        case 30: break;
        case 19: 
          { if(Debug.debugTokenizer)
		dump("TWIG KEYWORD");

	return TWIG_KEYWORD;
          }
        case 31: break;
        case 4: 
          { if(Debug.debugTokenizer)
		dump("TWIG WHITESPACE");

	return TWIG_WHITESPACE;
          }
        case 32: break;
        case 2: 
          { if(Debug.debugTokenizer)
		dump("TWIG LABEL");

	return TWIG_LABEL;
          }
        case 33: break;
        case 9: 
          { if(Debug.debugTokenizer)
		dump("TWIG DOUBLE QUOTES START");

	pushState(ST_TWIG_DOUBLE_QUOTES);
    return TWIG_DOUBLE_QUOTES_START;
          }
        case 34: break;
        case 22: 
          { if(Debug.debugTokenizer)
		dump("TWIG_STMT_CLOSE");

	return TWIG_STMT_CLOSE;
          }
        case 35: break;
        case 12: 
          { if(Debug.debugTokenizer)
		dump("TWIG DOUBLE QUOTES CONTENT");

    return TWIG_DOUBLE_QUOTES_CONTENT;
          }
        case 36: break;
        case 15: 
          { if(Debug.debugTokenizer)
		dump("TWIG DOUBLE QUOTES END");

	popState();
    return TWIG_SINGLE_QUOTES_END;
          }
        case 37: break;
        case 7: 
          { if(Debug.debugTokenizer)
		dump("TWIG JSON START");


    return TWIG_JSON_START;
          }
        case 38: break;
        case 5: 
          { if(Debug.debugTokenizer)
		dump("TWIG DELIMITER TOKEN");
	return TWIG_DELIMITER;
          }
        case 39: break;
        case 21: 
          { if(Debug.debugTokenizer)
		dump("TWIG_CONSTANT_ENCAPSED_STRING");

    return TWIG_CONSTANT_ENCAPSED_STRING;
          }
        case 40: break;
        case 20: 
          { if(Debug.debugTokenizer)
		dump("TWIG_CLOSETAG");

	return TWIG_CLOSETAG;
          }
        case 41: break;
        case 18: 
          { if(Debug.debugTokenizer)
		dump("TWIG COMMENT");

	return TWIG_COMMENT;
          }
        case 42: break;
        case 3: 
          { if(Debug.debugTokenizer)
		dump("TWIG NUMBER");

    return TWIG_NUMBER;
          }
        case 43: break;
        case 10: 
          { if(Debug.debugTokenizer)
		dump("TWIG KEYWORD");

	pushState(ST_TWIG_IN_STATEMENT_BODY);
	return TWIG_KEYWORD;
          }
        case 44: break;
        case 14: 
          { if(Debug.debugTokenizer)
		dump("TWIG SINGLE QUOTES CONTENT");

    return TWIG_SINGLE_QUOTES_CONTENT;
          }
        case 45: break;
        case 8: 
          { if(Debug.debugTokenizer)
		dump("TWIG DOUBLE QUOTES START");

	pushState(ST_TWIG_SINGLE_QUOTES);
    return TWIG_SINGLE_QUOTES_START;
          }
        case 46: break;
        case 6: 
          { if(Debug.debugTokenizer)
		dump("TWIG JSON END");

	//yybegin(ST_TWIG_JSON)

    return TWIG_JSON_END;
          }
        case 47: break;
        case 16: 
          { return UNKNOWN_TOKEN;
          }
        case 48: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            return null;
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}