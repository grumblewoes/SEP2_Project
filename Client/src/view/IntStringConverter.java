package view;

import javafx.util.StringConverter;

/**
 * 
 * 
 * 
 * @author 
 * @version 
 */
public class IntStringConverter extends StringConverter<Number>
{
  /**
   * 
   * 
   * @param number 
   *        
   *
   * @return 
   *        
   */
  @Override public String toString(Number number)
  {
    return number == null || number.intValue() == 0 ? "" : number.toString();
  }

  /**
   * 
   * 
   * @param s 
   *        
   *
   * @return 
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