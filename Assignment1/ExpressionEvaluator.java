package calculator;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import data_structures.Queue;
import data_structures.Stack;


/**
 * This is an expression evaluator. 
 * Only works with five operations: _,+,*,/,^
 * This class is meant to be used with Calculator.java.
 * Uses the concept of infix and postfix.
 * @see Calculator.java.
 * 
 * @author Tom Nguyen <tommie_89@yahoo.com>.
 * 
 * @version: Neon Release (4.6.0)- Eclipse. jre 1.8.0_92.
 */

public class ExpressionEvaluator {

	/**
	 * Calls on methods infixToPostfix to covert iOText into a postfix queue.
	 * Checks for error from the user.
	 * Takes the postfix and evaluate it.
	 * 
	 * @param iOText the input from the calculator.
	 * @return the answer for the calculator.
	 */
	public String processInput(String iOText) {
		Queue<String> test1 = new Queue<>();
		Queue<String> error = new Queue<>();
		if(checkForError(iOText))
			return "ERROR";
		String answer;
		test1 = infixToPostfix(iOText);
		error = infixToPostfix(iOText);
		if(anyError(error))
			return "ERROR";
		answer = evaluating(test1);
		if(anyError(answer))
			return "ERROR";
		return answer;
	}

	/**
	 * Test in a string if the five operators repeated. *,/,+,-,^.
	 * 
	 * @param input string from calculator.
	 * @return true if an operator is repeated in the string, otherwise false.
	 */
	public boolean checkForError(String input){
		StringTokenizer token = new StringTokenizer(input);
		String s;
		while(token.hasMoreTokens()){
			s = token.nextToken();
			if(isOperator(s)){
				try{
					s=token.nextToken();
					if(isOperator(s))
						return true;
				}catch(NoSuchElementException E){return true;}
			}
		}
		return false;
	}

	/**
	 * Looks for the string Error
	 * 
	 * @param error the result from evaluator.
	 * @return true if there are any errors, otherwise false.
	 */
	public boolean anyError(String error){
		try{
			if(error.equals("ERROR"))
				return true;
		}catch(NullPointerException E){return true;	}
		return false;
	}

	/**
	 * Looks for the String Error or any null pointer exception.
	 *  
	 * @param error is the postfix queue.
	 * @return true if there are any errors, otherwise false.
	 */
	public boolean anyError(Queue<String> error){
		int size = error.size();
		for(int i=0;i<size;i++)
			try{
				if(error.dequeue().equals("ERROR"))
					return true;
			}catch(NullPointerException E){return true;}
		return false;
	}

	/**
	 * Takes the input string of infix and change it to postfix.
	 * 
	 * @param input string coming from the calculator.
	 * @return postfix as queue<String>.
	 */
	public Queue<String> infixToPostfix(String input){
		Stack<String> stack = new Stack<>();
		Queue<String> queue = new Queue<>();
		StringTokenizer token = new StringTokenizer(input);
		String instance;
		while(token.hasMoreTokens()){
			instance = token.nextToken();
			if(instance.equals("("))
				stack.push(instance);
			if(instance.equals(")"))
				try{
					do{
						queue.enqueue(stack.pop());
					}while (!stack.peek().equals("("));
					stack.pop();
				}catch(NullPointerException E){
					queue.enqueue("Error");
					return queue;
				}
			if(isOperator(instance)){
				while(!stack.isEmpty() && !stack.peek().equals("(") && precedence(stack.peek())>= precedence(instance))
					queue.enqueue(stack.pop());
				stack.push(instance);
			}
			if(isNumeric(instance))
				queue.enqueue(instance);
		}
		while(!stack.isEmpty())
			queue.enqueue(stack.pop());
		return queue;
	}

	/**
	 * Test whether the string is a operator -,+,/,*,^
	 * 
	 * @param symbol the string that needs to be checked.
	 * @return true if the symbol is one of the five operators, otherwise false.
	 */
	public boolean isOperator(String symbol){
		if(symbol.equals("^"))return true;
		if(symbol.equals("*"))return true;
		if(symbol.equals("/"))return true;
		if(symbol.equals("+"))return true;
		if(symbol.equals("-"))return true;
		return false;		
	}

	/**
	 * Takes two number from the stack and use the correct operator
	 * to calculate the result.
	 * 
	 * @param str the symbol that is the operator.
	 * @param stack_1 the number pop from stack.
	 * @param stack_2 the number pop from stack.
	 * @return a double of the answer.
	 */
	public double result(String str,double stack_1, double stack_2){
		if(str.equals("^"))return Math.pow(stack_1, stack_2);
		if(str.equals("*"))return stack_1 * stack_2;
		if(str.equals("/"))return stack_1 / stack_2;
		if(str.equals("+"))return stack_1 + stack_2;
		if(str.equals("-"))return stack_1 - stack_2;
		return 0;
	}

	/**
	 * Test whether the string can be converted into a string.
	 * 
	 * @param str the string that is to be tested.
	 * @return true if it can be converted into a double, otherwise false;
	 */
	public boolean isNumeric(String str)  
	{  
		@SuppressWarnings("unused")
		double d;
		try  {d = Double.parseDouble(str);}  
		catch(NumberFormatException nfe){return false;}  
		return true;  
	}

	/**
	 * Return a value for the precedence of the token.
	 * 
	 * @param priority the operator that is to be tested.
	 * @return the precedence of the token.
	 */
	public int precedence(String priority){
		if(priority.equals("^"))return 3;
		if(priority.equals("*") || priority.equals("/"))return 2;
		if(priority.equals("+") || priority.equals("-"))return 1;
		return 0; 
	}

	/**
	 * Returns the result of the postfix.
	 * Takes the first two number off the queue.
	 * Takes the function of the operator and calculates it.
	 * 
	 * @param que the postfix queue.
	 * @return a double that is a string of the answer.
	 */
	public String evaluating(Queue<String> que){
		Stack<String> sta = new Stack<>();
		double fromStack_1,fromStack_2;
		boolean good_1 = false;
		while (!que.isEmpty()){
			if(isNumeric(que.peek())){
				sta.push(que.dequeue());
				good_1=true;
			}
			else if (isOperator(que.peek())){
				fromStack_2 = Double.parseDouble(sta.pop());
				fromStack_1 = Double.parseDouble(sta.pop());
				sta.push(Double.toString(result(que.dequeue(),fromStack_1,fromStack_2)));
				good_1=true;
			}
			else if (!good_1)
				return "ERROR";
			good_1=false;
		}
		return sta.pop();
	}
}
