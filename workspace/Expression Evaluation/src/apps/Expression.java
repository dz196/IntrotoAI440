package apps;

import java.io.*;
import java.util.*;
import java.util.regex.*;

import structures.Stack;

public class Expression {

	/**
	 * Expression to be evaluated
	 */
	String expr;                
    
	/**
	 * Scalar symbols in the expression 
	 */
	ArrayList<ScalarSymbol> scalars;   
	
	/**
	 * Array symbols in the expression
	 */
	ArrayList<ArraySymbol> arrays;
    
    /**
     * String containing all delimiters (characters other than variables and constants), 
     * to be used with StringTokenizer
     */
    public static final String delims = " \t*+-/()[]";
    
    /**
     * Initializes this Expression object with an input expression. Sets all other
     * fields to null.
     * 
     * @param expr Expression
     */
    public Expression(String expr) {
        this.expr = expr;
    }

    /**
     * Populates the scalars and arrays lists with symbols for scalar and array
     * variables in the expression. For every variable, a SINGLE symbol is created and stored,
     * even if it appears more than once in the expression.
     * At this time, values for all variables are set to
     * zero - they will be loaded from a file in the loadSymbolValues method.
     */
    public void buildSymbols() {
    		/** COMPLETE THIS METHOD **/	
    	
    	arrays = new ArrayList<ArraySymbol>();
    	scalars = new ArrayList<ScalarSymbol>();
    	
    	StringTokenizer st = new StringTokenizer(expr, delims);
    	String x = "";
    	int index = 0;
    	int addIndex = 0;
    	while (st.hasMoreElements())
    	{
    		x = st.nextToken();
    		//System.out.println("NextToken: "+ x);
    		if (index>0)
    		{
    			index = expr.indexOf(x,index+addIndex);
    		}
    		else
    		{
    			index = expr.indexOf(x);
    		}
    		addIndex = x.length(); 
    		//System.out.println(index);
    		if (index+x.length()+1 < expr.length()+1)
    		{
    			if (!Character.isLetter(x.charAt(0)))
    			{
    				continue; 
    			}
    			else if (expr.substring(index+x.length(), index+x.length()+1).equals("["))
    			{
    				ArraySymbol var = new ArraySymbol(x);
    				if (!arrays.contains(var))
    				{
    					arrays.add(var);
    				}
    			}
    			else 
    			{
    				ScalarSymbol var = new ScalarSymbol(x);
        			if (!scalars.contains(var))
        			{
        			scalars.add(var);
        			}
    			}
    		}
    	}
    	ScalarSymbol last = new ScalarSymbol(x);
    	if (!Character.isLetter(x.charAt(0)))
		{
  
		}
    	else if (!scalars.contains(last))
    	{
    		scalars.add(last);
    	}
    	printScalars();
    	printArrays();
    }
    
