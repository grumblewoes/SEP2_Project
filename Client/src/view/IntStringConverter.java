package view;

import javafx.util.StringConverter;

/**
 * Utility converter that converts string to integer and back.
 * 
 * 
 * @author Damian Trafiałek
 * @version 1.0
 */
public class IntStringConverter extends StringConverter<Number>
{
  /**
   * Method that converts integer to String
   * 
   * @param number - integer
   *        
   *
   * @return string value
   *        
   */
  @Override public String toString(Number number)
  {
    return number == null || number.intValue() == 0 ? "" : number.toString();
  }

  /**
   * Method that converts string to integer
   * 
   * @param s - string
   *        
   *
   * @return integer value
   *        
   */
  @Override public Number fromString(String s)
  {
    try
    {
      return Integer.parseInt(s);
    }
    catch (Exception e)
    {
      return 0;
    }
  }
}