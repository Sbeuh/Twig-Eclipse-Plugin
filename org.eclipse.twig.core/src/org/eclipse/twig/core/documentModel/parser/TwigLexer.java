/* The following code was generated by JFlex 1.4.1 on 6/17/11 10:51 PM */

/********************************************************************************   
 * Copyright (c) 2006 Zend Corporation and IBM Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Zend and IBM - Initial implementation
 *******************************************************************************/

package org.eclipse.twig.core.documentModel.parser;

import org.eclipse.php.internal.core.util.collections.IntHashtable;
import org.eclipse.wst.sse.core.utils.StringUtils;
import org.eclipse.twig.core.util.Debug;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.1
 * on 6/17/11 10:51 PM from the specification file
 * <tt>highlighting_scanner.jflex</tt>
 */
public class TwigLexer extends org.eclipse.twig.core.documentModel.parser.AbstractTwigLexer {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int ST_TWIG_DOUBLE_QUOTES = 2;
  public static final int ST_TWIG_CONTENT = 1;
  public static final int YYINITIAL = 0;
  public static final int ST_TWIG_DOUBLE_QUOTES_SPECIAL = 3;

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\30\1\37\2\0\1\30\22\0\1\30\1\31\1\40\1\33"+
    "\1\32\1\0\1\31\1\35\2\44\3\31\2\44\1\31\12\3\1\31"+
    "\1\0\2\31\1\44\2\31\1\22\1\12\1\15\1\10\1\4\1\17"+
    "\1\2\1\25\1\21\1\2\1\16\1\13\1\26\1\7\1\14\1\27"+
    "\1\2\1\20\1\11\1\6\1\23\1\2\1\24\1\5\2\2\1\42"+
    "\1\36\1\43\1\31\1\2\1\41\1\22\1\12\1\15\1\10\1\4"+
    "\1\17\1\2\1\25\1\21\1\2\1\16\1\13\1\26\1\7\1\14"+
    "\1\27\1\2\1\20\1\11\1\6\1\23\1\2\1\24\1\5\2\2"+
    "\1\1\1\31\1\34\1\31\201\2\uff00\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\4\0\1\1\1\2\1\3\13\2\1\4\2\5\3\0"+
    "\1\6\1\7\1\0\1\10\1\11\1\12\1\13\1\0"+
    "\1\14\6\2\2\15\5\2\1\16\1\17\1\20\1\21"+
    "\1\0\1\22\1\23\11\2\1\0\11\2\2\0\3\2"+
    "\1\24\1\25\4\2";