    /**
     * Loads values for symbols in the expression
     * 
     * @param sc Scanner for values input
     * @throws IOException If there is a problem with the input 
     */
    public void loadSymbolValues(Scanner sc) 
    throws IOException {
        while (sc.hasNextLine()) {
            StringTokenizer st = new StringTokenizer(sc.nextLine().trim());
            int numTokens = st.countTokens();
            String sym = st.nextToken();
            ScalarSymbol ssymbol = new ScalarSymbol(sym);
            ArraySymbol asymbol = new ArraySymbol(sym);
            int ssi = scalars.indexOf(ssymbol);
            int asi = arrays.indexOf(asymbol);
            if (ssi == -1 && asi == -1) {
            	continue;
            }
            int num = Integer.parseInt(st.nextToken());
            if (numTokens == 2) { // scalar symbol
                scalars.get(ssi).value = num;
            } else { // array symbol
            	asymbol = arrays.get(asi);
            	asymbol.values = new int[num];
                // following are (index,val) pairs
                while (st.hasMoreTokens()) {
                    String tok = st.nextToken();
                    StringTokenizer stt = new StringTokenizer(tok," (,)");
                    int index = Integer.parseInt(stt.nextToken());
                    int val = Integer.parseInt(stt.nextToken());
                    asymbol.values[index] = val;              
                }
            }
        }
    }
    
    
    /**
     * Evaluates the expression, using RECURSION to evaluate subexpressions and to evaluate array 
     * subscript expressions.
     * 
     * @return Result of evaluation
     */
    public float evaluate() {
    		/** COMPLETE THIS METHOD **/
    		// following line just a placeholder for compilation
    	Stack <String> operands = new Stack <String>(); 
    	Stack <String> operators = new Stack <String>();
    	
    	Stack <String> TmpOperands = new Stack <String>();
    	Stack <String> TmpOperators = new Stack <String>();
    	
    	
    	expr = expr.replaceAll("\\s", "");
    	for (int i = 0 ; i < expr.length()-1; i++)
    	{
    		if (expr.charAt(i) == '-')
    		{
    			if (i-1 > -1)
    			{
    				if (Character.isLetter(expr.charAt(i-1)) || Character.isDigit(expr.charAt(i-1)))
    				{
    					expr = expr.replace("" + expr.charAt(i), "+-");
    				}
    			}
    		}
    	}
    	//expr = expr.replaceAll("-", "+-");
    	//System.out.println("Expression: " + expr);
    	String tmp = "";
    	float val = 0;
    	int begin = 0;
    	int end = 0;
    	boolean began = true;
    	int count = 0;
		String storage=expr;

// Evaluating Parenthesis & Array Variables
    	for (int i = 0; i< expr.length(); i++)
    	{
    		if (expr.charAt(i) == '(' || expr.charAt(i) == '[')
    		{
    			count++;
    			if (began)
    			{
    			begin = i+1;
    			began = false;
    			}
    		}
    	    if (expr.charAt(i) == ')' || expr.charAt(i) == ']')
    	    {
    	    	count --;
    	    	if (count == 0)
    	    	{
    	    	end = i;
    	    	break;
    	    	}
    	    }
    	}  
    	if (begin != 0)
    	{
    	expr=expr.substring(begin, end);
    	String ending = "";
    	if (end+1 <= storage.length())
    	{
    		ending = storage.substring(end+1);
    	}
    	System.out.println("Storage: " + storage);
    	float answer = evaluate();
    	String str = Float.toString(answer);
       	storage = storage.substring(0,begin-1) + str + ending;
    	expr = storage;
    	return evaluate();
    	}
// Storing into stacks
    	System.out.println("StoredExpressions: " + expr);
    	for (int i = expr.length()-1; i >= 0; i--) 
    	{
    		if (expr.charAt(i) != '+'&& expr.charAt(i) != '*' && expr.charAt(i) != '/'
    				&& expr.charAt(i) != '(' && expr.charAt(i) != ')')
    		{
    			tmp = expr.charAt(i) + tmp;
    		}
    		else 
    		{
    			if (tmp != "")
    			{
    	    		//System.out.println("TMP: "+ tmp);
    	        	for (int k = 0; k < tmp.length()-1; k++)
    	        	{
    	        		if (Character.isLetter(tmp.charAt(k)))
    	        		{
    	        			if (!Character.isLetter(tmp.charAt(k+1)))
    	        			{
    	        				String arrayVal = tmp.substring(k+1);
    	        				//System.out.println("ArrayVal: " + arrayVal);
    	        				int value = (int) Double.parseDouble(arrayVal);
    	        				//System.out.println("Value: " + value);
    	        				//System.out.println("Size: " + arrays.size());
    	        				for (int j = 0; j < arrays.size(); j++)
    	        				{
    	        					String arrayVar = tmp.substring(0, k+1);
    	        					//System.out.println("arrayVar: " +arrayVar);
    	        					if (tmp.charAt(0) == '-')
    	        					{
    	        						arrayVar = arrayVar.substring(1);
    	        						if (arrayVar.equals(arrays.get(j).name))
        	        					{
        	        						tmp = "" + arrays.get(j).values[value]*-1;
        	        						//System.out.println("NEWTMP: " + tmp);
        	        						break;
        	        					}
    	        					}
    	        					if	 (arrayVar.equals(arrays.get(j).name))
    	        					{
    	        						tmp = "" + arrays.get(j).values[value];
    	        						//System.out.println("NEWTMP: " + tmp);
    	        						break;
    	        					}
    	        				}
    	        				
    	        			}
    	        		}
    	        	}
    			operands.push(tmp);
    			}
    			if (expr.charAt(i) != '(' && expr.charAt(i) != ')')
    			{
    			operators.push("" + expr.charAt(i));
    			}
    			tmp = "";
    		}
    	}
    	if (tmp != "")
    	{
    	for (int i = 0; i < tmp.length()-1; i++)
    	{
    		if (Character.isLetter(tmp.charAt(i)))
    		{
    			if (!Character.isLetter(tmp.charAt(i+1)))
    			{
    				String arrayVal = tmp.substring(i+1);
    				int value = (int) Double.parseDouble(arrayVal);
 
    				for (int j = 0; j < arrays.size(); j++)
    				{
    					String arrayVar = tmp.substring(0, i+1);
    					if (arrayVar.charAt(0) == '-')
    					{
    						arrayVar = arrayVar.substring(1);
        					if (arrayVar.equals(arrays.get(j).name))
        					{
        						tmp = "" + arrays.get(j).values[value]*-1;
        						break;
        					}
    					}
    					if (arrayVar.equals(arrays.get(j).name))
    					{
    						tmp = "" + arrays.get(j).values[value];
    						break;
    					}
    				}
    				
    			}
    		}
    	}
    	//System.out.println("FINALTMP: " + tmp);
    	operands.push(tmp);
    	}
// FOR DIVISION
    	while (!operators.isEmpty())
    	{
    		String value1 = operands.pop();
    		Float v1 = 0f;
    		if (value1.charAt(0) == '-')
    		{
    			if (value1.charAt(1) == '-')
    			{
    				value1 = value1.substring(2);
    				v1 = Float.parseFloat(value1);
    			}
    			else if (!Character.isLetter(value1.charAt(1)))
        		{
        			v1 = Float.parseFloat(value1.substring(0));
        		}
        		else
        		{
        	    	for (int j = 0 ; j < scalars.size(); j++)
        			{
        				if (value1.substring(1).equals(scalars.get(j).name))
        				{
        					v1 = (float) scalars.get(j).value;
        					v1 *= -1; 
        				}
        			}
        		}
    		}
    		else if (!Character.isLetter(value1.charAt(0)))
    		{
    			v1 = Float.parseFloat(value1);
    		}
    		else
    		{
    	    	for (int j = 0 ; j < scalars.size(); j++)
    			{
    				if (value1.equals(scalars.get(j).name))
    				{
    					v1 = (float) scalars.get(j).value;
    				}
    			}
    		}
    		String sign = operators.pop();
    		if (sign.equals("/"))
    		{
    			String value2 = operands.pop();
        		Float v2 = 0f;
        		if (value2.charAt(0) == '-')
        		{
        			if (value2.charAt(1) == '-')
        			{
        				value2 = value2.substring(2);
        				v2 = Float.parseFloat(value1);
        			}
        			else if (!Character.isLetter(value2.charAt(1)))
            		{
            			v2 = Float.parseFloat(value2.substring(0));
            		}
            		else
            		{
            	    	for (int j = 0 ; j < scalars.size(); j++)
            			{
            				if (value2.substring(1).equals(scalars.get(j).name))
            				{
            					v2 = (float) scalars.get(j).value;
            					v2 *= -1; 
            				}
            			}
            		}
        		}
        		else if (!Character.isLetter(value2.charAt(0)))
        		{
        			v2 = Float.parseFloat(value2);
        		}
        		else
        		{
        	    	for (int j = 0 ; j < scalars.size(); j++)
        			{
        				if (value2.equals(scalars.get(j).name))
        				{
        					v2 = (float) scalars.get(j).value;
        				}
        			}
        	    }
        		//System.out.println("VALUE1DIV: " + v1);
        		//System.out.println("VALUE2DIV: " + v2);
        		val = v1/v2; 
        		//System.out.println("Valdiv: " + val);
        		tmp = "" + val;
        		operands.push(tmp);
    		}
    		else 
    		{
    			TmpOperators.push(sign);
    			TmpOperands.push(value1);
    		}
    	}
		while (!TmpOperators.isEmpty())
		{
			operators.push(TmpOperators.pop());
		}
		while (!TmpOperands.isEmpty())
		{
			operands.push(TmpOperands.pop());
		}
// FOR MULTIPLICATION
    	while (!operators.isEmpty())
    	{
    		String value1 = operands.pop();
    		Float v1 = 0f;
    		if (value1.charAt(0) == '-')
    		{
    			if (!Character.isLetter(value1.charAt(1)))
        		{
        			v1 = Float.parseFloat(value1.substring(0));
        		}
        		else
        		{
        	    	for (int j = 0 ; j < scalars.size(); j++)
        			{
        				if (value1.substring(1).equals(scalars.get(j).name))
        				{
        					v1 = (float) scalars.get(j).value;
        					v1 *= -1;
        				}
        			}
        		}
    		}
    		else if (!Character.isLetter(value1.charAt(0)))
    		{
    			v1 = Float.parseFloat(value1);
    		}
    		else
    		{
    	    	for (int j = 0 ; j < scalars.size(); j++)
    			{
    				if (value1.equals(scalars.get(j).name))
    				{
    					v1 = (float) scalars.get(j).value;
    				}
    			}
    		}
    		String sign = operators.pop();
    		if (sign.equals("*"))
    		{
    			String value2 = operands.pop();
        		Float v2 = 0f;
        		if (value2.charAt(0) == '-')
        		{
        			if (!Character.isLetter(value2.charAt(1)))
            		{
            			v2 = Float.parseFloat(value2.substring(0));
            		}
            		else
            		{
            	    	for (int j = 0 ; j < scalars.size(); j++)
            			{
            				if (value2.substring(1).equals(scalars.get(j).name))
            				{
            					v2 = (float) scalars.get(j).value;
            					v2 *= -1; 
            				}
            			}
            		}
        		}
        		else if (!Character.isLetter(value2.charAt(0)))
        		{
        			v2 = Float.parseFloat(value2);
        		}
        		else
        		{
        	    	for (int j = 0 ; j < scalars.size(); j++)
        			{
        				if (value2.equals(scalars.get(j).name))
        				{
        					v2 = (float) scalars.get(j).value;
        				}
        			}
        	    }
        		//System.out.println("VALUE2: " + v2);
        		//System.out.println("VALUE1: " + v1);
        		val = v2*v1; 
        		//System.out.println("VALUE: " + val);
        		tmp = "" + val;
        		operands.push(tmp);
    		}
    		else 
    		{
    			TmpOperators.push(sign);
    			TmpOperands.push(value1);
    		}
    	}
		while (!TmpOperators.isEmpty())
		{
			operators.push(TmpOperators.pop());
		}
		while (!TmpOperands.isEmpty())
		{
			operands.push(TmpOperands.pop());
		}

//FOR ADDITION
		while (!operators.isEmpty())
    	{
    		String value1 = operands.pop();
    		Float v1 = 0f;
    		if (value1.charAt(0) == '-')
    		{
    			if (!Character.isLetter(value1.charAt(1)))
        		{
        			v1 = Float.parseFloat(value1.substring(0));
        		}
        		else
        		{
        	    	for (int j = 0 ; j < scalars.size(); j++)
        			{
        				if (value1.substring(1).equals(scalars.get(j).name))
        				{
        					v1 = (float) scalars.get(j).value;
        					v1 *= -1; 
        				}
        			}
        		}
    		}
    		else if (!Character.isLetter(value1.charAt(0)))
    		{
    			v1 = Float.parseFloat(value1);
    		}
    		else
    		{
    	    	for (int j = 0 ; j < scalars.size(); j++)
    			{
    				if (value1.equals(scalars.get(j).name))
    				{
    					v1 = (float) scalars.get(j).value;
    				}
    			}
    		}
    		String sign = operators.pop();
    		if (sign.equals("+"))
    		{
    			if (operands.isEmpty())
    			{
    				return v1;
    			}
    			String value2 = operands.pop();
        		Float v2 = 0f;
        		if (value2.charAt(0) == '-')
        		{
        			if (value2.charAt(1) == '-')
        			{
        				value2 = value2.substring(2);
        				v2 = Float.parseFloat(value1);
        			}
        			else if (!Character.isLetter(value2.charAt(1)))
            		{
            			v2 = Float.parseFloat(value2.substring(0));
            		}
            		else
            		{
            	    	for (int j = 0 ; j < scalars.size(); j++)
            			{
            				if (value2.substring(1).equals(scalars.get(j).name))
            				{
            					v2 = (float) scalars.get(j).value;
            					v2 *= -1; 
            				}
            			}
            		}
        		}
        		else if (!Character.isLetter(value2.charAt(0)))
        		{
        			v2 = Float.parseFloat(value2);
        		}
        		else
        		{
        	    	for (int j = 0 ; j < scalars.size(); j++)
        			{
        				if (value2.equals(scalars.get(j).name))
        				{
        					v2 = (float) scalars.get(j).value;
        				}
        			}
        	    }
        		val = v2+v1; 
        		tmp = "" + val;
        		operands.push(tmp);
    		}
    		else 
    		{
    			TmpOperators.push(sign);
    			TmpOperands.push(value1);
    		}
    	}
		while (!TmpOperators.isEmpty())
		{
			operators.push(TmpOperators.pop());
		}
		while (!TmpOperands.isEmpty())
		{
			operands.push(TmpOperands.pop());
		}
    	String finalvalue = operands.pop();
    	Float result = 0f;
    	for (int i = 0; i < finalvalue.length(); i++)
    	{
    		if (Character.isLetter(finalvalue.charAt(i)))
    		{
    	    	for (int j = 0 ; j < scalars.size(); j++)
    			{
    	    		if (finalvalue.charAt(0) == '-')
    				{
    	    			if (finalvalue.substring(1).equals(scalars.get(j).name))
    	    			{
    	    				result = (float) scalars.get(j).value;
    	    				result *= -1;
    	    			}
    				}
    	    		else
    	    		{
    	    			if (finalvalue.equals(scalars.get(j).name))
    	    			{
    	    				result = (float) scalars.get(j).value;
    	    			}
    	    		}
    			}
    		}
    		else
    		{
    			result = Float.parseFloat(finalvalue);
    		}
    	}
    	return result;
    }
    /**
     * Utility method, prints the symbols in the scalars list
     */
    public void printScalars() {
        for (ScalarSymbol ss: scalars) {
            System.out.println(ss);
        }
    }
    
    /**
     * Utility method, prints the symbols in the arrays list
     */
    public void printArrays() {
    		for (ArraySymbol as: arrays) {
    			System.out.println(as);
			}
    }
}
			