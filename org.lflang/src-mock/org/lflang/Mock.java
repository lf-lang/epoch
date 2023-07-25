package org.lflang;

/**
 * This entire package is for mocking Kotlin and other classes that cannot
 * be compiled by Eclipse but should not throw error in a debug run in Eclipse.
 * 
 * The actual product will have the real classes.
 */
public class Mock {
  public static final String KOTLIN_MSG = "Kotlin-based classes are not available in a live run of Epoch, only in the final product.";
  public static final String VERIFIER_MSG = "Verifier classes are not available in a live run of Epoch, only in the final product.";
}