  private static int [] zzUnpackAction() {
    int [] result = new int[83];
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
    "\0\0\0\45\0\112\0\157\0\0\0\224\0\271\0\336"+
    "\0\u0103\0\u0128\0\u014d\0\u0172\0\u0197\0\u01bc\0\u01e1\0\u0206"+
    "\0\u022b\0\u0250\0\u0275\0\0\0\u029a\0\u02bf\0\u02e4\0\u0309"+
    "\0\0\0\u032e\0\u0353\0\0\0\0\0\u0378\0\u039d\0\u03c2"+
    "\0\0\0\u03e7\0\u040c\0\u0431\0\u0456\0\u047b\0\u04a0\0\u04c5"+
    "\0\224\0\u04ea\0\u050f\0\u0534\0\u0559\0\u057e\0\u05a3\0\0"+
    "\0\u05c8\0\0\0\u05ed\0\u0612\0\u0637\0\u065c\0\u0681\0\u06a6"+
    "\0\u06cb\0\u06f0\0\u0715\0\u073a\0\u075f\0\u0784\0\u07a9\0\u07ce"+
    "\0\u07f3\0\u0818\0\u083d\0\u0862\0\u0887\0\u08ac\0\u08d1\0\u08f6"+
    "\0\u091b\0\u0940\0\u0965\0\u098a\0\u09af\0\0\0\0\0\u09d4"+
    "\0\u09f9\0\u0a1e\0\u0a43";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[83];
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
    "\46\0\1\5\1\6\1\7\1\10\2\6\1\11\1\6"+
    "\1\12\1\13\4\6\1\14\1\15\1\16\1\17\1\20"+
    "\1\21\1\6\1\22\1\6\1\23\1\24\1\25\1\26"+
    "\1\27\1\30\1\0\1\23\1\31\1\0\3\24\32\32"+
    "\1\33\5\32\1\34\1\35\3\32\2\0\1\36\1\37"+
    "\24\36\2\0\1\40\6\0\1\41\2\0\1\24\2\0"+
    "\26\6\20\0\1\7\43\0\3\6\1\42\1\6\1\43"+
    "\20\6\17\0\12\6\1\44\13\6\17\0\2\6\1\44"+
    "\23\6\17\0\11\6\1\45\14\6\17\0\12\6\1\46"+
    "\13\6\17\0\2\6\1\47\23\6\17\0\5\6\1\50"+
    "\7\6\1\51\6\6\1\52\1\6\17\0\7\6\1\51"+
    "\11\6\1\53\4\6\17\0\7\6\1\54\16\6\17\0"+
    "\17\6\1\55\6\6\17\0\20\6\1\56\5\6\45\0"+
    "\1\23\6\0\1\23\7\0\1\57\1\0\24\57\51\0"+
    "\1\60\44\0\1\61\10\0\35\30\1\62\1\63\6\30"+
    "\32\32\1\0\5\32\2\0\3\32\2\0\1\64\1\0"+
    "\24\64\17\0\26\36\20\0\1\37\43\0\1\65\1\0"+
    "\24\65\17\0\4\6\1\66\21\6\17\0\6\6\1\67"+
    "\17\6\17\0\4\6\1\51\21\6\17\0\12\6\1\70"+
    "\13\6\17\0\16\6\1\51\7\6\17\0\5\6\1\71"+
    "\20\6\17\0\13\6\1\72\12\6\17\0\25\6\1\73"+
    "\17\0\4\6\1\74\21\6\17\0\2\6\1\51\23\6"+
    "\17\0\4\6\1\75\21\6\17\0\13\6\1\76\12\6"+
    "\17\0\26\57\45\0\1\61\6\0\1\61\5\0\37\30"+
    "\1\0\5\30\2\0\26\64\12\0\1\77\4\0\26\65"+
    "\17\0\2\6\1\100\23\6\17\0\10\6\1\13\4\6"+
    "\1\14\1\6\1\101\1\102\3\6\1\22\1\6\17\0"+
    "\13\6\1\103\12\6\17\0\6\6\1\104\17\6\17\0"+
    "\11\6\1\105\14\6\17\0\12\6\1\106\13\6\17\0"+
    "\12\6\1\107\13\6\17\0\23\6\1\51\2\6\17\0"+
    "\16\6\1\110\7\6\17\0\1\111\1\112\24\111\17\0"+
    "\5\6\1\113\20\6\17\0\15\6\1\51\10\6\17\0"+
    "\21\6\1\53\4\6\17\0\14\6\1\51\11\6\17\0"+
    "\2\6\1\46\23\6\17\0\21\6\1\114\4\6\17\0"+
    "\16\6\1\44\7\6\17\0\2\6\1\115\23\6\17\0"+
    "\12\6\1\51\13\6\17\0\26\111\13\0\1\116\4\0"+
    "\1\112\37\0\1\117\3\0\6\6\1\120\17\6\17\0"+
    "\6\6\1\54\17\6\17\0\7\6\1\121\16\6\17\0"+
    "\7\6\1\51\16\6\17\0\13\6\1\122\12\6\17\0"+
    "\20\6\1\123\5\6\17\0\25\6\1\54\15\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[2664];
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
    "\1\10\3\0\1\11\16\1\1\11\1\1\3\0\1\11"+
    "\1\1\1\0\2\11\2\1\1\0\1\11\16\1\1\11"+
    "\1\1\1\11\1\0\13\1\1\0\11\1\2\0\3\1"+
    "\2\11\4\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[83];
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
			System.out.println(s + " (" + yychar + "-" + //$NON-NLS-2$//$NON-NLS-1$
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
    while (i < 172) {
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
        case 5: 
          { return TWIG_DELIMITER;
          }
        case 22: break;
        case 21: 
          { if (Debug.debugTokenizer)
		System.out.println("variable3");

    return TWIG_VARIABLE;
          }
        case 23: break;
        case 19: 
          { if (Debug.debugTokenizer)
		System.out.println("variable5");

    return TWIG_VARIABLE;
          }
        case 24: break;
        case 17: 
          { if(Debug.debugTokenizer)
		dump("TWIG CONSTANT");

    return TWIG_CONSTANT_ENCAPSED_STRING;
          }
        case 25: break;
        case 16: 
          { return TWIG_CLOSE;
          }
        case 26: break;
        case 13: 
          { if(Debug.debugTokenizer)
		dump("TWIG KEYWORD");

	return TWIG_KEYWORD;
          }
        case 27: break;
        case 4: 
          { if(Debug.debugTokenizer)
		dump("TWIG WHITESPACE");

	return TWIG_WHITESPACE;
          }
        case 28: break;
        case 10: 
          { return TWIG_LABEL;
          }
        case 29: break;
        case 2: 
          { if(Debug.debugTokenizer)
		dump("TWIG LABEL");

	return TWIG_LABEL;
          }
        case 30: break;
        case 8: 
          { if(Debug.debugTokenizer)
		dump("TWIG DOUBLE QUOTES END");

	yybegin(ST_TWIG_CONTENT);
    return TWIG_DOUBLE_QUOTES_END;
          }
        case 31: break;
        case 7: 
          { if(Debug.debugTokenizer)
		dump("TWIG DOUBLE QUOTES CONTENT");

    return TWIG_DOUBLE_QUOTES_CONTENT;
          }
        case 32: break;
        case 18: 
          { if (Debug.debugTokenizer)
		dump("TWIG DOLLAR VAR");
		
    return TWIG_VARIABLE;
          }
        case 33: break;
        case 1: 
          { if(Debug.debugTokenizer)
		dump("TWIG JSON START");


    return TWIG_JSON_START;
          }
        case 34: break;
        case 12: 
          { yybegin(ST_TWIG_DOUBLE_QUOTES);
    return TWIG_BACKTICK_END;
          }
        case 35: break;
        case 14: 
          { if (Debug.debugTokenizer)
		dump("TWIG VARIABLE");

	return TWIG_VARIABLE;
          }
        case 36: break;
        case 9: 
          { yybegin(ST_TWIG_DOUBLE_QUOTES_SPECIAL);
    return TWIG_BACKTICK_START;
          }
        case 37: break;
        case 20: 
          { if (Debug.debugTokenizer)
		System.out.println("variable4");

    return TWIG_VARIABLE;
          }
        case 38: break;
        case 3: 
          { if(Debug.debugTokenizer)
		dump("TWIG NUMBER");

    return TWIG_NUMBER;
          }
        case 39: break;
        case 11: 
          { return TWIG_NUMBER;
          }
        case 40: break;
        case 15: 
          { if(Debug.debugTokenizer)
		dump("TWIG COMMENT CLOSE");
		
	yybegin(YYINITIAL);
	return TWIG_COMMENT_CLOSE;
          }
        case 41: break;
        case 6: 
          { if(Debug.debugTokenizer)
		dump("TWIG DOUBLE QUOTES START");

	yybegin(ST_TWIG_DOUBLE_QUOTES);
    return TWIG_DOUBLE_QUOTES_START;
          }
        case 42: break;
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
